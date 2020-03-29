package com.wlt.wla.data;
import java.util.List;

import com.wlt.wla.auth.model.DBWishItems;
import com.wlt.wla.auth.model.User;

public interface WishListDao {

	public List<DBWishItems> WlistEmp();
	public List<DBCatItems> CatEmp();
	public List<DBPriorities> PriorEmp();
	public List<User> UlistEmp();

	public float getBalance();
	public String getCurrencyCode();

}