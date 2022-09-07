package com.Github.IkhideIfidon;

import java.util.Deque;
import java.util.LinkedList;

public class DepthFirstPaths {
    // Instance Variables
    private final boolean[] marked;
    private final int[] edgeTo;
    private final int source;

    public DepthFirstPaths(DirectedGraph G, int source) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.source = source;
        dfs(G, source);
    }

    private void dfs(DirectedGraph G, int source) {
        marked[source] = true;
        for (int v : G.neighbors(source)) {
            if (!marked[v]) {
                edgeTo[v] = source;
                dfs(G, v);
            }
        }
    }

    public boolean hasPath(int v) { return marked[v]; }

    public Iterable<Integer> findPath(int v) {
        if (!hasPath(v))
            return null;

        Deque<Integer> stack = new LinkedList<>();
        for (int value = v; value != source; value = edgeTo[value])
            stack.push(value);
        stack.push(source);
        return stack;
    }


}
