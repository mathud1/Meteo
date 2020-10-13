package meteo.assessment;

import meteo.WeatherData;

/**
 * Created by Matt on 20.09.2018 at 19:52.
 */

public class TrekkingAssessmentStrategy implements AssessmentStrategy {

    private int assessTemperature(double temp) {
        return temp > 10 && temp < 25 ? 1 : 0;
    }

    private int assessWind(double wind) {
       return wind < 5 ? 1 : 0;
    }

    private int assessPressure(double pressure) {
        return pressure < 1013 ? 0 : 1;
    }

    private int assessHumidity(double humidity) {
        return humidity < 70 ? 1 : 0;
    }

    private int assessClouds(double cloudCover) {
        return cloudCover < 50 ? 1 : 0;
    }

    public int assess(WeatherData weatherData) {

        return assessTemperature(weatherData.getTemperature()) + assessWind(weatherData.getWind())
                + assessPressure(weatherData.getPressure()) + assessHumidity(weatherData.getHumidity())
                + assessClouds(weatherData.getCloudCover());

    }

}
