package com.wlt.wla.auth.web;

import com.wlt.wla.parsers.imgParsers;
import com.wlt.wla.parsers.priceParsers;
import com.wlt.wla.auth.model.*;
import com.wlt.wla.auth.service.*;
import com.wlt.wla.auth.validator.*;
import com.wlt.wla.data.WishListDaoImpl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    
    @GetMapping({"/", "/admin"})
    public String admin(Model model) {
        return "admin";
    }

    @GetMapping({"/", "/userList"})
    public String userList(Model model) {
        return "userList";
    }

    
    
    @GetMapping("/removeUser")
    public String removeUser(Model model, String error, String remove) {
        return "redirect:/users";
    }
    
    @PostMapping("/removeUser")
    public String removeUser(@ModelAttribute("User") User user) {
    	String sql;
    	sql = "DELETE FROM user WHERE `user`.`id` = " + user.getId();
        jdbcTemp.execute(sql);
    	sql = "DELETE FROM balance WHERE `balance`.`user_id` = " + user.getId();
        jdbcTemp.execute(sql);        
    	sql = "DELETE FROM user_pref WHERE `user_pref`.`user_id` = " + user.getId();
        jdbcTemp.execute(sql);          
    	sql = "DELETE FROM user_roles WHERE `user_roles`.`users_id` = " + user.getId();
        jdbcTemp.execute(sql);
    	sql = "DELETE FROM wishlist_items WHERE `wishlist_items`.`user_id` = " + user.getId();
        jdbcTemp.execute(sql);          
      
        return "redirect:/users";
    }

    
    
    @GetMapping("/remove")
    public String remove(Model model, String error, String remove) {
        return "redirect:/home";
    }

    @PostMapping("/remove")
    public String removeItem(@ModelAttribute("Item") DBWishItems item) {
        String sql = "DELETE FROM `dr_wishlist`.`wishlist_items` WHERE `wishlist_items`.`id` = " + item.getId();
        jdbcTemp.execute(sql);

        return "redirect:/itemList";
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
        float f = -1;
        String url = item.getUrl();

        if (url.contains("www.salidzini.lv")) f = pp.getPriceSalidzini(item.getUrl());
        if (url.contains("www.aliexpress.com")) f = pp.getPriceAlie(item.getUrl());

        if (f > 0f) {
            String sql = "UPDATE `dr_wishlist`.`wishlist_items` SET `price` = '" + f + "' WHERE `wishlist_items`.`id` =" + item.getId();
            jdbcTemp.execute(sql);
        } else {

            System.out.println("price cannot be received");
        }
        return "redirect:/itemList";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("Item") DBWishItems item, BindingResult bindingResult) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        int userId = 0;
        String query = "SELECT id FROM dr_wishlist.user WHERE username=?";

        try {
            userId = jdbcTemp.queryForObject(query, new Object[]{currentPrincipalName}, Integer.class);
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }

        String sql = "INSERT INTO `dr_wishlist`.`wishlist_items` (`id`, `user_id`, `cat_id`, `name`, `priority`, `price` , url) "
                + "VALUES (NULL, " + userId + ", '" + item.getGroup() + "', '" + item.getName() + "', '"
                + item.getPriority() + "', '" + item.getPrice() + "','" + item.getUrl() + "');";
        jdbcTemp.execute(sql);

        return "redirect:/itemList";

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
            userId = jdbcTemp.queryForObject(query, new Object[]{currentPrincipalName}, Integer.class);
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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        int userId = 0;
        String query = "SELECT id FROM dr_wishlist.user WHERE username=?";

        try {
            userId = jdbcTemp.queryForObject(query, new Object[]{currentPrincipalName}, Integer.class);
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }

        query = "INSERT INTO dr_wishlist.user_pref (user_id) VALUES (" + userId + ")";
        jdbcTemp.execute(query);

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

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }

    
    
    @GetMapping({"/", "/addItem"})
    public String addItem(Model model) {
        return "addItem";
    }

    @PostMapping("/fulfill")
    public String fulfill(@ModelAttribute("Item") DBWishItems item) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        int userId = 0;
        String query = "SELECT id FROM dr_wishlist.user WHERE username=?";

        try {
            userId = jdbcTemp.queryForObject(query, new Object[]{currentPrincipalName}, Integer.class);
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }

        query = "SELECT name, price FROM dr_wishlist.wishlist_items WHERE user_id = "+userId+" AND id = " + item.getId();

		class Item {
			private float price;
			private String name;

			public float getPrice() {
				return price;
			}

			public String getName() {
				return name;
			}

			public Item(float price, String name) {
				this.price = price;
				this.name = name;
			}
		}

		List<Item> list = jdbcTemp.query(query, new RowMapper<Item>() {

			@Override
			public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Item(rs.getFloat("price"), rs.getString("name"));
			}

		});

		String note = "You spent " + String.format(Locale.US, "%.2f", list.get(0).getPrice()) + " on " + list.get(0).getName();

		query = "INSERT INTO dr_wishlist.balance (user_id, balance_changes, note, timestamp) VALUES (" + userId + ", " + (list.get(0).getPrice() * -1) + ", '" + note + "', '" + formatter.format(date) + "')";
		jdbcTemp.execute(query);
        query = "UPDATE dr_wishlist.wishlist_items SET status = 1 WHERE user_id = " + userId + " AND id = " + item.getId();
        jdbcTemp.execute(query);

        return "redirect:/itemList";
    }

}
