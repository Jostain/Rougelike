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
 * @param <N>
 */
public class MatrixGraph<N> implements Graph<N> {

    private HashMap<N, Integer> key = new HashMap<>();
    private Edge<N>[][] nodes = (Edge<N>[][]) new Edge[0][0];
    private N lastNode;

    public MatrixGraph() {

    }

    @Override
     public void add(N node) {
        if (!key.containsKey(node)) {
            key.put(node, key.size());
            resize();
            lastNode = node;
        }
        
    }

    @Override
    public void connect(N from, N to, String name, int weight) {
        if (!key.containsKey(from) || !key.containsKey(to)) {
            throw new NoSuchElementException();
        }
        if (weight < 0) {
            throw new IllegalArgumentException();
        }

        nodes[key.get(from)][key.get(to)] = new Edge<>(to, name, weight);
        nodes[key.get(to)][key.get(from)] = new Edge<>(from, name, weight);
    }

    @Override
    public void setConnectionWeight(N from, N to, int weight) {
        if (!key.containsKey(from) || !key.containsKey(to)) {
            throw new NoSuchElementException();
        }
        if (weight < 0) {
            throw new IllegalArgumentException();
        }
        nodes[key.get(from)][key.get(to)].setWeight(weight);
        nodes[key.get(to)][key.get(from)].setWeight(weight);
    }

    @Override
    public List<N> getNodes() {
        return new ArrayList<>(key.keySet());
    }

    @Override
    public List<Edge> getEdgesFrom(N node) {
        List<Edge> returnList = new ArrayList<>();
        if (key.containsKey(node)) {

            for (int y = 0; y < nodes.length; y++) {
                if (nodes[key.get(node)][y] != null) {
                    returnList.add(nodes[key.get(node)][y]);
                }
            }

        } else {
            throw new NoSuchElementException();
        }

        return returnList;
    }

    @Override
    public Edge getEdgeBetween(N from, N to) {
        if (!key.containsKey(from) || !key.containsKey(to)) {
            throw new NoSuchElementException();
        }
        return nodes[key.get(from)][key.get(to)];
    }

    @Override
    public void disconnect(N from, N to) {
        if (!key.containsKey(from) || !key.containsKey(to)) {
            throw new NoSuchElementException();
        }

        nodes[key.get(from)][key.get(to)] = null;
        nodes[key.get(to)][key.get(from)] = null;
    }

    @Override
    public void remove(N node) {
        for (int x = 0; x < nodes.length; x++) {

            nodes[key.get(node)][x] = nodes[key.get(lastNode)][x];
            nodes[x][key.get(node)] = nodes[x][key.get(lastNode)];

        }

        key.put(lastNode, key.get(node));

        key.remove(node);
        resize();
    }

    private void resize() {
        int i = nodes.length;
        if (nodes.length > key.size()) {
            i = key.size();
        }
        Edge<N>[][] newNodes = (Edge<N>[][]) new Edge[key.size()][key.size()];
        for (int x = 0; x < i; x++) {
            for (int y = 0; y < i; y++) {
                newNodes[x][y] = nodes[x][y];
            }
        }
        nodes = newNodes;

    }

    @Override
    public String toString() {
        String string = "";
        for (N temp : getNodes()) {
            for (Edge e : getEdgesFrom(temp)) {
                string = string + temp + " connects to " + e.toString() + "" + "\n";
            }
        }
        return string;
    }

}
