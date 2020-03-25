package com.wlt.wla.data;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.wlt.wla.data.WishListDao;
import com.wlt.wla.data.WishItems;

@Controller
public class WishController {
	
	@Autowired
	private WishListDao empDao;

	@RequestMapping(value = "/home")
	public ModelAndView listEmployee(ModelAndView model) throws IOException {

		List<WishItems> WlistEmp = empDao.WlistEmp();
		float balance = empDao.getBalance();
		model.addObject("balance", balance);
		model.addObject("WlistEmp", WlistEmp);
		model.setViewName("mainPage");

		return model;
	}

}
