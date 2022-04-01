package com.computer.community_laundry.support.config.security;

import com.computer.community_laundry.support.entity.system.JsonData;
import net.sf.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description: 登录失败操作
 */

@Component("UserLoginAuthenticationFailureHandler")
public class UserLoginAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        JsonData jsonData = null;
        if (exception.getMessage().equals("用户名不存在")){
            jsonData = new JsonData(402,"用户不存在");
        }


        if(exception.getMessage().equals("Bad credentials")){
            jsonData = new JsonData(403,"密码错误");
        }

        String json = JSONObject.fromObject(jsonData).toString();//包装成Json 发送的前台
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        out.write(json);
        out.flush();
        out.close();
    }
}
