package randombits.service.impl;

import org.springframework.stereotype.Service;
import randombits.service.MdService;
import randombits.util.MdFactory;
import randombits.util.MdResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class MdServiceImpl implements MdService {

    @Override
    public MdResult computeMdStatistic(MdFactory method, Map<String, String> testParams) {
        return method.getResult(testParams);
    }

    @Override
    public Map<String, MdResult> computeMdStatisticForAll(List<MdFactory> methods, Map<String, String> map) {
        Map<String, MdResult> results = new LinkedHashMap<>();
        for (MdFactory method: methods){
            results.put(method.toString().toLowerCase(), method.getResult(map));
        }
        return results;
    }
}
