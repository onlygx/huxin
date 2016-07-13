package com.elangzhi.modules.target.app;

import com.elangzhi.modules.target.services.TargetService;
import com.elangzhi.modules.targetSupervise.services.TargetSuperviseService;
import com.elangzhi.modules.user.services.UserService;
import com.elangzhi.ssm.controller.json.Tip;
import com.elangzhi.ssm.model.Target;
import com.elangzhi.ssm.model.TargetSupervise;
import com.elangzhi.ssm.model.User;
import com.elangzhi.ssm.tools.Const;
import com.elangzhi.ssm.tools.UUIDFactory;
import com.github.pagehelper.PageInfo;
import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
* 挑战 客户端 Controller
* @author GaoXiang
* @version 1.0
*/
@Controller
@RequestMapping("/app/target")
@Api(value = "挑战", description = "挑战发起、监督")
public class AppTargetController {

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "提交挑战",  notes = "发起挑战")
    public Tip<Target> submit(
            @ApiParam(name = "target",value = "提交 title/content/keep/price")
            @RequestBody Target target,
            @ApiParam(name = "supervise",value = "监督员id，用英文半角逗号拼接，不能少于10个")
            @RequestParam String supervise,
            @ApiIgnore HttpSession session
    ){
        try {
            target.setId(UUIDFactory.getLongId());
            target.setSetTime(new Date());
            target.setType(1);
            target.setStatus(1);
            target.setOpinion(2);
            target.setUserId(((User)session.getAttribute(Const.USER)).getId());

            for(String s : supervise.split(",")){
                TargetSupervise ts = new TargetSupervise();
                ts.setId(UUIDFactory.getLongId());
                ts.setUserId(Long.valueOf(s));
                ts.setOpinion(2);
                ts.setTargetId(target.getId());
                targetSuperviseService.insert(ts);
            }
            targetService.insert(target);
            return new Tip<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new Tip<>(1);
        }

    }

    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "挑战详情",  notes = "根据ID获取挑战详情")
    public Tip<Target> findById(
            @ApiParam(name = "id",value = "挑战id")
            @RequestParam Long id
    ){
        try {
            Target target = targetService.selectById(id);
            List<TargetSupervise> targetSuperviseList = targetSuperviseService.listByTargetId(id);
            for(TargetSupervise ts : targetSuperviseList){
                ts.setUser(userService.selectById(ts.getUserId()));
            }
            target.setSuperviseList(targetSuperviseList);
            return new Tip<>(target);
        } catch (Exception e) {
            e.printStackTrace();
            return new Tip<>(1);
        }
    }


    @RequestMapping(value = "/listTarget", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "发现挑战列表",  notes = "发现挑战列表（分页）")
    public Tip<PageInfo<Target>> listTarget(
            @ApiIgnore HttpSession session,
            @ApiParam(name = "page",value = "第几页")
            @RequestParam Integer page,
            @ApiParam(name = "id",value = "每页大小")
            @RequestParam Integer size
    ){
        PageInfo<Target> targetPageInfo = null;
        try {
            targetPageInfo = targetService.list(null,page,size);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Tip<>(targetPageInfo);
    }

    @RequestMapping(value = "/listByMy", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "我的挑战列表",  notes = "获取我的挑战列表（分页）")
    public Tip<PageInfo<Target>> listByMy(
            @ApiIgnore HttpSession session,
            @ApiParam(name = "page",value = "第几页")
            @RequestParam Integer page,
            @ApiParam(name = "id",value = "每页大小")
            @RequestParam Integer size
    ){
        User user = (User) session.getAttribute(Const.USER);
        PageInfo<Target> targetPageInfo = targetService.listByUserId(user.getId(),page,size);
        return new Tip<>(targetPageInfo);
    }

    @RequestMapping(value = "/listSuperviseByMy", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "我监督的",  notes = "我监督的挑战列表（分页）")
    public Tip<PageInfo<Target>> listSuperviseByMy(
                                         @ApiIgnore HttpSession session,
                                         @ApiParam(name = "page",value = "第几页")
                                         @RequestParam Integer page,
                                         @ApiParam(name = "id",value = "每页大小")
                                         @RequestParam Integer size){
        //TODO
        User user = (User) session.getAttribute(Const.USER);
        PageInfo<Target> targetPageInfo = targetService.listByUserId(user.getId(),page,size);
        return new Tip<>(targetPageInfo);
    }




    //---------------------------- property -------------------------------

    @Resource
    private TargetService targetService;

    @Resource
    private TargetSuperviseService targetSuperviseService;

    @Resource
    private UserService userService;
}
