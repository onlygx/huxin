package com.elangzhi.modules.target.controller;

import com.elangzhi.generator.util.GenUtil;
import com.elangzhi.modules.money.services.MoneyService;
import com.elangzhi.modules.targetSupervise.services.TargetSuperviseService;
import com.elangzhi.modules.user.services.UserService;
import com.elangzhi.ssm.controller.AdminBaseController;
import com.elangzhi.ssm.controller.json.Tip;
import com.elangzhi.ssm.controller.util.ParamMap;
import com.elangzhi.ssm.model.Account;
import com.elangzhi.ssm.model.Admin;
import com.elangzhi.ssm.model.Target;
import com.elangzhi.modules.target.services.TargetService;
import com.elangzhi.ssm.model.TargetSupervise;
import com.elangzhi.ssm.services.AdminService;
import com.elangzhi.ssm.tools.Const;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
* 挑战 Controller
* @author GaoXiang
* @version 1.0
*/
@Controller
@RequestMapping("/target")
public class TargetController extends AdminBaseController<Target> {

    @RequestMapping(value="/chooseOpinion")
    @ResponseBody
    public Tip chooseOpinion(Target target){

        try {
            target.setStatus(3);
            target.setFinishTime(new Date());
            targetService.updateById(target);
            checkOpinion(target);
            return new Tip();
        } catch (Exception e) {
            System.out.println("裁决失败");
            e.printStackTrace();
            return new Tip(1);
        }
    }

    private void checkOpinion(Target target) {
        try {
            if(target.getOpinion() == 1){

                moneyService.insertByType(target.getUserId(),5,target.getPrice(),target.getId());

            }else if(target.getOpinion() == 0){

                    List<TargetSupervise> tsList = targetSuperviseService.listByTargetId(target.getId());
                    Double price = target.getPrice()/tsList.size();
                    for(TargetSupervise ts : tsList){
                        moneyService.insertByType(ts.getUserId(),4,price,target.getId());
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value="/chooseReferee")
    @ResponseBody
    public Tip chooseReferee(Target target){

        try {
            targetService.updateById(target);
            return new Tip();
        } catch (Exception e) {
            System.out.println("选择裁判失败");
            e.printStackTrace();
            return new Tip(1);
        }
    }

    @RequestMapping(value="/listTarget")
    public ModelAndView list(HttpServletRequest request, Integer page, Integer size, HttpSession session) throws Exception {
        if(page == null || page == 0){
            page = 1;
            size = 10;
        }
        ParamMap paramMap = new ParamMap(request);

        Account account = (Account) session.getAttribute(Const.ACCOUNT);
        if(account.getType() == 2){
            paramMap.put("refereeId",account.getInfoId());
        }
        PageInfo<Target> pageInfo =  targetService.list(paramMap,page,size);
        for(Target target : pageInfo.getList()){
            target.setUser(userService.selectById(target.getUserId()));
        }
        paramMap.put("pageInfo",pageInfo);
        //添加裁判列表
        paramMap.put("refereeList",adminService.listReferee());
        return new ModelAndView("admin/target/list",paramMap);
    }

    //---------------------------- property -------------------------------

    @Resource
    private TargetService targetService;

    @Resource
    private UserService userService;

    @Resource
    AdminService adminService;

    @Resource
    private MoneyService moneyService;

    @Resource
    private TargetSuperviseService targetSuperviseService;

}
