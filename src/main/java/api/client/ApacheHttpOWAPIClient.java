package api.client;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;

public class ApacheHttpOWAPIClient implements OWAPIClient {

    @Override
    public JSONObject getStatistics(String btag) throws IOException{
        HttpClient httpClient = HttpClients.createDefault();
        String statisticsRequestURL = String.format("https://owapi.net/api/v3/u/%1$s/stats", btag);
        HttpResponse response = httpClient.execute(makeGETRequest(statisticsRequestURL));
        return new JSONObject(EntityUtils.toString(response.getEntity())); //get json from response.
    }

    private HttpGet makeGETRequest(String requestURL){
        int requestTimeout = 30000; //30 sec.
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(requestTimeout) //set timeouts.
                .setConnectTimeout(requestTimeout).build();
        HttpGet request = new HttpGet(requestURL);
        request.setConfig(requestConfig);
        return request;
    }

}
