package practicafinal_estructuradatos;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*Para implementar este algoritmo se tomo como referencia 
 *http://www.sanfoundry.com/java-program-implement-ford-fulkerson-algorithm/
 *se hicieron algunos cambios de variable pero la lógica es la misma
 */
public class ford_Fulkerson {

    private int[] parent;
    private Queue<Integer> queue;
    private int numN;
    private boolean[] visited;

    public ford_Fulkerson(int numN) {
        this.numN = numN;
        this.queue = new LinkedList<Integer>();
        parent = new int[numN + 1];
        visited = new boolean[numN + 1];
    }

    public boolean busquedaAnchura(int inicio, int fin, int graph[][]) {
        boolean llegarFin = false;
        int destino, elemento;

        for (int i = 1; i <= numN; i++) {
            parent[i] = -1;
            visited[i] = false;
        }
        queue.add(inicio);
        parent[inicio] = -1;
        visited[inicio] = true;

        while (!queue.isEmpty()) {
            elemento = queue.remove();
            destino = 1;
            while (destino <= numN) {
                if (graph[elemento][destino] > 0
                        && !visited[destino]) {
                    parent[destino] = elemento;
                    queue.add(destino);
                    visited[destino] = true;
                }
                destino++;
            }
        }
        if (visited[fin]) {
            llegarFin = true;
        }
        return llegarFin;
    }

    public int fordFulkerson(int graph[][], int inicio, int fin) {
        int u, v;
        int maxFlow = 0;
        int max;
        
        int[][] residualGraph
                = new int[numN + 1][numN + 1];

        for(int i=0;i<numN+1;i++){
            for(int j=0;j<numN+1;j++){
                residualGraph[i][j]=graph[i][j];
            }
        }
        
        while (busquedaAnchura(inicio, fin, residualGraph)) {
            max = Integer.MAX_VALUE;
            for (v = fin; v != inicio; v = parent[v]) {
                u = parent[v];
                max = Math.min(max, residualGraph[u][v]);
            }

            for (v = fin; v != inicio; v = parent[v]) {
                u = parent[v];
                residualGraph[u][v] -= max;
                residualGraph[v][u] += max;
            }
            maxFlow += max;
        }
        return maxFlow;
    }

    public int ford(int[][] graph, int numN, int inicio, int fin) {
        int maxFlow;
        maxFlow = fordFulkerson(graph, inicio, fin);
        return maxFlow;
    }

}
