/*
 * Copyright (C) 2015-2017, Enrico Scala, contact: enricos83@gmail.com
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


import com.hstairs.ppmajal.conditions.Terminal;
import enhsp2.ENHSP;

import java.util.List;

public class main {

    public static void main(String[] args) throws Exception {
        ENHSP p = new ENHSP(false);
        p.parseInput(args);
        p.configurePlanner();
        p.parsingDomainAndProblem(args);
        List<Terminal> subgoals = p.getProblem().createSubgoals();
        p.planning();
    }
}