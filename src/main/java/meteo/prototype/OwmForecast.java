package meteo.prototype;/* To jest klasa testująca zaciąganie prognoz z API OWM */

import java.util.Objects;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.HourlyWeatherForecast;
import net.aksingh.owmjapis.model.param.WeatherData;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Matt on 14.09.2018 at 18:37.
 */

public class OwmForecast {

    private static final Logger LOG = LoggerFactory.getLogger(OwmForecast.class);
    private static final String CITY_NAME = "Katowice";

    public static void main(String[] args) throws APIException {

        OWM owmForecast = new OWM("");

        HourlyWeatherForecast forecast = owmForecast.hourlyWeatherForecastByCityName(CITY_NAME);

        List<WeatherData> overall = forecast.getDataList();

        for (WeatherData object: Objects.requireNonNull(overall))
            LOG.info(String.valueOf(object.getWeatherList()));
    }

}
