import java.util.*;

public class Practical1 {

    int[] randomValues(int min, int max, int size) {
        Random r = new Random();
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = r.nextInt((max - min + 1)) + min;
        }
        return data;
    }

    void taskA(int[] temp, int[] pressure) {
        long start = System.nanoTime();
        int minTemp = Integer.MAX_VALUE;
        int maxPressure = Integer.MIN_VALUE;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] < minTemp) minTemp = temp[i];
            if (pressure[i] > maxPressure) maxPressure = pressure[i];
        }
        long end = System.nanoTime();
        System.out.println("Task A (Linear Search - O(n))");
        System.out.println("Minimum Temperature: " + minTemp);
        System.out.println("Maximum Pressure: " + maxPressure);
        System.out.printf("Execution Time: %.6f ms%n", (end - start) / 1_000_000.0);
    }

    void taskB(int[] temp, int[] pressure) {
        long start = System.nanoTime();
        int minTemp = temp[0];
        int maxPressure = pressure[0];
        for (int i = 0; i < temp.length; i++) {
            boolean isMin = true;
            for (int j = 0; j < temp.length; j++) {
                if (temp[j] < temp[i]) { isMin = false; break; }
            }
            if (isMin) { minTemp = temp[i]; break; }
        }
        for (int i = 0; i < pressure.length; i++) {
            boolean isMax = true;
            for (int j = 0; j < pressure.length; j++) {
                if (pressure[j] > pressure[i]) { isMax = false; break; }
            }
            if (isMax) { maxPressure = pressure[i]; break; }
        }
        long end = System.nanoTime();
        System.out.println("Task B (Pairwise Comparison - O(n²))");
        System.out.println("Minimum Temperature: " + minTemp);
        System.out.println("Maximum Pressure: " + maxPressure);
        System.out.printf("Execution Time: %.6f ms%n", (end - start) / 1_000_000.0);
    }

    void taskC(int[] data) {
        Arrays.sort(data);
        long startLinear = System.nanoTime();
        int linearIndex = -1;
        for (int i = 0; i < data.length; i++) {
            if (data[i] >= 30) { linearIndex = i; break; }
        }
        long endLinear = System.nanoTime();
        long startBinary = System.nanoTime();
        int low = 0, high = data.length - 1, binaryIndex = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (data[mid] >= 30) { binaryIndex = mid; high = mid - 1; }
            else { low = mid + 1; }
        }
        long endBinary = System.nanoTime();
        System.out.println("Task C (First Occurrence ≥ 30)");
        System.out.println("Linear Search Index: " + linearIndex);
        System.out.printf("Linear Search Time: %.6f ms%n", (endLinear - startLinear) / 1_000_000.0);
        System.out.println("Binary Search Index: " + binaryIndex);
        System.out.printf("Binary Search Time: %.6f ms%n", (endBinary - startBinary) / 1_000_000.0);
    }

    public static void main(String[] args) {
        Practical1 p = new Practical1();
        int[] temp = p.randomValues(-20, 50, 100);
        int[] pressure = p.randomValues(950, 1050, 100);
        p.taskA(temp, pressure);
        System.out.println();
        p.taskB(temp, pressure);
        System.out.println();
        int[] sortedTemp = p.randomValues(20, 50, 100);
        p.taskC(sortedTemp);
    }
}
