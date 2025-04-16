import java.util.HashMap;
import java.util.Map;

public class CollectionUtils {

    public static <T> Map<T, Integer> countElements(T[] array) {
        Map<T, Integer> elementCounts = new HashMap<>();

        for (T element : array) {
            if (elementCounts.containsKey(element)) {
                elementCounts.put(element, elementCounts.get(element) + 1);
            } else {
                elementCounts.put(element, 1);
            }
        }

        return elementCounts;
    }

    public static void main(String[] args) {
        String[] words = {"apple", "banana", "apple", "orange", "banana", "apple"};
        Map<String, Integer> counts = countElements(words);

        System.out.println(counts);
    }
}