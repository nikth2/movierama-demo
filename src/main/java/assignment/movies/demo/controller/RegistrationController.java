package assignment.movies.demo.controller;

import assignment.movies.demo.dto.UserDto;
import assignment.movies.demo.model.UserEntity;
import assignment.movies.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class RegistrationController {
    private final UserService userService;

    @GetMapping("/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@Valid UserDto user,
                                            BindingResult result,
                                            ModelMap model,
                                            HttpServletRequest request) throws ServletException {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "registration";
        }
        //try {
            UserEntity registered = userService.registerNewUserAccount(user);
        //} catch (Exception uaeEx) {
        //    mav.addObject("message", "An account for that username/email already exists.");
        //    return mav;
        //}
        model.addAttribute("userDto", registered);
        request.login(user.getUserName(), user.getPassword());
        return "redirect:/";
        //return new ModelAndView("movies", "user", userDto);
    }
}
