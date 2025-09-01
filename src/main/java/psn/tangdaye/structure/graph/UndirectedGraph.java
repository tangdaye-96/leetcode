package psn.tangdaye.structure.graph;


import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author : shayan
 * @date : 2023/4/16 20:50
 */
public class UndirectedGraph<T> implements Graph<T> {
    private Map<T, Set<Edge<T>>> data;

    @Override
    public Iterator<T> iterator() {
        return data.keySet().iterator();
    }
}
