package br.com.spedison.graphland.tree;

import br.com.spedison.logger.ConfigLogger;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class SortedIntegerTree extends SortedTree<Integer> {

    static final Logger log = ConfigLogger.getLogger(SortedIntegerTree.class);

    public SortedIntegerTree(Integer rootData) {
        super(rootData, Integer::compareTo);
    }

    public static void main(String[] args) {
        SortedIntegerTree a = new SortedIntegerTree(-1);

        log.info(">>>> Inicio da adição de valores na lista <<<<< ");
        Random rand = new Random();
        final boolean [] isNotRoot = {false};
        /*IntStream
                .range(0,15)
                .map(i->rand.nextInt())
                .map(Math::abs)
                .map(i->i%600) ;*/

                Arrays.stream((new Integer[]{500, 250, 750, 125, 300, 745, 800, 100,
                                130, 290, 310, 765, 950, 30, 145}))
                .sequential()
                .peek(i->log.info("Adicionando %d".formatted(i)))
                .peek(i-> {
                    if (!isNotRoot[0]){
                        isNotRoot[0] = true;
                        a.getRootNode().setData(i);
                    }
                } )
                .filter(i->isNotRoot[0])
                .forEach(a::addNewData);
        log.info(">>>> Fim da adição de valores na lista <<<<< ");
        log.info(">>>> Inicio da lista Ordenada <<<<< ");
        a.printInOrder();
        log.info(">>>> Fim da lista Ordenada <<<<< ");
        int maxHeight = a.calculateMaxHeigth();
        int minHeight = a.calculateMinHeigth();
        int heightIfFilled = a.calcHeightIfFilled();
        log.info("Altura mínima da árvore para ordenação eh %d".formatted(minHeight));
        log.info("Altura máxima da árvore para ordenação eh %d".formatted(maxHeight));
        log.info("Altura calculada se preenchida completamente %d".formatted(heightIfFilled));
        log.info("Número de elementos na árvore %d".formatted(a.getNumElements()));

        log.info(">>>>>>> Inicio Lista de alturas <<<<<");
        List<Integer> heights = a.getAllHeigth();
        heights.stream().map("Altura atual :: %d"::formatted).forEach(log::info);
        log.info(">>>>>>> Fim Lista de alturas <<<<<");

        List<Integer> valoresNivel3 = a.getValoresDoNivel(3);
        valoresNivel3.stream().map("Valor encotrado no nível 3 :: %d"::formatted).forEach(log::info);

    }

}
