import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import net.aksingh.owmjapis.model.param.Weather;

import java.util.List;

/**
 * Created by Matt on 11.09.2018 at 20:41.
 */
public class OwmUpdateStrategy implements UpdateStrategy {

   private OWM owm;
   private String location;

   /* public void setLocation(String location) {
        this.location = location;
    }*/

    public OwmUpdateStrategy() {
        this.owm = new OWM("***REMOVED***");
    }

    public OwmUpdateStrategy(String location) {
        this.owm = new OWM("***REMOVED***");
        this.location = location;
    }

    public void update() {


        CurrentWeather cwd = null;


            try {
                cwd = owm.currentWeatherByCityName(location);
            } catch (APIException e) {
                e.printStackTrace();
            }


        String localisation = cwd.getCityName();
        double windSpeed = cwd.getWindData().getSpeed() ;
        long temperature = Math.round(cwd.getMainData().getTemp() - 273.15);
        double pressure = cwd.getMainData().getPressure();
        double humidity = cwd.getMainData().getHumidity();
        double cloudCover = cwd.getCloudData().getCloud();
        List<Weather> overall = cwd.getWeatherList();

        WeatherStation stacja = WeatherStation.INSTANCE;
        stacja.setLocalisation(localisation);
        stacja.setWind(windSpeed);
        stacja.setTemperature(temperature);
        stacja.setPressure(pressure);
        stacja.setHumidity(humidity);
        stacja.setCloudCover(cloudCover);
        stacja.setOverall(overall);
    }

}
