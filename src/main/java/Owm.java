import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import net.aksingh.owmjapis.model.param.Weather;

import java.util.List;

public class Owm {

    public static void main(String[] args)
            throws APIException {


        OWM owm = new OWM("dcf890fc0ba06ef58dd0ec3a8842e983");


        CurrentWeather cwd = owm.currentWeatherByCityName("Zug");


        System.out.println("City: " + cwd.getCityName());


        System.out.println("Temperature: " + (Math.round(cwd.getMainData().getTemp() - 273.15)) + " \'C");

        System.out.println("Pressure: " + (cwd.getMainData().getPressure() + " hpa"));

        System.out.println("Wind speed: " + (cwd.getWindData().getSpeed() + " m/s"));

        System.out.println("Humidity: " + (cwd.getMainData().getHumidity()) + " %");

        System.out.println("Cloud cover: " + (cwd.getCloudData().getCloud()) + " %");

       /* if (cwd.hasRainData())
            System.out.println("Rain: " + (cwd.getRainData().getPrecipVol3h()));*/

        List<Weather> overall = cwd.getWeatherList();

        for (Weather object: overall)
            System.out.println(object.getMoreInfo());




    }
}
