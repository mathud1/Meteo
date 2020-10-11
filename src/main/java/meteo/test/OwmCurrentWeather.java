package meteo.test;

import java.util.Objects;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import net.aksingh.owmjapis.model.param.Weather;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OwmCurrentWeather {

    private static final Logger LOG = LoggerFactory.getLogger(OwmCurrentWeather.class);

    public static void main(String[] args) throws APIException {

        OWM owm = new OWM("");

        CurrentWeather cwd = owm.currentWeatherByCityName("Żywiec");

        LOG.info("City: {}", cwd.getCityName());

        if (cwd.getMainData() != null && cwd.getMainData().getTemp() != null) {
            LOG.info("Temperature: {}", (Math.round(cwd.getMainData().getTemp() - 273.15)) + " 'C");
        }
        LOG.info("Pressure: {}", (Objects.requireNonNull(cwd.getMainData()).getPressure() + " hpa"));

        LOG.info("Wind speed: {}", (Objects.requireNonNull(cwd.getWindData()).getSpeed() + " m/s"));

        LOG.info("Humidity: {}",  (cwd.getMainData().getHumidity()) + " %");

        LOG.info("Cloud cover: {}", (Objects.requireNonNull(cwd.getCloudData()).getCloud()) + " %");

        LOG.info("Opis: {}", Objects.requireNonNull(cwd.getWeatherList()).get(0).getMoreInfo());

        List<Weather> overall = cwd.getWeatherList();

        for (Weather object: overall) {
            LOG.info(object.getMoreInfo());
        }
        LOG.info(String.valueOf(overall));

    }

}
