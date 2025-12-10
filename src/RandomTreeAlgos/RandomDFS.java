package RandomTreeAlgos;

import Graph.Arc;
import Graph.Edge;
import Graph.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomDFS {

    private static final Random gen = new Random();
    private static BitSet visited;
    private static ArrayList<Edge> tree;

    public static ArrayList<Edge> generateTree(Graph graph) {
        tree = new ArrayList<>();
        visited = new BitSet(graph.order);
        // Choisir un sommet racine aléatoire
        int root = gen.nextInt(graph.order);
        // On lance le parcours récursif
        explore(graph, root);
        
        return tree;
    }

    private static void explore(Graph graph, int u) {
        visited.set(u);
        
        //Récupérer les voisins
        Arc[] arcs = graph.outEdges(u);
        

        List<Arc> neighbors = Arrays.asList(arcs);
        Collections.shuffle(neighbors); 

        // On visite chaque voisin dans l'ordre aléatoire
        for (Arc a : neighbors) {
            int v = a.getDest();
            if (!visited.get(v)) {
                // Si le voisin n'est pas visité, on ajoute l'arrêt et on continue l'exploration
                tree.add(a.support);
                explore(graph, v);
            }
        }
    }
}