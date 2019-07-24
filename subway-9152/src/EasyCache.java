import java.util.HashMap;
import java.util.Map;

public class EasyCache {

    private Map<String, Object> map = new HashMap<String, Object>();

    public void put(String key, Object value) {
        this.map.put(key, value);
    }

    public Object get(String key) {
        return map.get(key);
    }
}
