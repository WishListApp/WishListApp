package com.wlt.wla.data;
import java.util.List;

import com.wlt.wla.auth.model.Balance;
import com.wlt.wla.auth.model.DBWishItems;
import com.wlt.wla.auth.model.Settings;
import com.wlt.wla.auth.model.User;

public interface WishListDao {

	public List<DBWishItems> WlistEmp(int limit, int offset);
	public List<DBWishItems> WlistEmp(int limit, int offset, String category);
	List<DBWishItems> WlistEmp();
	public int WlistEmpSize();
	public int WlistEmpSize(String category);
	public List<DBCatItems> CatEmp();
	public List<DBPriorities> PriorEmp();
	public List<DBWishItems> WlistRestoreEmp(int limit, int offset);
	public int getWlistRestoreSize();
	public List<User> UlistEmp(int limit, int offset);
	public int getUlistEmpSize();
	public float getBalance();
	public String getCurrencyCode();
	public String getSettings();
	public List<Balance> getBalanceHistory(int limit, int offset);
	List<Balance> getBalanceHistory();
	public int getBalanceHistorySize();
	List<DBWishItems> WlistArchiveEmp(int limit, int offset);
	public int getWlistArchiveSize();
	public int getUserId();
	public int getFulfilledItemsCount();

	
	

}