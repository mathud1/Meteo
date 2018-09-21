// trzeba dostosować do wersji z Weather Provider
package meteo.update;

//import java.nio.channels.Channel;



/**
 * Created by Matt on 11.09.2018 at 20:22.
 */
/*
public class YahooWeatherProvider implements WeatherProvider {

    private YahooWeatherService yahooWeatherService;

    public YahooWeatherProvider() {
        try {
            this.yahooWeatherService = new YahooWeatherService();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


    public void getWeatherData() {

        Channel channel;
        try {
            channel = yahooWeatherService.getForecast("498842", DegreeUnit.CELSIUS);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
            return;
        }

        int windSpeed = channel.getWind().getSpeed().intValue();
        int temperature = channel.getItem().getCondition().getTemp();



        WeatherStation stacja = WeatherStation.INSTANCE;
        //stacja.setTemperature(temperature);
        //stacja.setWind(windSpeed);
        //stacja.setCloudCover();
    }
} */
