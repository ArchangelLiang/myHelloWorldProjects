package com.datastructure_arithmetic.datastructure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphOfMatrix {

    private List<String> vertexes;
    private int[][] graph;
    private int numOfEdge;
    private boolean[] isVisit;

    public GraphOfMatrix(String[] arr) {
        vertexes = new ArrayList<>();
        vertexes.addAll(Arrays.asList(arr));
        graph = new int[arr.length][arr.length];
        isVisit = new boolean[arr.length];
    }

    public int getNumOfEdge() {
        return numOfEdge;
    }

    private void createEdge(String v1, String v2) {
        graph[vertexes.indexOf(v1)][vertexes.indexOf(v2)] = 1;
        graph[vertexes.indexOf(v2)][vertexes.indexOf(v1)] = 1;
        numOfEdge++;
    }

    public void printGraphMatrix() {
        System.out.print("  ");
        Object[] verArr = vertexes.toArray();
        for (int i = 0; i < verArr.length; i++) {
            System.out.print(verArr[i]);
            if (i != verArr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
        for (int i = 0; i < graph.length; i++) {
            System.out.print(vertexes.get(i));
            System.out.println(Arrays.toString(graph[i]));
        }
    }

    private int getNextVertex(int a,int b) {
        for (int i = b + 1; i < graph.length; i++) {
            if (graph[a][i] > 0 && !isVisit[i]) {
                return i;
            }
        }
        return -1;
    }

    public void bfs() {
        for (int i = 0; i < graph.length; i++) {
            int b = 0;
            int index = i;
            while (index != -1){
                if (!isVisit[index]) {
                    System.out.print(vertexes.get(index)+"->");
                    isVisit[index] = true;
                }
                index= getNextVertex(i,index);
            }
        }
    }

    public static void main(String[] args) {
        String[] vs = new String[]{"A", "B", "C", "D", "E"};
        GraphOfMatrix matrix = new GraphOfMatrix(vs);
        matrix.createEdge("A", "C");
        matrix.createEdge("A", "B");
        matrix.createEdge("B", "C");
        matrix.createEdge("B", "D");
        matrix.createEdge("B", "E");
//        matrix.printGraphMatrix();
        matrix.bfs();
    }

}
