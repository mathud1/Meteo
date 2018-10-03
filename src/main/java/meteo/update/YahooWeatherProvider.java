package meteo.update;

import com.github.fedy2.weather.YahooWeatherService;
import com.github.fedy2.weather.data.unit.DegreeUnit;
import meteo.WeatherData;
import meteo.locations.Locations;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Created by Matt on 11.09.2018 at 20:22.
 */

public class YahooWeatherProvider implements WeatherProvider {

    private YahooWeatherService yahooWeatherService;

    public YahooWeatherProvider() {
        try {
            this.yahooWeatherService = new YahooWeatherService();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


    public WeatherData getWeatherData(Locations location) {

        com.github.fedy2.weather.data.Channel channel;

        try {
            channel = yahooWeatherService.getForecast(location.getYahooWoeid(), DegreeUnit.CELSIUS);

        } catch (JAXBException | IOException e) {
            e.printStackTrace();
            return null;
        }

        String localisation = location.getName();
        int windSpeed = channel.getWind().getSpeed().intValue();
        int temperature = channel.getItem().getCondition().getTemp();
        double pressure = Math.round(channel.getAtmosphere().getPressure() / 6894.75);
        double humidity = channel.getAtmosphere().getHumidity();

        return new WeatherData(localisation, temperature, windSpeed, pressure, humidity);

    }
}
