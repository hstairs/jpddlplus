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
package com.hstairs.ppmajal.heuristics.utils;

import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.problem.GroundAction;
import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;

/**
 * @author enrico
 */
public class AchieverSet {

    private float cost;
    private ReferenceOpenHashSet<GroundAction> actions;
    private ReferenceOpenHashSet<Condition> targetCond;

    public AchieverSet ( ) {
        setActions(new ReferenceOpenHashSet<>());
        setTargetCond(new ReferenceOpenHashSet<>());
    }

    public float getCost ( ) {
        return cost;
    }

    public void setCost (float cost) {
        this.cost = cost;
    }

    public ReferenceOpenHashSet<GroundAction> getActions ( ) {
        return actions;
    }

    public void setActions (ReferenceOpenHashSet<GroundAction> actions) {
        this.actions = actions;
    }

    public ReferenceOpenHashSet<Condition> getTargetCond ( ) {
        return targetCond;
    }

    public void setTargetCond (ReferenceOpenHashSet<Condition> targetCond) {
        this.targetCond = targetCond;
    }
}
