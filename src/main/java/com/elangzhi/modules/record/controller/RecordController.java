package com.elangzhi.modules.record.controller;

import com.elangzhi.ssm.controller.AdminBaseController;
import com.elangzhi.ssm.model.Record;
import com.elangzhi.modules.record.services.RecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;

/**
* 时间轴 Controller
* @author GaoXiang
* @version 1.0
*/
@Controller
@RequestMapping("/record")
public class RecordController extends AdminBaseController<Record> {







    //---------------------------- property -------------------------------

    @Resource
    private RecordService recordService;

}
