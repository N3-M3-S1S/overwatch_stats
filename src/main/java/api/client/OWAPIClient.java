package api.client;

import org.json.JSONObject;

import java.io.IOException;

/**
 * An interface for OWAPI Overwatch API methods.
 * https://github.com/SunDwarf/OWAPI/blob/master/api.md
 */
public interface OWAPIClient {

    /**
     * Query for GET /api/v3/u/:battletag/stats api method.
     * @param btag BattleTag.
     * @return  Statistics JSON.
     * @throws IOException for connection problems.
     */
    JSONObject getStatistics(String btag) throws IOException;
}

