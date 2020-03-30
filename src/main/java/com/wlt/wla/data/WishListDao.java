package com.wlt.wla.data;
import java.util.List;

import com.wlt.wla.auth.model.DBWishItems;
import com.wlt.wla.auth.model.User;

public interface WishListDao {

	public List<DBWishItems> WlistEmp();
	public List<DBCatItems> CatEmp();
	public List<DBPriorities> PriorEmp();
	public List<DBWishItems> WlistRestoreEmp();
	public List<User> UlistEmp(int limit, int offset);
	public int UlistEmpSize();
	public float getBalance();
	public String getCurrencyCode();
	List<DBWishItems> WlistArchiveEmp();
	

}