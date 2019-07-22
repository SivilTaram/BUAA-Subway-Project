import java.util.*;
public class Main2{

    public static void main(String args[]){
        Scanner cin = new Scanner(System.in);
        String station = "A1 A2 A3 A4 A5 A6 A7 A8 A9 T1 A10 A11 A12 A13 T2 A14 A15 A16 A17 A18 B1 B2 B3 B4 B5 T1 B6 B7 B8 B9 B10 T2 B11 B12 B13 B14 B15";
        String[] stations = station.split(" ");
        int length = stations.length;
        int[][] arr = new int[length][length];
        HashMap<String, Integer> stationMap = new HashMap<String, Integer>();
        for(int i=0; i<stations.length; i++) {
            stationMap.put(stations[i], i);
        }
        for(int i=0; i<length; i++){
            for(int j=0; j<length; j++) {
                if(i == j) {
                    arr[i][j] = 0;
                } else {
                    arr[i][j] = 1000;
                }
            }
        }
        for(int i=0; i<length-1; i++) {
            arr[i][i+1] = 1;
            arr[i+1][i] = 1;
        }
        arr[9][25] = 0;
        arr[14][31] = 0;
        arr[25][9] = 0;
        arr[31][14] = 0;
        arr[stationMap.get("A18")][stationMap.get("B1")] = 1000;
        arr[stationMap.get("B1")][stationMap.get("A18")] = 1000;

        arr[stationMap.get("A1").intValue()][stationMap.get("A18").intValue()] = 1;

//Floyd算法求解
        for(int k=0; k<length; k++){
            for(int i=0; i<length; i++) {
                for(int j=0; j<length; j++) {
                    if((arr[i][k] + arr[k][j]) < arr[i][j]) {
                        arr[i][j] =  arr[i][k] + arr[k][j];
                    }
                }
            }
        }

        while (cin.hasNext()){
            String state1 = cin.next();
            String state2 = cin.next();

            int i = stationMap.get(state1);
            int j = stationMap.get(state2);

            System.out.println(arr[i][j] + 1);
        }
    }

}