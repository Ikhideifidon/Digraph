package com.Github.IkhideIfidon;

import java.io.*;
import java.util.*;

/**
 * An Adjacency List representation of a DIRECTED GRAPH.
 * A Directed Graph (or digraph) is a set of vertices and a collection of directed edges.
 * Each directed edge connects an ordered pair of vertices.
 * **/

public class DirectedGraph {

    // Instance variables.
    private final int V;
    private int E;
    private final List<Integer>[] adjacent;
    private final Map<Integer, List<Integer>> inComing;
    private final Map<Integer, List<Integer>> outGoing;

    // Constructor
    public DirectedGraph(int V) {
        this.V = V;
        this.E = 0;

        //noinspection unchecked
        adjacent = (List<Integer>[]) new LinkedList[V];
        inComing = new HashMap<>();
        outGoing = new HashMap<>();
        for (int i = 0; i < V; i++) {
            adjacent[i] = new LinkedList<>();
            inComing.put(i, new LinkedList<>());
            outGoing.put(i, new LinkedList<>());
        }
    }

    public DirectedGraph(BufferedReader in) throws IOException {
        this(Integer.parseInt(in.readLine()));
        this.E = Integer.parseInt(in.readLine());
        for (int i = 0; i < E; i++) {
            // Add an edge
            String[] stringSplit = in.readLine().split("[ \\t]+"); // capture one or more space and tab
            int v = Integer.parseInt(stringSplit[0]);
            int w = Integer.parseInt(stringSplit[1]);
            addEdge(v, w);
            E--;
        }
    }

    public DirectedGraph(DirectedGraph G) {
        /*
        Instance variables.
        private final int V...............................immutable
        private int E.....................................immutable
        private final List<Integer>[] adjacent............mutable
        private Map<Integer, List<Integer>> inComing......mutable
        private Map<Integer, List<Integer>> outGoing......mutable
         */
        this(G.V());
        Deque<Integer> stack = new LinkedList<>();
        for (int v = 0; v < V; v++) {
            for (int w : G.neighbors(v))
                stack.push(w);

            while (!stack.isEmpty())
                adjacent[v].add(stack.pollLast());
        }
        this.E = G.E();

        for (int key : G.inComing.keySet()) {
            List<Integer> list = new LinkedList<>(G.inComing.get(key));
            this.inComing.put(key, list);
        }

        for (int key : G.outGoing.keySet()) {
            List<Integer> list = new LinkedList<>(G.outGoing.get(key));
            this.outGoing.put(key, list);
        }
    }

    public void addEdge(int v, int u) {
        // v--->u
        // Outgoing: v
        // Incoming: u
        if ((0 <= v && v < V) && (0 <= u && u < V)) {
            adjacent[v].add(u);

            List<Integer> outList = outGoing.get(v);
            outList.add(u);
            outGoing.put(v, outList);

            List<Integer> inList = inComing.get(u);
            inList.add(v);
            inComing.put(u, inList);
            E++;
        }
    }

    /**
     * Vertex v Neighbors are edges leaving a vertex v
     * **/
    public Iterable<Integer> neighbors(int v) {
        /*
        * vertices connected to v by edges pointing from v
        * */
        if ((0 <= v && v < V)) {
            return adjacent[v];
        }
        throw new ArrayIndexOutOfBoundsException(v + "is not a valid vertex.");
    }

    public int inDegree(int v) {
        if ((0 <= v && v < V)) {
            return inComing.get(v).size();
        }
        return 0;
    }

    public int outDegree(int v) {
        if ((0 <= v && v < V)) {
            return outGoing.get(v).size();
        }
        return 0;
    }

    public List<Integer> outGoingEdges(int v) {
        return outGoing.get(v);
    }

    public List<Integer> inComingEdges(int u) {
        return inComing.get(u);
    }

    public int V() { return V; }
    public int E() { return E; }

    public DirectedGraph reverse() {
        DirectedGraph R = new DirectedGraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : this.neighbors(v))
                R.addEdge(w, v);
        }
        return R;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < V; i++) {
            sb.append(neighborsToString(i));
            if (i + 1 != V)
                sb.append(" ,");
        }
        sb.append("]");
        return sb.toString();
    }

    private String neighborsToString(int v) {
        StringBuilder sb = new StringBuilder("[");

        Iterator<Integer> iter = adjacent[v].iterator();
        while (iter.hasNext()) {
            sb.append(iter.next());
            if (iter.hasNext())
                sb.append("-->");
        }
        sb.append("]");
        return sb.toString();
    }
}
