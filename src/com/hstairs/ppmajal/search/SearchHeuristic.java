/*
 * Copyright (C) 2010-2017 Enrico Scala. Contact: enricos83@gmail.com.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package jpddlplus.search;

import java.io.PrintStream;
import jpddlplus.problem.*;
import jpddlplus.transition.TransitionGround;
import java.util.*;


/**
 * @author enrico
 */
public abstract class SearchHeuristic {

  protected boolean verboseHeuristic = false;
  protected PrintStream out;
  protected float cachedH;
//  protected State cachedS;

  public float computeEstimate(State s) {
    float h = computeEstimateForStore(s);
    cachedH = h;
//    cachedS = s;
    return h;
  }

  protected abstract float computeEstimateForStore(State s);

  public float computeEstimateCached(State s) {
//    assert(!cachedS.equals(s));
    return cachedH;
  }

  public float computeEstimate(State s, boolean useCache) {
    if (useCache) {
      return computeEstimateCached(s);
    } else {
      return computeEstimate(s);
    }
  }

  public abstract Object[] getTransitions(boolean helpful);

  public abstract Collection<TransitionGround> getAllTransitions();

  public void setVerboseHeuristic(boolean verboseHeuristic) {
    this.verboseHeuristic = verboseHeuristic;
  }

  public void dumpHeuristicStats() {

  }

  public void setPrintStream(PrintStream out) {
    this.out = out;
  }
}
