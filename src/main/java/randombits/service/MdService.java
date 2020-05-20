package randombits.service;

import randombits.util.MdFactory;
import randombits.util.MdResult;

import java.util.List;
import java.util.Map;

public interface MdService {
    MdResult computeMdStatistic(MdFactory method, Map<String, String> testParams);

    Map<String, MdResult> computeMdStatisticForAll(List<MdFactory> methods, Map<String, String> map);

}

