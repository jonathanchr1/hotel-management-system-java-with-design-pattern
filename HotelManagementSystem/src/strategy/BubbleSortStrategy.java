package strategy;

public class BubbleSortStrategy implements SortingStrategy {
	@Override
	public void sort(String[] names) {
		boolean flag = true;
        for (int i = 1; i < names.length - 1; i++) {
            flag = false;
            for (int j = i + 1; j < names.length; j++) {
                if (names[j].compareTo(names[i]) < 0) {
                    String temp = names[j];
                    names[j] = names[i];
                    names[i] = temp;
                    flag = true;
                }
            }
            if (!flag) break;
        }
	}
}