package com.Github.IkhideIfidon;


public class TransitiveClosureMatrix {
    // Instance Variables
    private final boolean[][] marked;
    private final int[][] tc;

    public TransitiveClosureMatrix(DirectedGraph G) {
        marked = new boolean[G.V()][G.V()];
        tc = new int[G.V()][G.V()];

        for (int i = 0; i < G.V(); i++)
            dfs(G, i, i); // Every vertex is reachable from itself.
    }

    public void dfs(DirectedGraph G, int s, int v) {
        // Mark reachability from s to v as true.
        marked[s][v] = true;
        tc[s][v] = 1;
        for (Integer w : G.neighbors(v)) {
            if (!marked[s][w])
                dfs(G, s, w);
        }
    }

    public int[][] transitiveClosure() { return tc; }
    public boolean reachable(int v, int u) { return marked[v][u]; }
}
