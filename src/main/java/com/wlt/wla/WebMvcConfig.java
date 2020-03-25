package com.wlt.wla;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.wlt.wla.data.WishListDao;
import com.wlt.wla.data.WishListDaoImpl;

@Configuration
@EnableWebMvc
//@ComponentScan(basePackages = { "com.wlt.wla.WlaController", "com.wlt.wla.data" })
public class WebMvcConfig {

	@Bean
	public InternalResourceViewResolver viewResolver() {

		InternalResourceViewResolver vr = new InternalResourceViewResolver();
		vr.setPrefix("/");
		vr.setSuffix(".jsp");

		return vr;
	}

	@Bean
	public DriverManagerDataSource getDataSource() {
		
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://195.60.248.170:3306/dr_wishlist");
		ds.setUsername("dr_wishusr");
		ds.setPassword("wishpas75");

		return ds;
	}

	@Bean
	public WishListDao getWishListDao() {
		return new WishListDaoImpl(getDataSource());
	}

}