package baseball.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ComputerTest {

	@Test
	@DisplayName("컴퓨터를 생성할떄 랜덤한 숫자야구의 숫자가 초기화 된다")
	void createComputerRandomBaseBall() {
		assertThat(new Computer().getBaseBallNumber()).isNotNull();
	}

	@Test
	@DisplayName("컴퓨터의 값과 파라미터로 받은 BaseballNumber로 baseBallResult 값이 생성된다.")
	void createBaseBallResult() {
		Computer computer = new Computer();
		BaseBallNumber computerBaseball = computer.getBaseBallNumber();
		BaseBallResult baseBallResult = computer.answerComputer(computerBaseball);

		assertThat(baseBallResult.hasStrike()).isTrue();
		assertThat(baseBallResult.hasBall()).isFalse();
		assertThat(baseBallResult.getStatus()).isEqualTo(BaseBallResultStatus.COLLECT);

		computerBaseball.changeRandomBallNumber();
		BaseBallResult hintBaseBallResult = computer.answerComputer(computerBaseball);
		assertThat(hintBaseBallResult.getStatus()).isEqualTo(BaseBallResultStatus.COLLECT);

	}

}