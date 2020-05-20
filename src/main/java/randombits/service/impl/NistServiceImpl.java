package randombits.service.impl;

import org.springframework.stereotype.Service;
import randombits.service.NistService;
import randombits.util.MdFactory;
import randombits.util.NistFactory;
import utils.TestResult;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class NistServiceImpl implements NistService {

    @Override
    public Object computeNistStatistic(NistFactory method, Map<String, String> testParams) {
        return method.getResult(testParams);
    }

    @Override
    public Map<String, Object> computeNistStatisticForAll(Map<String, Map<String, String>> testParams) {
        Map<String, Object> results = new LinkedHashMap<>();
        for (Map.Entry<String, Map<String, String>> map: testParams.entrySet()){
            NistFactory method = NistFactory.valueOf(map.getKey().toUpperCase());
            results.put(method.toString().toLowerCase(), method.getResult(map.getValue()));
        }
        return results;
    }

}
