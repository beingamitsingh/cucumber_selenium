package framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

public class Support {

    protected static WebDriver setBrowser(String browser)  {
        WebDriver webDr = null;

        switch (browser)  {
            case "Chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("disable-popup-blocking");
                chromeOptions.addArguments("disable-infobars");
                chromeOptions. addArguments("--disable-extensions");
                chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                WebDriverManager.chromedriver().setup();
                webDr = new ChromeDriver(chromeOptions);
                break;
            case "Edge":
                WebDriverManager.edgedriver().setup();
                webDr = new EdgeDriver();
                break;
            case "Firefox":
                WebDriverManager.firefoxdriver().setup();
                webDr = new FirefoxDriver();
                break;
            default:
                System.out.println("Invalid browser specified in Config file: " + browser);
                break;
        }
        return webDr;
    }

    protected static HashMap<String, ArrayList<String>> getAllObjects()   {

        HashMap<String, ArrayList<String>> objData = new HashMap<>();
        ArrayList<String> objInnerData;
        try {
            FileInputStream fis=new FileInputStream(new File(Config.getProperty("objectRepository")));
            XSSFWorkbook wb= new XSSFWorkbook(fis);
            XSSFSheet sheet=wb.getSheetAt(0);
            for(int i=1; i< sheet.getPhysicalNumberOfRows(); i++)  {
                Row row = sheet.getRow(i);
                objInnerData = new ArrayList<>();
                objInnerData.add(0, row.getCell(1).getStringCellValue().trim());
                objInnerData.add(1, row.getCell(2).getStringCellValue().trim());
                objData.put(row.getCell(0).getStringCellValue().trim(), objInnerData);
                objInnerData = null;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return objData;
    }

    private String convertToMD5(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte byteData[] = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++)
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));

        return sb.toString();
    }
}
