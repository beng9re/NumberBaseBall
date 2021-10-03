package baseball.domain;

import java.util.LinkedHashSet;

import baseball.util.ArraysUtil;


public class BaseBallNumber {
	private LinkedHashSet<Integer> ballNumbers;

	BaseBallNumber(int[] ballNumbers) {
		this.ballNumbers = createBallNumber(ballNumbers);
	}

	public static BaseBallNumber createRandomBaseBallNumber() {
		return new BaseBallNumber(ArraysUtil.randomIntegerArray(1, 9, 3));
	}

	public LinkedHashSet<Integer> getBallNumbers() {
		return this.ballNumbers;
	}

	private LinkedHashSet<Integer> createBallNumber(int[] ballNumberArr) {
		LinkedHashSet<Integer> ballNumbers = ArraysUtil.convertIntegerArrayToSet(ballNumberArr);
		validate(ballNumbers);
		return ballNumbers;
	}

	public void changeBallNumber(int[] ballNumberArr) {
		this.ballNumbers = createBallNumber(ballNumberArr);
	}

	private void validateNumber(int number) {
		if (number > 9 || number < 1) {
			throw new IllegalArgumentException("야구 숫자는 1 ~ 9 사이여야 합니다. 숫자 : " + number);
		}
	}

	public void validate(LinkedHashSet<Integer> ballNumbers) {
		if (ballNumbers.size() != 3) {
			throw new IllegalArgumentException("야구 숫자는 세자리이며 중복된 수가 없어야 합니다.");
		}
		for (int ballNumber : ballNumbers) {
			validateNumber(ballNumber);
		}
	}
}
