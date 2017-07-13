package api.parser;

import api.Region;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static api.Region.*;
import static org.junit.Assert.assertTrue;

public class ActiveRegionsParser_Test {
   private JSONObject testResponse;

    //assuming user played only on Asia.
    @Before
    public void prepareTestingJSON(){
        testResponse = new JSONObject();
        testResponse.put(US.getRegionKey(), JSONObject.NULL);
        testResponse.put(EU.getRegionKey(), JSONObject.NULL);
        testResponse.put(AS.getRegionKey(), new JSONObject());
    }


    @Test
    public void getActiveRegions() throws Exception {
        ActiveRegionsParser parser = new ActiveRegionsParser();
        List<Region> activeRegions = parser.parse(testResponse);
        assertTrue(activeRegions.size() == 1);
        assertTrue(activeRegions.get(0).equals(AS));
    }

}