package meteo.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import meteo.WeatherStation;
import meteo.update.RandomWeatherProvider;
import meteo.update.YahooWeatherProvider;


public class Controller  {
  @FXML
  private TextField temperatureTextField;
  @FXML
  private TextField windSpeedTextField;
  @FXML
  private TextField cloudCoverTextField;
  @FXML
  private ChoiceBox<String> weatherUpdaterChoiceBox;

  private final static String YAHOO_WEATHER_UPDATER_DISPLAY_NAME = "Yahoo API";
  private final static String SIMULATOR_WEATHER_UDPATER_DISPLAY_NAME = "Symulator";



  public void initialize() {
    WeatherStation weatherStation = WeatherStation.INSTANCE;
    weatherStation.setWeatherProvider(new YahooWeatherProvider());
    weatherStation.start();


    //weatherStation.addObserver(this);

    weatherUpdaterChoiceBox.getItems().addAll(YAHOO_WEATHER_UPDATER_DISPLAY_NAME, SIMULATOR_WEATHER_UDPATER_DISPLAY_NAME);
    weatherUpdaterChoiceBox.getSelectionModel().selectFirst();

    weatherUpdaterChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
      switch (newValue) {
        case YAHOO_WEATHER_UPDATER_DISPLAY_NAME:
          weatherStation.setWeatherProvider(new YahooWeatherProvider());
          break;
        case SIMULATOR_WEATHER_UDPATER_DISPLAY_NAME:
          weatherStation.setWeatherProvider(new RandomWeatherProvider());
          break;
      }
    });
  }


}
