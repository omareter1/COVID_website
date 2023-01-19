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
public class Page6 implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/page6.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        JDBCConnection jdbc = new JDBCConnection();
       
        String html = "";
        // Add some Header information
        html = html + "<!DOCTYPE html>";
        html = html + "<html lang='en'>";
        html = html + "<head>";
        html = html + "<meta charset='UTF-8'>";
        html = html + " <meta http-equiv='X-UA-Compatible' content='IE=edge'>";
        html = html + " <meta name='viewport' content='width=device-width, initial-scale=1.0'>";
        html = html + "  <link rel='stylesheet' href = 'hover-min.css'>";
        html = html + "  <title>Similar Countries</title>";
        html = html + " <link rel='stylesheet' href='CSS.css'>";
        html = html + " <link rel='preconnect' href='https://fonts.gstatic.com'>";
        html = html + " <link href='https://fonts.googleapis.com/css2?family=IBM+Plex+Sans&display=swap' rel='stylesheet'> ";
        html = html + " </head>";
        html = html + " <body>";
        html = html + "  <button onclick='topFunction()' id= 'myBtn' title= 'Go to top'><i class='fas fa-angle-double-up'></i>&nbsp;Back to top</button>";
        html = html + " <div class= 'topnav '>";
        html = html + " <a href =  '/'><img src= 'icon.png ' style =  'width: 10vh; '></a>";
        html = html + "  <a  href= '/' class= 'hvr-underline-from-center ' >Home</a>";
        html = html + " <a href =  'page1.html ' class = 'hvr-underline-from-center'>World Stats</a>";
        html = html + "  <a href= 'level2.html ' class= 'hvr-underline-from-center'>Map</a>";
        html = html + " <a href= 'page3.html ' class= 'hvr-underline-from-center'>Countries</a>";
        
        html = html + "  <a href= 'page5.html' class= 'hvr-underline-from-center'>Advanced Reports</a>";
        html = html + "  <a href= 'page6.html' class= 'hvr-underline-from-center active'>Similar Countries</a>";
        
        html = html + " </div> ";
        html = html + " <h1>Similar Countries</h1>";
            
            
        html = html + " <form autocomplete='off' action='/page6.html' method='POST'>";
        html = html + "  <div class = 'center'>";
        html = html + "     <h3 style = 'display: inline'>Find regions similar to:</h3>";
        html = html + "   <div class='autocomplete' style='width:300px;'>";
        html = html + "   <input id='myInput' type='text' name='myCountry' placeholder='Search for a country or a US state...'>";
        html = html + "  </div>";
        html = html + "  <input type='radio' name = 'state' id = 'state' value='state'>";
        html = html + " <label for='state'>Show US states only</label>";
        html = html + "  <input type='radio' name = 'state' id = 'state' value='country'>";
        html = html + " <label for='state'>Show countries only</label>";
  

        html = html + "   <button type='submit' class='button1'>Submit</button>";
        html = html + "  </div>";
        html = html + " </form>";
        String country = context.formParam("myCountry");
        String state = context.formParam("state");
        if(country == null && state == null){
            html = html + "<h3 style = 'text-align:center;'>NO RESULTS TO SHOW<h3>";
        }
        else if (isInListOfCountries(country)  && (state == null || state.equals("country"))){
        html = html + "  <h2 style='text-align: center;'>" + country + "</h2>";
        html = html + " <br>";
        html = html + " <div class = 'flexbox-container' style = 'padding: 50px;'>";
        html = html + "  <div class = 'flex-item'>";
        html = html + "     Infections per million";
        html = html + "    <div class = 'number'>";
        html = html + String.format("%.2f", jdbc.getTotalCasesByCountry(country) * 1.0 * 1000000 / jdbc.getPopulationByCountry(country));
        html = html + "     </div>";
        html = html + "  </div>";
        html = html + "  <div class = 'vl'></div>";
        html = html + "  <div class = 'flex-item'>";
        html = html + "    Infections/Deaths ratio";
        html = html + "    <div class = 'number'>";
        html = html + String.format("%.2f", jdbc.getTotalCasesByCountry(country) * 1.0 / jdbc.getTotalDeathsByCountry(country));
        html = html + "      </div>";
        html = html + "   </div>";
        html = html + "    <div class = 'vl'></div>";
        
        html = html + "  <div class = 'flex-item'>";
        html = html + " Maximum daily infections";
        html = html + " <div class = 'number'>";
        html = html + jdbc.getMaxDailyInf(country);
        html = html + "      </div>";
        html = html + " </div>";
        html = html + "  <div class = 'vl'></div>";
        html = html + " <div class = 'flex-item'>";
        html = html + "      Maximm daily deaths";
        html = html + "      <div class = 'number'>";
        html = html + jdbc.getMaxDailyDeaths(country);
        html = html + "       </div>";
        html = html + "   </div>";
        html = html + "  </div>";
        html = html + " <br>";
        html = html + "  <div class = 'center' style = 'margin-bottom: 50px ;'>";
                
        html = html + "  <div class = 'flexbox-container'>";
        html = html + "   <table class = 'table-trans'>";
        html = html + "    <th colspan='2'>Countries with the similar number of total infections per million</th>";
        html = html + get3CountriesWithSimilarInfRate(country);
        html = html + "  </table>";
        html = html + "    <table class = 'table-trans'>";
        html = html + "    <th colspan='2'>Countries with similar ratio of infections to deaths</th>";
        html = html +get3CountriesWithSimilarInftoDeathsRatio(country);
        html = html + "   </table>";
        html = html + "   <table class = 'table-trans'>";
        html = html + "    <th colspan='2'>Countries with similar max daily infections</th>";
        html = html +  get3CountriesWithSimilarMaxDailyInf(country);
        html = html + "  </table>";
       
        html = html + " <table class = 'table-trans'>";
        html = html + "     <th colspan='2'>Countries with similar max daily deaths</th>";
        html = html  + get3CountriesWithSimilarMaxDailyDeaths(country);
        html = html + "  </table>";
        html = html + "   </div>";
        html = html + "  </div>";
        }
        else if( !isInListOfCountries(country) && state.equals("state")){
            html = html + "  <h2 style='text-align: center;'>" + country + "</h2>";
            html = html + " <br>";
            html = html + " <div class = 'flexbox-container' style = 'padding: 50px;'>";
            html = html + "  <div class = 'flex-item'>";
            html = html + "     Infections per million";
            html = html + "    <div class = 'number'>";
            html = html + String.format("%.2f", jdbc.getTotalCasesByState(country) * 1.0 * 1000000 / jdbc.getPopulationByState(country));
            html = html + "     </div>";
            html = html + "  </div>";
            html = html + "  <div class = 'vl'></div>";
            html = html + "  <div class = 'flex-item'>";
            html = html + "    Infections/Deaths ratio";
            html = html + "    <div class = 'number'>";
            html = html + String.format("%.2f", jdbc.getTotalCasesByState(country) * 1.0 / jdbc.getTotalDeathsByState(country));
            html = html + "      </div>";
            html = html + "   </div>";
            html = html + "    <div class = 'vl'></div>";
            
            html = html + "  <div class = 'flex-item'>";
            html = html + " Maximum daily infections";
            html = html + " <div class = 'number'>";
            html = html + jdbc.getMaxDailyInfState(country);
            html = html + "      </div>";
            html = html + " </div>";
            html = html + "  <div class = 'vl'></div>";
            html = html + " <div class = 'flex-item'>";
            html = html + "      Maximm daily deaths";
            html = html + "      <div class = 'number'>";
            html = html + jdbc.getMaxDailyDeathsState(country);
            html = html + "       </div>";
            html = html + "   </div>";
            html = html + "  </div>";
            html = html + " <br>";
            html = html + "  <div class = 'center' style = 'margin-bottom: 50px ;'>";
                    
            html = html + "  <div class = 'flexbox-container'>";
            html = html + "   <table class = 'table-trans'>";
            html = html + "    <th colspan='2'>Countries with the similar number of total infections per million</th>";
            html = html + get3StatesWithSimilarInfRate(country);
            html = html + "  </table>";
            html = html + "    <table class = 'table-trans'>";
            html = html + "    <th colspan='2'>Countries with similar ratio of infections to deaths</th>";
            html = html +get3StatesWithSimilarInftoDeathsRatio(country);
            html = html + "   </table>";
            html = html + "   <table class = 'table-trans'>";
            html = html + "    <th colspan='2'>Countries with similar max daily infections</th>";
            html = html +  get3StatesWithSimilarMaxDailyInf(country);
            html = html + "  </table>";
           
            html = html + " <table class = 'table-trans'>";
            html = html + "     <th colspan='2'>Countries with similar max daily deaths</th>";
            html = html  + get3StatesWithSimilarMaxDailyDeaths(country);
            html = html + "  </table>";
            html = html + "   </div>";
            html = html + "  </div>";

        }
        else if(!isInListOfCountries(country) && state.equals("country")){
            html = html + "  <h2 style='text-align: center;'>" + country + "</h2>";
            html = html + " <br>";
            html = html + " <div class = 'flexbox-container' style = 'padding: 50px;'>";
            html = html + "  <div class = 'flex-item'>";
            html = html + "     Infections per million";
            html = html + "    <div class = 'number'>";
            html = html + String.format("%.2f", jdbc.getTotalCasesByState(country) * 1.0 * 1000000 / jdbc.getPopulationByState(country));
            html = html + "     </div>";
            html = html + "  </div>";
            html = html + "  <div class = 'vl'></div>";
            html = html + "  <div class = 'flex-item'>";
            html = html + "    Infections/Deaths ratio";
            html = html + "    <div class = 'number'>";
            html = html + String.format("%.2f", jdbc.getTotalCasesByState(country) * 1.0 / jdbc.getTotalDeathsByState(country));
            html = html + "      </div>";
            html = html + "   </div>";
            html = html + "    <div class = 'vl'></div>";
            
            html = html + "  <div class = 'flex-item'>";
            html = html + " Maximum daily infections";
            html = html + " <div class = 'number'>";
            html = html + jdbc.getMaxDailyInfState(country);
            html = html + "      </div>";
            html = html + " </div>";
            html = html + "  <div class = 'vl'></div>";
            html = html + " <div class = 'flex-item'>";
            html = html + "      Maximm daily deaths";
            html = html + "      <div class = 'number'>";
            html = html + jdbc.getMaxDailyDeathsState(country);
            html = html + "       </div>";
            html = html + "   </div>";
            html = html + "  </div>";
            html = html + " <br>";
            html = html + "  <div class = 'center' style = 'margin-bottom: 50px ;'>";
                    
            html = html + "  <div class = 'flexbox-container'>";
            html = html + "   <table class = 'table-trans'>";
            html = html + "    <th colspan='2'>Countries with the similar number of total infections per million</th>";
            html = html + get3CountriesWithSimilarInfRateState(country);
            html = html + "  </table>";
            html = html + "    <table class = 'table-trans'>";
            html = html + "    <th colspan='2'>Countries with similar ratio of infections to deaths</th>";
            html = html +get3CountriesWithSimilarInftoDeathsRatioState(country);
            html = html + "   </table>";
            html = html + "   <table class = 'table-trans'>";
            html = html + "    <th colspan='2'>Countries with similar max daily infections</th>";
            html = html +  get3CountriesWithSimilarMaxDailyInfState(country);
            html = html + "  </table>";
           
            html = html + " <table class = 'table-trans'>";
            html = html + "     <th colspan='2'>Countries with similar max daily deaths</th>";
            html = html  + get3CountriesWithSimilarMaxDailyDeathsState(country);
            html = html + "  </table>";
            html = html + "   </div>";
            html = html + "  </div>";
        }
        else{
            html = html + "<h2 style = 'text-align:center;'>CANNOT COMPARE A COUNTRY TO US STATES ONLY, PLEASE TRY AGAIN</h2>";
        }
        
        html = html + "   <script src='autocompleteUSA.js'></script>";
        html = html + "   <script src='backtotop.js'></script>";
        html = html + "    <script src='https://www.kryogenix.org/code/browser/sorttable/sorttable.js'></script>";
        html = html + " </body>";
        html = html + " </html>";

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

    public String get3CountriesWithSimilarInfRate(String country) {
        String html = "";
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> countries = jdbc.get3CountriesWithSimilarInfRatePerM(country);
        for(String name:countries){
            html = html + "<tr><td>" + name + "</td><td>" + String.format("%.2f",jdbc.getTotalCasesByCountry(name) * 1.0 * 1000000/jdbc.getPopulationByCountry(name)) + "</td></tr>";
        }
        return html;
    }

    public String get3CountriesWithSimilarInftoDeathsRatio(String country) {
        String html = "";
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> countries = jdbc.get3CountriesWithSimilarInfToDeathsRatio(country);
        for(String name:countries){
            html = html + "<tr><td>" + name + "</td><td>" + String.format("%.2f",jdbc.getTotalCasesByCountry(name) * 1.0 /jdbc.getTotalDeathsByCountry(name)) + "</td></tr>";
        }
        return html;
    }    

    public String get3CountriesWithSimilarMaxDailyInf(String country) {
        String html = "";
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> countries = jdbc.get3CountriesWithSimilarMaxDailyInf(country);
        for(String name:countries){
            html = html + "<tr><td>" + name + "</td><td>" + jdbc.getMaxDailyInf(name) + "</td></tr>";
        }
        return html;
    }    

    public String get3CountriesWithSimilarMaxDailyDeaths(String country) {
        String html = "";
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> countries = jdbc.get3CountriesWithSimilarMaxDailyDeaths(country);
        for(String name:countries){
            html = html + "<tr><td>" + name + "</td><td>" + jdbc.getMaxDailyDeaths(name) + "</td></tr>";
        }
        return html;
    }    

    public String get3StatesWithSimilarInfRate(String state){
        String html = "";
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> states = jdbc.get3StatesWithSimilarInfRatePerM(state, "US");
        for(String name: states){
            html = html + "<tr><td>" + name + "</td><td>" + String.format("%.2f",jdbc.getTotalCasesByState(name) * 1.0 * 1000000 / jdbc.getPopulationByState(name)) + "</td></tr>";
        }

        return html;
    }


    
    public String get3StatesWithSimilarInftoDeathsRatio(String state) {
        String html = "";
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> states = jdbc.get3StatesWithSimilarInfToDeathsRatio(state, "US");
        for(String name:states){
            html = html + "<tr><td>" + name + "</td><td>" + String.format("%.2f",jdbc.getTotalCasesByState(name) * 1.0 /jdbc.getTotalDeathsByState(name)) + "</td></tr>";
        }
        return html;
    }    


    public String get3StatesWithSimilarMaxDailyInf(String state) {
        String html = "";
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> states = jdbc.get3StatesWithSimilarMaxDailyInf(state, "US");
        for(String name:states){
            html = html + "<tr><td>" + name + "</td><td>" + jdbc.getMaxDailyInfState(name) + "</td></tr>";
        }
        return html;
    }    


    public String get3StatesWithSimilarMaxDailyDeaths(String state) {
        String html = "";
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> states = jdbc.get3StatesWithSimilarMaxDailyDeaths(state, "US");
        for(String name:states){
            html = html + "<tr><td>" + name + "</td><td>" + jdbc.getMaxDailyDeathsState(name) + "</td></tr>";
        }
        return html;
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

}    public String get3CountriesWithSimilarInfRateState(String country){
    String html = "";
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> countries = jdbc.get3CountriesWithSimilarInfRateState(country);
        for(String name:countries){
            html = html + "<tr><td>" + name + "</td><td>" + String.format("%.2f",jdbc.getTotalCasesByCountry(name) * 1.0 * 1000000/jdbc.getPopulationByCountry(name)) + "</td></tr>";
        }
        return html;
}

public String get3CountriesWithSimilarInftoDeathsRatioState(String country){
    String html = "";
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> countries = jdbc.get3CountriesWithSimilarInfToDeathsRatioState(country);
        for(String name:countries){
            html = html + "<tr><td>" + name + "</td><td>" + String.format("%.2f",jdbc.getTotalCasesByCountry(name) * 1.0 /jdbc.getTotalDeathsByCountry(name)) + "</td></tr>";
        }
        return html;
}

public String get3CountriesWithSimilarMaxDailyInfState(String country){
    String html = "";
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> countries = jdbc.get3CountriesWithSimilarMaxDailyInfState(country);
        for(String name:countries){
            html = html + "<tr><td>" + name + "</td><td>" + jdbc.getMaxDailyInf(name) + "</td></tr>";
        }
        return html;
}

public String get3CountriesWithSimilarMaxDailyDeathsState(String country){
    String html = "";
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> countries = jdbc.get3CountriesWithSimilarMaxDailyDeathsState(country);
        for(String name:countries){
            html = html + "<tr><td>" + name + "</td><td>" + jdbc.getMaxDailyDeaths(name) + "</td></tr>";
        }
        return html;
}
    


}
