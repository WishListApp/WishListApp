package com.wlt.wla.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import javax.sql.DataSource;

import com.wlt.wla.auth.model.DBWishItems;
import com.wlt.wla.auth.model.User;
import com.wlt.wla.parsers.imgParsers;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class WishListDaoImpl implements WishListDao {

    private JdbcTemplate jdbcTemp;

    public WishListDaoImpl(DataSource dataSource) {
        jdbcTemp = new JdbcTemplate(dataSource);
    }

    @Override
    public List<DBCatItems> CatEmp() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        List<DBCatItems> list = jdbcTemp.query(
                "SELECT * from item_cat ORDER BY name ASC",
                new RowMapper<DBCatItems>() {

                    @Override
                    public DBCatItems mapRow(ResultSet rs, int rowNum) throws SQLException {
                        DBCatItems emp = new DBCatItems();

                        emp.setName(rs.getString("name"));
                        emp.setId(rs.getInt("id"));
                        return emp;
                    }

                });

        return list;
    }

    @Override
    public List<DBPriorities> PriorEmp() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        List<DBPriorities> list = jdbcTemp.query(
                "SELECT * from priority ORDER BY id ASC",
                (rs, rowNum) -> {
                    DBPriorities emp = new DBPriorities();

                    emp.setName(rs.getString("name"));
                    emp.setId(rs.getInt("id"));
                    return emp;
                });

        return list;
    }


    @Override
    public List<User> UlistEmp(int limit, int offset) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        List<User> list = jdbcTemp.query(
                "SELECT * from user,user_roles WHERE user_roles.users_id=user.id ORDER BY id ASC LIMIT " + limit + " OFFSET " + offset,
                (rs, rowNum) -> {
                    User emp = new User();

                    emp.setUsername(rs.getString("username"));
                    emp.setId(rs.getLong("id"));
                    emp.setPassword(rs.getString("password"));
                    emp.setuRoleId(rs.getInt("roles_id"));

                    return emp;
                });

        return list;
    }

    @Override
    public int getUlistEmpSize() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        List<User> list = jdbcTemp.query(
                "SELECT * from user,user_roles WHERE user_roles.users_id=user.id ORDER BY id ASC",
                (rs, rowNum) -> {
                    User emp = new User();

                    emp.setUsername(rs.getString("username"));
                    emp.setId(rs.getLong("id"));
                    emp.setPassword(rs.getString("password"));
                    emp.setuRoleId(rs.getInt("roles_id"));

                    return emp;
                });

        return list.size();
    }


    @Override
    public List<DBWishItems> WlistEmp(int limit, int offset) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        List<DBWishItems> list = jdbcTemp.query(
                "SELECT wishlist_items . * , item_cat.name AS cat_name, priority.name AS priority_name\n" +
                        "FROM `wishlist_items` , priority, user, item_cat\n" +
                        "WHERE priority.id = wishlist_items.priority\n" +
                        "AND user.username = '" + currentPrincipalName + "'\n" +
                        "AND item_cat.id = wishlist_items.cat_id\n" +
                        "AND user.id = user_id\n" + "AND status = 0 " +
                        "ORDER BY priority DESC, wishlist_items.id ASC\n" +
                        "LIMIT " + limit + " OFFSET " + offset,
                (rs, rowNum) -> {
                    DBWishItems emp = new DBWishItems();

                    emp.setName(rs.getString("name"));
                    emp.setGroup(rs.getInt("cat_id"));
                    emp.setId(rs.getInt("id"));
                    emp.setPrice(rs.getFloat("price"));
                    emp.setPriceStr(String.format(Locale.US, "%.2f", emp.getPrice()));
                    emp.setUser_id(rs.getInt("user_id"));
                    emp.setPriority(rs.getInt("priority"));
                    emp.setCat_name(rs.getString("cat_name"));
                    emp.setPriority_name(rs.getString("priority_name"));
                    emp.setUrl(rs.getString("url"));
                    //parseimg
                    imgParsers pp = new imgParsers();
                    if (rs.getString("url").contains("www.salidzini.lv/i/"))
                        emp.setUrlImg(pp.getImgSalidzini(rs.getString("url")));
                    if (rs.getString("url").contains("aliexpress.com"))
                        emp.setUrlImg(pp.getImgAlie(rs.getString("url")));

                    //emp.setUrlImg("https://www.websitecodetutorials.com/code/images/jamie-small1big.jpg");

                    return emp;
                });

        return list;
    }

    @Override
    public List<DBWishItems> WlistEmp(int limit, int offset, String category) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        List<DBWishItems> list = jdbcTemp.query(
				"SELECT wishlist_items. * , item_cat.name AS cat_name, priority.name AS priority_name\n" +
						"FROM wishlist_items , priority, user, item_cat\n" +
						"WHERE priority.id = wishlist_items.priority\n" +
						"AND user.username = '" + currentPrincipalName + "'\n" +
						"AND item_cat.id = wishlist_items.cat_id\n" +
						"AND user.id = user_id AND item_cat.name ='" + category + "'\n" +
						"AND STATUS =0 LIMIT " + limit + " OFFSET " + offset,
                (rs, rowNum) -> {
                    DBWishItems emp = new DBWishItems();

                    emp.setName(rs.getString("name"));
                    emp.setGroup(rs.getInt("cat_id"));
                    emp.setId(rs.getInt("id"));
                    emp.setPrice(rs.getFloat("price"));
                    emp.setPriceStr(String.format(Locale.US, "%.2f", emp.getPrice()));
                    emp.setUser_id(rs.getInt("user_id"));
                    emp.setPriority(rs.getInt("priority"));
                    emp.setCat_name(rs.getString("cat_name"));
                    emp.setPriority_name(rs.getString("priority_name"));
                    emp.setUrl(rs.getString("url"));
                    //parseimg
                    imgParsers pp = new imgParsers();
                    if (rs.getString("url").contains("www.salidzini.lv/i/"))
                        emp.setUrlImg(pp.getImgSalidzini(rs.getString("url")));
                    if (rs.getString("url").contains("aliexpress.com"))
                        emp.setUrlImg(pp.getImgAlie(rs.getString("url")));

                    //emp.setUrlImg("https://www.websitecodetutorials.com/code/images/jamie-small1big.jpg");

                    return emp;
                });
        return list;
    }

    @Override
    public int WlistEmpSize(String category) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        List<DBWishItems> list = jdbcTemp.query(
                "SELECT wishlist_items. * , item_cat.name AS cat_name, priority.name AS priority_name\n" +
                        "FROM wishlist_items , priority, user, item_cat\n" +
                        "WHERE priority.id = wishlist_items.priority\n" +
                        "AND user.username = '" + currentPrincipalName + "'\n" +
                        "AND item_cat.id = wishlist_items.cat_id\n" +
                        "AND user.id = user_id AND item_cat.name ='" + category + "'\n" +
                        "AND STATUS =0",
                (rs, rowNum) -> {
                    DBWishItems emp = new DBWishItems();

                    emp.setName(rs.getString("name"));
                    emp.setGroup(rs.getInt("cat_id"));
                    emp.setId(rs.getInt("id"));
                    emp.setPrice(rs.getFloat("price"));
                    emp.setPriceStr(String.format(Locale.US, "%.2f", emp.getPrice()));
                    emp.setUser_id(rs.getInt("user_id"));
                    emp.setPriority(rs.getInt("priority"));
                    emp.setCat_name(rs.getString("cat_name"));
                    emp.setPriority_name(rs.getString("priority_name"));
                    emp.setUrl(rs.getString("url"));
                    //parseimg
                    imgParsers pp = new imgParsers();
                    if (rs.getString("url").contains("www.salidzini.lv/i/"))
                        emp.setUrlImg(pp.getImgSalidzini(rs.getString("url")));
                    if (rs.getString("url").contains("aliexpress.com"))
                        emp.setUrlImg(pp.getImgAlie(rs.getString("url")));

                    //emp.setUrlImg("https://www.websitecodetutorials.com/code/images/jamie-small1big.jpg");

                    return emp;
                });

        return list.size();
    }

    @Override
    public int WlistEmpSize() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        List<DBWishItems> list = jdbcTemp.query(
                "SELECT wishlist_items . * , item_cat.name AS cat_name, priority.name AS priority_name\n" +
                        "FROM `wishlist_items` , priority, user, item_cat\n" +
                        "WHERE priority.id = wishlist_items.priority\n" +
                        "AND user.username = '" + currentPrincipalName + "'\n" +
                        "AND item_cat.id = wishlist_items.cat_id\n" +
                        "AND user.id = user_id\n" + "AND status = 0 " +
                        "ORDER BY priority DESC, wishlist_items.id ASC\n",
                (rs, rowNum) -> {
                    DBWishItems emp = new DBWishItems();

                    emp.setName(rs.getString("name"));
                    emp.setGroup(rs.getInt("cat_id"));
                    emp.setId(rs.getInt("id"));
                    emp.setPrice(rs.getFloat("price"));
                    emp.setPriceStr(String.format(Locale.US, "%.2f", emp.getPrice()));
                    emp.setUser_id(rs.getInt("user_id"));
                    emp.setPriority(rs.getInt("priority"));
                    emp.setCat_name(rs.getString("cat_name"));
                    emp.setPriority_name(rs.getString("priority_name"));
                    emp.setUrl(rs.getString("url"));
                    //parseimg
                    imgParsers pp = new imgParsers();
                    if (rs.getString("url").contains("www.salidzini.lv/i/"))
                        emp.setUrlImg(pp.getImgSalidzini(rs.getString("url")));
                    if (rs.getString("url").contains("aliexpress.com"))
                        emp.setUrlImg(pp.getImgAlie(rs.getString("url")));

                    //emp.setUrlImg("https://www.websitecodetutorials.com/code/images/jamie-small1big.jpg");

                    return emp;
                });

        return list.size();
    }

    @Override
    public List<DBWishItems> WlistArchiveEmp() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        List<DBWishItems> list = jdbcTemp.query(
                "SELECT wishlist_items . * , item_cat.name AS cat_name, priority.name AS priority_name\n" +
                        "FROM `wishlist_items` , priority, user, item_cat\n" +
                        "WHERE priority.id = wishlist_items.priority\n" +
                        "AND user.username = '" + currentPrincipalName + "'\n" +
                        "AND item_cat.id = wishlist_items.cat_id\n" +
                        "AND user.id = user_id\n" + "AND status = 1 " +
                        "ORDER BY priority DESC, wishlist_items.id ASC\n",
                (rs, rowNum) -> {
                    DBWishItems emp = new DBWishItems();

                    emp.setName(rs.getString("name"));
                    emp.setGroup(rs.getInt("cat_id"));
                    emp.setId(rs.getInt("id"));
                    emp.setPrice(rs.getFloat("price"));
                    emp.setPriceStr(String.format(Locale.US, "%.2f", emp.getPrice()));
                    emp.setUser_id(rs.getInt("user_id"));
                    emp.setPriority(rs.getInt("priority"));
                    emp.setCat_name(rs.getString("cat_name"));
                    emp.setPriority_name(rs.getString("priority_name"));
                    emp.setUrl(rs.getString("url"));
                    //parseimg
                    imgParsers pp = new imgParsers();
                    if (rs.getString("url").contains("www.salidzini.lv/i/"))
                        emp.setUrlImg(pp.getImgSalidzini(rs.getString("url")));
                    if (rs.getString("url").contains("aliexpress.com"))
                        emp.setUrlImg(pp.getImgAlie(rs.getString("url")));


                    return emp;
                });

        return list;
    }


    @Override
    public List<DBWishItems> WlistRestoreEmp() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        List<DBWishItems> list = jdbcTemp.query(
                "SELECT wishlist_items . * , item_cat.name AS cat_name, priority.name AS priority_name\n" +
                        "FROM `wishlist_items` , priority, user, item_cat\n" +
                        "WHERE priority.id = wishlist_items.priority\n" +
                        "AND user.username = '" + currentPrincipalName + "'\n" +
                        "AND item_cat.id = wishlist_items.cat_id\n" +
                        "AND user.id = user_id\n" + "AND status = -1 " +
                        "ORDER BY priority DESC, wishlist_items.id ASC\n",
                (rs, rowNum) -> {
                    DBWishItems emp = new DBWishItems();

                    emp.setName(rs.getString("name"));
                    emp.setGroup(rs.getInt("cat_id"));
                    emp.setId(rs.getInt("id"));
                    emp.setPrice(rs.getFloat("price"));
                    emp.setPriceStr(String.format(Locale.US, "%.2f", emp.getPrice()));
                    emp.setUser_id(rs.getInt("user_id"));
                    emp.setPriority(rs.getInt("priority"));
                    emp.setCat_name(rs.getString("cat_name"));
                    emp.setPriority_name(rs.getString("priority_name"));
                    emp.setUrl(rs.getString("url"));
                    //parseimg
                    imgParsers pp = new imgParsers();
                    if (rs.getString("url").contains("www.salidzini.lv/i/"))
                        emp.setUrlImg(pp.getImgSalidzini(rs.getString("url")));
                    if (rs.getString("url").contains("aliexpress.com"))
                        emp.setUrlImg(pp.getImgAlie(rs.getString("url")));

                    //emp.setUrlImg("https://www.websitecodetutorials.com/code/images/jamie-small1big.jpg");

                    return emp;
                });

        return list;
    }

    public int getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        int userId = 0;
        String query = "SELECT id FROM dr_wishlist.user WHERE username=?";

        try {
            userId = jdbcTemp.queryForObject(query, new Object[]{currentPrincipalName}, Integer.class);
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }
        return userId;
    }

    @Override
    public float getBalance() {

        int userId = getUserId();

        float balance = 0;

        String query = "SELECT SUM(balance_changes ) as total\n" +
                "FROM dr_wishlist.balance\n" +
                "WHERE user_id=?";

        try {
            balance = jdbcTemp.queryForObject(query, new Object[]{userId}, Float.class);
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }

        return balance;
    }

    @Override
    public String getCurrencyCode() {
        int userId = getUserId();

        String query = "SELECT currency_id FROM dr_wishlist.user_pref WHERE user_id = ?";
        int currencyId = 1;

        try {
            currencyId = jdbcTemp.queryForObject(query, new Object[]{userId}, Integer.class);
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }

        query = "SELECT code FROM dr_wishlist.currency WHERE id = ?";
        String currencyCode = "None";

        try {
            currencyCode = jdbcTemp.queryForObject(query, new Object[]{currencyId}, String.class);
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }

        return currencyCode;
    }

}
