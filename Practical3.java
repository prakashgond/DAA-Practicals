import java.util.*;

public class Practical3{
    
    private static class Item {
        double value;
        double mass;
        double ratio;
        
        Item(double v, double m) {
            this.value = v;
            this.mass = m;
            this.ratio = (m == 0) ? Double.MAX_VALUE : v / m;
        }
    }
    
    private static void bubbleSort(Item[] items, Comparator<Item> comp) {
        int n = items.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (comp.compare(items[j], items[j + 1]) < 0) {
                    Item temp = items[j];
                    items[j] = items[j + 1];
                    items[j + 1] = temp;
                }
            }
        }
    }
    
    private static double calculateResult(Item[] items, double capacity) {
        double totalValue = 0;
        double usedCapacity = 0;
        
        for (Item item : items) {
            if (usedCapacity + item.mass <= capacity) {
                totalValue += item.value;
                usedCapacity += item.mass;
            } else {
                double remainingCapacity = capacity - usedCapacity;
                totalValue += (remainingCapacity / item.mass) * item.value;
                usedCapacity = capacity;
                break;
            }
        }
        
        return totalValue;
    }
    
    public static void solveKnapsack(double[] values, double[] weights, double maxCapacity) {
        int itemCount = values.length;
        
        Item[] valueBasedItems = new Item[itemCount];
        Item[] weightBasedItems = new Item[itemCount];
        Item[] ratioBasedItems = new Item[itemCount];
        
        for (int i = 0; i < itemCount; i++) {
            valueBasedItems[i] = new Item(values[i], weights[i]);
            weightBasedItems[i] = new Item(values[i], weights[i]);
            ratioBasedItems[i] = new Item(values[i], weights[i]);
        }
        
        bubbleSort(valueBasedItems, (a, b) -> Double.compare(a.value, b.value));
        bubbleSort(weightBasedItems, (a, b) -> Double.compare(b.mass, a.mass));
        bubbleSort(ratioBasedItems, (a, b) -> Double.compare(a.ratio, b.ratio));
        
        double maxValueResult = calculateResult(valueBasedItems, maxCapacity);
        double minWeightResult = calculateResult(weightBasedItems, maxCapacity);
        double ratioResult = calculateResult(ratioBasedItems, maxCapacity);
        
        System.out.println("Maximum value strategy result: " + maxValueResult);
        System.out.println("Minimum weight strategy result: " + minWeightResult);
        System.out.println("Value-to-weight ratio strategy result: " + ratioResult);
    }
    
    public static void main(String[] args) {
        double[] itemValues = {360, 83, 59, 130, 431, 67, 230, 52, 93, 125, 670, 892, 600, 38, 48, 147,
            78, 256, 63, 17, 120, 164, 432, 35, 92, 110, 22, 42, 50, 323, 514, 28, 87, 73, 78, 15, 
            26, 78, 210, 36, 85, 189, 274, 43, 33, 10, 19, 389, 276, 312};
        
        double[] itemWeights = {7, 0, 30, 22, 80, 94, 11, 81, 70, 64, 59, 18, 0, 36, 3, 8, 15, 42, 9, 0, 42,
            47, 52, 32, 26, 48, 55, 6, 29, 84, 2, 4, 18, 56, 7, 29, 93, 44, 71, 3, 86, 66, 31, 65, 0, 
            79, 20, 65, 52, 13};
        
        solveKnapsack(itemValues, itemWeights, 850);
    }
}