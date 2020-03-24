package com.wlt.wla.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.wlt.wla.data.WishItems;

public class WishListDaoImpl implements WishListDao {

	private JdbcTemplate jdbcTemp;

	public WishListDaoImpl(DataSource dataSource) {
		jdbcTemp = new JdbcTemplate(dataSource);
	}

	@Override
	public List<WishItems> WlistEmp() {
		List<WishItems> list = jdbcTemp.query("SELECT * FROM wishlist_items", new RowMapper<WishItems>() {

			@Override
			public WishItems mapRow(ResultSet rs, int rowNum) throws SQLException {
				WishItems emp = new WishItems();

				emp.setName(rs.getString("name"));
//				private String name;
//				private int id;
//				private float price;
//				private int group;
//				private int user_id;
				emp.setGroup(rs.getInt("cat_id"));
				emp.setId(rs.getInt("id"));
				emp.setPrice(rs.getFloat("price"));
				emp.setUser_id(rs.getInt("user_id"));
				return emp;
			}

		});

		return list;
	}

}
