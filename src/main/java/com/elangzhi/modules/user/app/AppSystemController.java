package com.elangzhi.modules.user.app;

import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 应用相关
 * 关于、升级、建议之类
 */
@Controller
@RequestMapping("/app/system")
public class AppSystemController {

    @RequestMapping("about")
    @ApiOperation(value = "关于我们",  notes = "获取关于我们web页面")
    public ModelAndView about(){
        return new ModelAndView("web/about");
    }


}
