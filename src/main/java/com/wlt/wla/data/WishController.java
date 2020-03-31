package com.wlt.wla.data;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.wlt.wla.auth.model.Balance;
import com.wlt.wla.auth.model.DBWishItems;
import com.wlt.wla.auth.model.User;

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

    @RequestMapping(value = "/admin/cat")
    public ModelAndView editCatlistEmp(ModelAndView model) {

		model.setViewName("editCatList");
		model.addObject("CatEmp", empDao.CatEmp());

		return model;
	}



    private List<User> getPartOfUserList(int page, int itemsPerPage) {
        int startItem = page * itemsPerPage;

        return empDao.UlistEmp(itemsPerPage, startItem - itemsPerPage);
    }

    private List<DBWishItems> getPartOfItemList(int page, int itemsPerPage) {
        int startItem = page * itemsPerPage;

        return empDao.WlistEmp(itemsPerPage, startItem - itemsPerPage);
    }

    private List<DBWishItems> getPartOfItemList(int page, int itemsPerPage, String category) {
        int startItem = page * itemsPerPage;

        return empDao.WlistEmp(itemsPerPage, startItem - itemsPerPage, category);
    }

    private int getPageCurrentPage(String pageStr) {
        int page = 1;
        if (pageStr != null && !pageStr.equals("0")) {
            page = Integer.parseInt(pageStr);
        }
        return page;
    }

    @RequestMapping(value = "/admin/users")
    public ModelAndView UserlistEmp(ModelAndView model, HttpServletRequest request) {

        model.setViewName("userList");

        int page = getPageCurrentPage(request.getParameter("page"));
        int itemsPerPage = 5;
        int pageCount = (int) Math.ceil(empDao.getUlistEmpSize() * 1.0 / itemsPerPage);

        List<User> test = getPartOfUserList(page, itemsPerPage);

        model.addObject("UlistEmp", test);
        model.addObject("currentPage", page);
        model.addObject("pageCount", pageCount);
        return model;
    }

    @RequestMapping(value = "/add")
    public ModelAndView listCat(ModelAndView modelAndView) {

        modelAndView.addObject("CatEmp", empDao.CatEmp());
        modelAndView.addObject("balance", String.format(Locale.US, "%.2f", empDao.getBalance()));
        modelAndView.addObject("currencyCode", empDao.getCurrencyCode());
        modelAndView.addObject("PriorEmp", empDao.PriorEmp());
        modelAndView.addObject("Item", new DBWishItems());
		modelAndView.setViewName("addItem");

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
    public ModelAndView itemList(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.addObject("balance", String.format(Locale.US, "%.2f", empDao.getBalance()));
        modelAndView.addObject("currencyCode", empDao.getCurrencyCode());

        int page = getPageCurrentPage(request.getParameter("page"));
        int itemsPerPage = 5;

        String category = request.getParameter("category");
        List<DBWishItems> test;
        int pageCount;

        if (category != null) {
            pageCount = (int) Math.ceil(empDao.WlistEmpSize(category) * 1.0 / itemsPerPage);
            test = getPartOfItemList(page, itemsPerPage, category);
        } else {
            pageCount = (int) Math.ceil(empDao.WlistEmpSize() * 1.0 / itemsPerPage);
            test = getPartOfItemList(page, itemsPerPage);
        }

        modelAndView.addObject("category", category);
        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("pageCount", pageCount);
        modelAndView.addObject("WlistEmp", test);
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
                Integer.parseInt(request.getParameter("category")),
                Integer.parseInt(request.getParameter("priority")),
                request.getParameter("url")
        ));

        List<DBCatItems> catItems = empDao.CatEmp();
        Map<Integer, String> categoriesMap = new HashMap<>();
        for (DBCatItems item : catItems) {
            categoriesMap.put(item.getId(), item.getName());
        }

        List<DBPriorities> priorities = empDao.PriorEmp();
        Map<Integer, String> prioritiesMap = new HashMap<>();
        for (DBPriorities priority : priorities) {
            prioritiesMap.put(priority.getId(), priority.getName());
        }

        model.addObject("CatEmp", categoriesMap);
        model.addObject("PriorEmp", prioritiesMap);
        model.setViewName("itemEdit");
        return model;
    }

}
