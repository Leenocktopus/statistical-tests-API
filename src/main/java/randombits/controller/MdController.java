package randombits.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import randombits.service.MdService;
import randombits.util.MdFactory;
import randombits.util.MdResult;

import java.util.List;
import java.util.Map;

/**
 * @author Alexey Raichev
 * @since 15.04.2020
 * Controller for Multidimensional Statistics tests API
 */
@RestController
@RequestMapping("/md_test")
public class MdController {

    MdService mdService;

    @Autowired
    public MdController(MdService mdService) {
        this.mdService = mdService;
    }


    @PostMapping
    Map<String, MdResult> testSequenceAll(@RequestParam("tests") List<MdFactory> methods, @RequestBody Map<String, String> params) {
        return mdService.computeMdStatisticForAll(methods, params);
    }

    @PostMapping("/{test}")
    MdResult testSequence(@PathVariable("test") MdFactory method, @RequestBody Map<String, String> params) throws JsonProcessingException {
        return mdService.computeMdStatistic(method, params);
    }



}
