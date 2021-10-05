package baseball.domain;

public class Computer {
	private BaseBallNumber baseBallNumber;

	public Computer() {
		this.baseBallNumber = BaseBallNumber.createRandomBaseBallNumber();
	}

	public BaseBallNumber getBaseBallNumber() {
		return baseBallNumber;
	}

	public BaseBallResult answerComputer(BaseBallNumber answerBallNumber) {
		BaseBallResult baseBallResult = new BaseBallResult();
		baseBallResult.setBall(baseBallNumber.verifyBallCount(answerBallNumber));
		baseBallResult.setStrike(baseBallNumber.verifyStrikeCount(answerBallNumber));
		return baseBallResult;
	}


}
