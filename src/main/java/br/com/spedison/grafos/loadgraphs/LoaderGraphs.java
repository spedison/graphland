package br.com.spedison.grafos.loadgraphs;

import br.com.spedison.grafos.base.Graph;

public interface LoaderGraphs {
    Graph load();
    void save();
}
