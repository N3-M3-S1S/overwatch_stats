package api.client;

import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class OWAPIClientTest {

    @Test
    public void testGetStatisticJSON() throws Exception{
        OWAPIClient client = new ApacheHttpOWAPIClient();
        JSONObject statistics = client.getStatistics("Nemesis-22872").getJSONObject("eu").getJSONObject("stats"); //using my BattleTag here. Add me to friends ;).
        assertTrue(statistics!=null);
        assertTrue(statistics.has("competitive"));
        assertTrue(statistics.has("quickplay"));
    }

}