import net.aksingh.owmjapis.model.param.Weather;

import java.util.List;

/**
 * Created by Matt on 11.09.2018 at 18:37.
 */
public enum WeatherStation {
    INSTANCE;

    private String localisation;
    private float temperature;
    private double wind;
    private double pressure;
    private double cloudCover;
    private double humidity;
    List<Weather> overall;

    public List<Weather> getOverall() {
        return overall;
    }

    public void setOverall(List<Weather> overall) {
        this.overall = overall;
    }

    private boolean on;

    private UpdateStrategy updateStrategy;

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public double getWind() {
        return wind;
    }

    public void setWind(double wind) {
        this.wind = wind;
    }

    public double getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(double cloudCover) {
        this.cloudCover = cloudCover;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public UpdateStrategy getUpdateStrategy() {
        return updateStrategy;
    }

    public void setUpdateStrategy(UpdateStrategy updateStrategy) {
        this.updateStrategy = updateStrategy;
    }

    public void update() {
        updateStrategy.update();
    }


    public void start() {
        if (!on) {
            on = true;
            Thread weatherThread1 = new Thread(() -> {
                while (on) {
                    System.out.println("Updating...");
                    update();
                    System.out.println("-------------------");
                    System.out.println("Current meteo conditions in: " + localisation);
                    System.out.println("Temperature: " + temperature + " \'C");
                    System.out.println("Wind speed: " + wind + " m/s");
                    System.out.println("Pressure: " + pressure + " hpa");
                    System.out.println("Humidity: " + humidity + " %");
                    System.out.println("Cloud cover: " + cloudCover + " %");
                    for (Weather object: overall)
                        System.out.println(object.getMoreInfo());
                    try {
                        Thread.sleep(1000);
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
        stacja.setUpdateStrategy(new OwmUpdateStrategy());
        stacja.start();
        Thread.sleep(1000);
        stacja.stop();
    }


}
