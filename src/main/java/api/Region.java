package api;

/**
 * Represents playable regions.
 * There's also Public Test Region (PTR) but the API doesn't provide one. (Or may be i don't know how to get that region)
 */
public enum Region {
    EU, US, AS;

    @Override
    public String toString() {
        switch (this){
            case EU: return "Europe";
            case US: return "United States";
            case AS: return "Asia";
            default: return "";
        }
    }

    /**
     * @return JSON key name for a region.
     */
    public String getRegionKey(){
        switch (this){
            case EU: return "eu";
            case US: return "us";
            case AS: return "kr";
            default: return "";
        }
    }
}
