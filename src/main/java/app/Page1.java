package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.text.NumberFormat;
import java.text.DecimalFormat;
/**
 * Temporary HTML as an example page.
 * 
 * Based on the Project Workshop code examples.
 * This page currently:
 *  - Provides a link back to the index page
 *  - Displays the list of movies from the Movies Database using the JDBCConnection
 *
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class Page1 implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/page1.html";

    @Override
    public void handle(Context context) throws Exception {
        NumberFormat myFormat = NumberFormat.getInstance();
        NumberFormat formatter = new DecimalFormat("#0.00"); 
        myFormat.setGroupingUsed(true);
        JDBCConnection jdbc = new JDBCConnection();
        int sumCases = jdbc.getTotalCases();
        int sumDeaths = jdbc.getTotalDeaths();
        int sumRecov = jdbc.getTotalRecoveries();
        int sumCasesUS = jdbc.getTotalCasesByCountry("US");
        int sumCasesIndia = jdbc.getTotalCasesByCountry("India");
        int sumCasesBrazil = jdbc.getTotalCasesByCountry("Brazil");
        String html = "<!DOCTYPE html>";
        html = html + "<html>";
        html = html + "<head>";
        html = html + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" charset=\"UTF-8\">";
        html = html + "<link rel = \"stylesheet\" href = \"stylesheet.css\">";
        html = html + "<link href=\"hover-min.css\" rel=\"stylesheet\">";
        html = html + "<link href=\"https://fonts.googleapis.com/css2?family=IBM+Plex+Sans&display=swap\" rel=\"stylesheet\">";
        html = html + "<title>World Stats</title>";
        html = html + "</head>";
        html = html + "<body>";
        html = html + "<div class=\"topnav\">";
        html = html + "<a href = \"/\"><img src=\"icon.png\" style = \"width: 10vh;\"></a>";
        html = html + "<a  href=\"/\" class=\"hvr-underline-from-center\" >Home</a>";
        html = html + " <a href = \"page1.html\" class =\"hvr-underline-from-center active\">World Stats</a>";
        html = html + "  <a href=\"level2.html\" class=\"hvr-underline-from-center\">Map</a>";
        html = html + "  <a href=\"page3.html\" class=\"hvr-underline-from-center\">List of Countries</a>";

        html = html + " <a href=\"page5.html\" class=\"hvr-underline-from-center\">Advanced Reports</a>";
        html = html + "  <a href= 'page6.html' class= 'hvr-underline-from-center'>Similar Countries</a>";

        html = html + " </div> ";
        html = html + "<div class=\"flexbox-container\">";
        html = html + "<div class=\"total\">";
        html = html + " Total cases worldwide<br>";
        html = html + myFormat.format(sumCases);
        html = html + " </div>";
        html = html + "</div>";
        html = html + "<div class=\"scroll-left\">";
        html = html + " <p>Leading countries in COVID-19 cases: 1.USA " + myFormat.format(sumCasesUS) +"  &nbsp;&nbsp;  2.India " + myFormat.format(sumCasesIndia) + "&nbsp;&nbsp;  3.Brazil " + myFormat.format(sumCasesBrazil);
        html = html + "  </p>";
        html = html + "</div>";
        html = html + " <div class=\"flexbox-container2\">";
        html = html + "<div class = \"active\">";
        html = html + " Active Cases <br><br><br>";
        html = html + " <div class = \"number\">" +  myFormat.format(sumCases - (sumDeaths + sumRecov)) + "</div>";
        html = html + " <div class=\"percentage\">";
        html = html + "(" + formatter.format(((sumCases - (sumDeaths + sumRecov)) * 100.0) / sumCases) + "%)";
        html = html + " </div>";
        html = html + " </div>";
        html = html + "<div class = \"vl\"></div>";
        html = html + "<div class = \"death\">";
        html = html + "Deaths <br><br><br>";
        html = html + " <div class = \"number \">" + myFormat.format(sumDeaths)+"</div>";
        html = html + " <div class=\"percentage\">(" + formatter.format(sumDeaths * 100.0 / sumCases)+"%)</div>";
        html = html + " </div>";
        html = html + " <div class=\"vl\"></div>";
        html = html + " <div class = \"recoveries\">";
        html = html + " Recoveries <br><br><br>";
        html = html + " <div class = \"number\">" + myFormat.format(sumRecov) +"</div>";
        html = html + "<div class=\"percentage\">(" + formatter.format(sumRecov * 100.0 / sumCases)+"%)</div>";
        html = html + " </div>";
        html = html + "</div>";        
        html = html +"</body>";
        html = html +"</html>";
        

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}
