package baseball.util;



import java.util.HashSet;
import java.util.LinkedHashSet;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

	@ParameterizedTest
	@DisplayName("특정 범위의 랜덤한 숫자의 중복되지 않은 배열을 생성한다")
	@CsvSource(value = {"1, 5, 2", "1, 9, 3"})
	void createRandomArray(int start, int end, int count) {
		int[] randomArray = ArraysUtil.randomIntegerArray(start, end, count);
		HashSet<Integer> randomArrayCk = new HashSet();
		for (int value : randomArray) {
			randomArrayCk.add(value);
			Assertions.assertThat(value).isBetween(start, end);
		}
		Assertions.assertThat(randomArrayCk.size()).isEqualTo(count);
	}

}
