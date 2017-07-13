package api;

import api.statistics.OverallStatistics;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;


public class OverallStatisticsTest {
    @Test
    public void getCompetitiveTier() throws Exception {
        ThreadLocalRandom randomizer = ThreadLocalRandom.current();
        OverallStatistics bronzeTierStat = makeStatisticForTierTest(randomizer.nextInt(0, 1500));
        OverallStatistics silverTierStat = makeStatisticForTierTest(randomizer.nextInt(1500, 2000));
        OverallStatistics goldTierStat = makeStatisticForTierTest(randomizer.nextInt(2000, 2500));
        OverallStatistics platinumTierStat = makeStatisticForTierTest(randomizer.nextInt(2500, 3000));
        OverallStatistics diamondTierStat = makeStatisticForTierTest(randomizer.nextInt(3000, 3500));
        OverallStatistics masterTierStat = makeStatisticForTierTest(randomizer.nextInt(3500, 4000));
        OverallStatistics grandmasterTierStat = makeStatisticForTierTest(randomizer.nextInt(4000, 5001));
        assertTrue(bronzeTierStat.getCompetitiveTier().equals("Bronze"));
        assertTrue(silverTierStat.getCompetitiveTier().equals("Silver"));
        assertTrue(goldTierStat.getCompetitiveTier().equals("Gold"));
        assertTrue(platinumTierStat.getCompetitiveTier().equals("Platinum"));
        assertTrue(diamondTierStat.getCompetitiveTier().equals("Diamond"));
        assertTrue(masterTierStat.getCompetitiveTier().equals("Master"));
        assertTrue(grandmasterTierStat.getCompetitiveTier().equals("Grandmaster"));
    }

    private OverallStatistics makeStatisticForTierTest(int score){
        return new OverallStatistics(0,0,0, score, 0,0);
    }

    @Test
    public void getWinRate() throws Exception {
        OverallStatistics s = new OverallStatistics(200, 100, 0 ,0,0,0);
        assertTrue((int) s.getCompetitiveWinRate() == 66);
    }



}