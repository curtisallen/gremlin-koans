package gremlin.koan;

import java.lang.annotation.*;

/**
 * Annotation to that loads graph with data. This annotation is for use only with (@link AbstractTest)
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LoadGraphWith {
    public enum GraphData {
        CLASSIC,
        GRATEFUL,
        MODERN;


        public String location() {
            switch (this) {
                case CLASSIC:
                    return RESOURCE_PATH_PREFIX + "tinkerpop-classic.gio";
                case GRATEFUL:
                    return RESOURCE_PATH_PREFIX + "grateful-dead.gio";
                case MODERN:
                    return RESOURCE_PATH_PREFIX + "tinkerpop-modern.gio";
            }

            throw new RuntimeException("No file for this GraphData type");
        }
    }

    public static final String RESOURCE_PATH_PREFIX = "/com/tinkerpop/gremlin/structure/util/io/kryo/";

    /**
     * The name of the resource to load with full path.
     */
    public GraphData value() default GraphData.CLASSIC;
}
