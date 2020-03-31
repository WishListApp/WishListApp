package com.wlt.wla.export;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;
import com.wlt.wla.auth.model.Balance;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.FontFormatting;


@Component("balView")
public class BalanceExcelReportView extends AbstractXlsView {
	  @Override
	  protected void buildExcelDocument(Map<String, Object> model, Workbook workbook,
	                                    HttpServletRequest request, HttpServletResponse response)
	          throws Exception {
	      List<Balance> itemList = (List<Balance>) model.get("BalanceModXLS");
	      String balance = (String) model.get("balance");
	      
	      Sheet sheet = workbook.createSheet("Exported Balance from Wish List");
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
          cell.setCellValue("TimeStamp");  
          cell.setCellStyle(style);  

	       cell = header.createCell(1);  
          cell.setCellValue("Note");  
          cell.setCellStyle(style);  

	       cell = header.createCell(2);  
          cell.setCellValue("Changes");  
          cell.setCellStyle(style);  

	      
  

	      for (Balance item : itemList) {
	          Row wishRow = sheet.createRow(rowCount++);
	          wishRow.createCell(0).setCellValue(item.getTimestamp());
	          wishRow.createCell(1).setCellValue(item.getNote());
	          wishRow.createCell(2).setCellValue(item.getBalanceChangeStr());
	          //wishRow.createCell(3).setCellValue(item.getBalanceChange());
	          //wishRow.createCell(2).setCellValue(Math.round(item.getBalanceChangeStr() * 100.0) / 100.0);
	          
	      }
	      Row wishRow = sheet.createRow(rowCount++);
	      wishRow.createCell(1).setCellValue("Total:");
	      wishRow.createCell(2).setCellValue(balance);
	      
	      for (int i = 0; i < 4; i++) {
	    	    sheet.autoSizeColumn(i);
	    	}
	      
	      response.setHeader("Content-Disposition", "attachment; filename=WishBalance.xls");
	      
	  }

}
