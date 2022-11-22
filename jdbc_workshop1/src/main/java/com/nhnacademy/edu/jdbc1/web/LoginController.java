package com.nhnacademy.edu.jdbc1.web;

import com.nhnacademy.edu.jdbc1.exception.CustomerNotFoundException;
import com.nhnacademy.edu.jdbc1.service.login.UserLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.ModelMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserLoginService userLoginService;

    public LoginController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    @GetMapping
    public String login(@SessionAttribute(value = "LOGINID", required = false) String session){
        if(Objects.isNull(session)){
            return "loginForm";
        }
        return "loginSuccess";
    }

    @PostMapping
    public String doLogin(@RequestParam("id") String username, @RequestParam("pwd") String pwd,
                          HttpServletRequest request, ModelMap modelMap){
        if(userLoginService.isExistUser(username, pwd)){
            HttpSession session = request.getSession();
            session.setAttribute("LOGINID", username);
            modelMap.put("username", username);
            return "loginSuccess";
        }
        throw new CustomerNotFoundException();
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public String handleCustomerNotFoundException(){
        return "redirect:/login";
    }
}
