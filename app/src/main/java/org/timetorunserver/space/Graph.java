package org.timetorunserver.space;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Graph {
    String mapName;
    int vertices;
    Edge[][] edges;
    int[][] shortestPaths;
    public void findShortestPaths(){
        int[][] weights = new int[vertices][vertices];
        shortestPaths = new int[vertices][vertices];
        for(int i = 0; i < vertices; i++){
            for(int j = 0; j < vertices; j++){
                if(i == j){
                    weights[i][j] = 0;
                    shortestPaths[i][j] = i;
                }else if(edges[i][j] != null){
                    weights[i][j] = edges[i][j].weight;
                    shortestPaths[i][j] = j;
                }else {
                    shortestPaths[i][j] = -1;
                    weights[i][j] = 1000000000;
                }
            }
        }
        for(int k = 0; k < vertices; k++){
            for(int i = 0; i < vertices; i++){
                for(int j = 0; j < vertices; j++){
                    if(weights[i][j] > weights[i][k] + weights[k][j]){
                        shortestPaths[i][j] = shortestPaths[i][k];
                        weights[i][j] = weights[i][k] + weights[k][j];
                    }
                }
            }
        }
    }


    public boolean getGraphFromFile(Path path){
        Scanner input;
        try {
            input = new Scanner(path);
        } catch (IOException e) {
            return false;
        }
        input.useDelimiter("[ \n]");
        vertices = input.nextInt();
        edges = new Edge[vertices][vertices];
        int m = input.nextInt();
        int q = input.nextInt();
        mapName = input.next();

        for(int i = 0; i < q; i++){
            //TODO init players;
        }

        for(int i = 0; i < m; i++){
            int u = input.nextInt(), v = input.nextInt(), is_double = input.nextInt(), weight = input.nextInt();
            Edge edge = new Edge(weight);
            edges[u][v] = edge;
            if(is_double == 1){
                edges[v][u] = edge;
            }
        }
        findShortestPaths();
        return true;
    }

    public void draw(){
        for(int i = 0; i < vertices; i++){
            for(int j = 0; j < vertices; j++){
                System.out.print(shortestPaths[i][j]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    public boolean isEdge(int u, int v){
        return (edges[u][v] != null);
    }
}
