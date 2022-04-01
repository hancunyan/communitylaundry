package com.computer.community_laundry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    /**
     * 登录跳转
     * @return
     */
    @RequestMapping(value = "login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "loginout")
    public String loginout() {
        return "login";
    }

    @RequestMapping("/")
    public String index(Model model, HttpSession session, HttpServletResponse response) {
        if (session.getAttribute("userid") != null){
            String user_id = session.getAttribute("userid").toString();
            model.addAttribute("userid", user_id);
            return "index";
        }else{
            return "redirect:/login";
        }
    }
}
