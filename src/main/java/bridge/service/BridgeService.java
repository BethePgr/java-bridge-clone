package bridge.service;

import bridge.domain.BridgeGame;
import bridge.domain.BridgeMaker;
import bridge.domain.BridgeRandomNumberGenerator;
import java.util.ArrayList;
import java.util.List;

public class BridgeService {

    private final BridgeMaker bridgeMaker;

    public BridgeService(){
        this.bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
    }

    public List<String> initBridge(int size){
        validSize(size);
        return bridgeMaker.makeBridge(size);
    }

    private void validSize(int size) {
        if(size < 3 || size > 20){
            throw new IllegalArgumentException("[ERROR] 다리 길이는 3과 20사이입니다.");
        }
    }

    public List<List<String>> initGameMap(){
        List<List<String>> moveResult = new ArrayList<>();
        moveResult.add(new ArrayList<>());
        moveResult.add(new ArrayList<>());
        return moveResult;
    }

    public void moveBridge(String moving, BridgeGame bridgeGame,List<List<String>> moveResult){
        moveResult = bridgeGame.move(moving ,moveResult);
    }
}
