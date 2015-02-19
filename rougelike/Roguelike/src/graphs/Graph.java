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
public interface Graph<N> {

    void add(N node);

    void connect(N from, N to, String name, int weight);

    void setConnectionWeight(N from, N to, int weight);

    List<N> getNodes();

    List<Edge> getEdgesFrom(N node);

    Edge getEdgeBetween(N from, N to);

    void disconnect(N from, N to);

    void remove(N node);
}