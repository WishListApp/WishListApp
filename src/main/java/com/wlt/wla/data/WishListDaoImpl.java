package com.wlt.wla.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.wlt.wla.data.*;

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
			"SELECT * from item_cat ORDER BY id ASC",
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
				new RowMapper<DBPriorities>() {

					@Override
					public DBPriorities mapRow(ResultSet rs, int rowNum) throws SQLException {
						DBPriorities emp = new DBPriorities();

						emp.setName(rs.getString("name"));
						emp.setId(rs.getInt("id"));
						return emp;
					}

				});

		return list;
	}
	
	
	@Override
	public List<DBWishItems> WlistEmp() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		List<DBWishItems> list = jdbcTemp.query(
			"SELECT wishlist_items . * , item_cat.name AS cat_name, priority.name AS priority_name\n" + 
			"FROM `wishlist_items` , priority, user, item_cat\n" + 
			"WHERE priority.id = wishlist_items.priority\n" + 
			"AND user.username = '"+currentPrincipalName+"'\n" + 
			"AND item_cat.id = wishlist_items.cat_id\n" + 
			"AND user.id = user_id\n" + 
			"ORDER BY priority DESC\n",
				new RowMapper<DBWishItems>() {

					@Override
					public DBWishItems mapRow(ResultSet rs, int rowNum) throws SQLException {
						DBWishItems emp = new DBWishItems();

						emp.setName(rs.getString("name"));
						emp.setGroup(rs.getInt("cat_id"));
						emp.setId(rs.getInt("id"));
						emp.setPrice(rs.getFloat("price"));
						emp.setUser_id(rs.getInt("user_id"));
						emp.setPriority(rs.getInt("priority"));
						emp.setCat_name(rs.getString("cat_name"));
						emp.setPriority_name(rs.getString("priority_name"));
						return emp;
					}

				});

		return list;
	}


}
