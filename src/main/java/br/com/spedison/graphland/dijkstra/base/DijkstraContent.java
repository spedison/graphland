package br.com.spedison.graphland.dijkstra.base;

import br.com.spedison.graphland.base.Graph;
import br.com.spedison.graphland.base.Node;
import br.com.spedison.graphland.helper.ConverterValues;

public class DijkstraContent {
    private Node<DijkstraContent,Integer> previusNode;
    private String previusNodeName;
    private Integer distancePath;

    public DijkstraContent() {
        distancePath = Integer.MAX_VALUE;
        this.previusNode = null;
        this.previusNodeName = null;
    }

    public Integer getDistancePath() {
        return distancePath;
    }

    public Node<DijkstraContent,Integer> getPreviusNode() {
        return previusNode;
    }

    public String getPreviusNodeName() {
        return previusNodeName;
    }

    public void setPreviusNode(Node<DijkstraContent,Integer> previusNode) {
        this.previusNode = previusNode;
    }

    public void setPreviusNodeName(String previusNodeName) {
        this.previusNodeName = previusNodeName;
    }

    public void setDistancePath(Integer distancePath) {
        this.distancePath = distancePath;
    }

    static public DijkstraContent fromString(String value){
        DijkstraContent ret = new DijkstraContent();
        String tmpString = value.trim();
        int lenString = tmpString.length();
        String [] splited = tmpString.substring(1,lenString-1).split("[,]");
        ret.setPreviusNodeName(splited[1]);
        ret.setDistancePath(ConverterValues.stringToInteger(splited[0]));
        return ret ;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DijkstraContent{");
        sb.append("previusNode='").append(previusNodeName).append('\'');
        sb.append(", sumMinPath=").append(distancePath);
        sb.append('}');
        return sb.toString();
    }
}
