package drawpanel;

import static drawpanel.View.getAvgDepthZ;
import static drawpanel.View.setColorOfTriangles;
import static drawpanel.View.setPriorityOfTriangles;
import static drawpanel.View.xyzRotation;
import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author gorbushin_v_a
 */
public class CustomFigure {
    
    double[][] vertexCoordinates; // массив вершин
    int[][] indexOfVertxes; // массив индексов
    Color[] colorOfTriangles; // массив цветов граней
    Polygon[] flatTriangles;

    double angle = 0;
    
    public CustomFigure(){
        vertexCoordinates = new double[0][0];
        indexOfVertxes = new int[0][0];
        colorOfTriangles = new Color[0];
        flatTriangles = new Polygon[0];
    }
    
    public void assembly() {
        this.vertexCoordinates = xyzRotation(angle, vertexCoordinates);

        for (int i = 0; i < indexOfVertxes.length; i++) {
            Polygon p = new Polygon();
            for (int j = 0; j < indexOfVertxes[i].length; j++) {
                int x2 = (int) (vertexCoordinates[indexOfVertxes[i][j]][0])+200;
                int y2 = (int) (vertexCoordinates[indexOfVertxes[i][j]][1])+230;
                p.addPoint(x2, y2);
            }
            flatTriangles[i] = p;
        }
        double[][] avgDepthZ = getAvgDepthZ(vertexCoordinates, indexOfVertxes);
        this.flatTriangles = setPriorityOfTriangles(avgDepthZ, flatTriangles);
        this.colorOfTriangles = setColorOfTriangles(avgDepthZ, colorOfTriangles, vertexCoordinates, indexOfVertxes);
    }
 
    public void readIntArray2(String data) {
            indexOfVertxes = toIntArray2(readLines(data));
            colorOfTriangles = new Color[indexOfVertxes.length];
            flatTriangles = new Polygon[indexOfVertxes.length];
    }

    public static int[][] toIntArray2(String[] lines) {
        int[][] arr2 = new int[lines.length][];
        for (int r = 0; r < lines.length; r++) {
            arr2[r] = toIntArray(lines[r]);
        }
        return arr2;
    }
    
    public static int[] toIntArray(String str) {
        Scanner scanner = new Scanner(str);
        scanner.useLocale(Locale.ROOT);
        scanner.useDelimiter("(\\s|[,;])+");
        List<Integer> list = new ArrayList<>();

        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                list.add(scanner.nextInt());
            } else {
                return null;
            }
        }
        Integer[] arr = list.toArray(new Integer[0]);
        return toPrimitive(arr);
    }
    
    public static String[] readLines(String data) {
        List<String> lines;
        try (Scanner scanner = new Scanner(data)) {
            lines = new ArrayList<>();
            while (scanner.hasNext()) {
                lines.add(scanner.nextLine());
            }
        }
        return lines.toArray(new String[0]);
    }
    
    public static int[] toPrimitive(Integer[] arr) {
        if (arr == null) {
            return null;
        }
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i];
        }
        return result;
    }
    
    public void readDoubleArray2(String data) {
        vertexCoordinates = toArrayD(readLines(data));
    }

    public static double[][] toArrayD(String[] lines) {
        double[][] arr2 = new double[lines.length][];
        for (int r = 0; r < lines.length; r++) {
            arr2[r] = toArrayD(lines[r]);
        }
        return arr2;
    }
    
    public static double[] toArrayD(String str) {
        Scanner scanner = new Scanner(str);
        scanner.useLocale(Locale.ROOT);
        scanner.useDelimiter("(\\s|[,;])+");
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<3; i++){
            if (scanner.hasNextDouble()) {
                list.add(scanner.nextInt());
            } else {
                return null;
            }
        }
        double[] target = new double[list.size()];
        for (int i = 0; i < target.length; i++) {
            target[i] = list.get(i);
        }
        return target;
    }
}
