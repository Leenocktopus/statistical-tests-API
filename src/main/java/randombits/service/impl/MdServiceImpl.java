package randombits.service.impl;

import org.springframework.stereotype.Service;
import randombits.service.MdService;
import randombits.util.MdFactory;

import java.util.Map;

@Service
public class MdServiceImpl implements MdService {

    @Override
    public Object computeMdStatistic(MdFactory method, Map<String, String> testParams) {
        return method.getResult(testParams);
    }
}
