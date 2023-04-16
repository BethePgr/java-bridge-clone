package bridge.view;

import camp.nextstep.edu.missionutils.Console;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {

    /**
     * 다리의 길이를 입력받는다.
     */
    public int readBridgeSize() {
        String input = Console.readLine();
        try{
            validSize(input);
        }catch(IllegalArgumentException e){
            System.out.println("[ERROR] 다리 길이는 숫자여야 합니다.");
        }
        return Integer.parseInt(input);
    }

    private void validSize(String input){
        int size = Integer.parseInt(input);
        if(size < 0){
            throw new IllegalArgumentException();
        }
    }
    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public String readMoving() {
        return null;
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public String readGameCommand() {
        return null;
    }
}
