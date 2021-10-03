package baseball.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashSet;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ArraysUtilTest {

	@Test
	@DisplayName("Integer 배열이 LinkedHashSet으로 변환된다.")
	void convertArrayIntegerToSet() {
		int[] strings = {1, 3, 4};
		int[] intArray = new int[strings.length];
		LinkedHashSet<Integer> convertValue = ArraysUtil.convertIntegerArrayToSet(intArray);
		Integer[] outArr = convertValue.toArray(new Integer[0]);

		for (int i = 0; i < outArr.length; i++) {
			Assertions.assertThat(intArray[i]).isEqualTo(outArr[i]);
		}
	}

	@Test
	@DisplayName("String 배열이 Integer 배열로 변환된다")
	void stringsChangeIntegersArray() {
		String[] changeValue = {"1", "3", "4"};
		int[] convertArray = ArraysUtil.convertIntegerArray(changeValue);

		for (int i = 0; i < convertArray.length; i++) {
			Assertions.assertThat(convertArray[i]).isEqualTo(Integer.parseInt(changeValue[i]));
		}


	}

}