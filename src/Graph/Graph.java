package Graph;

import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {
    public int order;
    public int upperBound;
    public int edgeCardinality;
    public ArrayList<Edge> edges;
    ArrayList<LinkedList<Arc>> outIncidency;
    ArrayList<LinkedList<Arc>> inIncidency;

    public Graph(int upperBound) {
        this.upperBound = upperBound;
        this.order = upperBound;
        this.edgeCardinality = 0;
        
        this.outIncidency = new ArrayList<>(upperBound);
        this.inIncidency = new ArrayList<>(upperBound);
        this.edges = new ArrayList<>();

        for (int i = 0; i < upperBound; i++) {
            outIncidency.add(new LinkedList<>());
            inIncidency.add(new LinkedList<>());
        }
    }

    public boolean isVertex(int vertex) {
        return vertex >= 0 && vertex < upperBound;
    }

    public void addVertex(int vertex) {
        if (vertex >= upperBound) {
            // Logique d'agrandissement nécessaire si on dépasse upperBound
        }
    }

    public void deleteVertex(int vertex){
    }

    public void ensureVertex(int vertex) {
        if (!isVertex(vertex)) addVertex(vertex);
    }

    public void addArc(Arc arc) {
        outIncidency.get(arc.getSource()).add(arc);
        inIncidency.get(arc.getDest()).add(arc);
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
        edgeCardinality++;
        
        // Comme c'est un graphe non-orienté, on ajoute les deux directions
        // Arc u -> v
        addArc(new Arc(edge, false));
        // Arc v -> u (renversé)
        addArc(new Arc(edge, true));
    }

    // Retourne les arêtes sortantes pour un sommet donné
    public Arc[] outEdges(int vertex) {
        return outIncidency.get(vertex).toArray(new Arc[0]);
    }
    
    // Accesseur pour toutes les arêtes (utile pour Kruskal)
    public ArrayList<Edge> getEdges() {
        return edges;
    }
}