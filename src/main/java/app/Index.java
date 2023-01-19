package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class Index implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = " <!DOCTYPE html>";
        html = html  + "<html lang=\"en\">";
        html = html + "<head>";
        html = html + "<meta charset=\"UTF-8\">";
        html = html + "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">";
        html = html + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">";
        html = html + "<link rel=\"stylesheet\" href=\"style.css\">";
        html = html + "<link rel = \"stylesheet\" href=\"stylesheet.css\">";
        html = html + "<link rel=\"stylesheet\" href = \"hover-min.css\">";
        html = html + "<title>Home</title>";
        html = html + "<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\">";
        html = html + "<link href=\"https://fonts.googleapis.com/css2?family=IBM+Plex+Sans&display=swap\" rel=\"stylesheet\">";
        html = html + "</head>";
        html = html + "<body>";
        html = html + "<button onclick='topFunction()' id= 'myBtn' title= 'Go to top'><i class='fas fa-angle-double-up'></i>&nbsp;Back to top</button>";

        html = html + "<div class=\"topnav\">";
        html = html + "<a href = \"/\"><img src=\"icon.png\" style = \"width: 10vh;\"></a>";
        html = html + "<a  href=\"/\" class=\"hvr-underline-from-center active\" >Home</a>";
        html = html + " <a href = \"page1.html\" class =\"hvr-underline-from-center\">World Stats</a>";
        html = html + "  <a href=\"level2.html\" class=\"hvr-underline-from-center\">Map</a>";
        html = html + "  <a href=\"page3.html\" class=\"hvr-underline-from-center\">List of Countries</a>";

        html = html + " <a href=\"page5.html\" class=\"hvr-underline-from-center\">Advanced Reports</a>";
        html = html + "  <a href= 'page6.html' class= 'hvr-underline-from-center'>Similar Countries</a>";

        html = html + " </div> ";
        html = html + " <div class=\"wrapper\">";
        html = html + " <div class=\"header\">";
        html = html + "<div class=\"grid-container\">";
        html = html + " <p></p> <p id=\"title\" >COVID-19</p> <p></p>";
        html = html + " <a href = '/page3.html' class=\"buttontextnav\" ><button class=\"butt1\"><div class=\"buttontextnav\">Countries</div></button></a>";
        html = html + " <a href=\"https://www.who.int/southeastasia/outbreaks-and-emergencies/novel-coro navirus-2019/protective-measures/stay-healthy-at-home\" class=\"buttontextnav\" target=\"_blank\"><button class=\"butt2\"><div class=\"buttontextnav\"> How to stay safe?</div></button></a>";
        html = html + "  <a href=\"https://www.who.int/health-topics/vaccines-and-immunization#tab=tab_1\" class=\"buttontextnav\" target=\"_blank\"><button class=\"butt3\"><div class=\"buttontextnav\">Vaccines</div></button></a>";
                       
        html = html + "</div>";
        html = html + "</div>";
        html = html + "<article class=\"main\">";
        html = html + "<p class=\"symptomstitle\">Symptoms</p> "; 
        html = html + "<p class=\"text-main\">Coronavirus disease (COVID-19) is an infectious disease caused by a newly discovered coronavirus.";
        html = html + "<p class=\"text-main\"> Most people infected with the COVID-19 virus will experience mild to moderate respiratory illness and recover without requiring special treatment.  Older people, and those with underlying medical problems like cardiovascular disease, diabetes, chronic respiratory disease, and cancer are more likely to develop serious illness.</p>";
        html = html + "<p class=\"text-main\"> The best way to prevent and slow down transmission is to be well informed about the COVID-19 virus, the disease it causes and how it spreads. Protect yourself and others from infection by washing your hands or using an alcohol based rub frequently and not touching your face.</p>";
        html = html + "<p class=\"text-main\">The COVID-19 virus spreads primarily through droplets of saliva or discharge from the nose when an infected person coughs or sneezes, so it’s important that you also practice respiratory etiquette</p>";
        html = html + "</article>";
                
        html = html + "<div class=\"aside aside-1\">";
                        
        html = html + " <button class=\"butt4\"><a class=\"buttontext\" href=\"level2.html\" style=\"text-decoration: none;\">MAP</a></button>";
        html = html + " <button class=\"butt4\" ><a class=\"buttontext\" href = \"page1.html\" style=\"text-decoration: none;\">WORLD STATISTICS</a></button>";
        
        html = html + " </div>";
        html = html + "<aside class=\"aside aside-2\">";
        html = html + "<p class=\"overviewtitle\">Overview</p>";
        html = html + "<p class=\"text-main\">COVID-19 affects different people in different ways. Most infected people will develop mild to moderate illness and recover without </p>";
        
        html = html + " <p class=\"overviewtitle\">Most common symptoms:</p>";
        
        html = html + " <ul class=\"text-main\"> ";
        html = html + "     <li>Fever.</li>";
        html = html + "     <li>Dry cough.</li>";
        html = html + "     <li>Tiredness.</li>";
        html = html + "</ul>";
        
        html = html + "<p class=\"overviewtitle\">Less common symptoms:</p>";
        
        html = html + " <ul class=\"text-main\">";
        html = html + "     <li>Aches and pains.</li>";
        html = html + " <li>Sore throat.</li>";
        html = html + " </ul>";
        
        
        html = html + "<p class=\"text-main\"> <img src=\"https://cdn.iconscout.com/icon/free/png-256/warning-190-457484.png\" class=\"warningicon\" alt=\"warningicon\"> If you are experiencing any of the symptoms above please contact your doctor/chemist.</p> ";
        html = html + "</aside>";
        html = html + "</div>";
        
        
              html = html + "<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>";
              html = html + "<script src = 'backtotop.js'></script>";
              html = html + "</body>";
              html = html + "</html>";

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}
