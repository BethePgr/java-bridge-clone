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
        System.out.println("다리 길이를 입력해주세요.");
        String input = Console.readLine();
        try{
            validSize(input);
        }catch(IllegalArgumentException e){
            System.out.println("[ERROR] 다리 길이는 숫자여야 합니다.");
        }
        return Integer.parseInt(input);
    }

    private void validSize(String input){
        for(char c  :input.toCharArray()){
            checkDigit(c);
        }
    }

    private void checkDigit(char c) {
        if(!Character.isDigit(c) || c == '0'){
            throw new IllegalArgumentException("[ERROR] 다리의 길이는 자연수입니다.");
        }
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public String readMoving() {
        String input = Console.readLine();
        validCommand(input);
        return input;
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public String readGameCommand(){
        String input = Console.readLine();
        validCommand(input);
        return input;
    }

    private void validCommand(String input){
        try{
            for(char c : input.toCharArray()){
                checkUpperCase(c);
            }
        }catch(IllegalArgumentException e){
            System.out.println("[ERROR] 이동 값은 대문자 알파벳입니다.");
        }
    }

    private void checkUpperCase(char input) {
        if(!Character.isUpperCase(input)){
            throw new IllegalArgumentException();
        }
    }



}
