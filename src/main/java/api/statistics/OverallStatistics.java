package api.statistics;


/**
 * Statistics contains wins, loses etc.
 * Competitive stats are for current season.
 */
public class OverallStatistics {
    private int competitiveWins, competitiveLoses, competitiveTies, competitiveGamesCount, competitiveScore;
    private int quickWins, quickLoses, quickGamesCount;

    public OverallStatistics(int competitiveWins, int competitiveLoses, int competitiveTies, int competitiveScore, int quickWins, int quickLoses) {
        this.competitiveWins = competitiveWins;
        this.competitiveLoses = competitiveLoses;
        this.competitiveTies = competitiveTies;
        this.quickWins = quickWins;
        this.quickLoses = quickLoses;
        this.competitiveScore = competitiveScore;
        competitiveGamesCount = competitiveWins + competitiveLoses + competitiveTies;
        quickGamesCount = quickWins + quickLoses;
    }

    public int getCompetitiveGamesCount(){
        return competitiveGamesCount;
    }

    public int getCompetitiveWins() {
        return competitiveWins;
    }

    public int getCompetitiveLoses() {
        return competitiveLoses;
    }

    public int getCompetitiveTies() {
        return competitiveTies;
    }

    public int getCompetitiveScore(){
        return competitiveScore;
    }

    public String getCompetitiveTier(){
        if (competitiveScore < 1500) return "Bronze";
        else if (competitiveScore >= 1500 && competitiveScore < 2000) return "Silver";
        else if (competitiveScore >= 2000 && competitiveScore < 2500) return "Gold";
        else if (competitiveScore >= 2500 && competitiveScore < 3000) return "Platinum";
        else if (competitiveScore >= 3000 && competitiveScore < 3500) return "Diamond";
        else if (competitiveScore >= 3500 && competitiveScore < 4000) return "Master";
        else return "Grandmaster";
    }

    public int getQuickGamesCount(){
        return quickGamesCount;
    }

    public int getQuickWins() {
        return quickWins;
    }

    public int getQuickLoses() {
        return quickLoses;
    }

    public float getCompetitiveWinRate(){
        return calculateWinRate(competitiveWins, competitiveGamesCount);
    }

    public float getQuickWinRate(){
        return calculateWinRate(quickWins, quickGamesCount);
    }

    public int getOverallWins(){
        return getQuickWins() + getCompetitiveWins();
    }

    public int getOverallLoses(){
        return getQuickLoses() + getCompetitiveLoses();
    }

    public int getOverallGamesCount(){
        return getOverallWins() + getOverallLoses();
    }

    public float getOverallWinRate(){
        return calculateWinRate(getOverallWins(), getOverallGamesCount());
    }

    private float calculateWinRate(int wins, int gamesCount){
        return (wins * 100f) / gamesCount;
    }

}
