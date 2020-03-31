package com.wlt.wla.export;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;
import com.wlt.wla.auth.model.DBWishItems;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;


@Component("excelView")
public class ExcelReportView extends AbstractXlsView {
	  @Override
	  protected void buildExcelDocument(Map<String, Object> model, Workbook workbook,
	                                    HttpServletRequest request, HttpServletResponse response)
	          throws Exception {
	      List<DBWishItems> itemList = (List<DBWishItems>) model.get("WishListModXLS"); //
	      Sheet sheet = workbook.createSheet("Exported Wish List");
	      sheet.setFitToPage(true);

	      int rowCount = 0;
	      

	      
	      Row header = sheet.createRow(rowCount++);
	      header.createCell(0).setCellValue("Item Name");
	      
	      header.createCell(1).setCellValue("Category");
	      header.createCell(2).setCellValue("Price");
	      header.createCell(3).setCellValue("Priority");

	      for (DBWishItems item : itemList) {
	          Row wishRow = sheet.createRow(rowCount++);
	          wishRow.createCell(0).setCellValue(item.getName());
	          
	          wishRow.createCell(1).setCellValue(item.getCat_name());
	          wishRow.createCell(2).setCellValue(Math.round(item.getPrice() * 100.0) / 100.0);
	          wishRow.createCell(3).setCellValue(item.getPriority_name());
	          //currencyRow.createCell(2).setCellValue(item.getAskPrice().doubleValue());
	          //currencyRow.createCell(3).setCellValue( item.getDateTime().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
	      }
	      for (int i = 0; i < 4; i++) {
	    	    sheet.autoSizeColumn(i);
	    	}
	      
	      response.setHeader("Content-Disposition", "attachment; filename=WishList.xls");
	      
	  }

}
