package com.wlt.wla.data;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.wlt.wla.data.*;
@Controller
public class WishController {
	
	@Autowired
	private WishListDao empDao;

	@RequestMapping(value = "/add")
	public ModelAndView listCat(ModelAndView model) throws IOException {

		List<DBCatItems> CatEmp = empDao.CatEmp();
		model.addObject("CatEmp", CatEmp);
		List<DBPriorities> PriorEmp = empDao.PriorEmp();
		model.addObject("PriorEmp", PriorEmp);
		model.setViewName("addItem");

		return model;
	}

	@RequestMapping(value = "/home")
	public ModelAndView listEmp(ModelAndView model) throws IOException {

		float balance = empDao.getBalance();
		model.addObject("balance", balance);
		List<DBWishItems> WlistEmp = empDao.WlistEmp();
		model.addObject("WlistEmp", WlistEmp);
		model.setViewName("mainPage");

		return model;
	}

	@RequestMapping(value = "/itemList")
	public ModelAndView itemList(ModelAndView model) throws IOException {

		float balance = empDao.getBalance();
		model.addObject("balance", balance);
		List<DBWishItems> WlistEmp = empDao.WlistEmp();
		model.addObject("WlistEmp", WlistEmp);
		model.setViewName("itemList");

		return model;
	}
	@RequestMapping(value =  "/catList")
	public ModelAndView catList(ModelAndView model) throws IOException {

		float balance = empDao.getBalance();
		model.addObject("balance", balance);
		List<DBCatItems> CatEmp = empDao.CatEmp();
		model.addObject("CatEmp", CatEmp);
		model.setViewName("catList");

		return model;
	}
}
