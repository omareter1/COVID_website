package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.util.Collections;
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
public class Page5 implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/page5.html";

    @Override
    public void handle(Context context) throws Exception {
        JDBCConnection jdbc = new JDBCConnection();
        // Create a simple HTML webpage in a String
       String html = "<!DOCTYPE html>";
       html = html + "<html lang='en'>";
       html = html + "<head>";
       html = html + "<meta charset='UTF-8'>";
       html = html + "<meta http-equiv='X-UA-Compatible' content='IE=edge'>";
       html = html + " <meta name='viewport' content='width=device-width, initial-scale=1.0'>";
       html = html + " <link rel='stylesheet' href = 'hover-min.css'>";
       html = html + " <title>Advanced Reports</title>";
       html = html + " <link rel='stylesheet' href='CSS.css'>";
       html = html + " <link rel='preconnect' href='https://fonts.gstatic.com'>";
       html = html + " <link href='https://fonts.googleapis.com/css2?family=IBM+Plex+Sans&display=swap' rel='stylesheet'> ";
       html = html + " </head>";
       html = html + " <body>";
       html = html + "<button onclick='topFunction()' id= 'myBtn' title= 'Go to top'><i class='fas fa-angle-double-up'></i>&nbsp;Back to top</button>";
       html = html + " <div class= 'topnav '>";
       html = html + "<a href =  '/'><img src= 'icon.png ' style =  'width: 10vh; '></a>";
       html = html + "  <a  href= '/' class= 'hvr-underline-from-center ' >Home</a>";
       html = html + "<a href =  'page1.html ' class = 'hvr-underline-from-center'>World Stats</a>";
       html = html + " <a href= 'level2.html ' class= 'hvr-underline-from-center'>Map</a>";
       html = html + "  <a href= 'page3.html ' class= 'hvr-underline-from-center'>List of Countries</a>";
       
       html = html + " <a href= 'page5.html' class= 'hvr-underline-from-center active'>Advanced Reports</a>";
       html = html + "  <a href= 'page6.html' class= 'hvr-underline-from-center'>Similar Countries</a>";

       html = html + "  </div> ";
       html = html + " <h1>Advanced Reports</h1>";
       html = html + " <h2>In the last 7 days:</h2>";
       html = html + "<div style = 'margin-bottom: 50px'>";
       html = html + " <div class = 'flexbox-container'>";
       html = html + " <table class = 'table-trans'>";
       html = html + "  <th>Country with the greatest improvements in Infections rate and Deaths rate</th>";
       html = html + " <tr><td>" + jdbc.getMostImprovedCountry()+"</td></tr>";
       html = html + " </table>";
       html = html + " <table class = 'table-trans'>";
       html = html + "  <th>Country with the greatest increase in Infections and Deaths</th>";
       html = html + "  <tr><td>" + jdbc.getWorstCountry() + "</td></tr>";
       
       html = html + " </table>";
       html = html + " </div>";
       html = html + " </div>";
       html = html + " <form autocomplete='off' action='/page5.html' method='POST'>";
       html = html + "   <div class = 'center'>";
                 
       html = html + "  <div class='autocomplete' style='width:300px;'>";
       html = html + "   <input id='myInput' type='text' name='myCountry' placeholder='Search for a country...'>";
       html = html + "  </div>";
       html = html + "  <label for = 'days'>in the last</label>";
       html = html + "  <input type='number' min = '0' max = '457' name = 'days' placeholder='Leave empty to get data over the entire period'>";
       html = html + "   days";
       html = html + "<br><br>Show countries wihin";
       html = html + "<select name=\"km\" id = 'km' style=\"background-color: #000; border-radius: 30px; color: #fff; font-size: 18px;\">";
       html = html + " <option>2500</option>";
       html = html + " <option>5000</option>";
       html = html + " <option>7500</option>";
       html = html + " <option>10000</option>";
       html = html + "</select>KMs";
       html = html + "   <button type='submit' class='button1'>Submit</button>";
       html = html + "   </div>";
       html = html + " </form>";
       String country = "";
       String days = "";
        String km = "";
        country = country + context.formParam("myCountry");
        days = days + context.formParam("days");
        km = km + context.formParam("km");
        if((country == null || country.equals("")) || !isInListOfCountries(country) || km == null){
            html = html + "<h2>No results to show</h2>";
            html = html + "  <script src='autocomplete.js'></script>";
            html = html + "  <script src='backtotop.js'></script>";
            html = html + "  <script src='https://www.kryogenix.org/code/browser/sorttable/sorttable.js'></script>";
            html = html + "<script> var inputs = document.getElementsByTagName('input');";
        html = html  + "for(i=0; i<inputs.length; i++){";
            html = html + "inputs[i].setAttribute('size',inputs[i].getAttribute('placeholder').length);";
        html = html + "}</script>";
            html = html + "</body>";
            html = html + " </html>";  
        
        }else if ((days == null || days.equals(""))){
       html = html + " <h2 style = 'text-align: center;'>" + country + " over the entire period</h2>";
       html = html + "    <br>";
       html = html + "  <div class = 'flexbox-container2'>";
       html = html + "   <div class = 'flex-item'>";
       html = html + "     Infections/Death ratio";
       html = html + "    <div class = 'number'>";
       html = html + String.format("%.2f", jdbc.getTotalCasesByCountry(country) * 1.0/jdbc.getTotalDeathsByCountry(country));
       html = html + "    </div>";
       html = html + "    </div>";
       html = html + "   <div class='vl'></div>";
       html = html + " <div class = 'flex-item'>";
       html = html + "   Infection/population percentage";
       html = html + "   <div class = 'number'>";
       html = html + String.format("%.6f", jdbc.getTotalCasesByCountry(country) * 1.0/ jdbc.getPopulationByCountry(country) * 100);
       html = html + "%";
       html = html + "     </div>";
       html = html + "   </div>";
       html = html + "  <div class='vl'></div>";
       html = html + " <div class = 'flex-item'>";
       html = html + "   Death/population percentage";
       html = html + "   <div class='number'>";
       html = html + String.format("%.6f", jdbc.getTotalDeathsByCountry(country) * 1.0/ jdbc.getPopulationByCountry(country) * 100);
       html = html + "%";
       html = html + "     </div>";
       html = html + " </div>";
       
       
       
       
       
       
       html = html + " </div>";
       html = html + " <div class = 'flexbox-container'>";
       html = html + "  <div class = 'table-trans'>";
                    
       html = html + "  <table class = 'sortable'>";
       html = html + "    <caption style='margin-bottom: 20px;'>Top 3 countries with similar climates by transmission rate</caption>";
                       
       html = html + " <tr>";
       html = html + "       <th>Country</th>";
       html = html + "    <th>Tranmission Rate</th>";
       html = html + "          <th>Death Rate</th>";
       html = html + "    </tr>";
       html = html + get3similarCountries(country);
       
       html = html + "       </table>";
       
       html = html + "    </div>";
       html = html + "  <div class = 'vl'></div>";
       html = html + "  <div class = 'table-trans'>";
       html = html + "   <table class = 'sortable' style='margin-left: auto; margin-right: auto;'>";
       html = html + "    <caption style = 'margin-bottom: 20px;'>Top 3 countries within ";
       html = html + km;
       html = html + " KMs by infection rate</caption>";
       html = html + "   <tr>";
       html = html + "       <th>Country</th>";
       html = html + "       <th>Infection Rate</th>";
       html = html + "        <th>Death Rate</th>";
       html = html + "    </tr>";

      
       html = html + get3CountriesWithinXKms(country, km);
       
       html = html + "   </table>";
       html = html + " </div>";
       html = html + " </div>";
       
            
       
       html = html + "  <script src='autocomplete.js'></script>";
       html = html + "  <script src='backtotop.js'></script>";
       html = html + "  <script src='https://www.kryogenix.org/code/browser/sorttable/sorttable.js'></script>";
       html = html + "<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>";
       html = html + "<script> var inputs = document.getElementsByTagName('input');";
       html = html  + "for(i=0; i<inputs.length; i++){";
           html = html + "inputs[i].setAttribute('size',inputs[i].getAttribute('placeholder').length);";
       html = html + "}</script>";
       html = html + "</body>";
       html = html + " </html>";
        }
    else{


        html = html + " <h2 style = 'text-align: center;'>" + country + " in the last " + days + " days</h2>";
        html = html + "    <br>";
        html = html + "  <div class = 'flexbox-container2'>";
        html = html + "   <div class = 'flex-item'>";
        html = html + "     Infections/Death ratio";
        html = html + "    <div class = 'number'>";
        if(jdbc.getTotalDeathsByCountryInTheLastXDays(country, days) != 0)
        html = html + String.format("%.2f", jdbc.getTotalCasesByCountryInTheLastXDays(country, days) * 1.0 / jdbc.getTotalDeathsByCountryInTheLastXDays(country, days));
        else html = html + "N/A";
        html = html + "    </div>";
        html = html + "    </div>";
        html = html + "   <div class='vl'></div>";
        html = html + " <div class = 'flex-item'>";
        html = html + "   Infection/population percentage";
        html = html + "   <div class = 'number'>";
        html = html + String.format("%.6f", jdbc.getTotalCasesByCountryInTheLastXDays(country,days) * 1.0/ jdbc.getPopulationByCountry(country) * 100);
        html = html + "%";
        html = html + "     </div>";
        html = html + "   </div>";
        html = html + "  <div class='vl'></div>";
        html = html + " <div class = 'flex-item'>";
        html = html + "   Death/population percentage";
        html = html + "   <div class='number'>";
        html = html + String.format("%.6f", jdbc.getTotalDeathsByCountryInTheLastXDays(country,days) * 1.0/ jdbc.getPopulationByCountry(country) * 100);
        html = html + "%";
        html = html + "     </div>";
        html = html + " </div>";
        
        
        
        
        
        
        html = html + " </div>";
        html = html + " <div class = 'flexbox-container'>";
        html = html + "  <div class = 'table-trans'>";
                     
        html = html + "  <table class = 'sortable'>";
        html = html + "    <caption style='margin-bottom: 20px;'>Top 3 countries with similar climates by transmission rate</caption>";
                        
        html = html + " <tr>";
        html = html + "       <th>Country</th>";
        html = html + "    <th>Transmission Rate</th>";
        html = html + "          <th>Death Rate</th>";
        html = html + "    </tr>";
       html = html + get3similarCountriesInTheLastXDays(country,days);
        html = html + "       </table>";
        html = html + "    </div>";
        html = html + "  <div class = 'vl'></div>";
        html = html + "  <div class = 'table-trans'>";
        html = html + "   <table class = 'sortable' style='margin-left: auto; margin-right: auto;'>";
        html = html + "    <caption style = 'margin-bottom: 20px;'>Top 3 countries within ";
        html = html + km;
        html = html + "     KMs by infection rate</caption>";
        html = html + "   <tr>";
        html = html + "       <th>Country</th>";
        html = html + "       <th>Infection Rate</th>";
        html = html + "        <th>Death Rate</th>";
        html = html + "    </tr>";
        html = html + get3CountriesWithinXKmsInTheLastXdays(country,km,days);
        html = html + "   </table>";
        html = html + " </div>";
        html = html + " </div>";
        
             
        
        html = html + "  <script src='autocomplete.js'></script>";
        html = html + "  <script src='backtotop.js'></script>";
        html = html + "  <script src='https://www.kryogenix.org/code/browser/sorttable/sorttable.js'></script>";
        html = html + "<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>";
        html = html + "<script> var inputs = document.getElementsByTagName('input');";
        html = html  + "for(i=0; i<inputs.length; i++){";
            html = html + "inputs[i].setAttribute('size',inputs[i].getAttribute('placeholder').length);";
        html = html + "}</script>";
        html = html + "</body>";
        html = html + " </html>";






    }
        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }


public Boolean isInListOfCountries(String c){
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> names= jdbc.getAllCountries();

    for(String name: names){
        if(name.equals(c)){
            return true;
        }
    }
    return false;

}

public String get3similarCountries(String country){
    String html = "";
    JDBCConnection jdbc = new JDBCConnection();
    ArrayList<String> names= jdbc.getSimilarCountries(country);
    int i = 0;
    for(String name:names){
        html = html + "<tr><td>" + name + "</td><td>" +  String.format("%.2f",jdbc.getTotalCasesByCountry(name) *1.0 / jdbc.getPopulationByCountry(name) * 100) +"%</td><td>" + String.format("%.2f", jdbc.getTotalDeathsByCountry(name)*1.0/jdbc.getPopulationByCountry(name) * 100) + "%</td></tr>";
        i++;
        if(i == 3)
            break;
    }
    return html;
}

public String get3CountriesWithinXKms(String country, String kms){
    String html = "";
    JDBCConnection jdbc = new JDBCConnection();
    ArrayList<String> names = jdbc.getAllCountriesSortedByInfectionRate();
    ArrayList<Double> distances = jdbc.getDistanceBetween2Countries(country);
    ArrayList<Double> inf_rate = jdbc.getInfectionRateOfAllCountries();
    ArrayList<Double> death_rate = jdbc.getDeathRateOfAllCountries();
    int i = 0;
    int j = 0;
    

        for(i = 0; i < inf_rate.size(); i++){
            if(country.equals(names.get(i)))
                i++;
            
            if(distances.get(i) < Integer.parseInt(kms)){
                
                html = html + "<tr><td>" + names.get(i) + "</td><td>"+ String.format("%.2f",inf_rate.get(i))  + "%</td><td>" + String.format("%.2f", death_rate.get(i)) + "%</td></tr>";
                j++;
            }
            if(j == 3){
            break;    
            }
        }
        if(j == 0)
        html = html + "<tr><td colspan = '3'>No countries within " + kms + " kms</td></tr>";
return html;
    
}
public String get3similarCountriesInTheLastXDays(String country,String days){
    String html = "";
    JDBCConnection jdbc = new JDBCConnection();
    ArrayList<String> names= jdbc.getSimilarCountriesXdays(country,days);
    int i = 0;
    for(String name:names){
        html = html + "<tr><td>" + name + "</td><td>" + String.format("%.2f",jdbc.getTotalCasesByCountryInTheLastXDays(name, days) * 1.0/jdbc.getPopulationByCountry(name) * 100) + "%</td><td>" + String.format("%.2f", jdbc.getTotalDeathsByCountryInTheLastXDays(name,days)*1.0/jdbc.getPopulationByCountry(name) * 100) + "%</td></tr>";
        i++;
        if(i == 3)
            break;
    }
    return html;
}

public String get3CountriesWithinXKmsInTheLastXdays(String country, String kms, String days){
    String html = "";
    JDBCConnection jdbc = new JDBCConnection();
    ArrayList<String> names = jdbc.getAllCountriesSortedByInfectionRateInTheLastXDays(days);
    ArrayList<Double> distances = jdbc.getDistanceBetween2CountriesSortedByInfRateInTheLastXDays(country,days);
    ArrayList<Double> inf_rate = jdbc.getInfectionRateOfAllCountriesInTheLastXDays(days);
    ArrayList<Double> death_rate = jdbc.getDeathRateOfAllCountriesInTheLastXDays(days);
    int i = 0;
    int j = 0;
    

        for(i = 0; i < inf_rate.size(); i++){
            if(country.equals(names.get(i)))
                i++;
            
            if(distances.get(i) < Integer.parseInt(kms)){
                
                html = html + "<tr><td>" + names.get(i) + "</td><td>"+ String.format("%.2f",inf_rate.get(i))  + "%</td><td>" + String.format("%.2f", death_rate.get(i)) + "%</td></tr>";
                j++;
            }
           
            if(j == 3){
            break;    
            }
        }
        if(j == 0)
        html = html + "<tr><td colspan = '3'>No countries within " + kms + " kms</td></tr>";
return html;
    
}

}

