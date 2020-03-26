package com.wlt.wla.data;

import java.io.IOException;
import java.util.List;

import com.wlt.wla.auth.model.Balance;
import com.wlt.wla.auth.model.DBWishItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WishController {
	
	@Autowired
	private WishListDao empDao;

	@RequestMapping(value = "/add")
	public ModelAndView listCat(ModelAndView modelAndView, Model model) {

		modelAndView.addObject("CatEmp", empDao.CatEmp());
		modelAndView.addObject("balance", empDao.getBalance());
		modelAndView.addObject("currencyCode", empDao.getCurrencyCode());
		modelAndView.addObject("PriorEmp", empDao.PriorEmp());
		modelAndView.setViewName("addItem");
		model.addAttribute("Item", new DBWishItems());

		return modelAndView;
	}

	@RequestMapping(value = "/home")
	public ModelAndView listEmp(ModelAndView model) {

		model.addObject("balance", empDao.getBalance());
		model.addObject("currencyCode", empDao.getCurrencyCode());
		model.setViewName("mainPage");


		return model;
	}

	@RequestMapping(value = "/itemList")
	public ModelAndView itemList(ModelAndView modelAndView) {
		modelAndView.addObject("balance", empDao.getBalance());
		modelAndView.addObject("currencyCode", empDao.getCurrencyCode());
		List<DBWishItems> WlistEmp = empDao.WlistEmp();
		modelAndView.addObject("WlistEmp", WlistEmp);
		modelAndView.setViewName("itemList");


		return modelAndView;
	}

	@RequestMapping(value = "/catList")
	public ModelAndView catList(ModelAndView modelAndView) {
		modelAndView.addObject("balance", empDao.getBalance());
		modelAndView.addObject("CatEmp", empDao.CatEmp());
		modelAndView.setViewName("catList");

		return modelAndView;
	}

	@RequestMapping(value = "/balance")
	public ModelAndView balance(ModelAndView model) {
		model.addObject("BalanceForm", new Balance());
		model.addObject("balance", empDao.getBalance());
		model.setViewName("balance");

		return model;
	}

}
