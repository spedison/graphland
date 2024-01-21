package br.com.spedison.graphland.dijkstra.parses;

import br.com.spedison.graphland.base.Graph;
import br.com.spedison.graphland.base.Node;
import br.com.spedison.graphland.dijkstra.base.DijkstraContent;
import br.com.spedison.graphland.loadgraphs.ParseLine;

import java.util.Objects;

public class NodeDijkstraParser extends ParseLine<Node<DijkstraContent,Integer>, Graph<DijkstraContent,Integer>> {

    @Override
    public boolean isLineParser(String line) {
        if (Objects.isNull(line))
            return false;
        boolean start = line.toLowerCase().trim().startsWith("node::");
        boolean numParts = line.split(ParseLine.strReg).length == 3;
        return start && numParts;
    }

    @Override
    public Node<DijkstraContent,Integer> parseLine(String line) {
        String []  data = line.split(ParseLine.strReg);
        DijkstraContent value = DijkstraContent.fromString(data[2]);
        String name = data[1];
        Node<DijkstraContent,Integer> retNode = new Node<>(value,name);
        getGraph().addNode(retNode);
        return retNode;
    }

    @Override
    public String parse(Node<DijkstraContent, Integer> object) {
        return "Node::%s::%s".formatted(
                object.getName(),
                object.getContent().toString());
    }
}