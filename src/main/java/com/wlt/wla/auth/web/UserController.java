package com.wlt.wla.auth.web;

import com.wlt.wla.parsers.priceParsers;
import com.wlt.wla.auth.model.*;
import com.wlt.wla.auth.service.*;
import com.wlt.wla.auth.validator.*;
import com.wlt.wla.data.WishListDaoImpl;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UserController {
	private JdbcTemplate jdbcTemp;
	private WishListDaoImpl wishListDao;

	public UserController(DataSource dataSource) {
		jdbcTemp = new JdbcTemplate(dataSource);
	}



	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private InputValidator InputValidator;

	@GetMapping("/remove")
	public String remove(Model model, String error, String remove) {
		// System.out.println("remove pressed");

		return "redirect:/home";
	}

	@PostMapping("/remove")
	public String removeItem(@ModelAttribute("Item") DBWishItems item, BindingResult bindingResult) {

		String sql = "DELETE FROM `dr_wishlist`.`wishlist_items` WHERE `wishlist_items`.`id` = " + item.getId();
		jdbcTemp.execute(sql);

		return "redirect:/home";
	}

	@GetMapping("/updatePrice")
	public String updatePrice(Model model, String error, String remove) {
//		if (error != null)
//			model.addAttribute("error", "Your username and password is invalid.");
//		else System.out.println("else here");
		
		return "redirect:/home";
	}

	@PostMapping("/updatePrice")
	public String updatePrice(@ModelAttribute("Item") DBWishItems item, BindingResult bindingResult) {
		priceParsers pp = new priceParsers();
		float f = pp.getPriceSalidzini(item.getUrl());
		if (f>0f) {
			String sql = "UPDATE `dr_wishlist`.`wishlist_items` SET `price` = '"+f+"' WHERE `wishlist_items`.`id` ="+item.getId();
			jdbcTemp.execute(sql);
		}
		else {
//			ObjectError myError = new ObjectError("PRICE_UNAW", "price cannot be received");
//			bindingResult.addError(myError);
			
			System.out.println("price cannot be received");
		}
		return "redirect:/home";
	}

	@PostMapping("/add")
	public String add(@ModelAttribute("Item") DBWishItems item, BindingResult bindingResult) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		int userId = 0;
		String query = "SELECT id FROM dr_wishlist.user WHERE username=?";

		try {
			userId = jdbcTemp.queryForObject(query, new Object[] { currentPrincipalName }, Integer.class);
		} catch (NullPointerException e) {
			System.err.println(e.getMessage());
		}

		String sql = "INSERT INTO `dr_wishlist`.`wishlist_items` (`id`, `user_id`, `cat_id`, `name`, `priority`, `price` , url) "
				+ "VALUES (NULL, " + userId + ", '" + item.getGroup() + "', '" + item.getName() + "', '"
				+ item.getPriority() + "', '" + item.getPrice() + "','" + item.getUrl() + "');";
		jdbcTemp.execute(sql);

		return "redirect:/home";

	}

	@GetMapping("/balance")
	public String balance(Model model) {
		model.addAttribute("BalanceForm", new Balance());
		return "balance";
	}

	@PostMapping("/balance")
	public String balance(@ModelAttribute("BalanceForm") Balance balance, BindingResult bindingResult) {
		float change = balance.getBalanceChange();
		String note = balance.getNote();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		int userId = 0;
		String query = "SELECT id FROM dr_wishlist.user WHERE username=?";

		try {
			userId = jdbcTemp.queryForObject(query, new Object[] { currentPrincipalName }, Integer.class);
		} catch (NullPointerException e) {
			System.err.println(e.getMessage());
		}

		query = "INSERT INTO dr_wishlist.balance (user_id, balance_changes, note, timestamp) VALUES (" + userId + ", "
				+ change + ", '" + note + "', '" + formatter.format(date) + "')";
		jdbcTemp.execute(query);

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

}
