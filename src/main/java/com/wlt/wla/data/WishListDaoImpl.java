package com.wlt.wla.data;

import com.wlt.wla.auth.model.Balance;
import com.wlt.wla.auth.model.DBWishItems;
import com.wlt.wla.auth.model.User;
import com.wlt.wla.parsers.imgParsers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.sql.DataSource;
import java.util.List;
import java.util.Locale;

public class WishListDaoImpl implements WishListDao {

	private JdbcTemplate jdbcTemp;

	public WishListDaoImpl(DataSource dataSource) {
		jdbcTemp = new JdbcTemplate(dataSource);
	}

	@Override
	public List<DBCatItems> CatEmp() {
		List<DBCatItems> list = jdbcTemp.query("SELECT * from item_cat ORDER BY name ASC", (rs, rowNum) -> {
			DBCatItems emp = new DBCatItems();

			emp.setName(rs.getString("name"));
			emp.setId(rs.getInt("id"));
			return emp;
		});

		return list;
	}

	@Override
	public List<DBPriorities> PriorEmp() {
		List<DBPriorities> list = jdbcTemp.query("SELECT * from priority ORDER BY id ASC", (rs, rowNum) -> {
			DBPriorities emp = new DBPriorities();

			emp.setName(rs.getString("name"));
			emp.setId(rs.getInt("id"));
			return emp;
		});

		return list;
	}

	@Override
	public List<User> UlistEmp(int limit, int offset) {
		List<User> list = jdbcTemp
				.query("SELECT * from user,user_roles WHERE user_roles.users_id=user.id ORDER BY id ASC LIMIT " + limit
						+ " OFFSET " + offset, (rs, rowNum) -> {
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
		String query = "SELECT COUNT(*) " + "FROM dr_wishlist.user,dr_wishlist.user_roles "
				+ "WHERE user_roles.users_id=user.id ORDER BY id ASC";

		return jdbcTemp.queryForObject(query, Integer.class);
	}

	@Override
	public List<DBWishItems> WlistEmp() {
		List<DBWishItems> list = jdbcTemp
				.query("SELECT wishlist_items . * , item_cat.name AS cat_name, priority.name AS priority_name\n"
						+ "FROM `wishlist_items` , priority, user, item_cat\n"
						+ "WHERE priority.id = wishlist_items.priority\n" + "AND user.username = '" + getUsername()
						+ "'\n" + "AND item_cat.id = wishlist_items.cat_id\n" + "AND user.id = user_id\n"
						+ "AND status = 0 " + "ORDER BY priority DESC, wishlist_items.name, wishlist_items.id ASC\n", (rs, rowNum) -> {
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

							return emp;
						});

		return list;
	}

	@Override
	public List<DBWishItems> WlistEmp(int limit, int offset) {
		List<DBWishItems> list = jdbcTemp.query(
				"SELECT wishlist_items . * , item_cat.name AS cat_name, priority.name AS priority_name, `img_url` \n"
						+ "FROM `wishlist_items`\n" + "LEFT JOIN ImgSrc ON ImgSrc.items_id = wishlist_items.id\n"
						+ "INNER JOIN item_cat ON item_cat.id = wishlist_items.cat_id\n"
						+ "INNER JOIN user ON user.id = user_id \n"
						+ "INNER JOIN priority ON priority.id = wishlist_items.priority\n"
						+ "WHERE STATUS =0 AND user.username = '" + getUsername() + "'\n"
						+ "ORDER BY priority DESC, wishlist_items.name, wishlist_items.id ASC\n" + "LIMIT " + limit + " OFFSET " + offset,

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
					emp.setUrlImg(rs.getString("img_url"));

					if (rs.getString("img_url") == null) {
						imgParsers pp = new imgParsers();
						if (rs.getString("url").contains("www.salidzini.lv/i/")) {
							String img = pp.getImgSalidzini(rs.getString("url"));
							emp.setUrlImg(img);
							if (!img.equals("")) {
								String sql = "REPLACE INTO `dr_wishlist`.`ImgSrc` (`items_id`, `img_url`) VALUES ('"
										+ rs.getInt("id") + "', '" + img + "')";
								jdbcTemp.execute(sql);
							}
						}
						if (rs.getString("url").contains("aliexpress.com")) {
							String img = pp.getImgAlie(rs.getString("url"));
							emp.setUrlImg(img);
							if (!img.equals("")) {
								String sql = "REPLACE INTO `dr_wishlist`.`ImgSrc` (`items_id`, `img_url`) VALUES ('"
										+ rs.getInt("id") + "', '" + img + "')";
								jdbcTemp.execute(sql);
							}
						}
					}

					return emp;
				});

		return list;
	}

	@Override
	public List<DBWishItems> WlistEmp(int limit, int offset, String category) {
		List<DBWishItems> list = jdbcTemp
				.query("SELECT wishlist_items. * , item_cat.name AS cat_name, priority.name AS priority_name\n"
						+ "FROM wishlist_items , priority, user, item_cat\n"
						+ "WHERE priority.id = wishlist_items.priority\n" + "AND user.username = '" + getUsername()
						+ "'\n" + "AND item_cat.id = wishlist_items.cat_id\n"
						+ "AND user.id = user_id AND item_cat.name ='" + category + "'\n" + "AND STATUS =0 LIMIT "
						+ limit + " OFFSET " + offset, (rs, rowNum) -> {
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
	public int WlistEmpSize(String category) {
		String query = "SELECT \n" + "    COUNT(*)\n" + "FROM\n" + "    dr_wishlist.wishlist_items,\n"
				+ "    dr_wishlist.priority,\n" + "    dr_wishlist.user,\n" + "    dr_wishlist.item_cat\n" + "WHERE\n"
				+ "    priority.id = wishlist_items.priority\n" + "        AND user.username = '" + getUsername()
				+ "'\n" + "        AND item_cat.id = wishlist_items.cat_id\n" + "        AND user.id = user_id\n"
				+ "        AND item_cat.name = '" + category + "'\n" + "        AND STATUS = 0";

		return jdbcTemp.queryForObject(query, Integer.class);
	}

	@Override
	public int WlistEmpSize() {
		int user_id = getUserId();
		String query = "SELECT COUNT(*) FROM dr_wishlist.wishlist_items WHERE user_id = ? AND status = 0";

		return jdbcTemp.queryForObject(query, new Object[] { user_id }, Integer.class);
	}

	@Override
	public List<DBWishItems> WlistArchiveEmp(int limit, int offset) {
		List<DBWishItems> list = jdbcTemp
				.query("SELECT wishlist_items . * , item_cat.name AS cat_name, priority.name AS priority_name\n"
						+ "FROM `wishlist_items` , priority, user, item_cat\n"
						+ "WHERE priority.id = wishlist_items.priority\n" + "AND user.username = '" + getUsername()
						+ "'\n" + "AND item_cat.id = wishlist_items.cat_id\n" + "AND user.id = user_id\n"
						+ "AND status = 1 " + "ORDER BY priority DESC, wishlist_items.name, wishlist_items.id ASC LIMIT " + limit
						+ " OFFSET " + offset, (rs, rowNum) -> {
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
							// parseimg
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
	public List<DBWishItems> WlistRestoreEmp(int limit, int offset) {
		List<DBWishItems> list = jdbcTemp
				.query("SELECT wishlist_items . * , item_cat.name AS cat_name, priority.name AS priority_name\n"
						+ "FROM `wishlist_items` , priority, user, item_cat\n"
						+ "WHERE priority.id = wishlist_items.priority\n" + "AND user.username = '" + getUsername()
						+ "'\n" + "AND item_cat.id = wishlist_items.cat_id\n" + "AND user.id = user_id\n"
						+ "AND status = -1 " + "ORDER BY priority DESC, wishlist_items.name, wishlist_items.id ASC LIMIT " + limit
						+ " OFFSET " + offset, (rs, rowNum) -> {
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
	public int getUserId() {
		int userId = 0;
		String query = "SELECT id FROM dr_wishlist.user WHERE username=?";

		try {
			userId = jdbcTemp.queryForObject(query, new Object[] { getUsername() }, Integer.class);
		} catch (NullPointerException e) {
			System.err.println(e.getMessage());
		}
		return userId;
	}

	@Override
	public float getBalance() {

		int userId = getUserId();

		float balance = 0;

		String query = "SELECT SUM(balance_changes ) as total\n" + "FROM dr_wishlist.balance\n" + "WHERE user_id=?";

		try {
			balance = jdbcTemp.queryForObject(query, new Object[] { userId }, Float.class);
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
			currencyId = jdbcTemp.queryForObject(query, new Object[] { userId }, Integer.class);
		} catch (NullPointerException e) {
			System.err.println(e.getMessage());
		}

		query = "SELECT code FROM dr_wishlist.currency WHERE id = ?";
		String currencyCode = "None";

		try {
			currencyCode = jdbcTemp.queryForObject(query, new Object[] { currencyId }, String.class);
		} catch (NullPointerException e) {
			System.err.println(e.getMessage());
		}

		return currencyCode;
	}

	@Override
	public List<Balance> getBalanceHistory(int limit, int offset) {
		int user_id = getUserId();

		String query = "SELECT * FROM dr_wishlist.balance WHERE user_id = " + user_id + " LIMIT " + limit + " OFFSET "
				+ offset;

		List<Balance> balances = jdbcTemp.query(query, (rs, rowNum) -> {
			Balance balance = new Balance();
			balance.setBalanceChangeStr(String.format(Locale.US, "%.2f", rs.getFloat("balance_changes")));
			balance.setNote(rs.getString("note"));
			balance.setTimestamp(rs.getString("timestamp"));
			return balance;
		});

		return balances;
	}

	@Override
	public List<Balance> getBalanceHistory() {
		int user_id = getUserId();

		String query = "SELECT * FROM dr_wishlist.balance WHERE user_id = " + user_id;

		List<Balance> balances = jdbcTemp.query(query, (rs, rowNum) -> {
			Balance balance = new Balance();
			balance.setBalanceChangeStr(String.format(Locale.US, "%.2f", rs.getFloat("balance_changes")));
			balance.setNote(rs.getString("note"));
			balance.setTimestamp(rs.getString("timestamp"));
			return balance;
		});

		return balances;
	}

	@Override
	public int getBalanceHistorySize() {
		int id = getUserId();
		String query = "SELECT COUNT(*) FROM dr_wishlist.balance WHERE user_id = ?";
		return jdbcTemp.queryForObject(query, new Object[] { id }, Integer.class);
	}

	@Override
	public int getWlistRestoreSize() {
		String query = "SELECT COUNT(*)\n"
				+ "                FROM dr_wishlist.wishlist_items , dr_wishlist.priority, dr_wishlist.user, dr_wishlist.item_cat\n"
				+ "                WHERE priority.id = wishlist_items.priority\n"
				+ "                AND user.username = '" + getUsername() + "'\n"
				+ "                AND item_cat.id = wishlist_items.cat_id\n"
				+ "                AND user.id = user_id AND status = -1 \n"
				+ "                ORDER BY priority DESC, wishlist_items.id ASC";

		return jdbcTemp.queryForObject(query, Integer.class);
	}

	private String getUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

	public int getWlistArchiveSize() {
		String query = "SELECT COUNT(*)\n" + "FROM `wishlist_items` , priority, user, item_cat\n"
				+ "WHERE priority.id = wishlist_items.priority\n" + "AND user.username = '" + getUsername() + "'\n"
				+ "AND item_cat.id = wishlist_items.cat_id\n" + "AND user.id = user_id\n" + "AND status = 1 "
				+ "ORDER BY priority DESC, wishlist_items.id ASC\n";

		return jdbcTemp.queryForObject(query, Integer.class);
	}

	@Override
	public String getSettings() {
		String query ="SELECT value  FROM `settings` WHERE `name` LIKE 'ecb_xml'";

		return jdbcTemp.queryForObject(query, String.class);
		
		
	
	}

}
