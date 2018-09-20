/* To jest klasa testująca zaciąganie prognoz z API OWM */

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.HourlyWeatherForecast;
import net.aksingh.owmjapis.model.param.WeatherData;

import java.util.List;

/**
 * Created by Matt on 14.09.2018 at 18:37.
 */
public class OwmForecast {

    public static void main(String[] args) throws APIException {


        OWM owmForecast = new OWM("dcf890fc0ba06ef58dd0ec3a8842e983");

        HourlyWeatherForecast forecast = owmForecast.hourlyWeatherForecastByCityName("Katowice");

        List<WeatherData> overall = forecast.getDataList();

        for (WeatherData object: overall)
            System.out.println(object.getWeatherList());


    }
}
