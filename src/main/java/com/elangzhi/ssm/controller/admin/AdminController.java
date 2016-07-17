package com.elangzhi.ssm.controller.admin;

import com.elangzhi.generator.util.GenUtil;
import com.elangzhi.ssm.controller.AdminBaseController;
import com.elangzhi.ssm.controller.json.Tip;
import com.elangzhi.ssm.controller.util.ParamMap;
import com.elangzhi.ssm.model.Account;
import com.elangzhi.ssm.model.AccountRole;
import com.elangzhi.ssm.model.Admin;
import com.elangzhi.ssm.services.AccountRoleService;
import com.elangzhi.ssm.services.AccountService;
import com.elangzhi.ssm.services.AdminService;
import com.elangzhi.ssm.services.RolePowerService;
import com.elangzhi.ssm.tools.Const;
import com.elangzhi.ssm.tools.ImageHelper;
import com.elangzhi.ssm.tools.UUIDFactory;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 管理员 Controller
 * @author GaoXiang
 * @version 1.0
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends AdminBaseController<Admin> {

    @RequestMapping(value="/addReferee")
    public ModelAndView addReferee(ModelMap model) throws Exception {
        model.put("longId", UUIDFactory.getLongId());
        return new ModelAndView("admin/referee/add",model);
    }

    @RequestMapping(value="/listByType")
    public ModelAndView listByType(HttpServletRequest request,Integer page,Integer size){
        ParamMap paramMap = new ParamMap(request);
        PageInfo<Admin> pageInfo =  adminService.listByParam(paramMap,page,size);
        paramMap.put("pageInfo",pageInfo);
        return new ModelAndView("admin/referee/list",paramMap);
    }

    /**
     * 携带信息跳转到修改头像页面
     * @param id 管理员id
     * @return 头衔修改页面
     * @throws Exception 跳转头像修改页面错误
     */
    @RequestMapping(value="/headimg")
    public ModelAndView headImg(@RequestParam Long id) throws Exception {
        return new ModelAndView("admin/admin/headimg","data",adminService.selectById(id));
    }

    /**
     * 裁剪并应用新头像
     * @param request 请求信息
     * @param session 获取session
     * @param x1 裁剪开始坐标
     * @param y1 裁剪开始坐标
     * @param w 裁剪宽度
     * @param h 裁剪高度
     * @param id 管理员id
     * @param src 完整图片地址
     * @return
     */
    @RequestMapping(value="/changeHead")
    @ResponseBody
    public Tip changeHead(HttpServletRequest request,HttpSession session,
            @RequestParam(required = false) Integer x1,
            @RequestParam(required = false) Integer y1,
            @RequestParam(required = false) Integer w,
            @RequestParam(required = false) Integer h,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String src
    ) {

        if ("".equals(src)) {
            return new Tip(1); //请选择图片
        } else if (x1 == null || w == null || y1 == null || h == null) {
            return new Tip(2); //请选择剪裁区域
        } else {
            File file = new File(src);
            //保存地址
            String url = "/images/headImage/"
                    + System.currentTimeMillis()
                    + "_head"
                    + file.getName().substring(file.getName().lastIndexOf("."));

            String realPath = request.getServletContext().getRealPath(url);
            //剪裁
            try {
                ImageHelper.cutImage(file, realPath, x1, y1, w, h);
            } catch (IOException e) {
                e.printStackTrace();
                return new Tip(3); //剪裁失败
            }

            try {
                //获取账号信息 更改头像
                Admin admin = adminService.selectById(id);
                admin.setHead(url);
                adminService.updateById(admin);
                //更新session内对象
                session.setAttribute(Const.ADMIN,admin);
                return new Tip(url);
            } catch (Exception e) {
                e.printStackTrace();
                return new Tip(4); // 保存失败
            }
        }
    }



    /**
     * 保存
     * @param admin
     * @return
     * 1，管理员信息错误
     * 2，账号信息错误
     * 3，账号已存在
     */
    @Override
    public Tip save(Admin admin) throws Exception {

        Account isNull = accountService.findByUserName(admin.getUserName());
        if(isNull != null){
            return new Tip(3,isNull.getId());
        }else{
            Account account = new Account();
            account.setId(admin.getId()+1);
            account.setUserName(admin.getUserName());
            account.setPassword(admin.getPassword());
            account.setInfoId(admin.getId());
            account.setStatus(1);

            if(account.getType() == 2){
                AccountRole accountRole = new AccountRole();
                accountRole.setId(UUIDFactory.getLongId());
                accountRole.setAccountId(account.getId());
                accountRole.setRoleId(Long.valueOf("3411240225557332612"));
                accountRoleService.insert(accountRole);
            }

            admin.setSetTime(new Date());
            admin.setName(admin.getUserName());
            accountService.insert(account);
            return super.save(admin);
        }
    }

    //--------------------------- property --------------------------
    @Resource
    AccountService accountService;

    @Resource
    AdminService adminService;

    @Resource
    private AccountRoleService accountRoleService;
}
