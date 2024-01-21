package br.com.spedison.graphland.base;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/***
 * Define node of graph with a content defined by T
 * @param <TN>
 */
public class Node<TN extends Object, TE extends Object> {
    private TN content;
    private String name;
    private short countVisitedNode;
    private Boolean isVisited;
    List<Edge<TE,TN>> listEdges;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Boolean getChecked() {
        return isVisited;
    }

    public void clearChecked() {
        isVisited = false;
        countVisitedNode = 0;
    }

    public void setVisited() {
        isVisited = true;
        countVisitedNode++;
    }

    public Boolean isVisited(){
        return isVisited;
    }

    public short getCountVisitedNode() {
        return countVisitedNode;
    }


    public TN getContent() {
        return content;
    }

    public Node(TN content, String name) {
        this.content = content;
        this.name = name;
        isVisited = false;
        listEdges = new LinkedList<>();
    }

    void addEdge(Edge<TE,TN> nextEdge) {
        listEdges.add(nextEdge);
    }

    public List<Edge<TE, TN>> getListEdges() {
        return listEdges;
    }

    @Override
    public String toString() {
        return "Node{" +
                " content = " + content +
                ", name= '" + name + '\'' +
                ", isVisited = " + isVisited +
                ", numberOfEdges = " + listEdges.size() +
                " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Node otherNode) {
            return otherNode.getName().trim().equalsIgnoreCase(this.getName().trim());
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (content == null)
            return Objects.hashCode(name);
        return Objects.hash(name, content);
    }
}