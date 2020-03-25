package com.wlt.wla.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.wlt.wla.data.WishItems;

public class WishListDaoImpl implements WishListDao {

	private JdbcTemplate jdbcTemp;

	public WishListDaoImpl(DataSource dataSource) {
		jdbcTemp = new JdbcTemplate(dataSource);
	}

	@Override
	public List<WishItems> WlistEmp() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		List<WishItems> list = jdbcTemp.query(
			"SELECT wishlist_items . * , item_cat.name AS cat_name, priority.name AS priority_name\n" + 
			"FROM `wishlist_items` , priority, user, item_cat\n" + 
			"WHERE priority.id = wishlist_items.priority\n" + 
			"AND user.username = '"+currentPrincipalName+"'\n" + 
			"AND item_cat.id = wishlist_items.cat_id\n" + 
			"AND user.id = user_id\n" + 
			"ORDER BY priority DESC\n" + 
			"LIMIT 0 , 30\n" + 
			"",
				new RowMapper<WishItems>() {

					@Override
					public WishItems mapRow(ResultSet rs, int rowNum) throws SQLException {
						WishItems emp = new WishItems();

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
