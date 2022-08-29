import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader
{
    public static HSSFWorkbook workbook;
    public static HSSFSheet worksheet;
    public static DataFormatter formatter= new DataFormatter();
    public static String file_location = System.getProperty("user.dir")+"\\resources\\inputdata\\CityZipcodes.xls";
    public static String file_location_LatLon =System.getProperty("user.dir")+"\\resources\\inputdata\\Lat&Lon.xls";
    static String SheetName= "Sheet1";
    @DataProvider(name="ReadExcelData")

    //Following method will read the Excel/input data file based on the file name and path passed to the method
    // and will return the data accordingly
    public static Object[][] ReadExcelData(String fileName) throws IOException
    {
        FileInputStream fileInputStream ;
        if(!fileName.equals("CityZipCodes"))
        {
            fileInputStream= new FileInputStream(file_location_LatLon);
        }
        else
        {
            fileInputStream = new FileInputStream(file_location);
        }
        workbook = new HSSFWorkbook(fileInputStream);
        worksheet=workbook.getSheet(SheetName);
        HSSFRow Row=worksheet.getRow(0);

        int RowNum = worksheet.getPhysicalNumberOfRows();
        int ColNum= Row.getLastCellNum();

        Object Data[][]= new Object[RowNum-1][ColNum];

        for(int i=0; i<RowNum-1; i++)
        {
            HSSFRow row= worksheet.getRow(i+1);
            for (int j=0; j<ColNum; j++)
            {
                if(row==null)
                    Data[i][j]= "";
                else
                {
                    HSSFCell cell= row.getCell(j);
                    if(cell==null)
                        Data[i][j]= "";
                    else
                    {
                        String value=formatter.formatCellValue(cell);
                        Data[i][j]=value;
                    }
                }
            }
        }

        return Data;
    }

}

