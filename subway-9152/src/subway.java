import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class subway {
    ComputeShort cs;
    private String[][] xianlu;
    String resource="subway.txt";

    public subway() {
        Data data = null;
        cs = new ComputeShort();
        try {
            xianlu = createArray( resource);

            data = new Data(xianlu);
        } catch (IOException e) {
            e.printStackTrace();
        }
        cs.setData(data);
    }

    public static void main(String[] args) {

        subway sub = new subway();
        readStation("subway.txt");
        System.out.println("subway.txt loaded");
        System.out.println(sub.getXianLu("1号线", "subway.txt"));
        System.out.println(sub.getPath("刘园","天津站","routline.txt"));

/*
        // readStation("subway.txt" );
        try {
            if (args.length == 0) {
                throw new IllegalArgumentException("请输入参数");
            }
            for (int i = 0; i < args.length; i++) {
                System.out.println(i + "  " + args[i]);
            }
            if (args.length == 3) {
                readStation(args[2]);
                System.out.println("loading subway.txt");
            }
            if (args.length == 7) {
                readStation(args[4]);
                sub.getXianLu(args[2], args[6]);
                System.out.println(sub);
            }
            if (args.length == 8) {
                readStation(args[4]);
                sub.getPath(args[2], args[3],args[7]);
                System.out.println(sub);
            }

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
*/
    }

    public static List readStation(String fileName) {
        List<String> list = new ArrayList<String>();
        try {
            String encoding = "GBK";
            File file = new File(fileName);
            InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file), encoding);
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                list.add(lineTxt);
            }
            read.close();
        } catch (Exception e) {
            System.out.println("出错了");
            e.printStackTrace();
        }



        return list;
    }

    //写入二维数组
    public static String[][] createArray(String filePath) {
        List<String> list = readStation(filePath);
        String array[][] = new String[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            array[i] = new String[50];
            String linetxt = list.get(i);
            String[] myArray = linetxt.replaceAll("\\s+", "@").split("@");
            for (int j = 0; j < myArray.length; j++) {
                if (j < 50) {
                    array[i][j] = myArray[j];
                }
            }
        }
        return array;
    }

    public String getPath(String startAddress, String endAddress, String path) {
        Path p = this.cs.getShort(startAddress, endAddress);
        StringBuffer sb = new StringBuffer();
        int num = p.getDis() + 1;
        sb.append(num + "\n");
        LinkedList<Node> ll = p.getPath();
        StringBuffer sb1 = new StringBuffer();
        Iterator var8 = ll.iterator();
        if (var8.hasNext()) {
            Node node1 = (Node) var8.next();
            sb1.append(node1 + "\n");
            while (var8.hasNext()) {
                Node node2 = (Node) var8.next();
                //判断是否换线
                if (node1.getI(xianlu) != node2.getI(xianlu)) sb1.append(xianlu[node2.getI(xianlu)][0] + "\n");//
                sb1.append(node2 + "\n");
                node1 = node2;
            }
        }
        try {
            sb.append(sb1.toString().substring(0, sb1.length()) + "\n");
            FileWriter fileWriter = new FileWriter("src/" + path, true);     //写入routine
            fileWriter.write(String.valueOf(sb));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


    public String getXianLu(String line1, String path) {
        StringBuffer sb2 = new StringBuffer();
        List<String> list1 = readStation("subway.txt");
        for (int i = 0; i < list1.size(); i++) {
            String linetxt1 = list1.get(i);

        }

        for (int i = 0; i < xianlu.length; i++) {
            if (xianlu[i][0].equals(line1)) {
                sb2.append(list1.get(i));

            }
        }
        try {
            sb2.append(sb2.toString().substring(0, sb2.length()) + "\n");
            FileWriter fileWriter = new FileWriter(path, true);     //写入routine
            fileWriter.write(String.valueOf(sb2));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb2.toString();

    }

}
