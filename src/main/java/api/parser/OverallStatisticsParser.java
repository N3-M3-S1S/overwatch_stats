package api.parser;


import api.statistics.OverallStatistics;
import org.json.JSONObject;


public class OverallStatisticsParser implements JSONParser<OverallStatistics> {

    /**
     * Parses overall statistics (wins, loses etc) for both competitive and quick play modes.
     * @param jsonWithStats json contains stats object.
     * @return {@link OverallStatistics}.
     */
    @Override
    public OverallStatistics parse(JSONObject jsonWithStats) {
        JSONObject statisticsJSON = jsonWithStats.getJSONObject("stats"); //get stats object.

        int competitiveWins=0, competitiveLoses = 0, competitiveTies = 0, competitiveScore = 0;
        int quickWins, quickLoses;

        JSONObject competitiveOverallStats = getOverallStatsByGameMode(statisticsJSON, "competitive");
        JSONObject quickOverallStats = getOverallStatsByGameMode(statisticsJSON, "quickplay");

        quickWins = quickOverallStats.getInt("wins");
        quickLoses = quickOverallStats.getInt("losses");

        if(competitiveOverallStats != null){ // a user may not have played competitive games.
            competitiveWins = competitiveOverallStats.getInt("wins");
            competitiveLoses = competitiveOverallStats.getInt("losses");
            competitiveTies = competitiveOverallStats.getInt("ties");
            competitiveScore = competitiveOverallStats.getInt("comprank");
        }
        return new OverallStatistics(competitiveWins, competitiveLoses, competitiveTies, competitiveScore, quickWins, quickLoses);
    }

    /**
     * Parses json with overall statistics for game mode.
     * @param statsJSON stats JSON object contains game modes objects.
     * @param gameModeKey key for a game mode.
     * @return JSON with overall statistics for a game mode.
     */
    private JSONObject getOverallStatsByGameMode(JSONObject statsJSON, String gameModeKey){
        return statsJSON.getJSONObject(gameModeKey).getJSONObject("overall_stats");
    }



}


