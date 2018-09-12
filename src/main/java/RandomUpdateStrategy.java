import java.util.Random;

/**
 * Created by Matt on 11.09.2018 at 19:22.
 */
public class RandomUpdateStrategy implements UpdateStrategy {

    @Override
    public void update() {
        WeatherStation stacja = WeatherStation.INSTANCE;
        Random random = new Random();
        stacja.setTemperature(random.nextInt(40));
        stacja.setWind(random.nextInt(99));
        stacja.setCloudCover("stormy");
    }
}
