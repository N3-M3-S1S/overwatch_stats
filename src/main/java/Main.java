import api.parser.JSONParser;
import api.Region;
import api.client.ApacheHttpOWAPIClient;
import api.client.OWAPIClient;
import api.parser.ActiveRegionsParser;
import api.statistics.OverallStatistics;
import api.parser.OverallStatisticsParser;
import api.client.ResponseStatus;
import api.parser.ResponseStatusParser;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONObject;
import reporters.OverallStatisticsReporter;
import reporters.SystemOutOverallStatisticsReporter;
import utils.BattleTagValidator;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static Scanner scanner;
    private static JSONParser<OverallStatistics> overallStatisticsParser;
    private static OverallStatisticsReporter overallStatisticsReporter;
    private static OWAPIClient owapiClient;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        overallStatisticsParser = new OverallStatisticsParser();
        overallStatisticsReporter = new SystemOutOverallStatisticsReporter();
        owapiClient = new ApacheHttpOWAPIClient();
        JSONParser<List<Region>> regionsParser = new ActiveRegionsParser();
        JSONParser<ResponseStatus> responseStatusParser = new ResponseStatusParser();

        while (true) {
            String battleTag = askForBattleTag(); // get user's BattleTag
            JSONObject owapiResponse = queryOWAPIForStats(battleTag); // query the api.
            ResponseStatus responseStatus = responseStatusParser.parse(owapiResponse); //parse the API's response status.

            if (responseStatus == ResponseStatus.OK) { //if the response has no errors
                List<Region> activeRegions = regionsParser.parse(owapiResponse); //get regions a user played on.
                activeRegions.forEach(region -> parseAndPrintOverallStatisticsForRegion(owapiResponse, region)); //print statistics for each region.
            }
            else { //if there's an error in the response - report and ask for retry.
                System.out.println(responseStatus.getStatusMessage());
                retryOrExit();
            }
        }

    }

    /**
     * Prompt a user for a BattleTag and verify whether the BattleTag is in the correct format. Repeats until the user enters the BattleTag in the correct format.
     * @return user's BattleTag.
     */
    private static String askForBattleTag(){
        while (true){
            System.out.print("Enter your BattleTag in format 'nickname-12345': "); //show a hint about BattleTag format.
            String battleTag = scanner.nextLine(); //get user's input
            if(BattleTagValidator.isBattleTagCorrect(battleTag)) //Validate the input and if the one is correct - return it.
                return battleTag;
            else //If the input isn't correct - print about it.
                System.out.println("Incorrect BattleTag format.");
        }
    }

    /**
     * Performs a query to OWAPI. If there are errors with query - prints and asking for retry.
     * @param battleTag assuming that the BattleTag is in correct format.
     * @return JSON response from OWAPI.
     */
    private static JSONObject queryOWAPIForStats(String battleTag){
        while (true){
            try {
                return owapiClient.getStatistics(battleTag); //try to perform a query.

            }
            catch (ConnectTimeoutException | SocketTimeoutException c){ //catch timeouts exceptions. Both are treated the same way.
                System.out.println("Connection timeout");
                retryOrExit();
            }
            catch (IOException e) { //can be thrown if there's no internet connection or connection has been interrupted during query.
                System.out.println("Can't connect to api. Check internet connection.");
                retryOrExit();
            }
        }
    }

    /**
     * Asking a user whether he wants to retry an operation.
     * If answer is not "Y" - stops the program.
     */
    private static void retryOrExit() {
        System.out.print("Retry(Y\\n)?");
        String input = scanner.nextLine().toLowerCase();
        if(!input.equals("y"))
            System.exit(1);
    }


    private static void parseAndPrintOverallStatisticsForRegion(JSONObject owapiResponse, Region region){
        JSONObject statisticsForRegionJSON = owapiResponse.getJSONObject(region.getRegionKey()); //get json object with statistics for a region.
        OverallStatistics overallStatisticsForRegion = overallStatisticsParser.parse(statisticsForRegionJSON); //parse statistics
        System.out.printf("Overall statistics for region %s \n", region);
        overallStatisticsReporter.report(overallStatisticsForRegion); // print statistics report to system.out.
    }






}
