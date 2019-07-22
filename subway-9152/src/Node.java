import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Node {

    private String name;
    private Set<Node> connected = new HashSet<Node>();

    public Node(String name) {
        this.name = name;
    }

    public void connect(Node node) {
        this.connected.add(node);
    }

    public boolean isConnected(Node node) {
        return connected.contains(node);
    }

    public Iterator<Node> getConnected() {
        return this.connected.iterator();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return this.getName();
    }
    public int getI(String s[][]){//
        int n = 0;
        for (int i = 0; i < s.length; i++)
            for (int j = 0; j < s[i].length; j++) {
                if (s[i][j] == name) { n = i;break; }
            }
        return n;
    }
}