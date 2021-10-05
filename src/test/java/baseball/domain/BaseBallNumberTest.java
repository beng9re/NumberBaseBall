package baseball.domain;



import static org.assertj.core.api.Assertions.*;


import java.util.LinkedHashSet;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
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

	@Test
	@DisplayName("숫자가 같으면 두 객체는 같다")
	void equalBaseBall() {
		BaseBallNumber first = new BaseBallNumber(new int[]{1, 2,3});
		BaseBallNumber equalFirst = first;
		BaseBallNumber two = new BaseBallNumber(new int[]{1, 2, 3});
		Assertions.assertThat(first == two).isFalse();
		Assertions.assertThat(first.equals(equalFirst));

		Assertions.assertThat(first.equals(two)).isTrue();
		Assertions.assertThat(first.hashCode()).isEqualTo(two.hashCode());
		two.changeRandomBallNumber();
		Assertions.assertThat(first.equals(two)).isFalse();

		two = null;
		Assertions.assertThat(first.equals(two)).isFalse();

	}

	@RepeatedTest(5000)
	@DisplayName("숫자 야구가 랜덤으로 변경된다.")
	void changeRandomBaseBall() {
		BaseBallNumber baseBallNumber = BaseBallNumber.createRandomBaseBallNumber();
		BaseBallNumber cloneBaseBallNumber = new BaseBallNumber(ArraysUtil.integerWapToValueArray(
			baseBallNumber.getBallNumbers().toArray(new Integer[0])));

		baseBallNumber.changeRandomBallNumber();
		Assertions.assertThatCode(()-> baseBallNumber.validate(baseBallNumber.getBallNumbers()))
			.doesNotThrowAnyException();
		;

		Assertions.assertThat(cloneBaseBallNumber.equals(baseBallNumber)).isFalse();

	}

	@Test
	@DisplayName("랜덤한 숫자야구 숫자클래스를 생성한다.")
	void createRandomBaseBall() {
		BaseBallNumber baseBallNumber = BaseBallNumber.createRandomBaseBallNumber();
		Assertions.assertThatCode(()-> baseBallNumber.validate(baseBallNumber.getBallNumbers()))
			.doesNotThrowAnyException();
	}

	@DisplayName("다른 BallNumber 클래스를 비교하여 BallCount를 반환한다")
	@ParameterizedTest
	@CsvSource(value = {"1,3,4 : 1,4,3 : 2"}, delimiterString = ":" )
	void verifyBallCountTest(String source, String target, String count) {
		BaseBallNumber sourceBaseBall = new BaseBallNumber(ArraysUtil.convertIntegerArray(source.split(",")));
		BaseBallNumber targetBaseBall = new BaseBallNumber(ArraysUtil.convertIntegerArray(target.split(",")));

		Assertions.assertThat(sourceBaseBall.verifyBallCount(targetBaseBall)).isEqualTo(Integer.parseInt(count));
	}

	@DisplayName("다른 BallNumber 클래스를 비교하여 BallCount를 반환한다")
	@ParameterizedTest
	@CsvSource(value = {"1,3,4 : 1,4,3 : 1"},delimiterString = ":" )
	void verifyStrikeCountTest(String source,String target,String count) {
		BaseBallNumber sourceBaseBall = new BaseBallNumber(ArraysUtil.convertIntegerArray(source.split(",")));
		BaseBallNumber targetBaseBall = new BaseBallNumber(ArraysUtil.convertIntegerArray(target.split(",")));

		Assertions.assertThat(sourceBaseBall.verifyStrikeCount(targetBaseBall)).isEqualTo(Integer.parseInt(count));
	}



}
