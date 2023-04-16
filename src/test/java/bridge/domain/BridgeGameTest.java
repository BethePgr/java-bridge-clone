package bridge.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BridgeGameTest {

    List<String> bridge;
    BridgeGame bridgeGame;
    List<List<String>> gameProgress;

    @BeforeEach
    void setUp(){
        bridge = new ArrayList<>(List.of("U","D","U"));
        bridgeGame = new BridgeGame(bridge);
        gameProgress = new ArrayList<>();
        gameProgress.add(new ArrayList<>());
        gameProgress.add(new ArrayList<>());

    }

    @Test
    @DisplayName("결과 반영 테스트")
    void moveTest() throws Exception{
        //given
        String input1 = "U";
        String input2 = "D";
        String input3 = "U";
        List<List<String>> expect = new ArrayList<>();
        expect.add(List.of("O"," ","O"));
        expect.add(List.of(" ","O"," "));
        bridgeGame.move(input1,gameProgress);
        bridgeGame.move(input2,gameProgress);
        bridgeGame.move(input3,gameProgress);

        //then
        assertEquals(expect,gameProgress);
    }

    @Test
    @DisplayName("게임 통과 실패시 결과")
    void incorrectMoveTest() throws Exception{
        //given
        String input1 = "U";
        String input2 = "U";
        List<List<String>> expect = new ArrayList<>();
        expect.add(List.of("O","X"));
        expect.add(List.of(" "," "));
        //when
        bridgeGame.move(input1,gameProgress);
        bridgeGame.move(input2,gameProgress);
        //then
        assertEquals(expect,gameProgress);
    }

}