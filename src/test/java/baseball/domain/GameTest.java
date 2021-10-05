package baseball.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class GameTest {

	@Test
	@DisplayName("게임이 생성되면 종료 상태로 생성이 된다")
	void initGetStatus() {
		Game game = new Game();
		assertThat(game.getStatus()).isEqualTo(GameStatus.EXIT);
	}


	@Test
	@DisplayName("게임이 시작된다.")
	void start() {
		Game game = new Game();
		game.start();
		assertThat(game.getStatus()).isEqualTo(GameStatus.START);
	}

	@Test
	@DisplayName("게임이 종료 된다.")
	void stop() {
		Game game = new Game();
		game.start();
		game.stop();
		assertThat(game.getStatus()).isEqualTo(GameStatus.EXIT);
	}

	@ParameterizedTest
	@DisplayName("게임의 상태는 문자열 1,2만 가능하다.")
	@ValueSource(strings = {"1", "2", "3"})
	void changeGameStatus(String command) {
		Game game = new Game();

		if (command.equals("1") || command.equals("2")) {
			assertThatCode(()-> game.changeGameStatus(command)).doesNotThrowAnyException();
			return;
		}
		assertThatIllegalArgumentException().isThrownBy(()->game.changeGameStatus(command));
	}
}
