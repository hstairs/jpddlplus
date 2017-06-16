
/**
 * *******************************************************************
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place - Suite 330, Boston, MA 02111-1307, USA.
 *
 ********************************************************************
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heuristics.utils;

import conditions.Conditions;
import java.util.LinkedHashSet;
import problem.GroundAction;

/**
 *
 * @author enrico
 */
    public class achiever_set{
        public Float cost;
        public LinkedHashSet<GroundAction> actions;
        public LinkedHashSet<Conditions> target_cond;
        public achiever_set(){
            actions = new LinkedHashSet();
            target_cond = new LinkedHashSet();
        }
    }