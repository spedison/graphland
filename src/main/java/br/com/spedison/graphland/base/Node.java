package br.com.spedison.graphland.base;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/***
 * Define node of graph with a content defined by T
 * @param <T>
 */
public class Node<T extends Object> {
    private T content;
    private String name;
    private short countCheckNode;
    private Boolean isChecked;
    List<Node<T>> nextNodes;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void clearChecked() {
        isChecked = false;
        countCheckNode = 0;
    }

    public void setChecked() {
        isChecked = true;
        countCheckNode++;
    }

    public short getCountCheckNode() {
        return countCheckNode;
    }

    public List<Node<T>> getNextNodes() {
        return nextNodes;
    }

    public T getContent() {
        return content;
    }

    public Node(T content, String name) {
        this.content = content;
        this.name = name;
        isChecked = false;
        nextNodes = new LinkedList<>();
    }

    void addNextNode(Node<T> nextNode) {
        nextNodes.add(nextNode);
    }

    @Override
    public String toString() {
        return "Node{" +
                "content=" + content +
                ", name='" + name + '\'' +
                ", isChecked=" + isChecked +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ((o instanceof Node<?> node)) return node.name.equals(name);
        if ((o instanceof String nameNode)) return nameNode.equals(name);
        return false;
    }

    @Override
    public int hashCode() {
        if (content == null)
            return Objects.hash(name);
        return Objects.hash(name,content);
    }
}