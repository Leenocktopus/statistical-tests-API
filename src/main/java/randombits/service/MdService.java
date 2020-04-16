package randombits.service;

import randombits.util.MdFactory;

import java.util.Map;

public interface MdService {
    Object computeMdStatistic(MdFactory method, Map<String, String> testParams);
}
