package br.com.spedison.graphland.loadgraphs;

import br.com.spedison.graphland.base.Graph;

public abstract class ParseLine<T,TG extends Graph> {

    public static final String strReg = "[:][:]";

    private T objectToChange;

    private TG graph;

    public void setGraph(TG graph) {
        this.graph = graph;
    }

    public TG getGraph() {
        return graph;
    }

    public ParseLine(){
        objectToChange = null;
    }

    public void setObjectToChange(T objectToChange) {
        this.objectToChange = objectToChange;
    }

    abstract public boolean isLineParser(String line);

    abstract public T parseLine(String line);

    abstract public String parse(T object);
}
