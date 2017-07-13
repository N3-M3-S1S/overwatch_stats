
package api.parser;
import api.statistics.OverallStatistics;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.text.DecimalFormat;

import static org.junit.Assert.*;


public class OverallStatisticsParser_Test {
    private JSONParser<OverallStatistics> parser = new OverallStatisticsParser();
    private JSONObject TEST_STATISTICS_JSON = new JSONObject();
    private int TEST_WINS = 550;
    private int TEST_LOSSES = 350;
    private int TEST_TIES = 100;
    private int TEST_RANK = 3000; //diamond score


    @Before
    public void prepareTestStatisticsJSON(){
        TEST_STATISTICS_JSON.put("stats", new JSONObject());
        JSONObject stats = new JSONObject();
        stats.put("wins", TEST_WINS);
        stats.put("losses", TEST_LOSSES);
        stats.put("ties", TEST_TIES); //for competitive stats
        stats.put("comprank", TEST_RANK); //for competitive stats
        TEST_STATISTICS_JSON.getJSONObject("stats").put("competitive", new JSONObject().put("overall_stats", stats));
        TEST_STATISTICS_JSON.getJSONObject("stats").put("quickplay",  new JSONObject().put("overall_stats", stats));
    }


    @Test
    public void parseStatistics() throws Exception {
        OverallStatistics overallStatistics = parser.parse(TEST_STATISTICS_JSON);
        assertTrue(overallStatistics.getCompetitiveTier().equals("Diamond"));
        assertTrue(overallStatistics.getCompetitiveGamesCount() == TEST_WINS + TEST_LOSSES + TEST_TIES);
        assertTrue(overallStatistics.getQuickGamesCount() == TEST_WINS + TEST_LOSSES);

        //cut decimals to one digit after decimal point.
        assertTrue(new DecimalFormat("##.#").format(overallStatistics.getCompetitiveWinRate()),
                new DecimalFormat("##.#").format(overallStatistics.getCompetitiveWinRate()).equals("55"));
        assertTrue(new DecimalFormat("##.#").format(overallStatistics.getQuickWinRate()).equals("61.1"));
    }


}