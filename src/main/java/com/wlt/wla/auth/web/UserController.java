package com.wlt.wla.auth.web;

import com.wlt.wla.auth.model.User;
import com.wlt.wla.auth.service.SecurityService;
import com.wlt.wla.auth.service.UserService;
import com.wlt.wla.auth.validator.UserValidator;
import com.wlt.wla.data.*;
import com.wlt.wla.data.WishListDaoImpl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
	private JdbcTemplate jdbcTemp;

	public UserController(DataSource dataSource) {
		jdbcTemp = new JdbcTemplate(dataSource);
	}

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;

	@GetMapping("/add")
	public String add(Model model) {
		// TODO need to change next line from registration form!
		model.addAttribute("userForm", new DBWishItems());

		return "addItem";
	}

	@PostMapping("/add")
	public String add(@ModelAttribute("userForm") DBWishItems userForm, BindingResult bindingResult) {
		// Validate input! and if error stay on page
//    	userValidator.validate(userForm, bindingResult);
//        if (bindingResult.hasErrors()) {
//            return "addItem";
//        }
		
		
		//insert in db
		String sql = "INSERT INTO `dr_wishlist`.`wishlist_items` (`id`, `user_id`, `cat_id`, `name`, `type`, `priority`, `price`) VALUES (NULL, '5', '5', '"+userForm.getName()+"', '3', '1', '33');";
		jdbcTemp.execute(sql);


		return "redirect:/home";
	}

	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("userForm", new User());

		return "registration";
	}

	@PostMapping("/registration")
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "registration";
		}

		userService.save(userForm);

		securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

		return "redirect:/home";
	}

	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		return "login";
	}

	@GetMapping({ "/", "/welcome" })
	public String welcome(Model model) {
		return "welcome";
	}

	@GetMapping({ "/", "/addItem" })
	public String addItem(Model model) {
		return "addItem";
	}

//    @GetMapping({"/", "/mainPage"})
//    public String mainPage(Model model) {
//        return "mainPage";
//    }

	@GetMapping({ "/", "/balance" })
	public String balance(Model model) {
		return "balance";
	}
}
