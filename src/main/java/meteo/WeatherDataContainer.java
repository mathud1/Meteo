package meteo;

import meteo.assessment.AssessmentStrategy;
import meteo.locations.Locations;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Matt on 20.09.2018 at 20:19.
 */

public class WeatherDataContainer {

    private Map<Locations, WeatherData> weatherDataByLocation;

    public Map<Locations, WeatherData> getWeatherDataByLocation() {
        return weatherDataByLocation;
    }

    public WeatherDataContainer() {
        this.weatherDataByLocation = Collections.synchronizedMap(new HashMap<>());
    }

    public void appendData(Locations location, WeatherData weatherData) {
        weatherDataByLocation.put(location, weatherData);
    }

    public Map<Locations, Integer> rateLocations(AssessmentStrategy assessmentStrategy) {

        return weatherDataByLocation.keySet()
                .stream()
                .collect(Collectors.toMap(key -> key,
                        key -> assessmentStrategy.assess(weatherDataByLocation.get(key))));
    }

}


