package meteo.update;

import java.security.NoSuchAlgorithmException;
import meteo.WeatherData;
import meteo.locations.Locations;

/**
 * Created by Matt on 11.09.2018 at 19:18.
 */

public interface WeatherProvider {

    WeatherData getWeatherData(Locations location) throws NoSuchAlgorithmException;

}
