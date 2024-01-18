package br.com.spedison.graphland.loadgraphs;

import br.com.spedison.graphland.base.Graph;

public interface LoaderGraphs<TN,TE> {
    Graph<TN,TE> load();
    void save();
}
