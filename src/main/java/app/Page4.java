package app;

import java.util.ArrayList;

import org.sqlite.JDBC;

import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * Temporary HTML as an example page.
 * 
 * Based on the Project Workshop code examples. This page currently: - Provides
 * a link back to the index page - Displays the list of movies from the Movies
 * Database using the JDBCConnection
 *
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class Page4 implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/page4.html";

    @Override
    public void handle(Context context) throws Exception {

        String country = context.queryParam("country");
        JDBCConnection jdbc = new JDBCConnection();
        // Create a simple HTML webpage in a String
        String html = "<!DOCTYPE html>";
        html = html + "<html lang='en'>";
        html = html + "<head>";
        html = html + " <meta charset='UTF-8'>";
        html = html + " <meta http-equiv='X-UA-Compatible' content='IE=edge'>";
        html = html + " <meta name='viewport' content='width=device-width, initial-scale=1.0'>";
            
        html = html + " <link rel = 'stylesheet' href='stylesheet.css'>";
        html = html + " <link rel='stylesheet' href = 'hover-min.css'>";
        html = html + "<title>" + country + "</title>";
        html = html + " <link rel='preconnect' href='https://fonts.gstatic.com'>";
        html = html + " <link href='https://fonts.googleapis.com/css2?family=IBM+Plex+Sans&display=swap' rel='stylesheet'> ";
        html = html + " </head>";
        html = html + " <body>";
        html = html + " <button onclick=\"topFunction()\" id=\"myBtn\" title=\"Go to top\"><i class='fas fa-angle-double-up'></i>&nbsp;Back to top</button>";
        html = html + "<div class=\"topnav\">";
        html = html + "<a href = \"/\"><img src=\"icon.png\" style = \"width: 10vh;\"></a>";
        html = html + "<a  href=\"/\" class=\"hvr-underline-from-center\" >Home</a>";
        html = html + " <a href = \"page1.html\" class =\"hvr-underline-from-center\">World Stats</a>";
        html = html + "  <a href=\"level2.html\" class=\"hvr-underline-from-center\">Map</a>";
        html = html + "  <a href=\"page3.html\" class=\"hvr-underline-from-center active\">List of Countries</a>";

        html = html + " <a href=\"page5.html\" class=\"hvr-underline-from-center\">Advanced Reports</a>";
        html = html + "  <a href= 'page6.html' class= 'hvr-underline-from-center'>Similar Countries</a>";

        html = html + " </div> ";
        html = html + " <div class=\"backBtn\">";
        html = html + " <span class=\"line tLine\"></span>";
        html = html + " <span class=\"line mLine\"></span>";
        html = html + " <a onclick='goBack()' style ='text-decoration: none'><span class=\"label\">Back</span></a>";

        html = html + " <span class=\"line bLine\"></span>";
        html = html + "</div>";
        html = html + " <h1>";
        html = html + country;
        html = html + "</h1>";
        
        if(isCountry(country)){
            html = html + "<h2><u>Today's numbers</u></h2>";
        html = html + "<div class = 'flexbox-container3'>";
        html = html + " <div class = 'small-title' style = 'color: red;'>";
        html = html + "Cases<br>";
            html = html + jdbc.getLatestCasesCountry(country);
            html = html + "</div>";
            html = html + " <div class = 'small-title' style = 'color: gray;'>";
            html = html + "Deaths<br>";
            
            html = html + jdbc.getLatestDeathsCountry(country);
                    
            html = html + "</div>";
            html = html + "<div class = 'small-title' style = 'color: green;'>";
            html = html + " Recoveries<br>";
            html = html + jdbc.getLatestRecoveriesCountry(country);
            html = html + " </div>";
            html = html + "</div>";
        }

        else{
            html = html + "<h2><u>Today's numbers</u></h2>";
        html = html + "<div class = 'flexbox-container3'>";
        html = html + " <div class = 'small-title' style = 'color: red;'>";
        html = html + "Cases<br>";
            html = html + jdbc.getLatestCasesState(country);
            html = html + "</div>";
            html = html + " <div class = 'small-title' style = 'color: gray;'>";
            html = html + "Deaths<br>";
            
            html = html + jdbc.getLatestDeathsState(country);
                    
            html = html + "</div>";
            html = html + "<div class = 'small-title' style = 'color: green;'>";
            html = html + " Recoveries<br>";
            html = html + jdbc.getLatestRecoveriesState(country);
            html = html + " </div>";
            html = html + "</div>";
        }

               html = html + "<div class = 'center'>";
        html = html + " <form action='/page4.html?country=" + country +"' method='post'>";
        html = html + " <table>";
        html = html + " <tr><td><label for='from'>FROM</label>";
        html = html + " <input type='date' id = 'from' name = 'from' min = '2020-01-22' max = '2021-04-22' value = '2020-01-22'></input>";
        html = html + " </td>";
        html = html + "<td><label for='to' style = 'display: block;'>TO</label>";
        html = html + "   <input type='date' id = 'to' name = 'to' min = '2020-01-22' max = '2021-04-22' value = '2021-04-22'></input></td>";
        html = html + " </table>";
        html = html + "<button type='submit' class='button1'>Submit</button>";
        
        html = html + "</form>";
        html = html + "</div>";
        String from = context.formParam("from");
        String to = context.formParam("to");
        
        html = html + " <h2> ";
        if(isCountry(country)){
                        if(from == null || to == null){
                            html = html + "  <u>Totals across the entire period</u>:";
                            html = html + "</h2>";
                            html = html + " <div class='flexbox-container3'>";
                            html = html + " <div class = 'small-title' style = 'color: red;'>";
                            html = html + "  Total cases<br>";
                                    
                            html = html + jdbc.getTotalCasesByCountry(country);
                                    
                            html = html + "</div>";
                            html = html + " <div class = 'small-title' style = 'color: gray;'>";
                            html = html + " Total deaths<br>";
                            
                            html = html + jdbc.getTotalDeathsByCountry(country);
                                    
                            html = html + "</div>";
                            html = html + " </div>";
                            html = html + " <div class='flexbox-container3'>";
                            html = html + "<div class = 'small-title' style = 'color: green;'>";
                            html = html + " Recoveries<br>";
                            html = html + jdbc.getTotalRecoveriesByCountry(country);
                            html = html + " </div>";
                            html = html + "  <div class = 'small-title'>";
                            html = html + " Fatality rate<br>";
                            html = html + String.format("%.2f", jdbc.getTotalDeathsByCountry(country) * 1.0/jdbc.getTotalCasesByCountry(country) *100) + "%";
                            html = html + "</div>";
                            html = html + " </div>";
                            html = html + " <div class='flexbox-container3'>";
                            html = html + " <div class = 'small-title'>";
                            html = html + "  Day with the highest number of infections:<br>";
                            String [] inf =jdbc.getDayWithHighestInfectionsNumber(country, 'c', "2020-01-22", "2021-04-22").split(" ");
                            html = html + "<b><u>" + inf[0] + "</u></b><br>";
                            html = html + "with a number of:<br>";
                            html = html + "<b><u>" + inf[1] + "</u></b>";
                            html = html + "</div>";
                            html = html + " <div class = 'small-title'>";
                            html = html + "  Day with the highest number of deaths:<br>";
                            String [] deaths = jdbc.getDayWithHighestDeathsNumber(country, 'c', "2020-01-22", "2021-04-22").split(" ");
                            html = html + "<b><u>" + deaths[0] + "</u></b><br>";
                            html = html + "with a number of:<br>";
                            html = html + "<b><u>" + deaths[1] + "</u></b>";
                            html = html + "</div>";
                            html = html + " </div>";
                    
                            if(hasStates(country)){
                                html = html + " <h2>STATES</h2>";
                                html = html + " <table width = '100%' class = 'table sortable'>";
                                html = html + " <tr><th>Name</th><th>Cases</th><th>Deaths</th><th>Recoveries</th></tr>";
                                html = html + displayStatesAndData(country,"2020-01-22","2021-04-22");
                                html = html + "</table>";
                            }
                            
                        
                        
                            
                                                        }else{
                                        html = html + "  <u>FROM</u>:" + from +"&nbsp; <u>TO</u>:" + to;
                                        html = html + "</h2>";
                                        html = html + " <div class='flexbox-container3'>";
                                        html = html + " <div class = 'small-title' style = 'color:red;'>";
                                        html = html + "  Total cases<br>";
                                                
                                        html = html + jdbc.getTotalCasesByCountryWithinADate(country, from, to);
                                                
                                        html = html + "</div>";
                                        html = html + " <div class = 'small-title' style = 'color:gray;'>";
                                        html = html + " Total deaths<br>";
                                        
                                        html = html + jdbc.getTotalDeathsByCountryWithinADate(country, from, to);
                                                
                                        html = html + "</div>";
                                        html = html + " </div>";
                                        html = html + " <div class='flexbox-container3'>";
                                        html = html + "<div class = 'small-title' style = 'color:green;'>";
                                        html = html + " Recoveries<br>";
                                        html = html + jdbc.getTotalRecoveriesByCountryWithinADate(country, from, to);
                                        html = html + " </div>";
                                        html = html + "  <div class = 'small-title'>";
                                        html = html + " Fatality rate<br>";
                                        html = html + String.format("%.2f", jdbc.getTotalDeathsByCountryWithinADate(country, from, to) * 1.0/jdbc.getTotalCasesByCountryWithinADate(country, from, to) *100) + "%";
                                        html = html + "</div>";
                                        html = html + " </div>";
                                        html = html + " <div class='flexbox-container3'>";
                                        html = html + " <div class = 'small-title'>";
                                        html = html + "  Day with the highest number of infections:<br>";
                                        String [] inf =jdbc.getDayWithHighestInfectionsNumber(country, 'c', from, to).split(" ");
                                        html = html + "<b><u>" + inf[0] + "</u></b><br>";
                                        html = html + "with a number of:<br>";
                                        html = html + "<b><u>" + inf[1] + "</u></b>";
                                        html = html + "</div>";
                                        html = html + " <div class = 'small-title'>";
                                        html = html + "  Day with the highest number of deaths:<br>";
                                        String [] deaths = jdbc.getDayWithHighestDeathsNumber(country, 'c', from, to).split(" ");
                                        html = html + "<b><u>" + deaths[0] + "</u></b><br>";
                                        html = html + "with a number of:<br>";
                                        html = html + "<b><u>" + deaths[1] + "</u></b>";
                                        html = html + "</div>";
                                        html = html + " </div>";


                                        if(hasStates(country)){
                                            html = html + " <h2>STATES</h2>";
                                            html = html + " <table width = '100%' class = 'table sortable'>";
                                            html = html + " <tr><th>Name</th><th>Cases</th><th>Deaths</th><th>Recoveries</th></tr>";
                                            html = html + displayStatesAndData(country,from,to);
                                            html = html + "</table>";
                                        }
                                    }
    }
    else{
        if(from == null || to == null){
            html = html + "  <u>Totals across the entire period</u>:";
            html = html + "</h2>";
            html = html + " <div class='flexbox-container3'>";
            html = html + " <div class = 'small-title' style = 'color:red'>";
            html = html + "  Total cases<br>";
                    
            html = html + jdbc.getTotalCasesByState(country);
                    
            html = html + "</div>";
            html = html + " <div class = 'small-title' style = 'color:gray;'>";
            html = html + " Total deaths<br>";
            
            html = html + jdbc.getTotalDeathsByState(country);
                    
            html = html + "</div>";
            html = html + " </div>";
            html = html + " <div class='flexbox-container3'>";
            html = html + "<div class = 'small-title' style = 'color: green;'>";
            html = html + " Recoveries<br>";
            html = html + jdbc.getTotalRecoveriesByState(country);
            html = html + " </div>";
            html = html + "  <div class = 'small-title'>";
            html = html + " Fatality rate<br>";
            html = html + String.format("%.2f", jdbc.getTotalDeathsByState(country) * 1.0/jdbc.getTotalCasesByState(country) *100) + "%";
            html = html + "</div>";
            html = html + " </div>";
            html = html + " <div class='flexbox-container3'>";
            html = html + " <div class = 'small-title'>";
            html = html + "  Day with the highest number of infections:<br>";
            String [] inf =jdbc.getDayWithHighestInfectionsNumber(country, 's', "2020-01-22", "2021-04-22").split(" ");
            html = html + "<b><u>" + inf[0] + "</u></b><br>";
            html = html + "with a number of:<br>";
            html = html + "<b><u>" + inf[1] + "</u></b>";
            html = html + "</div>";
            html = html + " <div class = 'small-title'>";
            html = html + "  Day with the highest number of deaths:<br>";
            String [] deaths = jdbc.getDayWithHighestDeathsNumber(country, 's', "2020-01-22", "2021-04-22").split(" ");
            html = html + "<b><u>" + deaths[0] + "</u></b><br>";
            html = html + "with a number of:<br>";
            html = html + "<b><u>" + deaths[1] + "</u></b>";
            html = html + "</div>";
            html = html + " </div>";
    
            
    }
   else{
    html = html + "  <u>FROM</u>:" + from +"&nbsp; <u>TO</u>:" + to;
    html = html + "</h2>";
    html = html + " <div class='flexbox-container3'>";
    html = html + " <div class = 'small-title' style = 'color:red;'>";
    html = html + "  Total cases<br>";
            
    html = html + jdbc.getTotalCasesByStateWithinADate(country, from, to);
            
    html = html + "</div>";
    html = html + " <div class = 'small-title' style = 'color:gray;''>";
    html = html + " Total deaths<br>";
    
    html = html + jdbc.getTotalDeathsByStateWithinADate(country, from, to);
            
    html = html + "</div>";
    html = html + " </div>";
    html = html + " <div class='flexbox-container3'>";
    html = html + "<div class = 'small-title' style = 'color:green;'>";
    html = html + " Recoveries<br>";
    html = html + jdbc.getTotalRecoveriesByStateWithinADate(country, from, to);
    html = html + " </div>";
    html = html + "  <div class = 'small-title'>";
    html = html + " Fatality rate<br>";
    html = html + String.format("%.2f", jdbc.getTotalDeathsByStateWithinADate(country, from, to) * 1.0/jdbc.getTotalCasesByStateWithinADate(country, from, to) *100) + "%";
    html = html + "</div>";
    html = html + " </div>";
    html = html + " <div class='flexbox-container3'>";
    html = html + " <div class = 'small-title'>";
    html = html + "  Day with the highest number of infections:<br>";
    String [] inf =jdbc.getDayWithHighestInfectionsNumber(country, 's', from, to).split(" ");
    html = html + "<b><u>" + inf[0] + "</u></b><br>";
    html = html + "with a number of:<br>";
    html = html + "<b><u>" + inf[1] + "</u></b>";
    html = html + "</div>";
    html = html + " <div class = 'small-title'>";
    html = html + "  Day with the highest number of deaths:<br>";
    String [] deaths = jdbc.getDayWithHighestDeathsNumber(country, 's', from, to).split(" ");
    html = html + "<b><u>" + deaths[0] + "</u></b><br>";
    html = html + "with a number of:<br>";
    html = html + "<b><u>" + deaths[1] + "</u></b>";
    html = html + "</div>";
    html = html + " </div>";
}
   }   html = html + " <script src=\"https://www.kryogenix.org/code/browser/sorttable/sorttable.js\"></script>";
        html = html + " <script src=\"script.js\"></script>";
        html = html + "<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>";

        html = html + "</body>";
        html = html + " </html>";
    
        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    

    }

    public boolean hasStates(String country) {
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> countries = jdbc.getCountryNameFromRegionTable();
        for (String c : countries) {
            if (c.equals(country))
                return true;
        }
        return false;
    }

    public String displayStatesAndData(String country, String from, String to) {
        String html = "";
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> states = jdbc.getStates(country);
        for (String state : states) {
            html = html + "<tr><td><a href = '/page4.html?country=" + state +"'>" +state + "</a></td><td>" + jdbc.getStateCasesWithinADate(state, from, to)
                    + "</td><td>" + jdbc.getStateDeathsWithinADate(state, from, to) + "</td><td>"
                    + jdbc.getStateRecoveriesWithinADate(state, from, to);
        }
        return html;
    }

    public boolean isCountry(String country) {
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> countries = jdbc.getAllCountries();
        for (String c : countries) {
            if (c.equals(country))
                return true;
        }
        return false;
    }
}
