package com.wlt.wla.data;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.wlt.wla.auth.model.Balance;
import com.wlt.wla.auth.model.DBWishItems;
import com.wlt.wla.auth.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WishController {
	
	@Autowired
	private WishListDao empDao;
	
	@RequestMapping(value = "/admin/cat")
	public ModelAndView editCatlistEmp(ModelAndView model) {

		model.addObject("balance", String.format(Locale.US, "%.2f", empDao.getBalance()));
		model.addObject("currencyCode", empDao.getCurrencyCode());
		model.setViewName("editCatList");
		model.addObject("CatEmp", empDao.CatEmp());

		return model;
	}	
	
	@RequestMapping(value = "/admin/users")
	public ModelAndView UserlistEmp(ModelAndView model) {

		model.addObject("balance", String.format(Locale.US, "%.2f", empDao.getBalance()));
		model.addObject("currencyCode", empDao.getCurrencyCode());
		model.setViewName("userList");
		
		List<User> UlistEmp = empDao.UlistEmp();
		model.addObject("UlistEmp", UlistEmp);

		return model;
	}

	@RequestMapping(value = "/add")
	public ModelAndView listCat(ModelAndView modelAndView, Model model) {

		modelAndView.addObject("CatEmp", empDao.CatEmp());
		modelAndView.addObject("balance", String.format(Locale.US, "%.2f", empDao.getBalance()));
		modelAndView.addObject("currencyCode", empDao.getCurrencyCode());
		modelAndView.addObject("PriorEmp", empDao.PriorEmp());
		modelAndView.setViewName("addItem");
		model.addAttribute("Item", new DBWishItems());

		return modelAndView;
	}

	@RequestMapping(value = "/home")
	public ModelAndView listEmp(ModelAndView model) {

		model.addObject("balance", String.format(Locale.US, "%.2f", empDao.getBalance()));
		model.addObject("currencyCode", empDao.getCurrencyCode());
		model.setViewName("mainPage");

		return model;
	}

        model.addObject("balance", String.format(Locale.US, "%.2f", empDao.getBalance()));
        model.addObject("currencyCode", empDao.getCurrencyCode());
        model.setViewName("mainPage");

        return model;
    }

    @RequestMapping(value = "/itemList")
    public ModelAndView itemList(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.addObject("balance", String.format(Locale.US, "%.2f", empDao.getBalance()));
        modelAndView.addObject("currencyCode", empDao.getCurrencyCode());
        List<DBWishItems> fullList = empDao.WlistEmp();

        int page = 1;
        String pageStr;

        if ((pageStr = request.getParameter("page")) != null && pageStr != "0") {
            page = Integer.parseInt(pageStr);
        }
		final int LAST_ITEM = 5 * page;
        int lastItemNum = LAST_ITEM;

        int itemsPerPage = 5;
        int size = fullList.size();

        if (size <= lastItemNum) {
            lastItemNum = size;
        }

        int startItem;
        if (size < lastItemNum) {
            startItem = 0;
        } else {
        	if (lastItemNum % 5 == 0) {
				startItem = lastItemNum - itemsPerPage;
			} else {
        		startItem = lastItemNum - (lastItemNum - (LAST_ITEM - itemsPerPage));
			}
        }

		List<DBWishItems> partList = fullList.subList(startItem, lastItemNum);
        int pageCount = (int) Math.ceil(size * 1.0 / itemsPerPage);
        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("pageCount", pageCount);
        modelAndView.addObject("WlistEmp", partList);
        modelAndView.setViewName("itemList");

		return modelAndView;
	}
	
	@RequestMapping(value = "/restoreList")
	public ModelAndView restoreList(ModelAndView modelAndView) {
		modelAndView.addObject("balance", String.format(Locale.US, "%.2f", empDao.getBalance()));
		modelAndView.addObject("currencyCode", empDao.getCurrencyCode());
		modelAndView.addObject("WlistRestoreEmp", empDao.WlistRestoreEmp());
		modelAndView.setViewName("restoreList");

		return modelAndView;
	}
	
	@RequestMapping(value = "/archiveItemList")
	public ModelAndView archiveItemList(ModelAndView modelAndView) {
		modelAndView.addObject("balance", String.format(Locale.US, "%.2f", empDao.getBalance()));
		modelAndView.addObject("currencyCode", empDao.getCurrencyCode());
		modelAndView.addObject("WlistArchiveEmp", empDao.WlistArchiveEmp());
		modelAndView.setViewName("archiveItemList");

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
