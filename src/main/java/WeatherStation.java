/**
 * Created by Matt on 11.09.2018 at 18:37.
 */
public enum WeatherStation {
    INSTANCE;

    private float temperature;
    private float wind;
    private String cloudCover;
    //private CloudCover cloudCover;
    private boolean on;

    private UpdateStrategy updateStrategy;

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getWind() {
        return wind;
    }

    public void setWind(float wind) {
        this.wind = wind;
    }

    public String getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(String cloudCover) {
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
                    System.out.println("Current meteo conditions:");
                    System.out.println("temperature: " + temperature);
                    System.out.println("wind speed: " + wind);
                    // System.out.println("cloud cover: " + cloudCover);
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
        stacja.setUpdateStrategy(new YahooUpdateStrategy());
        stacja.start();
        Thread.sleep(1000);
        stacja.stop();
    }
}
