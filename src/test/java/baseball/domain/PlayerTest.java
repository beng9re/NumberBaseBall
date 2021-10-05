package baseball.domain;


import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import baseball.util.ArraysUtil;

class PlayerTest {

	@Test
	@DisplayName("정상적으로 Player BallNumber가 변경이 된다")
	void changeBallNumber() {
		BaseBallNumber baseBallNumber =  BaseBallNumber.createRandomBaseBallNumber();
		BaseBallNumber changeBaseBallNumber = new BaseBallNumber(
			ArraysUtil.integerWapToValueArray(baseBallNumber.getBallNumbers().toArray(new Integer[0])));

		changeBaseBallNumber.changeRandomBallNumber();

		Player player = new Player();
		player.setBaseBallNumber(baseBallNumber);
		player.setBaseBallNumber(changeBaseBallNumber);

		assertThat(player.getBaseBallNumber()).isEqualTo(changeBaseBallNumber);

	}

}
