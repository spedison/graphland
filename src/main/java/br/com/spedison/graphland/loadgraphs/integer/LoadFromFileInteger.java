package br.com.spedison.graphland.loadgraphs.integer;

import br.com.spedison.graphland.base.Edge;
import br.com.spedison.graphland.base.Graph;
import br.com.spedison.graphland.loadgraphs.LoaderGraphs;
import br.com.spedison.graphland.loadgraphs.integer.parses.EdgeIntegerParser;
import br.com.spedison.graphland.loadgraphs.integer.parses.GraphIntegerParser;
import br.com.spedison.graphland.loadgraphs.integer.parses.NodeIntegerParser;
import br.com.spedison.logger.ConfigLogger;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.logging.Logger;

public class LoadFromFileInteger implements LoaderGraphs<Integer, Integer> {

    private static final Logger log = ConfigLogger.getLogger(LoadFromFileInteger.class);

    private String fileName;

    public LoadFromFileInteger(String fileName) {
        this.fileName = fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    private boolean ignoreLine(String line) {
        return !Objects.isNull(line) && (line.trim().startsWith("#") || line.isBlank());
    }

    @Override
    public Graph<Integer, Integer> load() {

        Graph<Integer, Integer> retGraph = new Graph<>("");

        EdgeIntegerParser eip = new EdgeIntegerParser();
        eip.setGraph(retGraph);
        NodeIntegerParser nip = new NodeIntegerParser();
        nip.setGraph(retGraph);
        GraphIntegerParser gip = new GraphIntegerParser();
        gip.setGraph(retGraph);

        try {
            RandomAccessFile file = new RandomAccessFile(fileName, "r");
            // Read all Node.The nodes notations must be together and before Edge information block.
            // Start in Begging File.
            file.seek(0);

            String currentLine = null;
            currentLine = skipLinesInFile(file, nip);

            // Read all nodes in sequence ignoring Empty lines or
            // lines stating with '#'
            currentLine = readNodesFromFile(currentLine, nip, file);

            // Read next block of file with Edges.
            readEdgesFromFile(currentLine, eip, file);

            readGraphFromFile(file, gip, retGraph);

            // Close the file.
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return retGraph;
    }

    private void readGraphFromFile(RandomAccessFile file, GraphIntegerParser gip, Graph<Integer, Integer> retGraph) throws IOException {
        String currentLine;
        // Back to start of file ... Again.
        file.seek(0);
        do {
            // Read line.
            currentLine = file.readLine();

            // If line is empty or start with ## So read next line.
            if (ignoreLine(currentLine))
                continue;

            // Parse line filled Graph object.
            if (gip.isLineParser(currentLine))
                gip.parseLine(currentLine);

            // Stop when EOF or Graph data was filled.
            if (!retGraph.getName().isBlank() &&
                    !Objects.isNull(retGraph.getStartNode()))
                break;
        } while (Objects.isNull(currentLine));
    }

    private void readEdgesFromFile(String currentLine, EdgeIntegerParser eip, RandomAccessFile file) throws IOException {
        boolean ignoreLine;
        boolean readEdge = false;
        do {
            ignoreLine = ignoreLine(currentLine);
            readEdge = eip.isLineParser(currentLine);
            if (!ignoreLine && readEdge) {
                Edge<Integer, Integer> readedEdge = eip.parseLine(currentLine);
                log.fine("Read Edge %s".formatted(readedEdge.toString()));
            }
            currentLine = file.readLine();
            // Read all rest of File.
        } while (!Objects.isNull(currentLine));
    }

    private String readNodesFromFile(String currentLine, NodeIntegerParser nip, RandomAccessFile file) throws IOException {
        boolean readNode;
        boolean ignoreLine;
        do {
            ignoreLine = ignoreLine(currentLine);
            readNode = nip.isLineParser(currentLine);

            // If ignore line so read next line.
            if (!ignoreLine) {
                // If not read Node. This line can be Graph or Edge.
                if (readNode)
                    nip.parseLine(currentLine);
                else
                    break; // End of Block with Nodes.
            }
            currentLine = file.readLine();
            // Or line is null... Then Go Next Type.
        } while (!Objects.isNull(currentLine));
        return currentLine;
    }

    private String skipLinesInFile(RandomAccessFile file, NodeIntegerParser nip) throws IOException {
        String currentLine;
        // Skip all lines then is not Node<Integer>
        do {
            currentLine = file.readLine();
            if (Objects.isNull(currentLine))
                break;

            if (ignoreLine(currentLine))
                continue;

            if (nip.isLineParser(currentLine))
                break;
        } while (!Objects.isNull(currentLine));
        return currentLine;
    }

    @Override
    public void save(Graph<Integer, Integer> tg) {
        EdgeIntegerParser eip = new EdgeIntegerParser();
        eip.setGraph(tg);
        NodeIntegerParser nip = new NodeIntegerParser();
        nip.setGraph(tg);
        GraphIntegerParser gip = new GraphIntegerParser();
        gip.setGraph(tg);

        try (RandomAccessFile file = new RandomAccessFile(getFileName(), "rw")) {
            file.write(
                    gip.parse(tg).getBytes(StandardCharsets.UTF_8)
            );
            file.write("\n\n".getBytes(StandardCharsets.UTF_8));

            tg
                    .getListNodes()
                    .stream()
                    .map(nip::parse)
                    .map("%s\n"::formatted)
                    .forEach(s -> {
                        try {
                            file.write(s.getBytes(StandardCharsets.UTF_8));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

            file.write("\n\n".getBytes(StandardCharsets.UTF_8));

            tg
                    .getListEdges()
                    .stream()
                    .map(eip::parse)
                    .map("%s\n"::formatted)
                    .forEach(s -> {
                        try {
                            file.write(s.getBytes(StandardCharsets.UTF_8));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
