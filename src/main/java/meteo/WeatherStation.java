package meteo;

import meteo.assessment.TrekkingAssessmentStrategy;
import meteo.locations.Locations;
import meteo.update.OwmWeatherProvider;
import meteo.update.WeatherProvider;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Matt on 11.09.2018 at 18:37.
 */
public enum WeatherStation {
    INSTANCE;


    private boolean on;
    private WeatherProvider weatherProvider;
    private WeatherDataContainer weatherDataContainer = new WeatherDataContainer();

    private Set<WeatherStationObserver> observers;

    WeatherStation() {
        observers = new HashSet<>();
    }


    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
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
                    System.out.println("Updating...");
                    System.out.println();

                    for (Locations location : Locations.values()) {
                        WeatherData weatherData = weatherProvider.getWeatherData(location);


                        weatherDataContainer.appendData(location, weatherData);
                        System.out.println(weatherData.toString());
                        notifyObservers(weatherData);
                        System.out.println("Rating (out of 5): " + weatherDataContainer.rateLocations(new TrekkingAssessmentStrategy()).get(location));
                        System.out.println("Source: " + this.getWeatherProvider().getClass().getName());

                        System.out.println();
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
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


        WeatherStation stacja = WeatherStation.INSTANCE;

        stacja.setWeatherProvider(new OwmWeatherProvider());
        stacja.start();

        Thread.sleep(5000);

        stacja.stop();


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
