package com.elangzhi.modules.target.app;

import com.elangzhi.modules.money.services.MoneyService;
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
import java.util.*;

/**
* 挑战 客户端 Controller
* @author GaoXiang
* @version 1.0
*/
@Controller
@RequestMapping("/app/target")
@Api(value = "挑战", description = "挑战发起、监督")
public class AppTargetController {

    /**
     * 提交挑战信息并支付
     * @param supervise 监督员id
     * @param session session
     * @return 挑战信息
     * 1，系统错误
     * 2，余额不足
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "提交挑战",  notes = "发起挑战")
    public Tip<Target> submit(

            @ApiParam(name = "title",value = "标题")
            @RequestParam String title,
            @ApiParam(name = "content",value = "内容")
            @RequestParam String content,
            @ApiParam(name = "tag",value = "标签")
            @RequestParam String tag,
            @ApiParam(name = "keep",value = "保持天数")
            @RequestParam Integer keep,
            @ApiParam(name = "price",value = "保证金")
            @RequestParam Long price,
            @ApiParam(name = "supervise",value = "监督员id，用英文半角逗号拼接，不能少于10个")
            @RequestParam String supervise,
            @ApiIgnore HttpSession session
    ){
        try {
            Target target = new Target();
            //初始化挑战
            target.setId(UUIDFactory.getLongId());
            target.setSetTime(new Date());
            target.setType(1);
            target.setStatus(1);
            target.setOpinion(2);
            //填充参数
            target.setTitle(title);
            target.setContent(content);
            target.setTag(tag);
            target.setKeep(keep);
            target.setPrice(price);

            //判断余额，添加用户
            long value = Math.abs(target.getPrice());
            User user = (User)session.getAttribute(Const.USER);
            //验证余额
            int status = moneyService.insertByType(user.getId(),3,-value,target.getId());
            if(status == 2){
                return new Tip<>(2);
            }
            target.setUserId(user.getId());

            //添加监督员
            for(String s : supervise.split(",")){
                TargetSupervise ts = new TargetSupervise();
                ts.setId(UUIDFactory.getLongId());
                ts.setUserId(Long.valueOf(s));
                ts.setOpinion(2);
                ts.setTargetId(target.getId());
                targetSuperviseService.insert(ts);
            }

            Calendar cal = Calendar.getInstance();
            cal.setTime(target.getSetTime());
            cal.add(Calendar.DATE, keep);
            target.setEndTime(cal.getTime());
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
            target.setUser(userService.selectById(target.getUserId()));
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
    @ApiOperation(value = "首页和发现挑战列表",  notes = "首页和发现发现挑战列表（分页），发现列表传4 ")
    public Tip<PageInfo<Target>> listTarget(
            @ApiIgnore HttpSession session,
            @ApiParam(name = "status",value = "状态,1 挑战中（会获取status为1和2的）,3 已完成,4 获取全部")
            @RequestParam Integer status,
            @ApiParam(name = "page",value = "第几页")
            @RequestParam Integer page,
            @ApiParam(name = "size",value = "每页大小")
            @RequestParam Integer size
    ){
        PageInfo<Target> targetPageInfo = null;
        Target target = new Target();
        target.setStatus(status);
        try {
            targetPageInfo = targetService.listByTarget(target,page,size);
            for(Target t : targetPageInfo.getList()){
                t.setUser(userService.selectById(t.getUserId()));
            }
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
            @ApiParam(name = "status",value = "状态,1 挑战中（会获取status为1和2的）,3 已完成")
            @RequestParam Integer status,
            @ApiParam(name = "page",value = "第几页")
            @RequestParam Integer page,
            @ApiParam(name = "size",value = "每页大小")
            @RequestParam Integer size
    ){
        User user = (User) session.getAttribute(Const.USER);
        Target target = new Target();
        target.setUserId(user.getId());
        target.setStatus(status);
        PageInfo<Target> targetPageInfo = targetService.listByTarget(target,page,size);
        for(Target t : targetPageInfo.getList()){
            t.setUser(userService.selectById( t.getUserId()));
        }
        return new Tip<>(targetPageInfo);
    }

    @RequestMapping(value = "/listByUserStatus", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "用户的挑战列表",  notes = "获取某用户的挑战列表（分页）")
    public Tip<PageInfo<Target>> listByUserStatus(
            @ApiIgnore HttpSession session,
            @ApiParam(name = "userId",value = "用户id")
            @RequestParam Long userId,
            @ApiParam(name = "status",value = "状态,1 挑战中（会获取status为1和2的）,3 已完成")
            @RequestParam Integer status,
            @ApiParam(name = "page",value = "第几页")
            @RequestParam Integer page,
            @ApiParam(name = "size",value = "每页大小")
            @RequestParam Integer size
    ){

        Target target = new Target();
        target.setUserId(userId);
        target.setStatus(status);
        PageInfo<Target> targetPageInfo = targetService.listByTarget(target,page,size);
        for(Target t : targetPageInfo.getList()){
            t.setUser(userService.selectById( t.getUserId()));
        }
        return new Tip<>(targetPageInfo);
    }

    @RequestMapping(value = "/listSuperviseByMy", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "我监督的",  notes = "我监督的挑战列表（分页）")
    public Tip<PageInfo<Target>> listSuperviseByMy(
                                         @ApiIgnore HttpSession session,
                                         @ApiParam(name = "status",value = "状态,1 挑战中（会获取status为1和2的）,3 已完成")
                                         @RequestParam Integer status,
                                         @ApiParam(name = "page",value = "第几页")
                                         @RequestParam Integer page,
                                         @ApiParam(name = "size",value = "每页大小")
                                         @RequestParam Integer size){
        User user = (User) session.getAttribute(Const.USER);
        Map<String,String> param = new HashMap<>();
        param.put("status",status.toString());
        param.put("userId",user.getId().toString());
        PageInfo<Target> targetPageInfo = targetService.listBySupervise(param,page,size);
        for(Target t : targetPageInfo.getList()){
            t.setUser(userService.selectById(t.getUserId()));
        }
        return new Tip<>(targetPageInfo);
    }




    //---------------------------- property -------------------------------

    @Resource
    private TargetService targetService;

    @Resource
    private TargetSuperviseService targetSuperviseService;

    @Resource
    private UserService userService;

    @Resource
    private MoneyService moneyService;
}
