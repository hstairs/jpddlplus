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

import jpddlplus.problem.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author enrico
 */
public class SearchNode extends SimpleSearchNode {

  public List<Float> f;
  public int waitingPoints;
  public Object[] helpfulActions;
  public List<Object[]> helpfulActionsList;

  public SearchNode(State s1, Object action, SearchNode father, float gValue, float fExt) {
    super(s1, action, father, gValue);
    if (action instanceof final Integer act) {
      this.waitingPoints = act;
      //System.out.println(this.list_of_actions.size());
    } else {
      this.waitingPoints = 0;
    }
    f = new LinkedList<>(Collections.singletonList(fExt));
  }

  public SearchNode(State s1, Object action, SearchNode father, float gValue, List<Float> fExt) {
    super(s1, action, father, gValue);
    if (action instanceof final Integer act) {
      this.waitingPoints = act;
      //System.out.println(this.list_of_actions.size());
    } else {
      this.waitingPoints = 0;
    }
    f = fExt;
    helpfulActionsList = new ArrayList<>(Collections.nCopies(fExt.size(), null));
  }

  public SearchNode(State s1, float action_cost_to_get_here, float fExt) {
    super(s1, 0, null, action_cost_to_get_here);
    f = new LinkedList<>(Collections.singletonList(fExt));
    helpfulActionsList = new ArrayList<>(Collections.nCopies(1, null));
  }

  @Override
  public String toString() {
    return "SearchNode{" + "s=" + s + ", action=" + transition + ", gValue=" + gValue + '}';
  }


}
