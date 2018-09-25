package meteo.assessment;

import meteo.WeatherData;

/**
 * Created by Matt on 20.09.2018 at 19:52.
 */
public class TrekkingAssessmentStrategy implements AssessmentStrategy {



    //to jest do uzupełnienia
    public int assess(WeatherData weatherData) {

                return (int) weatherData.getTemperature();
    }
}
