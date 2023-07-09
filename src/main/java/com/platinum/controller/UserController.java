package com.platinum.controller;

import com.platinum.model.User;
import com.platinum.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.Map;



@Controller
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView model = new ModelAndView();

        model.setViewName("user/login");
        return model;
    }


	@RequestMapping(value = { "/signin" }, method = RequestMethod.POST)
	public ModelAndView loginPage(@Valid User user, BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		Map<String, String> validations = userService.verifyLogin(user.getUsername(), user.getPassword());

		if (!validations.isEmpty()) {
            model.addObject("errors", validations);
			model.setViewName("user/login");
		} else {

			model.addObject("user", userService.findByUsername(user.getUsername()));
			model.setViewName("user/welcome");
		}

		return model;
	}

	@RequestMapping(value = { "/users/logout" }, method = RequestMethod.GET)
	public ModelAndView logout() {
        ModelAndView model = new ModelAndView();
        model.setViewName("user/login");
        return model;
	}

    @RequestMapping(value = {"/users/welcome"}, method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView model = new ModelAndView();
        model.setViewName("user/welcome");
        return model;
    }

    @RequestMapping(value = {"/access_denied"}, method = RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView model = new ModelAndView();
        model.setViewName("errors/access_denied");
        return model;
    }
}