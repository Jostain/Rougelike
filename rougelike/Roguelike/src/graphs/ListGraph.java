
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import java.util.*;

/**
 *
 * @author Erik
 * @param <N> är typen som ListGraphen skall hantera
 *
 */
public class ListGraph<N> implements Graph<N> {

    private HashMap<N, ArrayList<Edge<N>>> nodes = new HashMap<>();

    public ListGraph() {

    }

    @Override
    public void add(N node) {
        if(!nodes.containsKey(node))
        {
            nodes.put(node, new ArrayList<>());
        }
    }

    @Override
    public void connect(N from, N to, String name, int weight) {
          try {
            getEdgeBetween(from,to);
            throw new IllegalStateException();
        } catch (NoSuchElementException e) {
            if (!nodes.containsKey(from) || !nodes.containsKey(to)) {
            throw new NoSuchElementException();
        }
        if (weight < 0) {
            throw new IllegalArgumentException();
        }
        }

        Edge toEdge = new Edge<>(to, name, weight);
        nodes.get(from).add(toEdge);

        Edge fromEdge = new Edge<>(from, name, weight);
        nodes.get(to).add(fromEdge);

    }

    @Override
    public void setConnectionWeight(N from, N to, int weight) {
        if (!nodes.containsKey(from) || !nodes.containsKey(to)) {
            throw new NoSuchElementException();
        }
        if (weight < 0) {
            throw new IllegalArgumentException();
        }
        boolean success = false;
        ArrayList<Edge<N>> tempFrom = nodes.get(from);
        for (Edge fromEdge : tempFrom) {
            if (fromEdge.getDestination().equals(to)) {
                fromEdge.setWeight(weight);
                success = true;

            }

        }
        ArrayList<Edge<N>> tempTo = nodes.get(to);
        for (Edge toEdge : tempTo) {
            if (toEdge.getDestination().equals(from)) {
                toEdge.setWeight(weight);
            }

        }
        if (!success) {
            throw new NoSuchElementException();
        }

    }

    @Override
    public List<N> getNodes() {
        return new ArrayList<>(nodes.keySet());
    }

    @Override
     public List<Edge> getEdgesFrom(N node) {
        if (nodes.containsKey(node)) {
            return new ArrayList<>(nodes.get(node));
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public Edge getEdgeBetween(N from, N to) {
        Edge tempEdge = null;
        for (Edge temp1 : nodes.get(from)) {
            if (temp1.getDestination().equals(to)) {
                tempEdge = temp1;

            }
        }
        if (tempEdge == null) {
            throw new NoSuchElementException();
        } else {
            return tempEdge;
        }
    }

    @Override
    public void disconnect(N from, N to) {
        if (nodes.containsKey(from) && nodes.containsKey(to)) {
            ArrayList<Edge<N>> tempFrom = nodes.get(from);
            for (Edge fromEdge : tempFrom) {
                if (fromEdge.getDestination().equals(to)) {
                    nodes.get(from).remove(fromEdge);
                    break;
                }

            }
            ArrayList<Edge<N>> tempTo = nodes.get(to);
            for (Edge toEdge : tempTo) {
                if (toEdge.getDestination().equals(from)) {
                    nodes.get(to).remove(toEdge);
                    break;
                }

            }

        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void remove(N node) {
        for (N nodeEdge : getNodes()) {

            disconnect(node, nodeEdge);

        }
        nodes.remove(node);

    }

    @Override
    public String toString() {
        String string = "";
        for (N temp : getNodes()) {
            string = string + temp.toString() + "\n";
            for (Edge temp2 : nodes.get(temp)) {
                string = string + temp.toString() + " connects to " + temp2.toString() + "" + "\n";
            }
        }
        return string;
    }
}
