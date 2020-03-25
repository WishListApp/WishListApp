package com.wlt.wla.data;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.wlt.wla.data.WishListDao;
import com.wlt.wla.data.DBWishItems;
import com.wlt.wla.data.DBCatItems;

@Controller
public class WishController {
	
	@Autowired
	private WishListDao empDao;

	@RequestMapping(value = "/add")
	public ModelAndView listCat(ModelAndView model) throws IOException {

		List<DBCatItems> CatEmp = empDao.CatEmp();
		model.addObject("CatEmp", CatEmp);
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

}
