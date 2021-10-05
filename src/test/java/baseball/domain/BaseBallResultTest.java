package baseball.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BaseBallResultTest {

	@Test
	@DisplayName("getStatus를 호출하면 성공상태와 힌트 제공상태를 반환한다.")
	void getStatus() {
		BaseBallResult baseBallResult = new BaseBallResult();
		baseBallResult.setBall(3);
		Assertions.assertThat(baseBallResult.getStatus()).isEqualTo(BaseBallResultStatus.HINT);
		Assertions.assertThatIllegalArgumentException().isThrownBy(() -> baseBallResult.setStrike(3));

		baseBallResult.setBall(0);
		baseBallResult.setStrike(3);
		Assertions.assertThat(baseBallResult.getStatus()).isEqualTo(BaseBallResultStatus.COLLECT);
	}

}