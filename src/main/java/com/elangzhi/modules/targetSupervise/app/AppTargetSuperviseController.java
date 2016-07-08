package com.elangzhi.modules.targetSupervise.app;

import com.elangzhi.modules.targetSupervise.services.TargetSuperviseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;

/**
* 挑战监督员 客户端 Controller
* @author GaoXiang
* @version 1.0
*/
@Controller
@RequestMapping("/app/targetSupervise")
public class AppTargetSuperviseController {







    //---------------------------- property -------------------------------

    @Resource
    private TargetSuperviseService targetSuperviseService;

}
