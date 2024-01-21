package br.com.spedison.graphland.dijkstra.parses;

import br.com.spedison.graphland.base.Edge;
import br.com.spedison.graphland.base.Graph;
import br.com.spedison.graphland.base.Node;
import br.com.spedison.graphland.base.Orientation;
import br.com.spedison.graphland.dijkstra.base.DijkstraContent;
import br.com.spedison.graphland.helper.ConverterValues;
import br.com.spedison.graphland.loadgraphs.ParseLine;

import java.util.Objects;

public class EdgeDijkstraParser extends ParseLine<Edge<Integer, DijkstraContent>, Graph<DijkstraContent,Integer>> {

    @Override
    public boolean isLineParser(String line) {
        if (Objects.isNull(line))
            return false;
        boolean start = line.toLowerCase().trim().startsWith("edge::");
        boolean numParts = line.split(ParseLine.strReg).length == 6;
        return start && numParts;
    }


    @Override
    public Edge<Integer, DijkstraContent> parseLine(String line) {
        String[] data = line.split(ParseLine.strReg);
        String name = data[1];
        Integer value = ConverterValues.stringToInteger(data[2]);
        Node<DijkstraContent, Integer> start = getGraph().findNodeByName(data[3].trim());
        Node<DijkstraContent, Integer> end = getGraph().findNodeByName(data[4].trim());
        Orientation orientation = ConverterValues.stringToOrientation(data[5]);
        Edge<Integer, DijkstraContent> ret = new Edge<>(value, name, orientation, start, end);
        getGraph().addEdge(ret);
        return ret;
    }

    @Override
    public String parse(Edge<Integer, DijkstraContent> object) {
        return "Edge::%s::%d::%s::%s::%s".formatted(
                object.getName(),
                object.getContent(),
                object.getNodeStart().getName(),
                object.getNodeEnd().getName(),
                object.getOrientation().equals(Orientation.FULL_CONNECTED) ?
                        "FULL" : "SIMPLE"
        );
    }
}