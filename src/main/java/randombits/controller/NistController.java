package randombits.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import randombits.service.NistService;
import randombits.util.NistFactory;

import java.util.Map;

/**
 * @author Alexey Raichev
 * @since 15.04.2020
 * Controller for NIST tests API
 */
@RestController
@RequestMapping("/nist_test")
public class NistController {

    NistService nistService;

    @Autowired
    public NistController(NistService nistService) {
        this.nistService = nistService;
    }

    @PostMapping
    Map<String, Object> testSequenceAll(@RequestBody Map<String, Map<String, String>> params) {
        return nistService.computeNistStatisticForAll(params);
    }

    @PostMapping("/{test}")
    Object testSequence(@PathVariable("test") NistFactory method, @RequestBody Map<String, String> params) {
        return nistService.computeNistStatistic(method, params);
    }




}
