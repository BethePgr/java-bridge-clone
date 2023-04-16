package bridge.service;

import bridge.domain.BridgeMaker;
import bridge.domain.BridgeRandomNumberGenerator;
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
}
