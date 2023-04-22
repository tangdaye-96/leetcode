package psn.tangdaye.structure.graph;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author : shayan
 * @date : 2023/4/16 20:50
 */
public class UndirectedGraph<T> implements Graph<T> {
    private Map<T, Set<Edge<T>>> data;

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return data.keySet().iterator();
    }
}
