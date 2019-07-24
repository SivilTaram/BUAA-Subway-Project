import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class ComputeShort {

    private Data data; // 存储数据的类

    private EasyCache cache = new EasyCache(); // 缓存数据，防止数据量过大导致内存溢出

    private Path getShortDis(Node a, Node b, Set<Node> visited) {
        // Set里的值是和放入顺序无关的固定顺序,这里恰好可以做cache的key
        String key = a.toString() + b.toString() + visited;
        Path rs = (Path) cache.get(key);
        if (rs != null) {
            //System.out.println("read from cache : " + key);
            return rs;
        }
        // cache里没有则计算
        rs = new Path();
        // 访问过了则返回
        if (visited.contains(a)) {
            cache.put(key, rs);
            return rs;
        } else {
            visited.add(a);
            rs.getPath().add(a);
        }
        // 如果是相连的直接返回结果
        if (a.isConnected(b)) {
            rs.getPath().add(b);
            rs.setDis(data.getDis().get(a, b));
            cache.put(key, rs);
            return rs;
        } else {
            // 否则递归调用
            Iterator<Node> nodes = a.getConnected();
            int tempRs = -1;
            LinkedList<Node> path_temp = null;
            while (nodes.hasNext()) {
                Node temp = nodes.next();
                Integer dis = -1;
                Set<Node> visted_child = new HashSet<Node>();
                visted_child.addAll(visited);
                Path child = getShortDis(temp, b, visted_child);
                if (child.getDis() == -1)
                    continue;
                dis = data.getDis().get(a, temp) + child.getDis();
                if (tempRs == -1 || dis < tempRs) {
                    tempRs = dis;
                    path_temp = child.getPath();
                }
            }
            if (path_temp != null)
                rs.getPath().addAll(path_temp);
            if (tempRs != -1)
                rs.setDis(tempRs);
            cache.put(key, rs);
            return rs;
        }
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Path getShort(String a, String b) {
        Node nodeA = data.getNodes().get(a);
        Node nodeB = data.getNodes().get(b);
        Path p = getShortDis(nodeA, nodeB, new HashSet<Node>());
        return p;
    }

}
