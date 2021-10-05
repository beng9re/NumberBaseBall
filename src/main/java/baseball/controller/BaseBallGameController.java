package baseball.controller;

import baseball.domain.BaseBallNumber;
import baseball.domain.BaseBallResult;
import baseball.domain.BaseBallResultStatus;
import baseball.domain.Computer;
import baseball.domain.Game;
import baseball.domain.GameStatus;
import baseball.domain.Player;
import baseball.view.InputView;
import baseball.view.OutPutView;

public class BaseBallGameController {
	private final InputView inputView;
	private final OutPutView outPutView;
	private Game game;
	private Computer computer;
	private Player player;

	public BaseBallGameController() {
		inputView = new InputView();
		outPutView = new OutPutView();
	}

	public void gameStart() {
		game = new Game();
		computer = new Computer();
		player = new Player();
		game.start();
		gameProgress(player, computer, game);
		if (game.getStatus() == GameStatus.START) {
			gameProgress(player, computer, game);
		}
		game.stop();
	}

	private void gameProgress(Player player, Computer computer, Game game) {
		playerQuestion(player);
		BaseBallResultStatus resultStatus = computerAnswer(computer, player);
		if (resultStatus == BaseBallResultStatus.COLLECT) {
			gameExitQuestion(game);
		}
	}

	private void gameExitQuestion(Game game) {
		String question = inputView.exitQuestion();
		try {
			game.changeGameStatus(question);
			if(game.getStatus() == GameStatus.START) {
				gameStart();
			}
		} catch (IllegalArgumentException e) {
			outPutView.reInput(e.getMessage());
			gameExitQuestion(game);
		}
	}

	private void playerQuestion(Player player) throws IllegalArgumentException {
		int[] playerInput = inputView.inputBaseBallNumber();
		try {
			player.setBaseBallNumber(new BaseBallNumber(playerInput));
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage() + "재 입력 해주세요");
			playerQuestion(player);
		}
	}

	private BaseBallResultStatus computerAnswer(Computer computer, Player player) {
		computer.getBaseBallNumber();
		BaseBallResult baseBallResult = computer.answerComputer(player.getBaseBallNumber());
		outPutView.resultBaseBall(baseBallResult);
		if (baseBallResult.getStatus() == BaseBallResultStatus.COLLECT) {
			outPutView.collectMsg();
		}
		return baseBallResult.getStatus();
	}



}
