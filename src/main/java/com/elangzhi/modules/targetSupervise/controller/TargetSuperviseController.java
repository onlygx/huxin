package com.elangzhi.modules.targetSupervise.controller;

import com.elangzhi.ssm.controller.AdminBaseController;
import com.elangzhi.ssm.model.TargetSupervise;
import com.elangzhi.modules.targetSupervise.services.TargetSuperviseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;

/**
* 挑战监督员 Controller
* @author GaoXiang
* @version 1.0
*/
@Controller
@RequestMapping("/targetSupervise")
public class TargetSuperviseController extends AdminBaseController<TargetSupervise> {







    //---------------------------- property -------------------------------

    @Resource
    private TargetSuperviseService targetSuperviseService;

}
