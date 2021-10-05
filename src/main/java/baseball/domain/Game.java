package baseball.domain;

public class Game {
	private GameStatus status = GameStatus.EXIT;
	private static final int STOP = 2;
	private static final int START = 1;

	public GameStatus getStatus() {
		return status;
	}

	public void start() {
		status = GameStatus.START;
	}

	public void stop() {
		status = GameStatus.EXIT;
	}

	public void changeGameStatus(String commandString) throws IllegalArgumentException {
		int command = validateCommand(commandString);
		if (command == STOP) {
			stop();
			return;
		}
		start();
	}

	private int validateCommand(String commandString) {
		int command = -9;
		try {
			command	= Integer.parseInt(commandString);
			if ( command != STOP && command != START) {
				throw new IllegalArgumentException("1 , 2의 숫자만 가능합니다.");
			}
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("숫자만 가능합니다.");
		}
		return command;
	}

}
