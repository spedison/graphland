package br.com.spedison.grafos.loadgraphs;

import br.com.spedison.grafos.base.Graph;

public class LoadFromFile implements LoaderGraphs{
    private String nomeArquivo;
    public LoadFromFile(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    @Override
    public Graph load() {
        return null;
    }

    @Override
    public void save() {

    }
}
