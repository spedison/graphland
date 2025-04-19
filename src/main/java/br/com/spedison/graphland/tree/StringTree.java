package br.com.spedison.graphland.tree;

import br.com.spedison.logger.ConfigLogger;

import java.io.*;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTree extends MakeTree<String> {

    static Logger log = ConfigLogger.getLogger(StringTree.class);

    // Regex com suporte a acentos e números
    private final Pattern padraoLeLinha = Pattern.compile("([\\p{L}\\p{N}]+)[ ]*->[ ]*([\\p{L}\\p{N}]+|[-])[ ]*;[ ]*([\\p{L}\\p{N}]+|[-])");


    public StringTree() {
        this("noSet");
    }

    public StringTree(String rootData) {
        super(rootData);
    }

    public void loadFromFile(String fileName) {
        boolean hasRoot = false;

        try (
                FileReader fis = new FileReader(new File(fileName));
                BufferedReader br = new BufferedReader(fis);
        ) {

            String line;

            while ((line = br.readLine()) != null) {

                if(line.trim().startsWith("#")){
                    log.warning("Linha será ignorada. Comentário :: " + line);
                    continue;
                }

                Matcher matcher = padraoLeLinha.matcher(line);
                String dadoNo;
                String dadoNoEsquerdo;
                String dadoNoDireito;

                if (matcher.find()) {

                    dadoNo = matcher.group(1);
                    dadoNoEsquerdo = matcher.group(2);
                    dadoNoDireito = matcher.group(3);

                    Node<String> noAtual;
                    if (!hasRoot) {
                        this.getRootNode().setData(dadoNo);
                        hasRoot = true;
                        noAtual = this.getRootNode();
                    } else {
                        noAtual = findNodeByData(dadoNo);
                    }

                    if (noAtual == null) {
                        log.severe("Nó " + dadoNo + " não encontrado.");
                        continue;
                    } else {
                        if (!dadoNoEsquerdo.trim().equals("-")) {
                            this.setLeftNodeTo(noAtual, new Node<String>(dadoNoEsquerdo));
                        }
                        if (!dadoNoDireito.trim().equals("-")) {
                            this.setRigthNodeTo(noAtual, new Node<String>(dadoNoDireito));
                        }
                    }
                } else {
                    log.warning("Linha não está no padrão. Continuando o processamento do arquivo :: line = [%s]".formatted(line));
                }

            }
        } catch (IOException ioe) {
            log.severe("Problemas ao ler arquivo " + ioe.getMessage());
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) {
        StringTree a = new StringTree();
        a.loadFromFile("./schemas/trees/NameTree2.txt");
        a.printInOrder();
        List<Node<String>> ret;
        ret = a.getPath(a.getRootNode(), "10", null);
        if (ret != null) {
            for (Node<String> p : ret) {
                log.fine(p.getData());
            }
        } else {
            log.severe("Nó não encontrado!!");
        }

        int heigth = a.calculateMaxHeigth();
        log.info("Altura da árvore é %d".formatted(heigth));
    }

}
