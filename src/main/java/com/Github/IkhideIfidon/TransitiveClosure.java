package com.Github.IkhideIfidon;

public class TransitiveClosure {
    // Instance Variables
    private final DirectedDFS[] all;

    public  TransitiveClosure(DirectedGraph G) {
        all = new DirectedDFS[G.V()];
        for (int v = 0; v < G.V(); v++)
            all[v] = new DirectedDFS(G, v);
    }

    public boolean reachable(int v, int w) { return all[v].hasPath(w); }
}
