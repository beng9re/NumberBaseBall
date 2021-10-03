package baseball.util;

import java.util.LinkedHashSet;

public class ArraysUtil {

	public static LinkedHashSet<Integer> convertIntegerArrayToSet(int[] integerArray) {
		LinkedHashSet<Integer> set = new LinkedHashSet<>();
		for (Integer value : integerArray) {
			set.add(value);
		}
		return set;
	}

	public static int[] convertIntegerArray(String[] values) {
		int[] result = new int[values.length];
		for (int i = 0; i < values.length; i++) {
			result[i] = Integer.parseInt(values[i]);
		}
		return result;
	}

}
