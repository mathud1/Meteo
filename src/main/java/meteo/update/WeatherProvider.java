package meteo.update;

import meteo.WeatherData;

/**
 * Created by Matt on 11.09.2018 at 19:18.
 */
public interface WeatherProvider {
    WeatherData getWeatherData();
}
