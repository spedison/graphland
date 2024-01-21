package br.com.spedison.graphland.loadgraphs.integer;

import br.com.spedison.graphland.base.Graph;
import br.com.spedison.graphland.helper.ConverterValues;
import br.com.spedison.graphland.helper.ManipuleBytes;
import br.com.spedison.graphland.loadgraphs.LoadModelDijkstrasExample;
import br.com.spedison.logger.ConfigLogger;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class LoadFromFileIntegerTest {

    Logger log = ConfigLogger.getLogger(LoadFromFileIntegerTest.class);

    private Graph<Integer, Integer> gh;
    private LoadFromFileInteger lf;

    @BeforeEach
    public void beforeEach() {
        gh = (new LoadModelDijkstrasExample()).load();
        gh.setName("Graph used for Unit test");
        lf = new LoadFromFileInteger("temp.txt");
        lf.save(gh);
    }

    @AfterEach
    public void afterEach() {
        File f = new File("temp.txt");
        f.delete();
    }

    @Test
    void setFileName() {
        lf.setFileName("temp2.tmp");
        assertEquals("temp2.tmp", lf.getFileName());
    }

    @Test
    void getFileName() {
        assertEquals("temp.txt", lf.getFileName());
    }

    @Test
    void load() {
        LoadFromFileInteger lfi2 = new LoadFromFileInteger("temp.txt");
        Graph<Integer, Integer> ghi2 = lfi2.load();
        assertEquals(gh.getListNodes().size(), ghi2.getListNodes().size());
        gh.getListNodes().stream().forEach(n1 -> {
            var comp = ghi2.findNodeByName(n1.getName());
            assertEquals(comp, n1);
        });

        assertEquals(gh.getListEdges().size(), ghi2.getListEdges().size());
        gh.getListEdges().stream().forEach(ed1 -> {
            var comp = ghi2.findEdgeByName(ed1.getName());
            assertEquals(comp, ed1);
        });
    }

    @Test
    void save() {
        File f = new File("temp.txt");
        assertTrue(f.exists() && f.isFile());
        try {
            // The first line of file contains Date/Time. It's so bad to calculate MD5 Hash.
            // The solution for it is remove first line (it's comment) and calcultate
            // MD5 hash and compate to hard code string value "f4dec...f28"
            byte [] allBytes = Files.readAllBytes(Path.of("temp.txt"));
            byte[] allBytesSkipedOneLine = ManipuleBytes.skipLines(allBytes,1);
            byte [] hash = MessageDigest.getInstance("MD5").digest(allBytesSkipedOneLine);
            String hashAsString = ConverterValues.bytesToHexString(hash);
            log.fine("MD5 file is :: "+  hashAsString );
            assertEquals( "f4dec156525a72d35362ee8d53d65f28", hashAsString);
        } catch (IOException e) {
            log.severe(e.getMessage());
            fail("Dont read file temp.txt");
        } catch (NoSuchAlgorithmException e) {
            log.severe(e.getMessage());
            fail("Dont load algothim MD5 of file.");
        }
    }
}