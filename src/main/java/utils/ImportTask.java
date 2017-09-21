/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.stage.FileChooser;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Pavel Papsh
 */
public class ImportTask extends Thread{
        
        
       // RingProgressIndicator rpi;
        static Connection conn;
	static PreparedStatement pst = null;
	static ResultSet rs = null;
    
        static int count;
        static int localI;
        static int progress;
        public static void importXSLToDB() throws FileNotFoundException, IOException, SQLException{
                CheckConnection();
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Excel file", "*.xls", "*.xlsx"));
        File selectedFile = fc.showOpenDialog(null);
        FileInputStream fileIn = new FileInputStream(selectedFile.getAbsoluteFile());
        DataFormatter formatter = new DataFormatter();
        XSSFWorkbook wb = new XSSFWorkbook(fileIn);
        XSSFSheet sheet =  wb.getSheetAt(0);
        count = sheet.getLastRowNum();
            System.out.println("Row is = " + count);
        Row row;
        
        String guery = "insert into patentInfo([cc], [patNumber], [kc], [derivativeFrom], [raiting], [office], [link], [originalTitle], [ruTrans], [enTrans], [deTrans], [spanishTrans], [chineseTrans], [inventor], [inventorsJob],  [applicantOriginal], [operatorApplicantsStatus], [operatorApplicantsCountries], [operatorApplicantEW], [ipc], [applDate], [pubDate], [nationalPhase], [patentFamily], [priorityCountry],[listOfCountries], [reassignmentRecepientOriginal], [operatorRRStatus], [OperatorRRCount], [applpat], [claims], [citings], [language], [abstOrFullText], [inventionType], [technology], [problematic], [component])" +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        pst = (PreparedStatement)conn.prepareStatement(guery);
        
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
   
        row = sheet.getRow(i);
        
            String cc = formatter.formatCellValue(row.getCell(0));
            String patNumber = formatter.formatCellValue(row.getCell(1));
            String kc = formatter.formatCellValue(row.getCell(2));
            String derivativeFrom = formatter.formatCellValue(row.getCell(3));
            String raiting = formatter.formatCellValue(row.getCell(4));
            String office = formatter.formatCellValue(row.getCell(5));
            String link = formatter.formatCellValue(row.getCell(6));
            String originalTitle = formatter.formatCellValue(row.getCell(7));
            String ruTrans = formatter.formatCellValue(row.getCell(8));
            String enTrans = formatter.formatCellValue(row.getCell(9));
            String deTrans = formatter.formatCellValue(row.getCell(10));
            String spanishTrans = formatter.formatCellValue(row.getCell(11));
            String chineseTrans = formatter.formatCellValue(row.getCell(12));
            String inventor = formatter.formatCellValue(row.getCell(13));
            String inventorsJob = formatter.formatCellValue(row.getCell(14));
            String applicantOriginal = formatter.formatCellValue(row.getCell(15));
            String operatorApplicantsStatus = formatter.formatCellValue(row.getCell(16));
            String operatorApplicantsCountries = formatter.formatCellValue(row.getCell(17));
            String operatorApplicantEW = formatter.formatCellValue(row.getCell(18));
            String ipc = formatter.formatCellValue(row.getCell(19));
            String applDate = formatter.formatCellValue(row.getCell(20));
            String pubDate = formatter.formatCellValue(row.getCell(21));
            String nationalPhase = formatter.formatCellValue(row.getCell(22));
            String patentFamily = formatter.formatCellValue(row.getCell(23));
            String priorityCountry = formatter.formatCellValue(row.getCell(24));
            String listOfCountries = formatter.formatCellValue(row.getCell(25));
            String reassignmentRecepientOriginal = formatter.formatCellValue(row.getCell(26)); 
            String operatorRRStatus = formatter.formatCellValue(row.getCell(27));
            String OperatorRRCount = formatter.formatCellValue(row.getCell(28));
            String applpat = formatter.formatCellValue(row.getCell(29));
            String claims = formatter.formatCellValue(row.getCell(30));
            String citings = formatter.formatCellValue(row.getCell(31));
            String language = formatter.formatCellValue(row.getCell(32));
            String abstOrFullText = formatter.formatCellValue(row.getCell(33));
            String inventionType = formatter.formatCellValue(row.getCell(34));
            String technology = formatter.formatCellValue(row.getCell(35));
            String problematic = formatter.formatCellValue(row.getCell(36));
            String component = formatter.formatCellValue(row.getCell(37));
            
        pst.setString(1, cc);
        pst.setString(2, patNumber);
        pst.setString(3, kc);
        pst.setString(4, derivativeFrom);
        pst.setString(5, raiting);
	pst.setString(6, office);
	pst.setString(7, link);
	pst.setString(8, originalTitle);
	pst.setString(9, ruTrans);
	pst.setString(10, enTrans);
	pst.setString(11, deTrans);
	pst.setString(12, spanishTrans);
	pst.setString(13, chineseTrans);
	pst.setString(14, inventor);
        pst.setString(15, inventorsJob);
	pst.setString(16, applicantOriginal);
	pst.setString(17, operatorApplicantsStatus);
	pst.setString(18, operatorApplicantsCountries);
	pst.setString(19, operatorApplicantEW);
	pst.setString(20, ipc);
	pst.setString(21, applDate);
	pst.setString(22, pubDate);
	pst.setString(23, nationalPhase);
	pst.setString(24, patentFamily);
	pst.setString(25, priorityCountry);
	pst.setString(26, listOfCountries);
	pst.setString(27, reassignmentRecepientOriginal);
	pst.setString(28, operatorRRStatus);
	pst.setString(29, OperatorRRCount);
	pst.setString(30, applpat);
	pst.setString(31, claims);
	pst.setString(32, citings);
	pst.setString(33, language);
	pst.setString(34, abstOrFullText);
	pst.setString(35, inventionType);
	pst.setString(36, technology);
	pst.setString(37, problematic);
	pst.setString(38, component);
	
        pst.execute();
        //System.out.println(i);
        localI = i;
        } 

DialogManager.showInfoDialog("Информационный диалог", "Информация из Excel файла перенесена в программу");

wb.close();
pst.close();
conn.close();
fileIn.close();
//rs.close();  
 System.out.println(localI);   
}
        private static void CheckConnection() {
		conn = SqlConnection.DbConnector();
		if (conn == null) {
			System.out.println("Соединение с таблицей  не удалось");
			System.exit(1);
		} else {
			System.out.println("Соединение с таблицей  удалось");
		}    
    }

}
