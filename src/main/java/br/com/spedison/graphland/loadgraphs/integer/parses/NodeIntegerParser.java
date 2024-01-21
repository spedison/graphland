package br.com.spedison.graphland.loadgraphs.integer.parses;

import br.com.spedison.graphland.base.Graph;
import br.com.spedison.graphland.base.Node;
import br.com.spedison.graphland.dijkstra.base.DijkstraContent;
import br.com.spedison.graphland.loadgraphs.ParseLine;
import br.com.spedison.graphland.helper.ConverterValues;

import java.util.Objects;

public class NodeIntegerParser extends ParseLine<Node<Integer, Integer>, Graph<Integer, Integer>> {

    @Override
    public boolean isLineParser(String line) {
        if (Objects.isNull(line))
            return false;
        boolean start = line.toLowerCase().trim().startsWith("node::");
        boolean numParts = line.split(ParseLine.strReg).length == 3;
        return start && numParts;
    }

    @Override
    public Node<Integer, Integer> parseLine(String line) {
        String[] data = line.split(ParseLine.strReg);
        Integer value = ConverterValues.stringToInteger(data[2]);
        String name = data[1];
        Node<Integer, Integer> retNode = new Node<>(value, name);
        getGraph().addNode(retNode);
        return retNode;
    }

    @Override
    public String parse(Node<Integer, Integer> object) {
        return "Node::%s::%s".formatted(
                object.getName(),
                ConverterValues.integerToString(object.getContent())
        );
    }
}