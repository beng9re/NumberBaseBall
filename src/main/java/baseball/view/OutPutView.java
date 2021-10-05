package baseball.view;

import baseball.domain.BaseBallResult;

public class OutPutView {

	public void resultBaseBall(BaseBallResult baseBallResult) {
		System.out.println(baseBallResult.makeResultMsg());
	}

	public void collectMsg() {
		System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 끝");
	}

	public void reInput(String msg) {
		System.out.println(msg + "");
	}



}
