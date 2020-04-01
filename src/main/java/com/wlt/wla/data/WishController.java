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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WishController {

    @Autowired
    private WishListDao empDao;

    @GetMapping("/export")
    public ModelAndView export(ModelAndView model) {
    	System.out.println("balexport 1");
        model.addObject("WishListModXLS", empDao.WlistEmp());
        System.out.println("balexport 2");
        model.setViewName("excelView");
        System.out.println("balexport 3");
        return model;
    }

    @GetMapping("/balexport")
    public ModelAndView balexport(ModelAndView model) {
        model.addObject("BalanceModXLS", empDao.getBalanceHistory());
        model.addObject("balance", String.format(Locale.US, "%.2f", empDao.getBalance()));
        model.setViewName("balView");
        return model;
    }


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

    private List<Balance> getPartOfBalanceList(int page, int itemsPerPage) {
        int startItem = page * itemsPerPage;

        return empDao.getBalanceHistory(itemsPerPage, startItem - itemsPerPage);
    }

    private List<DBWishItems> getPartOfWListRestore(int page, int itemsPerPage) {
        int startItem = page * itemsPerPage;

        return empDao.WlistRestoreEmp(itemsPerPage, startItem - itemsPerPage);
    }

    private List<DBWishItems> getPartOfWListArchive(int page, int itemsPerPage) {
        int startItem = page * itemsPerPage;

        return empDao.WlistArchiveEmp(itemsPerPage, startItem - itemsPerPage);
    }

    private int getCurrentPage(String pageStr) {
        int page = 1;
        if (pageStr != null && !pageStr.equals("0")) {
            page = Integer.parseInt(pageStr);
        }
        return page;
    }

    @RequestMapping(value = "/admin/users")
    public ModelAndView UserlistEmp(ModelAndView model, HttpServletRequest request) {

        model.setViewName("userList");

        int page = getCurrentPage(request.getParameter("page"));
        int itemsPerPage = 5;
        int pageCount = (int) Math.ceil(empDao.getUlistEmpSize() * 1.0 / itemsPerPage);

        List<User> test = getPartOfUserList(page, itemsPerPage);

        model.addObject("UlistEmp", test);
        model.addObject("currentPage", page);
        model.addObject("pageCount", pageCount);
        return model;
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

        int page = getCurrentPage(request.getParameter("page"));
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

        modelAndView.addObject("CatEmp", empDao.CatEmp());
        modelAndView.addObject("PriorEmp", empDao.PriorEmp());
        modelAndView.addObject("Item", new DBWishItems());

        modelAndView.setViewName("itemList");

        return modelAndView;
    }

    @RequestMapping(value = "/restoreList")
    public ModelAndView restoreList(ModelAndView modelAndView, HttpServletRequest request) {
        int itemsPerPage = 5;
        int currentPage = getCurrentPage(request.getParameter("page"));
        int pageCount = (int) Math.ceil(empDao.getWlistRestoreSize() * 1.0 / itemsPerPage);
        modelAndView.addObject("currentPage", currentPage);
        modelAndView.addObject("balance", String.format(Locale.US, "%.2f", empDao.getBalance()));
        modelAndView.addObject("currencyCode", empDao.getCurrencyCode());
        modelAndView.addObject("WlistRestoreEmp", getPartOfWListRestore(currentPage, itemsPerPage));
        modelAndView.addObject("pageCount", pageCount);
        modelAndView.setViewName("restoreList");

        return modelAndView;
    }

    @RequestMapping(value = "/archiveItemList")
    public ModelAndView archiveItemList(ModelAndView modelAndView, HttpServletRequest request) {
        int currentPage = getCurrentPage(request.getParameter("page"));
        int itemsPerPage = 5;
        modelAndView.addObject("balance", String.format(Locale.US, "%.2f", empDao.getBalance()));
        modelAndView.addObject("currencyCode", empDao.getCurrencyCode());
        modelAndView.addObject("WlistArchiveEmp", getPartOfWListArchive(currentPage, itemsPerPage));
        modelAndView.addObject("pageCount",
                (int) Math.ceil(empDao.getWlistArchiveSize() * 1.0 / itemsPerPage));
        modelAndView.addObject("currentPage", currentPage);
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

    @GetMapping("/balanceHistory")
    public ModelAndView balanceHistory(ModelAndView modelAndView, HttpServletRequest request) {
        int itemsPerPage = 8;

        int currentPage = getCurrentPage(request.getParameter("page"));
        modelAndView.addObject("currencyCode", empDao.getCurrencyCode());
        modelAndView.addObject("balance", String.format(Locale.US, "%.2f", empDao.getBalance()));
        modelAndView.addObject("balances", getPartOfBalanceList(currentPage, itemsPerPage));
        modelAndView.addObject("pageCount",
                (int) Math.ceil(empDao.getBalanceHistorySize() * 1.0 / itemsPerPage));
        modelAndView.addObject("currentPage", currentPage);
        modelAndView.setViewName("balanceHistory");

        return modelAndView;
    }

}
