package api.parser;

import api.Region;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ActiveRegionsParser implements JSONParser<List<Region>>{

    /**
     * Parses regions a user played on.
     * @param json with regions to parse.
     * @return list of parsed regions.
     */
    @Override
    public List<Region> parse(JSONObject json) {
        List<Region> regions = new ArrayList<>();
        for(Region region : Region.values()){
            if(!json.isNull(region.getRegionKey())){ //means that if a value for a region's key is null - user has not played on that region yet.
                regions.add(region);
            }
        }
        return regions;
    }
}
