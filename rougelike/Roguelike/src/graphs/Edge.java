/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

/**
 *
 * @author Erik
 * @param <D>
 */
public class Edge<D> {

    private final D destination;
    private String name;
    private int weight;

    protected Edge(D destination, String name, int weight) {
        this.destination = destination;
        this.weight = weight;
        this.name = name;
    }

   
    public D getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return getName() + " to " + getDestination() + ", " + getWeight();
    }

   
    public String getName() {
        return name;
    }

   
    public void setName(String name) {
        this.name = name;
    }

   
    public int getWeight() {
        return weight;
    }

   
    public void setWeight(int weight) {
        this.weight = weight;
    }

}
