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
 */
public class GraphMethods {

    static private <N> void dfs(Graph<N> g, N where, Set<N> visited) {
        visited.add(where);
        for (Edge<N> e : g.getEdgesFrom(where)) {
            if (!visited.contains(e.getDestination())) {
                dfs(g, e.getDestination(), visited);
            }
        }
    }

    static public <N> boolean pathExists(Graph<N> g, N from, N to) {
        Set<N> visited = new HashSet<>();
        dfs(g, from, visited);
        return visited.contains(to);
    }

    public static <N> List getPath(Graph<N> g, N from, N to) {
        if (!pathExists(g, from, to)) {
            return null;
        }
        HashMap<N, Integer> time = new HashMap<>();
        HashMap<N, Boolean> determined = new HashMap<>();
        HashMap<N, N> origin = new HashMap<>();
        N current = from;

        for (N node : g.getNodes()) {
            time.put(node, Integer.MAX_VALUE);
            determined.put(node, false);
            origin.put(node, null);

        }

        time.put(from, 0);
        determined.put(from, true);
        while (!determined.get(to)) {

            for (Edge<N> e : g.getEdgesFrom(current)) {

                if ((e.getWeight() + time.get(current)) < time.get(e.getDestination())) {

                    Integer newInt = time.get(current) + e.getWeight();
                    time.put(e.getDestination(), newInt);
                    origin.put(e.getDestination(), current);

                }
            }

            for (N node : g.getNodes()) {

                if (!determined.get(node)) {
                    if (determined.get(current)) {
                        current = node;

                    } else {
                        if (time.get(current) > time.get(node)) {
                            current = node;
                        }
                    }

                }

            }
            determined.put(current, true);
        }

        List returnList = new ArrayList<>();
        return recursionRus(g, to, from, origin, returnList);
    }

    private static <N> List recursionRus(Graph<N> g, N current, N from, HashMap<N, N> origin, List returnList) {
        if (!current.equals(from)) {
            N next = origin.get(current);
            recursionRus(g, next, from, origin, returnList);
            returnList.add(g.getEdgeBetween(origin.get(current),current));
            
        }

        return returnList;
    }
}
