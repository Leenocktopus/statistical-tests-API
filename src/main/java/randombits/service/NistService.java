package randombits.service;

import randombits.util.NistFactory;

import java.util.List;
import java.util.Map;

public interface NistService {
    Object computeNistStatistic(NistFactory method, Map<String, String> testParams);

    Map<String, Object> computeNistStatisticForAll(Map<String, Map<String, String>> testParams);
}
