package com.wlt.wla.data;
import java.util.List;

import com.wlt.wla.data.*;

public interface WishListDao {

	public List<DBWishItems> WlistEmp();
	public List<DBCatItems> CatEmp();
	public List<DBPriorities> PriorEmp();

}