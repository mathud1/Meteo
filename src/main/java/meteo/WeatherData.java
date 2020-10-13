package meteo;

import net.aksingh.owmjapis.model.param.Weather;

import java.util.List;

/**
 * Created by Matt on 20.09.2018 at 20:20.
 */

public class WeatherData {

    //TODO use builder design pattern

    private String localisation;
    private double temperature;
    private double wind;
    private double pressure;
    private double cloudCover;
    private double humidity;
    List<Weather> overall;
    String description;

    public WeatherData(String localisation, double temperature, double wind, double pressure, double cloudCover,
                       double humidity, List<Weather> overall, String description) {
        this.localisation = localisation;
        this.temperature = temperature;
        this.wind = wind;
        this.pressure = pressure;
        this.cloudCover = cloudCover;
        this.humidity = humidity;
        this.overall = overall;
        this.description = description;
    }

    public WeatherData(String localisation, double temperature, double wind, double pressure, double humidity) {
        this.localisation = localisation;
        this.temperature = temperature;
        this.wind = wind;
        this.pressure = pressure;
        this.humidity = humidity;
    }


    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getWind() {
        return wind;
    }

    public void setWind(double wind) {
        this.wind = wind;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(double cloudCover) {
        this.cloudCover = cloudCover;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public List<Weather> getOverall() {
        return overall;
    }

    public void setOverall(List<Weather> overall) {
        this.overall = overall;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Current meteo conditions in " + localisation + '\n' +
                "temperature: " + temperature + " 'C" + '\n' +
                "wind speed: " + wind + " m/s" + '\n' +
                "pressure: " + pressure + " hpa" + '\n' +
                "humidity: " + humidity + " %" + '\n' +
                "cloudCover: " + cloudCover + " %" + '\n' +
                "description: " + description;
    }

}
