package drawpanel;

import java.awt.Color;
import java.awt.Polygon;
import java.util.Arrays;

/**
 *
 * @author gorbushin_v_a
 */
public class View {
    
    static int vectorX = 1;
    static int vectorY = 1;
    static int vectorZ = 1;
    
    static int lightingVectorX = 1;
    static int lightingVectorY = 0;
    static int lightingVectorZ = -1;
    
    public static double[][] xyzRotation(double b, double[][] vertexCoordinates) {
        if (!(vectorX == 0 && vectorY == 0 && vectorZ == 0)) {
            for (double[] vertexCoordinate : vertexCoordinates) {
                double vectorRationing = Math.sqrt(vectorX * vectorX + vectorY * vectorY + vectorZ * vectorZ);
                double tempX = vertexCoordinate[0];
                double tempY = vertexCoordinate[1];
                double tempZ = vertexCoordinate[2];
                vertexCoordinate[0] = tempX * (Math.cos(b) + (1 - Math.cos(b)) * vectorX * vectorX / vectorRationing / vectorRationing) + tempY * ((1 - Math.cos(b)) * vectorX * vectorY / vectorRationing / vectorRationing + Math.sin(b) * vectorZ / vectorRationing) + tempZ * ((1 - Math.cos(b)) * vectorX * vectorZ / vectorRationing / vectorRationing - Math.sin(b) * vectorY / vectorRationing);
                vertexCoordinate[1] = tempY * (Math.cos(b) + (1 - Math.cos(b)) * vectorY * vectorY / vectorRationing / vectorRationing) + tempX * ((1 - Math.cos(b)) * vectorX * vectorY / vectorRationing / vectorRationing - Math.sin(b) * vectorZ / vectorRationing) + tempZ * ((1 - Math.cos(b)) * vectorZ * vectorY / vectorRationing / vectorRationing + Math.sin(b) * vectorX / vectorRationing);
                vertexCoordinate[2] = tempZ * (Math.cos(b) + (1 - Math.cos(b)) * vectorZ * vectorZ / vectorRationing / vectorRationing) + tempX * ((1 - Math.cos(b)) * vectorX * vectorZ / vectorRationing / vectorRationing + Math.sin(b) * vectorY / vectorRationing) + tempY * ((1 - Math.cos(b)) * vectorY * vectorZ / vectorRationing / vectorRationing - Math.sin(b) * vectorX / vectorRationing);
            }
        }
        return vertexCoordinates;
    }
    
    public static double[][] getAvgDepthZ(double[][] vertexCoordinates, int[][] indexOfVertxes) {
        double[][] avgDepthZ = new double[indexOfVertxes.length][2];
        for (int i = 0; i < indexOfVertxes.length; i++) {
            double avgCoordinateZ = 0;
            for (int j = 0; j < indexOfVertxes[i].length; j++) {
                avgCoordinateZ += vertexCoordinates[indexOfVertxes[i][j]][2];
            }
            avgCoordinateZ /= indexOfVertxes[i].length;
            avgDepthZ[i][0] = avgCoordinateZ;
            avgDepthZ[i][1] = i;
        }
        return avgDepthZ;
    }
    
    public static Polygon[] setPriorityOfTriangles(double[][] avgDepthZ, Polygon[] flatTriangles) {
        Arrays.sort(avgDepthZ, (double[] o1, double[] o2) -> {
            if (o1[0] < o2[0]) {
                return -1;
            }
            if (o1[0] > o2[0]) {
                return 1;
            }
            return 0;
        });
        Polygon[] changedTriangles = new Polygon[20];
        int number;
        for (int i = 0; i < avgDepthZ.length; i++) {
            number = (int) avgDepthZ[i][1];
            changedTriangles[i] = flatTriangles[number];
        }
        return changedTriangles;
    }
    
    public static Color[] setColorOfTriangles(double[][] avgDepthZ, Color[] colorOfTriangles, double[][] vertexCoordinates, int[][] indexOfVertxes) {
        int number;
        for (int i = 0; i < avgDepthZ.length; i++) {
            number = (int) avgDepthZ[i][1];

            double nx1 = vertexCoordinates[indexOfVertxes[number][0]][0] - vertexCoordinates[indexOfVertxes[number][1]][0];
            double ny1 = vertexCoordinates[indexOfVertxes[number][0]][1] - vertexCoordinates[indexOfVertxes[number][1]][1];
            double nz1 = vertexCoordinates[indexOfVertxes[number][0]][2] - vertexCoordinates[indexOfVertxes[number][1]][2];

            double nx2 = vertexCoordinates[indexOfVertxes[number][0]][0] - vertexCoordinates[indexOfVertxes[number][2]][0];
            double ny2 = vertexCoordinates[indexOfVertxes[number][0]][1] - vertexCoordinates[indexOfVertxes[number][2]][1];
            double nz2 = vertexCoordinates[indexOfVertxes[number][0]][2] - vertexCoordinates[indexOfVertxes[number][2]][2];

            double nx = -ny1 * nz2 + nz1 * ny2;
            double ny = -nx1 * nz2 + nz1 * nx2;
            double nz = -nx1 * ny2 + ny1 * nx2;

            double coefficient = (View.lightingVectorX * nx + View.lightingVectorY * ny + View.lightingVectorZ * nz) / (Math.sqrt(View.lightingVectorX * View.lightingVectorX + View.lightingVectorY * View.lightingVectorY + View.lightingVectorZ * View.lightingVectorZ) * Math.sqrt(nx * nx + ny * ny + nz * nz));
            if (coefficient < 0) {
                coefficient = 0;
            }
            int rgb = (int) (255 * coefficient);
            colorOfTriangles[i] = new Color(rgb, 255, rgb, 255);
        }
        return colorOfTriangles;
    }
}
