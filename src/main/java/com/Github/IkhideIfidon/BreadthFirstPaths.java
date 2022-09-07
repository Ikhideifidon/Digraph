package com.Github.IkhideIfidon;

import java.util.Deque;
import java.util.LinkedList;

public class BreadthFirstPaths {
    // Instance Variables
    private final boolean[] marked;
    private final int[] edgeTo;
    private final int source;

    public BreadthFirstPaths(DirectedGraph G, int source) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.source = source;
        bfs(G, source);
    }

    private void bfs(DirectedGraph G, int source) {
        marked[source] = true;
        Deque<Integer> queue = new LinkedList<>();
        queue.offer(source);

        while (!queue.isEmpty()) {
            int v = queue.poll();

            for (int w : G.neighbors(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = v;
                    queue.offer(w);
                }
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

