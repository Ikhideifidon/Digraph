package com.Github.IkhideIfidon;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class KosarajuSCC {
    private final boolean[] marked;           // reached vertices
    private final int[] id;                   // component identifiers
    private int count;                  // number of strong components


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
    public boolean stronglyConnected(int v, int w) { return id[v] == id[w]; }
    public int id(int v) {
        return id[v]; }
    public int count() { return count; }

    public List<Integer>[] connectedComponents() {

        List<Integer>[] cc;
        //noinspection unchecked
        cc = (List<Integer>[])  new LinkedList[count];
        for (int i = 0; i < count; i++)
            cc[i] = new LinkedList<>();

        for (int v = 0; v < marked.length; v++)
            cc[id[v]].add(v);

        return cc;
    }
}
