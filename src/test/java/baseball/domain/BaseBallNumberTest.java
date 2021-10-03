package baseball.domain;



import static org.assertj.core.api.Assertions.*;


import java.util.LinkedHashSet;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import baseball.util.ArraysUtil;

class BaseBallNumberTest {

	@ParameterizedTest()
	@CsvSource(value = {"1, 2, 3", "2, 3, 4", "4, 5, 6"})
	@DisplayName("중복된 숫자가 있으면 에러가 발생된다.")
	void numberOverlapValidation(String number) {
		String[] numbers = number.split(",");
		assertThatIllegalArgumentException().isThrownBy(()-> new BaseBallNumber(ArraysUtil.convertIntegerArray(numbers)));
	}

	@ParameterizedTest()
	@ValueSource(strings = {"3,3", "1,2"})
	@DisplayName("숫자 세자리가 아니면 에러가 발생된다.")
	void numberSizeValidation(String number) {
		String[] numbers = number.split(",");
		assertThatIllegalArgumentException().isThrownBy(()-> new BaseBallNumber(ArraysUtil.convertIntegerArray(numbers)));
	}

	@Test
	@DisplayName("숫자가 1 ~ 9의 범위가 아니면 에러가 발생한다.")
	void numberRangeValidation() {
		int[] numbers = {1, 10, 3};
		assertThatIllegalArgumentException().isThrownBy(()-> new BaseBallNumber(numbers));
	}

	@Test
	@DisplayName("integer 배열로 숫자 볼이 생성 된다.")
	void createStringsNumberBaseBall() {
		int[] numbers = {1, 2, 3};
		int index = 0;
		BaseBallNumber baseBallNumber = new BaseBallNumber(numbers);
		LinkedHashSet<Integer> baseBallNumbers = baseBallNumber.getBallNumbers();

		for (Integer ball : baseBallNumbers) {
			Assertions.assertThat(ball).isEqualTo(numbers[index]);
			index++;
		}
	}

	@Test
	@DisplayName("숫자 볼이 변경 된다.")
	void changeBallNumber() {
		int[] changeNumbers = {1, 3, 4};
		BaseBallNumber baseBallNumber = new BaseBallNumber(new int[]{2, 3, 4});
		baseBallNumber.changeBallNumber(changeNumbers);

		Integer[] currentBaseBallNumber = baseBallNumber.getBallNumbers().toArray(new Integer[0]);

		for (int i = 0; i < changeNumbers.length; i++) {
			Assertions.assertThat(changeNumbers[i]).isEqualTo(currentBaseBallNumber[i]);
		}


	}




}
