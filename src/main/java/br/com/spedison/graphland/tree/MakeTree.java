package br.com.spedison.graphland.tree;

import br.com.spedison.graphland.helper.PathsUtils;
import br.com.spedison.logger.ConfigLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Logger;

public class MakeTree<T> {

    private static Logger log = ConfigLogger.getLogger(MakeTree.class);
    private Node<T> rootNode;
    private HashMap<T, Node<T>> itens = new HashMap<>();
    private int numElements;

    public MakeTree(T rootData) {
        this.rootNode = new Node<T>(rootData);
        itens.put(rootData, rootNode);
        numElements = 1;
    }

    public MakeTree() {
        this.rootNode = null;
        numElements = 0;
    }

    public Node<T> getRootNode() {
        return rootNode;
    }

    public boolean addNodesByData(T data, Node<T> leftNode, Node<T> rightNode) {
        Node<T> curItem = this.findNodeByData(data);
        if (curItem == null)
            return false;
        curItem.setRightNode(leftNode);
        curItem.setRightNode(rightNode);
        return true;
    }


    public void setRigthNodeTo(Node<T> nodeInTree, Node<T> rigthNode) {
        if (rootNode == null) {
            rootNode = nodeInTree;
            itens.put(nodeInTree.getData(), rootNode);
            numElements++;
        }

        itens.put(rigthNode.getData(), rigthNode);
        nodeInTree.setRightNode(rigthNode);
        numElements++;
    }

    public void setLeftNodeTo(Node<T> nodeInTree, Node<T> leftNode) {
        if (rootNode == null) {
            rootNode = nodeInTree;
            itens.put(nodeInTree.getData(), rootNode);
            numElements++;
        }
        itens.put(leftNode.getData(), leftNode);
        nodeInTree.setLeftNode(leftNode);
        numElements++;
    }

    public Node<T> findNodeInTreeByData(T data) {
        Stack<Node<T>> pilha = new Stack<>();

        if (rootNode == null)
            return null;

        if (rootNode.getData().equals(data))
            return rootNode;

        if (rootNode.getLeftNode() != null)
            pilha.push(rootNode.getLeftNode());

        if (rootNode.getRightNode() != null)
            pilha.push(rootNode.getRightNode());

        while (!pilha.isEmpty()) {
            Node<T> noAtual = pilha.pop();
            if (noAtual.getData().equals(data))
                return noAtual;
            if (noAtual.getLeftNode() != null)
                pilha.push(noAtual.getLeftNode());
            if (noAtual.getRightNode() != null)
                pilha.push(noAtual.getRightNode());
        }
        return null;
    }

    public Node<T> findNodeByData(T data) {
        return itens.getOrDefault(data, null);
    }

    public Stack<Node<T>> getPath(Node<T> currentNode, T data, Stack<Node<T>> currentPath) {

        if (currentNode == null)
            return null;

        if (currentPath == null)
            currentPath = new Stack<>();

        if (currentNode.getData().equals(data)) {
            currentPath.add(currentNode);
            return currentPath;
        }

        currentPath.push(currentNode);
        if (currentNode.getLeftNode() != null) {
            Stack<Node<T>> ret = getPath(currentNode.getLeftNode(), data, currentPath);
            if (ret != null) {
                return currentPath;
            }
        }

        if (currentNode.getRightNode() != null) {
            Stack<Node<T>> ret = getPath(currentNode.getRightNode(), data, currentPath);
            if (ret != null) {
                return currentPath;
            }
        }

        currentPath.pop();

        return null;
    }

    public int calculateMaxHeigth() {
        return calculateMaxHeigth(getRootNode(), 0);
    }

    public int calculateMinHeigth() {
        int[] ret = {Integer.MAX_VALUE};
        return calculateMinHeigth(getRootNode(), 0, ret);
    }

    private int calculateMinHeigth(Node<T> node, int c, int[] minValue) {

        if (node == null || (node.getRightNode() == null && node.getLeftNode() == null)) {
            minValue[0] = Math.min(minValue[0], c);
            return minValue[0];
        }

        c++;

        return Math.min(
                calculateMinHeigth(node.getLeftNode(), c, minValue),
                calculateMinHeigth(node.getRightNode(), c, minValue)
        );
    }

    private int calculateMaxHeigth(Node<T> node, int c) {

        if (node == null || (node.getLeftNode() == null && node.getRightNode() == null))
            return c;

        c++;

        return Math.max(
                calculateMaxHeigth(node.getLeftNode(), c),
                calculateMaxHeigth(node.getRightNode(), c)
        );
    }

    public List<Integer> getAllHeigth() {
        List<Integer> rets = new LinkedList<>();
        getAllHeigth(getRootNode(), 0, rets);
        return rets;
    }

    private void getAllHeigth(Node<T> node, int pos, List<Integer> heights) {

        if (node == null)
            return;

        if (node.getRightNode() == null && node.getLeftNode() == null) {
            heights.add(pos);
            log.info("Encontrei o valor " + node.getData() + " na altura " + pos);
            return;
        }

        pos++;

        getAllHeigth(node.getLeftNode(), pos, heights);
        getAllHeigth(node.getRightNode(), pos, heights);
    }

    public void processTreeInOrder(Node<T> node, Consumer<Node<T>> executing) {
        if (node.getLeftNode() != null) {
            processTreeInOrder(node.getLeftNode(), executing);
        }
        executing.accept(node);
        if (node.getRightNode() != null) {
            processTreeInOrder(node.getRightNode(), executing);
        }
    }

    public void processTreePosOrder(Node<T> node, Consumer<Node<T>> executing) {
        if (node.getLeftNode() != null) {
            processTreePosOrder(node.getLeftNode(), executing);
        }
        if (node.getRightNode() != null) {
            processTreePosOrder(node.getRightNode(), executing);
        }
        executing.accept(node);
    }


    public void printInOrder() {
        processTreeInOrder(getRootNode(), new Consumer<Node<T>>() {
            @Override
            public void accept(Node<T> node) {
                log.fine("Encontrado o nó : " + node.getData());
            }
        });
    }

    public void printPosOrder() {
        processTreePosOrder(getRootNode(), new Consumer<Node<T>>() {
            @Override
            public void accept(Node<T> node) {
                log.fine("Encontrado o nó : " + node.getData());
            }
        });
    }

    public int getNumElements() {
        return numElements;
    }

    public List<T> getValoresDoNivel(int nivel) {
        List<T> ret = new LinkedList<>();
        getValoresDoNivel(getRootNode(), 0, nivel, ret);
        return ret;
    }

    private void getValoresDoNivel(Node<T> currentNode, int nivelAtual, int nivel, List<T> ret) {
        if (currentNode == null)
            return;

        if (nivel == nivelAtual) {
            ret.add(currentNode.getData());
            return;
        }

        getValoresDoNivel(currentNode.getLeftNode(), nivelAtual + 1, nivel, ret);
        getValoresDoNivel(currentNode.getRightNode(), nivelAtual + 1, nivel, ret);
    }


    public int calcHeightIfFilled() {
        return (int) Math.ceil(Math.log(numElements + 1) / Math.log(2)) - 1;
    }

    public void setRootNode(Node<T> rootNode) {
        this.rootNode = rootNode;
        numElements = 0;
        countNodes(getRootNode());
    }

    public void countNodes(Node<T> node) {
        if (node == null)
            return;
        numElements++;
        countNodes(node.getLeftNode());
        countNodes(node.getRightNode());
    }

    public void generateDotFile(String filename, Function<T, String> convertDataInString, boolean style, boolean createPngFile) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.println("digraph AST {");

            String styleNode = """
                    node [shape=ellipse, style=filled, color=lightgrey, fontname="Courier New"];
                    """;
            String simpleNode = "  node [shape=circle];";

            if (style)
                writer.println(styleNode);
            else
                writer.println(simpleNode);

            if (rootNode != null) {
                writeDotNode(writer, rootNode, convertDataInString);
            }
            writer.println("}");
        } catch (IOException ioe) {
            log.severe("Problemas ao gravar arquivo :: " + filename + " ::: " + ioe.getMessage());
        }

        if (createPngFile) {
            String pngFileName = PathsUtils.changeExtension(filename, "png");
            MakeTree.convertDotFileToPng(filename, pngFileName);
        }
    }

    private void writeDotNode(PrintWriter writer, Node<T> node, Function<T, String> convertDataInString) {
        String nodeId = "n" + System.identityHashCode(node);
        String label = convertDataInString.apply(node.getData()).replace("\"", "'");

        writer.printf("  %s [label=\"%s\"];\n", nodeId, label);

        if (node.getLeftNode() != null) {
            String leftId = "n" + System.identityHashCode(node.getLeftNode());
            writer.printf("  %s -> %s;\n", nodeId, leftId);
            writeDotNode(writer, node.getLeftNode(), convertDataInString);
        }

        if (node.getRightNode() != null) {
            String rightId = "n" + System.identityHashCode(node.getRightNode());
            writer.printf("  %s -> %s;\n", nodeId, rightId);
            writeDotNode(writer, node.getRightNode(), convertDataInString);
        }
    }

    static void convertDotFileToPng(String dotFileName, String pngFileName) {
        String debug = System.getProperty("java.lang.ProcessBuilder.debug");
        log.info("Valor do debug = %s".formatted(debug));

        try {
            ProcessBuilder pb = new ProcessBuilder("/usr/bin/dot", "-Tpng", dotFileName, "-o", pngFileName);
            Process p = pb.start();

            try (BufferedReader stdOut = new BufferedReader(new InputStreamReader(p.getInputStream()));
                 BufferedReader stdErr = new BufferedReader(new InputStreamReader(p.getErrorStream()))) {

                String line;
                while ((line = stdOut.readLine()) != null) {
                    System.out.println("[dot stdout] " + line);
                }
                while ((line = stdErr.readLine()) != null) {
                    System.err.println("[dot stderr] " + line); // Aqui provavelmente virá a mensagem real do erro
                }
            }

            int exitCode = p.waitFor();
            log.info("Imagem gerada em: " + pngFileName + " - Cod. de saída do dot. :: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            if (e.getMessage().contains("debug")) {
                // ignorar debug interno da JDK
            } else {
                throw e; // outros erros devem ser tratados
            }
        }
    }

}