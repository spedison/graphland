package br.com.spedison.graphland.tree;

import br.com.spedison.logger.ConfigLogger;

import java.util.Comparator;
import java.util.logging.Logger;

public class SortedTree<T> extends MakeTree<T> {

    static final Logger log = ConfigLogger.getLogger(SortedTree.class);

    Comparator<T> comparator;

    public SortedTree(T rootData, Comparator<T> comparator) {
        super(rootData);
        this.comparator = comparator;
    }

    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    private boolean addNewData(T data, Node<T> currentNode){
        int comp = comparator.compare(currentNode.getData(),data);

        if ( comp == 0) {
            log.severe("Elemento já adicionado :: " + data);
            return false; // Já existe.
        }

        if ( comp > 0) {
            if (currentNode.getLeftNode() != null) {
                return addNewData(data, currentNode.getLeftNode());
            } else {
                setLeftNodeTo(currentNode, new Node<>(data));
                return true;
            }
        }else {
            if (currentNode.getRightNode() != null) {
                return addNewData(data, currentNode.getRightNode());
            } else {
                this.setRigthNodeTo(currentNode,new Node<>(data));
                return true;
            }
        }
    }

    public boolean addNewData(T value){
        return addNewData(value, getRootNode());
    }
}
