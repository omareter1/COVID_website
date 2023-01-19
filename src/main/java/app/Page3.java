package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

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
public class Page3 implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/page3.html";

    @Override
    public void handle(Context context) throws Exception {
        JDBCConnection jdbc = new JDBCConnection();
        // Create a simple HTML webpage in a String
        String html = "<!DOCTYPE html>";
        html = html + "<html lang='en'>";
        html = html + "<head>";
        html = html + "<meta charset='UTF-8'>";
        html = html + "<meta http-equiv='X-UA-Compatible' content='IE=edge'>";
        html = html + "<link rel = 'stylesheet' href = 'mdb.min.css'>";
        html = html + "<link rel = 'stylesheet' href = 'style2.css'>";
        html = html + " <link rel = 'stylesheet' href='stylesheet.css'>";
        html = html + " <link rel='stylesheet' href = 'hover-min.css'>";
        html = html + "<title>List of Countries</title>";
        html = html + " <link rel='preconnect' href='https://fonts.gstatic.com'>";
        html = html + " <link href='https://fonts.googleapis.com/css2?family=IBM+Plex+Sans&display=swap' rel='stylesheet'> ";
        html = html + "<style> td{width: 12%;} th{text-align:center;}</style>";
        html = html + " </head>";
        html = html + "<body>";
        html = html + " <button onclick=\"topFunction()\" id=\"myBtn\" title=\"Go to top\"><i class='fas fa-angle-double-up'></i>&nbsp;Back to top</button>";

        html = html + "<div class=\"topnav\">";
        html = html + "<a href = \"/\"><img src=\"icon.png\" style = \"width: 10vh;\"></a>";
        html = html + "<a  href=\"/\" class=\"hvr-underline-from-center\" >Home</a>";
        html = html + " <a href = \"page1.html\" class =\"hvr-underline-from-center\">World Stats</a>";
        html = html + "  <a href=\"level2.html\" class=\"hvr-underline-from-center\">Map</a>";
        html = html + "  <a href=\"page3.html\" class=\"hvr-underline-from-center active\">List of Countries</a>";

        html = html + " <a href=\"page5.html\" class=\"hvr-underline-from-center\">Advanced Reports</a>";
        html = html + "  <a href= 'page6.html' class= 'hvr-underline-from-center'>Similar Countries</a>";

        html = html + " </div>";
        html = html + " <div id='title'>STATS BY COUNTRY/STATE</div>";
        html = html + "<div class = 'center'>";
        
        html = html + "<div class='title1'>Choose what you want to display (choose one or both):</div>";
        
        
        html = html + "<form action='/page3.html' method='post'>";
        
        html = html + " <input type='checkbox' id='States' name='States' value = 'States' style = 'display: inline-block;'>";
        html = html + " <label for='States' style = 'margin-right: 10px;'>States</label>";
        
        
        html = html + "<input type='checkbox' id='Countries' name='Countries' value = 'Countries' style = 'display: inline-block;'>";
        html = html + " <label for='Countries'>Countries</label>";
        
        
        html = html + "<button type='submit' class='button1'>Submit</button>";
        html = html + "</form>";
        
        html = html + "</div>";
        String hasCountries = context.formParam("Countries");
        String hasStates = context.formParam("States");
        html = html + "<input type=\"text\" id=\"myInput\" onkeyup=\"myFunction()\" placeholder=\"Search for names..\" style = 'margin-bottom:0px;'>";

        html = html + "<i> click on a header to sort ! :)</i>";

        html = html + " <table class='table sortable' id = 'myTable'>";
        html = html + "  <tr>";
        html = html + "  <th>Country/State</th>";
        html = html + " <th>Total Cases</th>";
        html = html + "  <th>Total Deaths</th>";
        html = html + " <th>Death Rate</th>";
        html = html + " <th>Infection Rate</th>";
        html = html + " <th>Death/Infection Ratio</th>";
        html = html + " <th>Infections Per Million</th>";
        html = html + " <th>Deaths Per Million</th>";


        html = html + "  </th>";
        html = html + "  </tr>";
        if((hasCountries == null && hasStates == null) || (hasStates == null && hasCountries.equals("Countries")))
            html = html + CountriesAndData();
        else if( hasCountries == null &&hasStates.equals("States") )
            html = html + StatesAndData();
        else {
            html = html + CountriesAndData();
            html = html + StatesAndData();
        }

        
        html = html + " </table>";
                      
       html = html + " <script src=\"https://www.kryogenix.org/code/browser/sorttable/sorttable.js\"></script>";
       html = html + " <script src=\"script.js\"></script>";
        html = html + "<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>";
        html = html + "</body>";
        html = html + " </html>";


        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }
    
    public String CountriesAndData() {
        String html = "";
        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> countries = jdbc.getAllCountries();
        // Add HTML for the movies list
        
        for (String country : countries) {
            html = html + "<tr><td><i class='flag flag-" + country.toLowerCase().replace(' ', '-');
            html = html + "'></i><a href = 'page4.html?country=" + country+"'>" + country + "</a></td><td>" +jdbc.getTotalCasesByCountry(country) + "</td><td>" +jdbc.getTotalDeathsByCountry(country) + "</td><td>"
            + String.format("%.2f", jdbc.getTotalDeathsByCountry(country) * 1.0/jdbc.getPopulationByCountry(country) * 100)      + "%</td><td>"
            + String.format("%.2f", jdbc.getTotalCasesByCountry(country) * 1.0/jdbc.getPopulationByCountry(country) * 100)        + "%</td><td>";
            if(jdbc.getTotalCasesByCountry(country) != 0)
                    html = html + String.format("%.2f", jdbc.getTotalDeathsByCountry(country) * 1.0/jdbc.getTotalCasesByCountry(country) * 100)+ "%</td>";
            else html = html + "0</td>";
            html = html + "<td>" + String.format("%.2f", (jdbc.getTotalCasesByCountry(country) *1.0 * 1000000) / jdbc.getPopulationByCountry(country)) + "</td><td>" 
            + String.format("%.2f", (jdbc.getTotalDeathsByCountry(country) * 1.0 * 1000000)/ jdbc.getPopulationByCountry(country)) + "</td></tr>";
        }

        return html;
    }
    public String StatesAndData(){
        String html = "";
        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> states = jdbc.getAllStates();
        // Add HTML for the movies list
        
        for (String state : states) {
          html = html + "<tr><td><i class='flag flag-" + jdbc.getCountryRelatedToState(state).toLowerCase() + "'></i> (" + jdbc.getCountryRelatedToState(state) + ") <a href = 'page4.html?country=" + state + "'>" + state + "</a></td><td>" +
          jdbc.getTotalCasesByState(state) + "</td><td>" + jdbc.getTotalDeathsByState(state) + "</td><td>" + String.format("%.2f", jdbc.getTotalDeathsByState(state) * 1.0/jdbc.getPopulationByState(state) * 100) + "%</td><td>"+
          String.format("%.2f", jdbc.getTotalCasesByState(state) * 1.0/jdbc.getPopulationByState(state) * 100) + "%</td><td>"+
          String.format("%.2f", jdbc.getTotalDeathsByState(state) * 1.0/jdbc.getTotalCasesByState(state) * 100) + "%</td></tr>";

        }

        return html;
    }


}
