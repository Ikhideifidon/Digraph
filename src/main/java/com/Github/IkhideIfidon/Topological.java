package com.Github.IkhideIfidon;

public class Topological {
    // Instance Variables
    private Iterable<Integer> order;

    public Topological(DirectedGraph G) {
        DirectedCycle cycleFinder = new DirectedCycle(G);
        // Check if Graph G is acyclic
        if (!cycleFinder.hasCycle()) {
            DepthFirstOrder depthFirstOrder = new DepthFirstOrder(G);
            order = depthFirstOrder.reversePostorder();
        }
    }

    public Iterable<Integer> order() { return order; }
    public boolean isDAG() { return order != null; }
}
