package com.Github.IkhideIfidon;

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

    private void dfs(DirectedGraph G, int source) {
        marked[source] = true;
        id[source] = count;

        for (int w : G.neighbors(source)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean stronglyConnected(int v, int u) {
        return id[v] == id[u];
    }

    public int id(int v) { return id[v]; }
    public int count() { return count; }
}
