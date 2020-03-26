package com.wlt.wla.data;

import java.io.IOException;
import java.util.List;

import com.wlt.wla.auth.model.DBWishItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WishController {
	
	@Autowired
	private WishListDao empDao;

	@RequestMapping(value = "/add")
	public ModelAndView listCat(ModelAndView modelAndView, Model model) {

		List<DBCatItems> CatEmp = empDao.CatEmp();
		modelAndView.addObject("CatEmp", CatEmp);
		modelAndView.addObject("currencyCode", empDao.getCurrencyCode());
		List<DBPriorities> PriorEmp = empDao.PriorEmp();
		modelAndView.addObject("PriorEmp", PriorEmp);
		modelAndView.setViewName("addItem");
		model.addAttribute("Item", new DBWishItems());

		return modelAndView;
	}

	@RequestMapping(value = "/home")
	public ModelAndView listEmp(ModelAndView model) {

		model.addObject("balance", empDao.getBalance());
		model.addObject("currencyCode", empDao.getCurrencyCode());
		List<DBWishItems> WlistEmp = empDao.WlistEmp();
		model.addObject("WlistEmp", WlistEmp);
		model.setViewName("mainPage");


		return model;
	}

}
