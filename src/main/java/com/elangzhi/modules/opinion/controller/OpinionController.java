package com.elangzhi.modules.opinion.controller;

import com.elangzhi.ssm.controller.AdminBaseController;
import com.elangzhi.ssm.model.Opinion;
import com.elangzhi.modules.opinion.services.OpinionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
* 产品意见 Controller
* @author GaoXiang
* @version 1.0
*/
@Controller
@RequestMapping("/opinion")
public class OpinionController extends AdminBaseController<Opinion> {







    //---------------------------- property -------------------------------

    @Resource
    private OpinionService opinionService;

}
