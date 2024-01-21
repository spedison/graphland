package br.com.spedison.graphland.dijkstra;

import br.com.spedison.graphland.base.Edge;
import br.com.spedison.graphland.base.Graph;
import br.com.spedison.graphland.base.Node;
import br.com.spedison.graphland.base.PathOfFindNode;
import br.com.spedison.graphland.dijkstra.base.DijkstraContent;
import br.com.spedison.logger.ConfigLogger;

import java.util.*;
import java.util.logging.Logger;

public class DijkstraMinimization {

    private static Logger log = ConfigLogger.getLogger(DijkstraMinimization.class);

    private Graph<DijkstraContent, Integer> graph;

    public void findNodeInAllPathsPossible() {

        graph.getStartNode().getContent().setDistancePath(0);

        PriorityQueue<Node<DijkstraContent, Integer>> priorityQueue = new
                PriorityQueue<>(
                (o1, o2) ->
                        o1.getContent().getDistancePath().compareTo(o2.getContent().getDistancePath())
        );

        priorityQueue.addAll((Collection<? extends Node<DijkstraContent, Integer>>) graph.getListNodes());

        while (!priorityQueue.isEmpty()) {
            Node<DijkstraContent, Integer> current = priorityQueue.poll();
            current.setVisited();
            for (Edge<Integer, DijkstraContent> edge : current.getListEdges()) {
                Node<DijkstraContent, Integer> neighbor = edge.getNodeEnd();
                if (!neighbor.isVisited()) {
                    int newDistance = current.getContent().getDistancePath() + edge.getContent();
                    if (newDistance < neighbor.getContent().getDistancePath()) {
                        log.fine("Removed node %s with result = %b".formatted(neighbor.toString(), priorityQueue.remove(neighbor)));
                        neighbor.getContent().setDistancePath(newDistance);
                        neighbor.getContent().setPreviusNode(current);
                        neighbor.getContent().setPreviusNodeName(current.getName());
                        log.fine("Added node %s with result = %b".formatted(neighbor.toString(), priorityQueue.add(neighbor)));
                        //priorityQueue.add(neighbor);
                    }
                }
            }
        }
    }


    public PathOfFindNode processAlg(Graph<DijkstraContent, Integer> graph, String nodeName) {

        this.graph = graph;
        findNodeInAllPathsPossible();
        PathOfFindNode path = new PathOfFindNode();
        Node<DijkstraContent, Integer> currentNode = graph.findNodeByName(nodeName);
        do{
            path.getList().add(currentNode);
            currentNode = currentNode.getContent().getPreviusNode();
        }while (!Objects.isNull(currentNode));
        path.reverse();
        return path;
    }
}
