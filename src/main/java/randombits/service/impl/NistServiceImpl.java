package randombits.service.impl;

import org.springframework.stereotype.Service;
import randombits.service.NistService;
import randombits.util.NistFactory;

import java.util.Map;

@Service
public class NistServiceImpl implements NistService {

    @Override
    public Object computeNistStatistic(NistFactory method, Map<String, String> testParams) {
        return method.getResult(testParams);
    }
}
