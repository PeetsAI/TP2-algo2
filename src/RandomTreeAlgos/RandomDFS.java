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
import java.util.Stack; 

public class RandomDFS {

    private static final Random gen = new Random();

    public static ArrayList<Edge> generateTree(Graph graph) {
        ArrayList<Edge> tree = new ArrayList<>();
        BitSet visited = new BitSet(graph.order);
        Stack<Arc> stack = new Stack<>();

        // 1. Choisir une racine au hasard
        int root = gen.nextInt(graph.order);
        visited.set(root);

        // 2. Initialiser la pile avec les voisins de la racine (mélangés)
        Arc[] rootArcs = graph.outEdges(root);
        List<Arc> rootNeighbors = Arrays.asList(rootArcs);
        Collections.shuffle(rootNeighbors);
        for (Arc a : rootNeighbors) {
            stack.push(a);
        }

        // 3. Boucle principale (remplace la récursivité)
        while (!stack.isEmpty()) {
            // On prend le dernier arc ajouté 
            Arc arc = stack.pop();
            int u = arc.getDest();

            if (!visited.get(u)) {
                // Si le sommet n'est pas encore visité, on l'ajoute à l'arbre
                visited.set(u);
                tree.add(arc.support);

                // On ajoute ses voisins à la pile pour les explorer plus tard
                Arc[] neighbors = graph.outEdges(u);
                List<Arc> neighborList = Arrays.asList(neighbors);
                Collections.shuffle(neighborList);

                for (Arc a : neighborList) {
                    if (!visited.get(a.getDest())) {
                        stack.push(a);
                    }
                }
            }
        }
        
        return tree;
    }
}