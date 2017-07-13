package reporters;

import api.statistics.OverallStatistics;

public interface OverallStatisticsReporter {
    void report(OverallStatistics overallStatistics);
}
