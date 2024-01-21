package br.com.spedison.graphland.loadgraphs.integer.parses;

import br.com.spedison.graphland.base.Edge;
import br.com.spedison.graphland.base.Graph;
import br.com.spedison.graphland.base.Node;
import br.com.spedison.graphland.base.Orientation;
import br.com.spedison.graphland.loadgraphs.ParseLine;
import br.com.spedison.graphland.helper.ConverterValues;

import java.util.Objects;

public class EdgeIntegerParser extends ParseLine<Edge<Integer, Integer>, Graph<Integer, Integer>> {

    @Override
    public boolean isLineParser(String line) {
        if (Objects.isNull(line))
            return false;
        boolean start = line.toLowerCase().trim().startsWith("edge::");
        boolean numParts = line.split(ParseLine.strReg).length == 6;
        return start && numParts;
    }

    private void addEdge(Edge<Integer, Integer> edge) {
        if (!Objects.isNull(getGraph()))
            getGraph().addEdge(edge);
    }

    @Override
    public Edge<Integer, Integer> parseLine(String line) {
        String[] data = line.split(ParseLine.strReg);
        String name = data[1];
        Integer value = ConverterValues.stringToInteger(data[2]);
        Node<Integer, Integer> left = getGraph().findNodeByName(data[3].trim());
        Node<Integer, Integer> rigth = getGraph().findNodeByName(data[4].trim());
        Orientation orientation = ConverterValues.stringToOrientation(data[5]);
        Edge<Integer, Integer> ret = new Edge<Integer, Integer>(value, name, orientation, left, rigth);
        addEdge(ret);
        return ret;
    }

    @Override
    public String parse(Edge<Integer, Integer> object) {
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