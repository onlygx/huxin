package com.elangzhi.ssm.interceptor;

import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.HttpMethod;

import com.elangzhi.ssm.controller.json.Tip;
import com.elangzhi.ssm.model.Account;
import com.elangzhi.ssm.model.User;
import com.elangzhi.ssm.tools.Const;
import com.sun.tools.internal.ws.wsdl.document.http.HTTPConstants;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
* 类名称：LoginHandlerInterceptor.java
* 类描述：拦截器
* @author GaoXiang
* @version 1.6
 */
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String path = request.getServletPath();
		if(path.matches(Const.NO_INTERCEPTOR_PATH)){
			return true;
		}else{
			HttpSession session = request.getSession();
			Account account = (Account)session.getAttribute(Const.ACCOUNT);
			User user = (User) session.getAttribute(Const.USER);
			if(account == null && user == null){
                String method = request.getMethod();
                if(method.equals(HttpMethod.GET)){
                    //登陆过滤
                    response.sendRedirect(request.getContextPath() + Const.LOGIN);
                }else{
                    response.setContentType("application/json");
                    response.getWriter().print("{'success':false,'code':-1}");
                }

				return false;

			}else{
				path = path.substring(1, path.length());
				/*boolean b = Jurisdiction.hasJurisdiction(path);
				if(!b){
					response.sendRedirect(request.getContextPath() + Const.LOGIN);
				}*/
				return true;
			}
		}
	}
	
}
