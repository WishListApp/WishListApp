package com.wlt.wla.export;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;
import com.wlt.wla.auth.model.DBWishItems;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.FontFormatting;


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
	      
	      CellStyle style = workbook.createCellStyle();  
          // Setting Background color  
          style.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT.getIndex());  
          style.setFillPattern(FillPatternType.BIG_SPOTS);
          Font font = workbook.createFont();
          font.setBold(true);
          style.setFont(font);
          
	      
	      Row header = sheet.createRow(rowCount++);
	      Cell cell;
	       cell = header.createCell(0);  
          cell.setCellValue("Item Name");  
          cell.setCellStyle(style);  

	       cell = header.createCell(1);  
          cell.setCellValue("Category");  
          cell.setCellStyle(style);  

	       cell = header.createCell(2);  
          cell.setCellValue("Price");  
          cell.setCellStyle(style);  

	       cell = header.createCell(3);  
          cell.setCellValue("Priority");  
          cell.setCellStyle(style);  

          
//	      header.createCell(1).setCellValue("Category");
//	      header.createCell(2).setCellValue("Price");
//	      header.createCell(3).setCellValue("Priority");
	      
  

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
