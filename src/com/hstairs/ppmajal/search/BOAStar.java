/* Pseudocodice BOAStar

Input: A search problem (S, E, c, s_start, s_goal) and a consistent heuristic function h
Output: A cost-unique Pareto-optimal solution set

for each s ∈ S do
	sols(s) ← ∅
	g2_min(s) ← ∞
x ← new node with s(x) = sstart
g(x) ← (0, 0)
parent(x) ← null
f(x) ← (h1(s_start), h2(s_start))
Initialize Open and add x to it
while Open != ∅ do
	Remove a node x from Open with the lexicographically smallest f-value of all nodes in Open
	if g2(x) ≥ g2_min(s(x)) ∨ f2(x) ≥ g2_min (s_goal) then
		continue
	g2_min(s(x)) ← g2(x)
	Add x to sols(s(x))
	if s(x) = sgoal then
		continue
	for each t ∈ Succ(s(x)) do
		y ← new node with s(y)= t
		g(y) ← g(x) + c(s(x), t)
		parent(y) ← x
		f(y) ← g(y) + h(t)
		if g2(y) ≥ g2_min(t) ∨ f2(y) ≥ g2_min(s_goal) then
			continue
		Add y to Open
return sols(s_goal) */

package com.hstairs.ppmajal.search;

import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode;
import com.hstairs.ppmajal.search.searchnodes.BoaStarSearchNode;
import com.hstairs.ppmajal.extraUtils.ExternalLoggerLogType;
import it.unimi.dsi.fastutil.objects.ObjectHeapPriorityQueue;
import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.jgrapht.alg.util.Pair;

public class BOAStar extends SearchEngine {

	protected long previous;

	public BOAStar(boolean helpfulActionsPruning) {
		super(false);
	}

	@Override
	public SearchStats getStats() {
		return new SearchStats(nodesExpanded, nodesEvaluated, deadEndsDetected, duplicatedDetected, totalTime,
				heuristicTime);
	}

	@Override
	public SimpleSearchNode search(SearchProblem problem, SearchHeuristic heuristic, PrintStream out) {
		zeroCounters();
		final State initState = problem.getInit();

		if (!problem.satisfyGlobalConstraints(initState)) {
			out.println("Initial State is not valid");
			return null;
		}

		long timeAtStart = System.currentTimeMillis();
		List<float[]> hAtInit = heuristic.computeBiObjectiveEstimate(initState);
		heuristicTime += System.currentTimeMillis() - timeAtStart;

		final ObjectHeapPriorityQueue<BoaStarSearchNode> frontier = new ObjectHeapPriorityQueue<>(
				new BoaStarTieBreaker());

		if (isDeadEnd(hAtInit)) {
			deadEndsDetected++;
			return null;
		} else {
			nodesEvaluated++;
		}

		Object2FloatMap<State> g2min = new Object2FloatOpenHashMap<>();
		g2min.put(initState.getRepresentative(), Float.MAX_VALUE);
		float g2minGoal = Float.MAX_VALUE;
		List<BoaStarSearchNode> paretoFrontier = new ArrayList<>();

		for (float[] hVector : hAtInit) {
			if (hVector[0] == Float.MAX_VALUE || hVector[1] == Float.MAX_VALUE)
				continue;
			BoaStarSearchNode init = new BoaStarSearchNode(initState, null, null, 0, 0, hVector[0], hVector[1]);
			frontier.enqueue(init);
			this.tryLog(init, ExternalLoggerLogType.Generating);
		}
		previous = 0;

		while (!frontier.isEmpty()) {
			if (shouldStop(timeAtStart)) {
				totalTime = (System.currentTimeMillis() - timeAtStart);
				out.println("Search interrupted: timeout reached.");
				return !paretoFrontier.isEmpty() ? new BoaStarSearchNode(paretoFrontier) : null;
			}
			final BoaStarSearchNode currentNode = frontier.dequeue();
			this.tryLog(currentNode, ExternalLoggerLogType.Expanding);
			float currentG2Min = g2min.getOrDefault(currentNode.s.getRepresentative(), Float.MAX_VALUE);
			// first pruning
			if (currentNode.g2 >= currentG2Min || currentNode.f2 >= g2minGoal) {
				duplicatedDetected++;
				this.tryLog(currentNode, ExternalLoggerLogType.Closing);
				continue;
			}
			g2min.put(currentNode.s.getRepresentative(), currentNode.g2);
			nodesExpanded++;

			long fromTheBeginning = (System.currentTimeMillis() - timeAtStart);
			printInfoDuringSearch(timeAtStart, out, fromTheBeginning, nodesExpanded, nodesEvaluated, frontier,
					currentNode);

			final Boolean res = problem.goalSatisfied(currentNode.s);
			if (res == null) {
				deadEndsDetected++;
				continue;
			} else if (res) {
				currentNode.timeFound = (System.currentTimeMillis() - timeAtStart);
				paretoFrontier.add(currentNode);
				g2minGoal = currentNode.g2;
				this.tryLog(currentNode, ExternalLoggerLogType.Closing);
				continue;
			}
			final Object[] actionsToSearch = getActionsToSearch(currentNode, problem, heuristic);
			for (final Iterator<Pair<State, Object>> it = problem.getSuccessors(currentNode.s, actionsToSearch); it
					.hasNext();) {
				if (shouldStop(timeAtStart)) {
					totalTime = (System.currentTimeMillis() - timeAtStart);
					out.println("Search interrupted: timeout reached.");
					return !paretoFrontier.isEmpty() ? new BoaStarSearchNode(paretoFrontier) : null;
				}
				final Pair<State, Object> next = it.next();
				final State successorState = next.getFirst();
				final Object act = next.getSecond();
				final float successorG1 = problem.gValue(currentNode.s, act, successorState, currentNode.gValue, 0);
				if (Objects.equals(successorG1, this.G_DEFAULT)) {
					deadEndsDetected++;
					continue;
				}
				float successorG2 = problem.gValue(currentNode.s, act, successorState, currentNode.g2, 1);
				long hStart = System.currentTimeMillis();
				List<float[]> heuristics = heuristic.computeBiObjectiveEstimate(successorState);
				heuristicTime += (System.currentTimeMillis() - hStart);
				if (isDeadEnd(heuristics)) {
					deadEndsDetected++;
					continue;
				} else {
					nodesEvaluated++;
				}
				for (float[] hVector : heuristics) {
					if (hVector[0] == Float.MAX_VALUE || hVector[1] == Float.MAX_VALUE)
						continue;
					float successorF1 = successorG1 + hVector[0];
					float successorF2 = successorG2 + hVector[1];
					float currentSuccG2min = g2min.getOrDefault(successorState.getRepresentative(), Float.MAX_VALUE);
					// second pruning
					if (successorG2 >= currentSuccG2min || successorF2 >= g2minGoal) {
						duplicatedDetected++;
						continue;
					}
					BoaStarSearchNode node = new BoaStarSearchNode(successorState, act, currentNode, successorG1,
							successorG2, successorF1, successorF2);
					frontier.enqueue(node);
					this.tryLog(node, ExternalLoggerLogType.Generating);
				}
			}
			this.tryLog(currentNode, ExternalLoggerLogType.Closing);
		}
		totalTime = System.currentTimeMillis() - timeAtStart;
		return !paretoFrontier.isEmpty() ? new BoaStarSearchNode(paretoFrontier) : null;
	}

	protected void printInfoDuringSearch(long timeAtStart, PrintStream out, long fromTheBeginning,
			int nodesExpanded, int nodesEvaluated, Object frontier,
			BoaStarSearchNode currentNode) {
		if (fromTheBeginning >= previous + 10000) {
			final float speed = (fromTheBeginning > 0) ? nodesExpanded / (fromTheBeginning / 1000f) : 0;
			out.println("-------------Time: " + (fromTheBeginning / 1000)
					+ "s ; Expanded Nodes: " + nodesExpanded +
					" (Avg-Speed " + String.format("%.2f", speed) + " n/s); Evaluated States: " + nodesEvaluated +
					" ; Pruned Duplicates: " + duplicatedDetected);
			previous = fromTheBeginning;
		}
	}

	// ordina la openList per f1; a parità di f1 guarda f2; a parità di f2 favorisce
	// il g2 maggiore.
	private static class BoaStarTieBreaker implements Comparator<BoaStarSearchNode> {
		@Override
		public int compare(BoaStarSearchNode n1, BoaStarSearchNode n2) {
			int cmpF1 = Float.compare(n1.f1, n2.f1);
			if (cmpF1 != 0) {
				return cmpF1;
			}
			int cmpF2 = Float.compare(n1.f2, n2.f2);
			if (cmpF2 != 0) {
				return cmpF2;
			}
			return Float.compare(n2.g2, n1.g2);
		}
	}

	// rilevamento dei vicoli ciechi.
	private boolean isDeadEnd(List<float[]> heuristics) {
		if (heuristics == null || heuristics.isEmpty())
			return true;
		for (float[] hVector : heuristics) {
			if (hVector[0] != Float.MAX_VALUE && hVector[1] != Float.MAX_VALUE) {
				return false;
			}
		}
		return true;
	}
}