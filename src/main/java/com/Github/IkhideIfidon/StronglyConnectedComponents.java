package com.Github.IkhideIfidon;

import java.util.Arrays;

public class StronglyConnectedComponents {

    // Instance Variables
    private final boolean[] marked;
    private int count;
    private final int[] id;

    public StronglyConnectedComponents(DirectedGraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];

        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }
    }

    private void dfs(DirectedGraph G, int v) {
        marked[v] = true;
        id[v] = count;

        for (int w : G.neighbors(v)) {
            if (!marked[w])
                dfs(G, w);
        }
    }

    public boolean stronglyConnected(int v, int u) {
        System.out.println(Arrays.toString(id));
        return id[v] == id[u];
    }

    public int id(int v) { return id[v]; }
    public int count() { return count; }
}
