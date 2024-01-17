package br.com.spedison.grafos.searcher;

import br.com.spedison.grafos.base.Graph;
import br.com.spedison.grafos.base.Node;
import br.com.spedison.grafos.base.PathOfFindNode;

import javax.swing.*;
import java.util.List;
import java.util.function.Function;

public class FindNode {

    /***
     * Find a Node in a Graph using criteria function
     * This method find the first path then to match to a criteria function.
     * @param graph - Graph usage
     * @param currentNode - What is a current Node usage in a Path
     * @param path - Register of Path used as result
     * @param criteria - Criteria used for a find node.
     * @return - return True if found a first result. Otherwise not found nay item node.
     */
    public Boolean findNode(Graph<?, ?> graph, Node<?> currentNode,
                            PathOfFindNode path, Function<Node<?>, Boolean> criteria) {


        /*** It's recursive function and the criterias for Stop are ::
         *  1) Don´t find Edge then Left Node is equal a currentNode.
         *  2) Find the node used criteria function.
         *  3) If current node Alread Checked.
         *
         *  The find result is variablé path.
         *
         */

        // The node alread checked OR
        if (currentNode.getChecked()) {
            System.out.println("Current Path :::" + path.toString() + " in " + currentNode.getName());
            return false;
        }

        // Found item:
        if (criteria.apply(currentNode)) {
            currentNode.setChecked();
            path.queue(currentNode);
            System.out.println("Current Path :::" + path.toString());
            return true;
        }

        // No there are more elements to verify!
        if (currentNode.getNextNodes().size() == 0) {
            currentNode.setChecked();
            System.out.println("Current Path :::" + path.toString() + " in " + currentNode.getName());
            return false;
        }

        path.queue(currentNode);
        currentNode.setChecked();

        final boolean[] findOne = {false};
        currentNode
                .getNextNodes()
                .stream()
                .sequential()
                .filter(n -> !findOne[0])
                .forEach(
                        newCurrentNode -> {
                            boolean ret = findNode(graph, newCurrentNode, path, criteria);
                            if (ret) {
                                findOne[0] = true;
                            }
                        }
                );

        if (findOne[0]) {
            return true;
        } else {
            path.dequeue();
            return false;
        }
    }


    /***
     * Find a Node in a Graph using criteria function
     *
     * @param graph - Graph usage
     * @param currentNode - What is a current Node usage in a Path
     * @param currentPath - Register of Path used as result
     * @param results - Register of Path is positive result
     * @param maxCheckNode - if node check many times... it's can ocasionete loops. If number of verificaction in note is more than maxCheckNode, the verification returns.
     * @param criteria - Criteria used for a find node.
     * @return - None
     */
    public void findNodeInAllPathsPossible(Graph<?, ?> graph, Node<?> currentNode,
                                           PathOfFindNode currentPath, List<PathOfFindNode> results,
                                           short maxCheckNode, Function<Node<?>, Boolean> criteria) {

        currentNode.setChecked();
        System.out.println("Current Path  :::  " + currentPath.toString() + " in " + currentNode.getName());

        /*** It's recursive function and the criterias for Stop are ::
         *  1) Don´t find Edge then Left Node is equal a currentNode.
         *  2) Find the node used criteria function.
         *  3) If current node Alread Checked.
         *
         *  The find result is variable path.
         *
         */

        // The node alread checked OR
        if (currentNode.getCountCheckNode() >= maxCheckNode) {
            return;
        }

        // If current node alread exists in Path, do not process
        // This check prevent unwanted loops.
        if (currentPath.exists(currentNode)){
            return;
        }

        // Found item:
        if (criteria.apply(currentNode)) {
            currentPath.queue(currentNode);
            results.add(currentPath.cloning());
            currentPath.dequeue();
            return;
        }

        // No there are more elements to verify!
        if (currentNode.getNextNodes().isEmpty()) {
            return;
        }

        currentPath.queue(currentNode);
        currentNode
                .getNextNodes()
                .stream()
                .sequential()
                .forEach(
                        newCurrentNode ->
                                findNodeInAllPathsPossible(graph, newCurrentNode,
                                        currentPath, results, maxCheckNode, criteria)
                );
        currentPath.dequeue();
    }
}
