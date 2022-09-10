package com.Github.IkhideIfidon;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TarjanSCC {
    // Instance Variables
    private final boolean[] marked;
    private final boolean[] onStack;
    private final int[] ids, scc;
    private int SCCCount, id;
    private final int[] lowLink;             // The smallest vertex id reachable from that vertex
    private final Deque<Integer> stack;

    public TarjanSCC(DirectedGraph G) {
        marked = new boolean[G.V()];
        onStack = new boolean[G.V()];
        ids = new int[G.V()];
        scc = new int[G.V()];
        lowLink = new int[G.V()];
        stack = new LinkedList<>();

        for (int v = 0; v < G.V(); v++) {
            if (!marked[v])
                dfs(G, v);
        }
    }

    private void dfs(DirectedGraph G, int v) {
        stack.push(v);
        marked[v] = true;
        onStack[v] = true;
        ids[v] = lowLink[v] = id++;

        for (int w : G.neighbors(v)) {
            if (!marked[w])
                dfs(G, w);
            if (onStack[w])
                lowLink[v] = Math.min(lowLink[v], lowLink[w]);
        }

        // On recursive callbacks, if we are at the root vertex i.e. start of SCC,
        // empty the seen stack until we are back to root.
        if (ids[v] == lowLink[v]) {
           for (int u = stack.pop(); ; u = stack.pop()) {
               onStack[u] = false;
               scc[u] = SCCCount;
               if (u == v)
                   break;
           }
           SCCCount++;
        }
    }

    public int numberOfStronglyConnectedComponents() { return SCCCount; }
    public boolean stronglyConnected(int v, int u) { return lowLink[v] == lowLink[u]; }
    public int id(int v) {
        return lowLink[v]; }

    public List<Integer>[] connectedComponents() {
        //noinspection unchecked
        List<Integer>[] cc = (List<Integer>[])  new LinkedList[SCCCount];
        for (int i = 0; i < SCCCount; i++)
            cc[i] = new LinkedList<>();

        for (int v = 0; v < marked.length; v++)
            cc[scc[v]].add(v);
        return cc;
    }

}
