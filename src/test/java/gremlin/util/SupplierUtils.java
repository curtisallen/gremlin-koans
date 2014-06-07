package gremlin.util;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

/**
 * Created by curtis on 6/7/14.
 */
public class SupplierUtils {
    public static <T> Supplier<T> wrap(Callable<T> callable) {
        return () -> {
            try {
                return callable.call();
            } catch(RuntimeException e) {
                throw e;
            } catch(Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}
