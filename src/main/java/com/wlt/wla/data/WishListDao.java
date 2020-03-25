package com.wlt.wla.data;
import java.util.List;

import com.wlt.wla.data.DBWishItems;
import com.wlt.wla.data.DBCatItems;

public interface WishListDao {

	public List<DBWishItems> WlistEmp();
	public List<DBCatItems> CatEmp();

}