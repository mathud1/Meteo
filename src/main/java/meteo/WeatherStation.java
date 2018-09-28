package meteo;

import meteo.assessment.TrekkingAssessmentStrategy;
import meteo.locations.Locations;
import meteo.update.OwmWeatherProvider;
import meteo.update.WeatherProvider;
import net.aksingh.owmjapis.model.param.Weather;

/**
 * Created by Matt on 11.09.2018 at 18:37.
 */
public enum WeatherStation {
    INSTANCE;


    private boolean on;

    private WeatherProvider weatherProvider;

    private WeatherDataContainer weatherDataContainer = new WeatherDataContainer();

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

                    for (Locations location : Locations.values()) {
                        WeatherData weatherData = weatherProvider.getWeatherData(location);

                        System.out.println("-------------------");
                        System.out.println("Current meteo conditions in " + weatherData.getLocalisation());
                        System.out.println("Temperature: " + weatherData.getTemperature() + " \'C");
                        System.out.println("Wind speed: " + weatherData.getWind() + " m/s");

                        System.out.println("Pressure: " + weatherData.getPressure() + " hpa");
                        System.out.println("Humidity: " + weatherData.getHumidity() + " %");

                        //Należy sprawdzić, czy istnieją dane
                        System.out.println("Cloud cover: " + weatherData.getCloudCover() + " %");


                        if (weatherData.getOverall() != null) {
                            for (Weather object : weatherData.getOverall())
                                System.out.println(object.getMoreInfo());
                        }
                        weatherDataContainer.appendData(location, weatherData);
                        System.out.println("Rating (out of 5): " + weatherDataContainer.rateLocations(new TrekkingAssessmentStrategy()).get(location));
                    }

                    try {
                        Thread.sleep(5000);
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
            //stacja.setWeatherProvider(new RandomWeatherProvider());
        //stacja.setWeatherProvider((new YahooWeatherProvider()));
            stacja.start();

            Thread.sleep(1000);

            stacja.stop();




    }



}
