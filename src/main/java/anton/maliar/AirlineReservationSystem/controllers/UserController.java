package anton.maliar.AirlineReservationSystem.controllers;

import anton.maliar.AirlineReservationSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request){
        if(userService.isUserExist(request)){
            userService.login(request);
            return "redirect:/user/account";
        }
        return "redirect:/?errorMessage=errorMessage";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession(false).invalidate();
        return "redirect:/";
    }
    @GetMapping("/account")
    public String userAccount(HttpServletRequest request, Model model){
        if(request.getSession(false) != null){
            model.addAttribute("user", userService.getUser(request));
            return "user/account";
        }
        return "redirect:/?errorMessage=errorMessage";
    }
    @PostMapping("/new")
    public String createUser(HttpServletRequest request, Model model){
        if(userService.validateInput(request)){
            model.addAttribute("errorMessage", "The fields must not be empty and their size must not exceed 20 symbols");
            return "user/login-registration";
        }
        if(userService.isUserExist(request)){
            model.addAttribute("errorMessage", "User already exists");
            return "user/login-registration";
        }
        userService.saveUser(request);
        model.addAttribute("errorMessage", "User created, please log in");
        return "user/login-registration";
    }
    @GetMapping("/update")
    public String updateUser(HttpServletRequest request, Model model){
        if(request.getSession(false) != null){
            model.addAttribute("user", userService.getUser(request));
            return "user/update";
        }
        return "redirect:/?errorMessage=errorMessage";
    }
    @PostMapping("/update")
    public String updateUserPost(HttpServletRequest request){
        if(request.getSession(false) != null){
            userService.updateUser(request);
            return "redirect:/user/account";
        }
        return "redirect:/?errorMessage=errorMessage";
    }

}
