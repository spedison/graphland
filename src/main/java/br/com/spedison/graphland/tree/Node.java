package br.com.spedison.graphland.tree;

import java.util.Stack;

public class Node<T extends Object> {
    private T data;
    private Node<T> leftNode;
    private Node<T> rightNode;

    public Node(T data, Node<T> leftNode, Node<T> rightNode) {
        this.data = data;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public Node(T data) {
        this.data = data;
        this.leftNode = null;
        this.rightNode = null;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setLeftNode(Node<T> leftNode) {
        this.leftNode = leftNode;
    }

    public void setRightNode(Node<T> rightNode) {
        this.rightNode = rightNode;
    }

    public Node<T> getLeftNode() {
        return leftNode;
    }

    public Node<T> getRightNode() {
        return rightNode;
    }

    public T getData() {
        return data;
    }



    @Override
    public String toString() {
         return "This node is : [%s], with%s node and with%s node"
                 .formatted(
                         data,
                         leftNode == null ? "out left" : " left",
                         rightNode == null ? "out right" : " right"
                 ) ;
    }
}
