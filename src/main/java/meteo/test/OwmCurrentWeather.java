package meteo.test;/* To jest klasa testująca API OWM. Nie jest potrzebna dla funkcjonowania całości aplikacji */

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import net.aksingh.owmjapis.model.param.Weather;

import java.util.List;

public class OwmCurrentWeather {

    public static void main(String[] args)
            throws APIException {


        OWM owm = new OWM("***REMOVED***");



        CurrentWeather cwd = owm.currentWeatherByCityName("Żywiec");




        System.out.println("City: " + cwd.getCityName());


        System.out.println("Temperature: " + (Math.round(cwd.getMainData().getTemp() - 273.15)) + " \'C");

        System.out.println("Pressure: " + (cwd.getMainData().getPressure() + " hpa"));

        System.out.println("Wind speed: " + (cwd.getWindData().getSpeed() + " m/s"));

        System.out.println("Humidity: " + (cwd.getMainData().getHumidity()) + " %");

        System.out.println("Cloud cover: " + (cwd.getCloudData().getCloud()) + " %");


        List<Weather> overall = cwd.getWeatherList();

        for (Weather object: overall)
            System.out.println(object.getMoreInfo());




    }
}
