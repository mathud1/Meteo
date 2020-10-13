package meteo;

import java.security.NoSuchAlgorithmException;
import meteo.assessment.TrekkingAssessmentStrategy;
import meteo.locations.Locations;
import meteo.update.OwmWeatherProvider;
import meteo.update.WeatherProvider;

import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Matt on 11.09.2018 at 18:37.
 */

public enum WeatherStation {

    INSTANCE;

    private static final Logger LOG = LoggerFactory.getLogger(WeatherStation.class);

    private boolean on;
    private WeatherProvider weatherProvider;
    private final WeatherDataContainer weatherDataContainer = new WeatherDataContainer();

    private final Set<WeatherStationObserver> observers;

    WeatherStation() {
        observers = new HashSet<>();
    }

    public boolean isOn() {
        return on;
    }

    void setOn(boolean on) {
        this.on = on;
    }

    public WeatherProvider getWeatherProvider() {
        return weatherProvider;
    }

    public void setWeatherProvider(WeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;

    }

    public void start() {
        if (!on) {
            on = true;
            Thread weatherThread1 = new Thread(() -> {
                while (on) {
                    LOG.info("Updating...");

                    for (Locations location : Locations.values()) {
                        WeatherData weatherData = null;
                        try {
                            weatherData = weatherProvider.getWeatherData(location);
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }

                        weatherDataContainer.appendData(location, weatherData);
                        LOG.info(String.valueOf(weatherData));
                        notifyObservers(weatherData);
                        LOG.info("Rating (out of 5): {}", weatherDataContainer.rateLocations(new TrekkingAssessmentStrategy()).get(location));
                        LOG.info("Source: {}", this.getWeatherProvider().getClass().getName());

                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            LOG.error(e.getMessage());
                            Thread.currentThread().interrupt();
                        }
                    }

                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        LOG.error(e.getMessage());
                        Thread.currentThread().interrupt();
                    }
                }

            });
            weatherThread1.start();

        }

    }

    public void stop() {
        on = false;
    }

    public static void main(String[] args) throws InterruptedException {

        WeatherStation station = WeatherStation.INSTANCE;

        station.setWeatherProvider(new OwmWeatherProvider());
        station.start();

        Thread.sleep(5000);

        station.stop();

    }


    public void addObserver(WeatherStationObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(WeatherStationObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(WeatherData weatherData) {
        observers.forEach(observer -> observer.update(weatherData));
    }

}
