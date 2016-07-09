package com.elangzhi.modules.record.app;

import com.elangzhi.modules.record.services.RecordService;
import com.elangzhi.ssm.controller.json.Tip;
import com.elangzhi.ssm.model.*;
import com.elangzhi.ssm.tools.Const;
import com.elangzhi.ssm.tools.UUIDFactory;
import com.github.pagehelper.PageInfo;
import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
* 时间轴 客户端 Controller
* @author GaoXiang
* @version 1.0
*/
@Controller
@RequestMapping("/app/record")
public class AppRecordController {

    @RequestMapping(value = "/listByUserId", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取动态记录",  notes = "获取某人的动态（分页）")
    public Tip<PageInfo<Record>> listByMy(
            @ApiParam(name = "page",value = "第几页")
            @RequestParam Long userId,
            @ApiParam(name = "page",value = "第几页")
            @RequestParam Integer page,
            @ApiParam(name = "id",value = "每页大小")
            @RequestParam Integer size
    ){
        PageInfo<Record> targetPageInfo = recordService.listByUserId(userId,page,size);
        return new Tip<>(targetPageInfo);
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "提交动态",  notes = "提交某个挑战下的动态")
    public Tip<Record> submit(
            @ApiParam(name = "record",value = "提交 title/content/targetId")
            @RequestBody Record record,
            @ApiIgnore HttpSession session
    ){
        try {
            record.setId(UUIDFactory.getLongId());
            record.setSetTime(new Date());
            record.setUserId(((User)session.getAttribute(Const.USER)).getId());
            recordService.insert(record);
            return new Tip<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new Tip<>(1);
        }

    }




    //---------------------------- property -------------------------------

    @Resource
    private RecordService recordService;

}
