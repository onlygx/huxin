package com.elangzhi.task;

import com.elangzhi.modules.target.services.TargetService;
import com.elangzhi.modules.targetSupervise.services.TargetSuperviseService;
import com.elangzhi.modules.user.services.UserService;
import com.elangzhi.ssm.model.Target;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;

/**
 *  更改挑战状态的定时器
 */
@Component
public class TargetStatusTask {

    private Logger logger = Logger.getLogger(this.getClass());

    /**
     * 查询已到达目标天数的挑战
     * 一小时一次
     * fixedDelay 上一次任务执行结束到下一次的时间
     */
    @Scheduled(fixedDelay = 1000 * 60 )
    public void checkTimeOut() {
        logger.info("开始处理已经坚持到天数的挑战");
        List<Target> targets = targetService.listFinishKeep();

        for(Target target : targets){
            target.setStatus(2);
            try {
                targetService.updateById(target);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("处理超过预期天数的挑战失败："+target.getTitle());
            }
        }
    }


    @Resource
    private TargetService targetService;

    @Resource
    private TargetSuperviseService targetSuperviseService;

    @Resource
    private UserService userService;
}
