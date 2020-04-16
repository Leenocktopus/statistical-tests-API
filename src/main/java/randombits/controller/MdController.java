package randombits.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import randombits.service.MdService;
import randombits.util.MdFactory;

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

    @PostMapping("/{test}")
    Object testSequence(@PathVariable("test") MdFactory method, @RequestBody Map<String, String> map) {
        return mdService.computeMdStatistic(method, map);
    }
}
