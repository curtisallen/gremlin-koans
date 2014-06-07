package gremlin;

import com.tinkerpop.gremlin.structure.Graph;
import com.tinkerpop.gremlin.structure.io.kryo.KryoReader;
import com.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import gremlin.koan.LoadGraphWith;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(AbstractTest.class);

    protected static Graph graph = null;

    @Rule
    public TestName name = new TestName();

    @Before
    public void setup() {
        Class<?> obj = this.getClass();

        List<Method> methods = Arrays.asList(obj.getDeclaredMethods());

        Optional<Method> method = methods.stream()
                .filter((m) -> m.getName().equals(name.getMethodName()))
                .findFirst();
        if(method.isPresent()){
            Optional<LoadGraphWith> loadGraphWith = Optional.ofNullable(method.get().getAnnotation(LoadGraphWith.class));
            if(loadGraphWith.isPresent()) {
                graph = TinkerGraph.open();
                final InputStream inputStream;
                switch(loadGraphWith.get().value()) {
                    case CLASSIC:
                        log.debug("Loading Classic");
                        inputStream = this.getClass().getResourceAsStream("tinkerpop-classic.gio");
                        writeGraph(graph, inputStream);
                        break;
                    case GRATEFUL:
                        log.debug("Loading Greatful");
                        inputStream = this.getClass().getResourceAsStream("grateful-dead.gio");
                        writeGraph(graph, inputStream);
                        break;
                    case MODERN:
                        log.debug("Loading Modern");
                        inputStream = this.getClass().getResourceAsStream("tinkerpop-modern.gio");
                        writeGraph(graph, inputStream);
                        break;
                    default:
                        throw new RuntimeException("No predefined graph for this type");
                }
            }

        }

    }

    @After
    public void after() {
        graph = null;
    }

    private void writeGraph(final Graph graph, final InputStream in) {
        try {
            KryoReader.create().build().readGraph(in, graph);
        } catch (IOException ex) {
            log.error("Error with streams", ex);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }
}