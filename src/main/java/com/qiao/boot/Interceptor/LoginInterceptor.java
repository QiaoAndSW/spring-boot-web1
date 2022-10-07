package com.qiao.boot.Interceptor;



import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 乔世伟
 * @Project_Nmae: spring-boot-web1
 * @Description:
 * @date 2022/10/5 12:27
 */
@Slf4j
public class LoginInterceptor  implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object loginUser = request.getSession().getAttribute("loginUser");
        if (loginUser != null){
            return  true;
        }

//        request.getSession().setAttribute("errorMsg","请登录");
        request.getRequestDispatcher("/login").forward(request,response);

        return false;
    }




}
