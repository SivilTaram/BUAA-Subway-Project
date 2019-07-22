import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Data {
    private Map<String, Node> nodes = new HashMap();
    private Distance dis = new Distance();
    private int length;
    // 把预备数据转换为无向图（线与起始站距离为0）
    public Data(String[][] s) throws IOException {
        for(int i = 0; i<s.length; ++i) {
            for(int j = 0; j < s[i].length-1; ++j) {
                if(j==0) this.length = 0;
                else this.length = 1;
                String nodeName1 = s[i][j];
                String nodeName2 = s[i][j + 1];
                Node node1 = (Node)this.nodes.get(nodeName1);
                if (node1 == null) {
                    node1 = new Node(nodeName1);
                    this.nodes.put(nodeName1, node1);
                }

                Node node2 = (Node)this.nodes.get(nodeName2);
                if (node2 == null) {
                    node2 = new Node(nodeName2);
                    this.nodes.put(nodeName2, node2);
                }

                node1.connect(node2);
                node2.connect(node1);
                this.dis.put(nodeName1 + nodeName2, this.length);
                this.dis.put(nodeName2 + nodeName1, this.length);
            }
        }
    }

    public Map<String, Node> getNodes() {
        return this.nodes;
    }

    public Distance getDis() {
        return this.dis;
    }
}
