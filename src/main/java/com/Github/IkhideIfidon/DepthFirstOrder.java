package com.Github.IkhideIfidon;

import java.util.Deque;
import java.util.LinkedList;

public class DepthFirstOrder {
    // Instance Variables
    private final boolean[] marked;
    private final Deque<Integer> preorder;              // Queue
    private final Deque<Integer> postorder;             // Queue
    private final Deque<Integer> reversePostorder;      // Stack

    public DepthFirstOrder(DirectedGraph G) {
        marked = new boolean[G.V()];
        preorder = new LinkedList<>();
        postorder = new LinkedList<>();
        reversePostorder = new LinkedList<>();

        for (int v = 0; v < G.V(); v++) {
            if (!marked[v])
                dfs(G, v);
        }
    }

    private void dfs(DirectedGraph G, int v) {
        marked[v] = true;
        preorder.offer(v);

        for (int w : G.neighbors(v)) {
            if (!marked[w])
                dfs(G, w);
        }
        postorder.offer(v);
        reversePostorder.push(v);
    }

    public Iterable<Integer> preorder() { return preorder; }
    public Iterable<Integer> postorder() {return postorder; }
    public Iterable<Integer> reversePostorder() { return reversePostorder; }
}
