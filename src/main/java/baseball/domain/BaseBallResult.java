package baseball.domain;

public class BaseBallResult {

	private int strike;
	private int ball;
	private static final String BALL_MSG = "볼";
	private static final String STRIKE_MSG = "스트라이크";
	private static final String NOTTING = "낫싱";

	public BaseBallResultStatus getStatus() {
		if (this.strike == 3 ) {
			return BaseBallResultStatus.COLLECT;
		}
		return BaseBallResultStatus.HINT;
	}

	public String makeResultMsg() {
		if (!hasBall() && !hasStrike()) {
			return NOTTING;
		}
		return new StringBuilder(makeStrikeMsg()).append(makeBallMsg()).toString().trim();
	}

	private String makeBallMsg() {
		if (!hasBall()) {
			return "";
		}
		return ball + BALL_MSG;
	}

	private String makeStrikeMsg() {
		if (!hasStrike()) {
			return "";
		}
		return strike + STRIKE_MSG + " ";
	}

	public boolean hasStrike() {
		return strike != 0;
	}

	public boolean hasBall() {
		return ball != 0;
	}

	private void validationStrike() {
		if (strike < 0 || strike > 3) {
			throw new IllegalArgumentException("Strike는 0 ~ 3범위 이어야 합니다. ");
		}
		validationSumBallResult();
	}

	private void validationBall() {
		if (ball < 0 || ball > 3) {
			throw new IllegalArgumentException("ball은 0 ~ 3범위입니다.");
		}
		validationSumBallResult();
	}

	private void validationSumBallResult() {
		if ((ball + strike) > 3) {
			throw new IllegalArgumentException("ball 과 Strike가 합이 3이 넘어갑니다.");
		}
	}

	public void setStrike(int strike) {
		int beforeStrike = this.strike;
		this.strike = strike;
		try {
			validationStrike();
		} catch (IllegalArgumentException e) {
			this.strike = beforeStrike;
			throw e;
		}
	}

	public void setBall(int ball) {
		int beforeBall = this.ball;
		this.ball = ball;
		try {
			validationBall();
		} catch (IllegalArgumentException e) {
			this.ball = beforeBall;
			throw e;
		}
	}
}
