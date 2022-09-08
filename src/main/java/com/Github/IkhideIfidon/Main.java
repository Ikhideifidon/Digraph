package com.Github.IkhideIfidon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        DirectedGraph G = new DirectedGraph(13);
        int[][] edges = {{0, 1}, {0, 5}, {2, 0}, {3, 2}, {2, 3}, {3, 5}, {4, 3}, {4, 2},
                {5, 4}, {6, 0}, {6, 4}, {6, 9}, {7, 6}, {7, 8}, {8, 7}, {8, 9}, {9, 10}, {9, 11},
                {11, 4}, {11, 12}, {10, 12}, {12, 9}};


        int[][] acyclic = {{0, 1}, {0, 6}, {0, 5}, {2, 0}, {2, 3}, {3, 5}, {5, 4}, {6, 4},
                {6, 9}, {7, 6}, {8, 7}, {9, 10}, {9, 11}, {11, 12}, {9, 12}};

        for (int[] edge : edges)
            G.addEdge(edge[0], edge[1]);
        System.out.println(G);
        DirectedCycle cycle = new DirectedCycle(G);
        System.out.println(cycle.hasCycle());

        KosarajuSCC scc = new KosarajuSCC(G);
        System.out.println(scc.count());
        System.out.println(scc.stronglyConnected(0, 3));



        System.out.println(G.inDegree(4));
        System.out.println(G.outDegree(4));
        System.out.println(G.outGoingEdges(4));
        System.out.println(G.neighbors(4));
        System.out.println(G.inComingEdges(4));


        System.out.println(G.E());

        DirectedGraph G2 = new DirectedGraph(G);
        System.out.println(G2);
        G.addEdge(6, 11);
//        G.addEdge(2, 6);
        System.out.println(G);
        System.out.println(G2);
        System.out.println(G2.E());
        G2.addEdge(6, 11);
        G2.addEdge(7, 9);
        System.out.println(G2.outGoingEdges(1));
        System.out.println(G.outGoingEdges(1));
        System.out.println(G2.inComingEdges(1));
        System.out.println(G.inComingEdges(1));
        System.out.println(G2);
        System.out.println(G);
        System.out.println(G.E());
        System.out.println(G2.outGoingEdges(6));
        System.out.println(G.reverse());

        DirectedDFS directedDFS = new DirectedDFS(G, 0);
        System.out.println(directedDFS.hasPath(2));
        System.out.println(directedDFS.hasPath(G, 2, 6));


        final String file = "src/main/resources/tinyDG.txt";
        try (FileReader fileReader = new FileReader(file);
             BufferedReader reader = new BufferedReader(fileReader) )

        {
            DirectedGraph G1 = new DirectedGraph(reader);
            System.out.println("This is from the input stream:\n" + G1.neighbors(0));
            System.out.println(G1.V());
            System.out.println(G1.E());
            System.out.println(G1);
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        int source = 0;
        DepthFirstPaths dfs = new DepthFirstPaths(G, source);
        System.out.println(dfs.findPath(2));

        BreadthFirstPaths bfs = new BreadthFirstPaths(G, source);
        System.out.println(bfs.findPath(2));

        Topological sort = new Topological(G);
        System.out.println(sort.isDAG());
        System.out.println(sort.order());



    }
}