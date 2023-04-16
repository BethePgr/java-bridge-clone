package bridge.controller;

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
        int bridgeSize = inputView.readBridgeSize();
        List<String> bridge = receiveBridgeSize();
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
