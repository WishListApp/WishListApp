package com.wlt.wla.data;

import java.util.Locale;

import com.wlt.wla.auth.model.Balance;
import com.wlt.wla.auth.model.DBWishItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WishController {
	
	@Autowired
	private WishListDao empDao;

	@RequestMapping(value = "/add")
	public ModelAndView listCat(ModelAndView modelAndView) {

		modelAndView.addObject("CatEmp", empDao.CatEmp());
		modelAndView.addObject("balance", String.format(Locale.US, "%.2f", empDao.getBalance()));
		modelAndView.addObject("currencyCode", empDao.getCurrencyCode());
		modelAndView.addObject("PriorEmp", empDao.PriorEmp());
		modelAndView.setViewName("addItem");
		modelAndView.addObject("Item", new DBWishItems());

		return modelAndView;
	}

	@RequestMapping(value = "/home")
	public ModelAndView listEmp(ModelAndView model) {

		model.addObject("balance", String.format(Locale.US, "%.2f", empDao.getBalance()));
		model.addObject("currencyCode", empDao.getCurrencyCode());
		model.setViewName("mainPage");


		return model;
	}

	@RequestMapping(value = "/itemList")
	public ModelAndView itemList(ModelAndView modelAndView) {
		modelAndView.addObject("balance", String.format(Locale.US, "%.2f", empDao.getBalance()));
		modelAndView.addObject("currencyCode", empDao.getCurrencyCode());
		modelAndView.addObject("WlistEmp", empDao.WlistEmp());
		modelAndView.setViewName("itemList");


		return modelAndView;
	}

	@RequestMapping(value = "/catList")
	public ModelAndView catList(ModelAndView modelAndView) {
		modelAndView.addObject("balance", String.format(Locale.US, "%.2f", empDao.getBalance()));
		modelAndView.addObject("CatEmp", empDao.CatEmp());
		modelAndView.addObject("currencyCode", empDao.getCurrencyCode());
		modelAndView.setViewName("catList");

		return modelAndView;
	}

	@RequestMapping(value = "/balance")
	public ModelAndView balance(ModelAndView model) {
		model.addObject("BalanceForm", new Balance());
		model.addObject("balance", String.format(Locale.US, "%.2f", empDao.getBalance()));
		model.addObject("currencyCode", empDao.getCurrencyCode());
		model.setViewName("balance");

		return model;
	}

	@PostMapping("/itemEditPage")
	public ModelAndView itemEdit(ModelAndView model, HttpServletRequest request) {
		model.addObject("balance", String.format(Locale.US, "%.2f", empDao.getBalance()));
		model.addObject("currencyCode", empDao.getCurrencyCode());
		model.addObject("Item", new DBWishItems(
				request.getParameter("name"),
				Integer.parseInt(request.getParameter("id")),
				Float.parseFloat(request.getParameter("price")),
				request.getParameter("category"),
				request.getParameter("priority"),
				request.getParameter("url")
				));
		model.addObject("CatEmp", empDao.CatEmp());
		model.addObject("PriorEmp", empDao.PriorEmp());
		model.setViewName("itemEdit");
		return model;
	}

}
