package com.Github.IkhideIfidon;

import java.util.Deque;
import java.util.LinkedList;

public class DirectedCycle {
    // Instance Variables
    private final boolean[] marked;
    private final int[] edgeTo;
    private Deque<Integer> cycle;
    private final boolean[] onStack;

    public DirectedCycle(DirectedGraph G) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        onStack = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v])
                dfs(G, v);
        }
    }

    private void dfs(DirectedGraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : G.neighbors(v)) {
            if (this.hasCycle())
                return;

            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }

            else if (onStack[w]) {
                cycle = new LinkedList<>();
                for (int value = v; value != w; value = edgeTo[value])
                    cycle.push(value);
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return  cycle != null;
    }

    public Iterable<Integer> cycle() { return cycle; }

}
