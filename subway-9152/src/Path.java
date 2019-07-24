import java.util.LinkedList;

public class Path {

    private Integer dis = -1;
    private LinkedList<Node> path = new LinkedList<Node>();

    public Integer getDis() {
        return dis;
    }

    public void setDis(Integer dis) {
        this.dis = dis;
    }

    public LinkedList<Node> getPath() {
        return path;
    }
}
