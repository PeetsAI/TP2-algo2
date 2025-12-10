package RandomTreeAlgos;

import Graph.Arc;
import Graph.Edge;
import Graph.Graph;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Random;

public class AldousBroder {

    private static final Random gen = new Random();

    public static ArrayList<Edge> generateTree(Graph graph) {
        ArrayList<Edge> tree = new ArrayList<>();
        BitSet visited = new BitSet(graph.order);
        
        //choisir un sommet initial aléatoire
        int currentVertex = gen.nextInt(graph.order);
        visited.set(currentVertex);
        int visitedCount = 1;

        while (visitedCount < graph.order) {
            //récupérer les voisins
            Arc[] neighbors = graph.outEdges(currentVertex);
            if (neighbors.length == 0) break; 

            //choisir un voisin au hasard (Marche aléatoire)
            Arc randomArc = neighbors[gen.nextInt(neighbors.length)];
            int nextVertex = randomArc.getDest();

            if (!visited.get(nextVertex)) {
                visited.set(nextVertex);
                visitedCount++;
                tree.add(randomArc.support);
            }
            
            currentVertex = nextVertex;
        }

        return tree;
    }
}