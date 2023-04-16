package bridge.controller;

import bridge.domain.BridgeGame;
import bridge.service.BridgeService;
import bridge.view.InputView;
import java.util.ArrayList;
import java.util.List;

public class BridgeController {

    private InputView inputView = new InputView();
    private final BridgeService bridgeService;

    public BridgeController(){
        this.bridgeService = new BridgeService();
    }

    public void start(){
        List<String> bridge = receiveBridgeSize();
        BridgeGame bridgeGame = new BridgeGame(bridge);
        List<List<String>> result = makeResult(bridgeGame);

    }

    private List<List<String>> makeResult(BridgeGame bridgeGame) {
        List<List<String>> moveResult = bridgeService.initGameResult();
        while(!failedClear(moveResult) && moveResult.get(0).size() < 3){
            crossBridge(bridgeGame,moveResult);
        }
        return moveResult;
    }

    private boolean failedClear(List<List<String>> result) {
        return result.stream().anyMatch(board -> board.contains("X"));
    }

    private void crossBridge(BridgeGame bridgeGame, List<List<String>> result) {
        try{
            String moving = inputView.readMoving();
            bridgeService.moveBridge(moving,bridgeGame,result);
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            crossBridge(bridgeGame,result);
        }
    }



    private List<String> receiveBridgeSize() {
        try{
            int size = inputView.readBridgeSize();
            return bridgeService.initBridge(size);
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            return receiveBridgeSize();
        }
    }

}
