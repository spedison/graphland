package br.com.spedison;

import br.com.spedison.grafos.base.Graph;
import br.com.spedison.grafos.loadgraphs.LoadSimpleExample;

public class MainLoadSimpleGraph {

    public static void main(String[] args) {
        LoadSimpleExample lse = new LoadSimpleExample();
        Graph<Void,Void> graph = lse.load();
        System.out.println(graph.toString());
    }

}
