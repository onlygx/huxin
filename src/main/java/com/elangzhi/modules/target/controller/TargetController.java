package com.elangzhi.modules.target.controller;

import com.elangzhi.ssm.controller.AdminBaseController;
import com.elangzhi.ssm.model.Target;
import com.elangzhi.modules.target.services.TargetService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;

/**
* 挑战 Controller
* @author GaoXiang
* @version 1.0
*/
@Controller
@RequestMapping("/target")
public class TargetController extends AdminBaseController<Target> {







    //---------------------------- property -------------------------------

    @Resource
    private TargetService targetService;

}
