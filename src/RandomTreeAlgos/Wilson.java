package RandomTreeAlgos;

import Graph.Arc;
import Graph.Edge;
import Graph.Graph;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Random;

public class Wilson {
    private static final Random gen = new Random();

    public static ArrayList<Edge> generateTree(Graph graph) {
        ArrayList<Edge> tree = new ArrayList<>();
        BitSet inTree = new BitSet(graph.order);
        
        // Initialisation : choisir un sommet arbitraire (ex: 0) et le mettre dans l'arbre
        inTree.set(0); 

        // Tableau pour suivre le chemin courant (next[u] = l'arc pris depuis u)
        Arc[] next = new Arc[graph.order];

        for (int i = 1; i < graph.order; i++) {
            if (inTree.get(i)) continue;

            int u = i;
            // Marche aléatoire effaçant les boucles (Loop-Erased Random Walk)
            while (!inTree.get(u)) {
                Arc[] neighbors = graph.outEdges(u);
                Arc randomArc = neighbors[gen.nextInt(neighbors.length)];
                next[u] = randomArc;
                u = randomArc.getDest();
            }

            // Ajouter le chemin sans cycle trouvé à l'arbre
            u = i;
            while (!inTree.get(u)) {
                inTree.set(u);
                Arc arc = next[u];
                tree.add(arc.support);
                u = arc.getDest();
            }
        }

        return tree;
    }
}