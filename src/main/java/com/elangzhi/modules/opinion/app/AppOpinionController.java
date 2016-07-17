package com.elangzhi.modules.opinion.app;

import com.elangzhi.ssm.controller.json.Tip;
import com.elangzhi.ssm.model.Opinion;
import com.elangzhi.modules.opinion.services.OpinionService;
import com.elangzhi.ssm.model.User;
import com.elangzhi.ssm.tools.Const;
import com.elangzhi.ssm.tools.UUIDFactory;
import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
* 产品意见 客户端 Controller
* @author GaoXiang
* @version 1.0
*/
@Controller
@RequestMapping("/app/opinion")
public class AppOpinionController {

    /**
     * 提交产品建议
     * @param content 产品建议
     * @return 成功状态
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "意见反馈",  notes = "提交意见反馈")
    public Tip submit(
            @ApiIgnore HttpSession session,
            @ApiParam(name = "content",value = "内容")
            @RequestParam String content
    ){
        Opinion opinion = new Opinion();
        opinion.setId(UUIDFactory.getLongId());
        opinion.setSetTime(new Date());
        opinion.setContent(content);
        opinion.setUserId(((User)session.getAttribute(Const.USER)).getId());

        try {
            opinionService.insert(opinion);
            return new Tip(opinion);
        } catch (Exception e) {
            e.printStackTrace();
            return new Tip(1);
        }
    }


    //---------------------------- property -------------------------------

    @Resource
    private OpinionService opinionService;

}
