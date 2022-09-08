package com.Github.IkhideIfidon;

import java.util.Arrays;

public class KosarajuSCC {
    private boolean[] marked;
    private int[] id;
    private int count;
    // reached vertices
// component identifiers
// number of strong components
    public KosarajuSCC(DirectedGraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        for (int s : order.reversePostorder()) {
            if (!marked[s]) {
                dfs(G, s);
                count++;
            }
        }
    }

    private void dfs(DirectedGraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.neighbors(v))
            if (!marked[w])
                dfs(G, w);
    }
    public boolean stronglyConnected(int v, int w) {
        System.out.println(Arrays.toString(id));
        return id[v] == id[w];  }
    public int id(int v) {
        return id[v];  }
    public int count()
    {  return count;  }
}
