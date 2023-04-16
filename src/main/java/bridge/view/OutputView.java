package bridge.view;

import bridge.domain.BridgeGame;
import java.util.List;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(List<List<String>> result) {
        String upBridge = createBridgeMap(result.get(0));
        String downBridge = createBridgeMap(result.get(1));
        System.out.println(upBridge);
        System.out.println(downBridge);

    }

    private String createBridgeMap(List<String> map) {

        StringBuilder stringBuilder = new StringBuilder().append("[");
        for(String point : map){
            stringBuilder.append(point).append("|");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.append("]").toString();
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printGameResult(List<List<String>> result, BridgeGame bridgeGame) {
        if(result.get(0).size() == bridgeGame.getBridgeSize()){
            printClearMap(result,bridgeGame.getRepeatCount());
        }if(result.get(0).size() != bridgeGame.getBridgeSize()){
            printFailedMap(result,bridgeGame.getRepeatCount());
        }
    }

    private void printFailedMap(List<List<String>> result, int repeatCount) {
        printResultIntro();
        printMap(result);
        System.out.println();
        System.out.println("게임 성공 여부 : 실패");
        System.out.printf("총 시도한 횟수 : %d\n",repeatCount);

    }

    private void printClearMap(List<List<String>> result, int repeatCount) {
        printResultIntro();
        printMap(result);
        System.out.println();
        System.out.println("게임 성공 여부 : 성공");
        System.out.printf("총 시도한 횟수 : %d\n",repeatCount);
    }

    private void printResultIntro() {
        System.out.println("최종 게임 결과");
    }
}
