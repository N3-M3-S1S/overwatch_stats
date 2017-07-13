import org.junit.Test;
import utils.BattleTagValidator;

import java.util.stream.Stream;

import static org.junit.Assert.*;

public class BattleTagVerifierTest {


    @Test
    public void testCorrectBattleTag() throws Exception {
        String correctBtag1 = "Nickname-12345";
        String correctBtag2 = "nicKName2-2";
        String correctBtag3 = "Nic-12345";
        Stream.of(correctBtag1, correctBtag2, correctBtag3)
                .forEach(battleTag -> assertTrue(BattleTagValidator.isBattleTagCorrect(battleTag)));
    }

    @Test
    public void testIncorrectBattleTag() throws Exception{
        String incorrectBtag1 = "1Nickname-12345";
        String incorrectBtag2 = "Ni-12345";
        String incorrectBtag3 = "NicknameNickname-12345";
        String incorrectBtag4 = "Nick-name-12345";
        String incorrectBtag5 = "Nickname12345";
        String incorrectBtag6 = "Nickname";
        String incorrectBtag7 = " Nick name-12345";
        Stream.of(incorrectBtag1, incorrectBtag2, incorrectBtag3,
                incorrectBtag4, incorrectBtag5, incorrectBtag6, incorrectBtag7)
                .forEach(battleTag -> assertFalse(BattleTagValidator.isBattleTagCorrect(battleTag)));
    }

}