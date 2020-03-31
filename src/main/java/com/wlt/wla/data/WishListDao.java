package com.wlt.wla.data;
import java.util.List;

import com.wlt.wla.auth.model.Balance;
import com.wlt.wla.auth.model.DBWishItems;
import com.wlt.wla.auth.model.User;

public interface WishListDao {

	public List<DBWishItems> WlistEmp(int limit, int offset);
	public List<DBWishItems> WlistEmp(int limit, int offset, String category);
	public int WlistEmpSize();
	public int WlistEmpSize(String category);
	public List<DBCatItems> CatEmp();
	public List<DBPriorities> PriorEmp();
	public List<DBWishItems> WlistRestoreEmp();
	public List<User> UlistEmp(int limit, int offset);
	public int getUlistEmpSize();
	public float getBalance();
	public String getCurrencyCode();
	public List<Balance> getBalanceHistory(int limit, int offset);
	public int getBalanceHistorySize();
	List<DBWishItems> WlistArchiveEmp();
	

}