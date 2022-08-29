import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

import static io.restassured.RestAssured.given;

public class GetEpochTimeConversionTest {
Properties properties = new Properties();
String currentDateTime="";
String epochTime="";
String expectedDate="";

String actualDateFromResponse="";


    @BeforeTest
    public void getServerDetails() throws IOException, ParseException {
        Reporter.log("--Test case has Started getting Server Details--");
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\resources\\properties\\global.properties");
        properties.load(fis);
        getCurrentDateTime();

    }

    @Test(enabled = true,priority = 0)
    public void getCurrentDateFromEpochServer() throws ParseException {
    Response result=  given().
                pathParam("unixtimestamp",epochTime).
                when().
                log().
                all().
                get("https://showcase.api.linx.twenty57.net/UnixTime/fromunixtimestamp?unixtimestamp={unixtimestamp}")
                .then().assertThat().statusCode(200).and()
                .log().all().extract().response();

        actualDateFromResponse = getJsonPath(result,"Datetime");
        System.out.println( actualDateFromResponse);

    }

    @Test(enabled = true,priority = 1)
    public void verifyDateTimeResponse()
    {
        Assert.assertEquals(expectedDate,actualDateFromResponse);
    }

    public static String getJsonPath(Response response, String key) {
        String complete = response.asString();
        JsonPath js = new JsonPath(complete);
        return js.get(key).toString();
    }

    public void getCurrentDateTime() throws ParseException {




        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        currentDateTime=dtf.format(now).toString();
        System.out.println(currentDateTime);
        getEpochTime();
        getExpectedDate();

    }


    public void getEpochTime() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = dateFormat.parse(currentDateTime);
        long epoch = date.getTime()/1000;
        epochTime =Long.toString(epoch);
        System.out.println("EpochTime--"+epoch);

    }

public void getExpectedDate()
{
    Date currentDate = new Date();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    System.out.println("Local Time: " + dateFormat.format(currentDate));
    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    System.out.println("Time in GMT: " + dateFormat.format(currentDate));
    expectedDate=dateFormat.format(currentDate);
    System.out.println("Expected Date--"+expectedDate);
}


}
