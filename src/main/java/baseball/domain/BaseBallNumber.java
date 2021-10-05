package baseball.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;

import baseball.util.ArraysUtil;


public class BaseBallNumber {
	private LinkedHashSet<Integer> ballNumbers;

	public BaseBallNumber(int[] ballNumbers) {
		this.ballNumbers = createBallNumber(ballNumbers);
	}

	public static BaseBallNumber createRandomBaseBallNumber() {
		return new BaseBallNumber(ArraysUtil.randomIntegerArray(1, 9, 3));
	}

	public LinkedHashSet<Integer> getBallNumbers() {
		return this.ballNumbers;
	}

	public int verifyBallCount(BaseBallNumber targetBaseBallNumber) {
		int ballCount = 0;
		List<Integer> thisBaseballList = new ArrayList<>(this.ballNumbers);
		List<Integer> targetBaseballList = new ArrayList<>(targetBaseBallNumber.getBallNumbers());
		for (int i = 0; i < thisBaseballList.size(); i++) {
			if (isBall(i, targetBaseballList)) {
				ballCount++;
			}
		}
		return ballCount;
	}

	public int verifyStrikeCount(BaseBallNumber targetBaseBallNumber) {
		int strikeCount = 0;
		List<Integer> thisBaseballList = new ArrayList<>(this.ballNumbers);
		List<Integer> targetBaseballList = new ArrayList<>(targetBaseBallNumber.getBallNumbers());
		for (int i = 0; i < thisBaseballList.size(); i++) {
			if (targetBaseballList.get(i).equals(thisBaseballList.get(i))) {
				strikeCount++;
			}

		}
		return strikeCount;
	}

	private boolean isBall(int index, List<Integer> targetBaseBallNumberList) {
		List<Integer> thisBaseballList = new ArrayList<>(this.ballNumbers);
		List<Integer> verifyList = new ArrayList<>(thisBaseballList);
		verifyList.remove(index);
		if (verifyList.contains(targetBaseBallNumberList.get(index))) {
			return true;
		}
		return false;
	}


	private LinkedHashSet<Integer> createBallNumber(int[] ballNumberArr) {
		LinkedHashSet<Integer> ballNumbers = ArraysUtil.convertIntegerArrayToSet(ballNumberArr);
		validate(ballNumbers);
		return ballNumbers;
	}

	public void changeBallNumber(int[] ballNumberArr) {
		this.ballNumbers = createBallNumber(ballNumberArr);
	}

	public void changeRandomBallNumber() {
		LinkedHashSet<Integer> current = this.ballNumbers;
		int[] currentArr = ArraysUtil.integerWapToValueArray(current.toArray(new Integer[0]));
		int[] randomNumber = ArraysUtil.randomIntegerArray(1, 9, 3);

		while (Arrays.equals(randomNumber, currentArr)) {
			randomNumber = ArraysUtil.randomIntegerArray(1, 9, 3);
		}

		changeBallNumber(randomNumber);
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

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		BaseBallNumber that = (BaseBallNumber)object;
		return Objects.equals(new ArrayList<>(ballNumbers), new ArrayList<>(that.ballNumbers));
	}

	@Override
	public int hashCode() {
		return Objects.hash(ballNumbers);
	}
}
