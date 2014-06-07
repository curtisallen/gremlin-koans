package gremlin;

import static org.junit.Assert.*;

import gremlin.util.Throwables;
import org.junit.Before;
import org.junit.Test;
import gremlin.koan.LoadGraphWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class FirstTest extends AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(FirstTest.class);

    @Before
    public void before() {

    }
    @Test
    @LoadGraphWith(LoadGraphWith.GraphData.CLASSIC)
    public void test() {
        assertTrue(true);
        log.info("Graph " + graph);
    }

    @Test
    @LoadGraphWith(LoadGraphWith.GraphData.GRATEFUL)
    public void second() {
        assertTrue(true);
        log.info("Graph " + graph);
    }

    @Test
    @LoadGraphWith(LoadGraphWith.GraphData.MODERN)
    public void third() {
        assertTrue(true);
        log.info("Graph " + graph);
    }

    @Test
    public void forth() {
        assertTrue(true);
        log.info("Null Graph " + graph);
    }
}