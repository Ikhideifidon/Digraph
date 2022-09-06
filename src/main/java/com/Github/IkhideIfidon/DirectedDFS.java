package com.Github.IkhideIfidon;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class DirectedDFS {

    // Instance Variable
    private boolean[] marked;

    public DirectedDFS(DirectedGraph G, int source) {
        marked = new boolean[G.V()];
        dfs(G, source);
    }

    /**
     * Find all vertices in Graph G that are reachable from sources.
     **/
    public List<Integer> reachableFromSources(DirectedGraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        for (int v : sources) {
            if (!marked[v])
                dfs(G, v);
        }

        List<Integer> reachable = new LinkedList<>();
        for (int i = 0; i < G.V(); i++) {
            if (marked[i])
                reachable.add((i));
        }
        return reachable;
    }

    private void dfs(DirectedGraph G, int source) {
        marked[source] = true;
        Deque<Integer> stack = new LinkedList<>();
        stack.push(source);

        while (!stack.isEmpty()) {
            int v = stack.pop();
            
            for (int w : G.neighbors(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    stack.push(w);
                }
            }
        }
    }

    /**
     * @param v: given vertex to check if path exist from source to it.
     * @return true if there is a path from source to v. False otherwise.
     */
    public boolean hasPath(int v) {
        return marked[v];
    }

    /**
     * @param G: Directed graph G
     * @param v: start vertex
     * @param u: end vertex;
     * @return true if there is a path from start to end. False otherwise.
     */
    public boolean hasPath(DirectedGraph G, int v, int u) {
        dfs(G, v);
        return marked[u];

    }
}
