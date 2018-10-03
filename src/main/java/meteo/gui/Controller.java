package meteo.gui;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import meteo.WeatherData;
import meteo.WeatherStation;
import meteo.WeatherStationObserver;
import meteo.update.OwmWeatherProvider;
import meteo.update.RandomWeatherProvider;
import meteo.update.YahooWeatherProvider;


public class Controller implements WeatherStationObserver {
    @FXML
    private TextField localisationTextField;
    @FXML
    private TextField temperatureTextField;
    @FXML
    private TextField windSpeedTextField;
    @FXML
    private TextField pressureTextField;
    @FXML
    private TextField humidityTextField;
    @FXML
    private PieChart humidityChart;
    @FXML
    private TextField cloudCoverTextField;
    @FXML
    private PieChart cloudChart;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private ChoiceBox<String> weatherUpdaterChoiceBox;

    private final static String OWM_WEATHER_PROVIDER_DISPLAY_NAME = "Open Weather Map";
    private final static String YAHOO_WEATHER_PROVIDER_DISPLAY_NAME = "Yahoo Weather";
    private final static String SIMULATOR_WEATHER_PROVIDER_DISPLAY_NAME = "Simulator (random)";


    public void initialize() {
        WeatherStation weatherStation = WeatherStation.INSTANCE;
        weatherStation.setWeatherProvider(new OwmWeatherProvider());
        weatherStation.start();


        weatherStation.addObserver(this);

        weatherUpdaterChoiceBox.getItems().addAll(OWM_WEATHER_PROVIDER_DISPLAY_NAME, YAHOO_WEATHER_PROVIDER_DISPLAY_NAME,
                SIMULATOR_WEATHER_PROVIDER_DISPLAY_NAME);
        weatherUpdaterChoiceBox.getSelectionModel().selectFirst();

        weatherUpdaterChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case OWM_WEATHER_PROVIDER_DISPLAY_NAME:
                    weatherStation.setWeatherProvider(new OwmWeatherProvider());
                    break;
                case YAHOO_WEATHER_PROVIDER_DISPLAY_NAME:
                    weatherStation.setWeatherProvider(new YahooWeatherProvider());
                    break;
                case SIMULATOR_WEATHER_PROVIDER_DISPLAY_NAME:
                    weatherStation.setWeatherProvider(new RandomWeatherProvider());
                    break;
            }
        });
    }





    @Override
    public void update(WeatherData weatherData) {
        Platform.runLater(() -> {

            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data("Covered", 0 + weatherData.getCloudCover()),
                            new PieChart.Data("Uncovered"  , 100 - weatherData.getCloudCover()));

            ObservableList<PieChart.Data> humChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data("Water vapor", 0 + weatherData.getHumidity()),
                            new PieChart.Data("Dry air" , 100 - weatherData.getHumidity()));



            localisationTextField.setText(String.valueOf(weatherData.getLocalisation()));
            temperatureTextField.setText(String.valueOf(weatherData.getTemperature()));
            windSpeedTextField.setText(String.valueOf(weatherData.getWind()));
            pressureTextField.setText(String.valueOf(weatherData.getPressure()));
            humidityTextField.setText(String.valueOf(weatherData.getHumidity()));
            cloudCoverTextField.setText(String.valueOf(weatherData.getCloudCover()));
            descriptionTextField.setText(String.valueOf(weatherData.getDescription()));
            cloudChart.setData(pieChartData);
            humidityChart.setData(humChartData);




        });
    }

}
