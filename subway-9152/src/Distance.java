import java.util.HashMap;
import java.util.Map;

public class Distance {

    private Map<String, Integer> distance = new HashMap<String, Integer>();

    public void put(String relation, int distance) {
        this.distance.put(relation, distance);
    }

    public Integer get(String relation) {
        Integer rs = distance.get(relation);
        return rs;
    }

    public Integer get(Node a, Node b) {
        return this.get(a.getName() + b.getName());
    }
}
