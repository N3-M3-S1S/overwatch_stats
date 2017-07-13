package reporters;

import api.statistics.OverallStatistics;


public class SystemOutOverallStatisticsReporter implements OverallStatisticsReporter {


    /**
     * Prints overall statistics report to System.out.
     * @param overallStatistics to print.
     */
    @Override
    public void report(OverallStatistics overallStatistics) {
        String delimiter = "===========================================\n";
        String totalPlayed = "Total played: %d\n";
        String wins = "Wins: %d\n";
        String loses = "Losses: %d\n";
        String winRate = "Win rate: %.1f%%\n";
        String report = delimiter +
                "Total\n" +
                String.format(totalPlayed, overallStatistics.getOverallGamesCount()) +
                String.format(wins, overallStatistics.getOverallWins()) +
                String.format(loses, overallStatistics.getOverallLoses()) +
                String.format(winRate, overallStatistics.getOverallWinRate()) +
                delimiter +
                "Competitive (current season)\n" +
                String.format(totalPlayed, overallStatistics.getCompetitiveGamesCount()) +
                String.format(wins, overallStatistics.getCompetitiveWins()) +
                String.format(loses, overallStatistics.getCompetitiveLoses()) +
                String.format("Ties: %d\n", overallStatistics.getCompetitiveTies()) +
                String.format(winRate, overallStatistics.getCompetitiveWinRate()) +
                String.format("Tier: %s\n", overallStatistics.getCompetitiveTier()) +
                String.format("Score: %d\n", overallStatistics.getCompetitiveScore()) +
                delimiter +
                "Quick games\n" +
                String.format(totalPlayed, overallStatistics.getQuickGamesCount()) +
                String.format(wins, overallStatistics.getQuickWins()) +
                String.format(loses, overallStatistics.getQuickLoses()) +
                String.format(winRate, overallStatistics.getQuickWinRate());
        System.out.println(report);
    }
}
