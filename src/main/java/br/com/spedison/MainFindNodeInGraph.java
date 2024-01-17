package br.com.spedison;

import br.com.spedison.grafos.searcher.FindNode;
import br.com.spedison.grafos.base.Graph;
import br.com.spedison.grafos.base.PathOfFindNode;
import br.com.spedison.grafos.loadgraphs.LoadSimpleExample;

public class MainFindNodeInGraph {
    public static void main(String[] args) {
        Graph g = (new LoadSimpleExample()).load();
        FindNode fn = new FindNode();
        PathOfFindNode path = new PathOfFindNode();
        boolean ret = fn.findNode(g, g.getStartNode(),
                path,
                node -> node.getName().trim().toLowerCase().equals("anuj"));

        System.out.println("Encontrou = " + ret);
        System.out.println("Caminho encontrado ::: " + path);
    }
}
