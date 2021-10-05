package baseball.util;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import nextstep.utils.Randoms;

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

	public static int[] randomIntegerArray(int start, int end, int count) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			list.add(randomNotDuplicatePickNumber(list, start, end));
		}
		return integerWapToValueArray(list.toArray(new Integer[0]));
	}

	private static int randomNotDuplicatePickNumber(List currenList, int start, int end) {
		while (true) {
			int randomValue = Randoms.pickNumberInRange(start, end);
			boolean contains = currenList.contains(randomValue);
			if (!contains) {
				return randomValue;
			}
		}

	}

	public static int[] integerWapToValueArray(Integer[] convert) {
		int[] result = new int[convert.length];
		for (int i = 0; i < convert.length; i++) {
			result[i] = convert[i];
		}
		return result;
	}

}
