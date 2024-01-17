package br.com.spedison.grafos.base;

import java.util.LinkedList;
import java.util.List;

public class PathOfFindNode implements Cloneable, Cloning<PathOfFindNode> {

    private List<Node<?>> listOfNodes = new LinkedList<>();

    @Override
    public Object clone() throws CloneNotSupportedException {
        PathOfFindNode ret = new PathOfFindNode();
        ret.listOfNodes.addAll(this.listOfNodes);
        return ret;
    }

    public void queue(Node<?> node) {
        listOfNodes.add(node);
    }

    public Node<?> dequeue() {

        if (listOfNodes.size() == 0)
            return null;

        int last = listOfNodes.size() - 1;
        Node<?> ret = listOfNodes.get(last); // get last
        listOfNodes.remove(last);
        return ret;
    }

    public void copy(PathOfFindNode path) {
        listOfNodes.clear();
        listOfNodes.addAll(path.listOfNodes);
    }

    public boolean exists(Node<?> node) {
        return listOfNodes.indexOf(node) != -1;
    }

    boolean exists(String nameNode) {
        return listOfNodes.indexOf(nameNode) != -1;
    }

    @Override
    public String toString() {
        if (listOfNodes.isEmpty())
            return "Path is Empty";

        StringBuffer sb = new StringBuffer();
        sb.append("Path used in find ::: ");
        sb.append("[[ " + listOfNodes.get(0).getName() + " ");
        listOfNodes.stream().skip(1).forEach(node -> sb.append(" -> " + node.getName()));
        sb.append(" ]]");
        return sb.toString();
    }

    @Override
    public PathOfFindNode cloning() {
        try {
            return (PathOfFindNode) this.clone();
        } catch (CloneNotSupportedException cnse) {
            return null;
        }
    }
}
