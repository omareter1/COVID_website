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
public class Page2 implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/level2.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<!DOCTYPE html>";
        html = html + "<html lang='en'>";
        html = html + "<head>";
        html = html + " <meta charset='UTF-8'>";
            
            
        html = html + " <link rel = 'stylesheet' href = 'stylesheet.css'>";
        html = html + "<link href='hover-min.css' rel='stylesheet'>";
        html = html + " <link rel='preconnect' href='https://fonts.gstatic.com'>";
        html = html + " <link href='https://fonts.googleapis.com/css2?family=IBM+Plex+Sans&display=swap' rel='stylesheet'> ";
        html = html + " <title>Map</title>";
        html = html + " </head>";
        html = html + " <body>";
        html = html + "<div class=\"topnav\">";
        html = html + "<a href = \"/\"><img src=\"icon.png\" style = \"width: 10vh;\"></a>";
        html = html + "<a  href=\"/\" class=\"hvr-underline-from-center\" >Home</a>";
        html = html + " <a href = \"page1.html\" class =\"hvr-underline-from-center\">World Stats</a>";
        html = html + "  <a href=\"level2.html\" class=\"hvr-underline-from-center active\">Map</a>";
        html = html + "  <a href=\"page3.html\" class=\"hvr-underline-from-center\">List of Countries</a>";

        html = html + " <a href=\"page5.html\" class=\"hvr-underline-from-center\">Advanced Reports</a>";
        html = html + "  <a href= 'page6.html' class= 'hvr-underline-from-center'>Similar Countries</a>";

        html = html + " </div> ";
        html = html + "  <div id='content'>";
        html = html + "  <iframe width='100%' height='90%'  src='https://coronavirus.app/map?embed=true' frameborder='0' allow='accelerometer' autoplay' encrypted-media' gyroscope' picture-in-picture' allowfullscreen></iframe>";
        html = html + "   </div>";
        
        html = html + "</body>";
        html = html + "</html>";
        
        
        

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}
