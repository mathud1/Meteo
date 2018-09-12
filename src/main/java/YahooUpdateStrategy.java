import com.github.fedy2.weather.YahooWeatherService;
import com.github.fedy2.weather.data.unit.DegreeUnit;
import com.github.fedy2.weather.data.Channel;
import javax.xml.bind.JAXBException;
import java.io.IOException;
//import java.nio.channels.Channel;



/**
 * Created by Matt on 11.09.2018 at 20:22.
 */
public class YahooUpdateStrategy implements UpdateStrategy {

    private YahooWeatherService yahooWeatherService;

    public YahooUpdateStrategy() {
        try {
            this.yahooWeatherService = new YahooWeatherService();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


    public void update() {

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
        stacja.setTemperature(temperature);
        stacja.setWind(windSpeed);
        //stacja.setCloudCover();
    }
}
