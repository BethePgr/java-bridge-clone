package bridge.controller;

import bridge.domain.BridgeGame;
import bridge.service.BridgeService;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class BridgeController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final BridgeService bridgeService;

    public BridgeController(){
        this.bridgeService = new BridgeService();
    }

    public void start(){
        List<String> bridge = receiveBridgeSize();
        BridgeGame bridgeGame = new BridgeGame(bridge);
        List<List<String>> currentMap = startBridgeGame(bridgeGame);
        outputView.printGameResult(currentMap,bridgeGame);
    }

    private List<List<String>> startBridgeGame(BridgeGame bridgeGame) {
        boolean playGame = true;
        List<List<String>> currentMap = new ArrayList<>();
        while(playGame){
            bridgeGame.retry();
            currentMap = makeResultMap(bridgeGame);
            if(currentMap.get(0).size() == bridgeGame.getBridgeSize()){
                return currentMap;
            }
            playGame = askRetry();

        }
        return currentMap;
    }

    private boolean askRetry() {
        try{
            String retryInput = inputView.readGameCommand();
            return bridgeService.retryOrEnd(retryInput);
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            return askRetry();
        }
    }

    private List<List<String>> makeResultMap(BridgeGame bridgeGame) {
        List<List<String>> moveMap = bridgeService.initGameMap();
        while(!failedClear(moveMap) && moveMap.get(0).size() < bridgeGame.getBridgeSize()){
            crossBridge(bridgeGame,moveMap);
        }
        return moveMap;
    }

    private boolean failedClear(List<List<String>> currentMap) {
        List<String> upMap = currentMap.get(0);
        List<String> downMap = currentMap.get(1);
        return (checkInCorrect(upMap) || checkInCorrect(downMap));
    }

    private boolean checkInCorrect(List<String> map) {
        return map.stream().anyMatch(m -> m.contains("X"));
    }

    private void crossBridge(BridgeGame bridgeGame, List<List<String>> currentMap) {
        try{
            String moving = inputView.readMoving();
            bridgeService.moveBridge(moving,bridgeGame,currentMap);
            outputView.printMap(currentMap);
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            crossBridge(bridgeGame,currentMap);
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
