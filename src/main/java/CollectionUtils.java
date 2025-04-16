import java.util.HashMap;
import java.util.Map;

public class CollectionUtils {

    public static <T> Map<T, Integer> countElements(T[] array) {
        Map<T, Integer> elementCounts = new HashMap<>();

        if (array == null) {
            return elementCounts; // Возвращаем пустую Map, если массив null
        }

        for (T element : array) {
            if (elementCounts.containsKey(element)) {
                // Увеличиваем счетчик, если элемент уже встречался
                elementCounts.put(element, elementCounts.get(element) + 1);
            } else {
                // Добавляем элемент в Map, если встречается впервые
                elementCounts.put(element, 1);
            }
        }

        return elementCounts;
    }

    // Пример использования
    public static void main(String[] args) {
        String[] words = {"apple", "banana", "apple", "orange", "banana", "apple"};
        Map<String, Integer> counts = countElements(words);

        System.out.println(counts); // Выведет: {banana=1, orange=1, apple=3, banana=1}
    }
}