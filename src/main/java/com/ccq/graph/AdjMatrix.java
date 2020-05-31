package com.ccq.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Description: 邻接矩阵
 * @Author: ChengChuanQiang
 * @Date: 2020/4/28 22:50
 */
public class AdjMatrix {

    private int V;
    private int E;
    private int[][] adj;

    public AdjMatrix(String fileName) {

        String path = this.getClass().getResource("/" + fileName).getPath();
        File file = new File(path);

        try (Scanner scanner = new Scanner(file)) {

            this.V = scanner.nextInt();
            if (this.V < 0) {
                throw new IllegalArgumentException("V must be non-negative");
            }

            this.E = scanner.nextInt();
            if (this.E < 0) {
                throw new IllegalArgumentException("E must be non-negative");
            }

            adj = new int[V][V];
            for (int i = 0; i < this.E; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();

                validateVertex(a);
                validateVertex(b);

                // 自环边
                if (a == b) {
                    throw new IllegalArgumentException("Self Loop is Detected!");
                }

                // 平行边
                if (adj[a][b] == 1) {
                    throw new IllegalArgumentException("Parallel Edge are Detected!");
                }

                adj[a][b] = 1;
                adj[b][a] = 1;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);

        return adj[v][w] == 1;
    }

    /**
     * v这个点所有的关联的点
     *
     * @param v v
     * @return list
     */
    public List<Integer> adj(int v) {
        validateVertex(v);
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < this.V; i++) {
            if (adj[v][i] == 1) {
                res.add(i);
            }
        }
        return res;
    }

    public int degree(int v) {
        return adj(v).size();
    }

    public int getV() {
        return this.V;
    }

    public int getE() {
        return this.E;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= this.V) {
            throw new IllegalArgumentException("vertex " + v + "is invalid");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("V = %d, E = %d\n", V, E));
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                sb.append(String.format("%d ", adj[i][j]));
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        AdjMatrix adjMatrix = new AdjMatrix("g.txt");

        System.out.println(adjMatrix);
        System.out.println(adjMatrix.adj(0));
        System.out.println(adjMatrix.degree(0));
    }
}
