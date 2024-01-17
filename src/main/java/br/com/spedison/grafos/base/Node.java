package br.com.spedison.grafos.base;

import java.util.LinkedList;
import java.util.List;

public class Node<T> {
    private T content;
    private String name;
    private short countCheckNode;
    private Boolean isChecked;
    List<Node> nextNodes;

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
        countCheckNode ++;
    }

    public short getCountCheckNode() {
        return countCheckNode;
    }

    public List<Node> getNextNodes() {
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

    void addNextNode(Node<?> nextNode) {
        nextNodes.add(nextNode);
    }

    @Override
    public String toString() {
        return "Node{" +
                "conteudo=" + content +
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
        return name.hashCode();
    }
}