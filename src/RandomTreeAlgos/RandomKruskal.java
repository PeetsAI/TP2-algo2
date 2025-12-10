package RandomTreeAlgos;

import Graph.Edge;
import Graph.Graph;
import DataStructures.UnionFind;

import java.util.ArrayList;
import java.util.Collections;

public class RandomKruskal {

    public static ArrayList<Edge> generateTree(Graph graph) {
        ArrayList<Edge> tree = new ArrayList<>();
        ArrayList<Edge> allEdges = new ArrayList<>(graph.getEdges());
        
        //Mixer les arêtes aléatoirement 
        Collections.shuffle(allEdges);

        UnionFind uf = new UnionFind(graph.order);

        for (Edge e : allEdges) {
            if (!uf.connected(e.source, e.dest)) {
                uf.union(e.source, e.dest);
                tree.add(e);
            }
            //arrêter quand on a |V|-1 arêtes
            if (tree.size() == graph.order - 1) break;
        }

        return tree;
    }
}