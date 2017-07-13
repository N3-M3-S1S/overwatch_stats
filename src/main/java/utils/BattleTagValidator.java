package utils;

import java.util.regex.Pattern;

public class BattleTagValidator {

    /**
     * Validates whether a given BattleTag is in the correct format.
     * @param battleTag given by a user.
     * @return true if the BattleTag is in the correct format. False otherwise.
     */
    public static boolean isBattleTagCorrect(String battleTag){
        /*The correct pattern of the BattleTag from https://eu.battle.net/support/ru/article/battletag-naming-policy.
           Explanation:
           ^\\pL - BattleTag must start only from a letter.
           \\w{2,11} - contains from 3 to 12 symbols, but first symbol is already used so bounds must be 2 - 11.
           -\d+$ - a numeric part.
         */
        String battleTagPattern = "^\\pL\\w{2,11}-\\d+$";
        return Pattern.compile(battleTagPattern).matcher(battleTag).find();
    }
}
