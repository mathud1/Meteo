package meteo.gui;

import eu.hansolo.medusa.Gauge;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
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
    private Gauge windGauge;
    @FXML
    private Gauge windGauge2;
    @FXML
    private Gauge pressureGauge;
    @FXML
    private  Gauge tempGauge;
    @FXML
    private TextField windSpeed2TextField;
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
                default:
                    weatherStation.setWeatherProvider(null);
                    break;
            }
        });
    }





    @Override
    public void update(WeatherData weatherData) {
        Platform.runLater(() -> {

            localisationTextField.setText(String.valueOf(weatherData.getLocalisation()));
            temperatureTextField.setText(String.valueOf(weatherData.getTemperature()));
            windSpeedTextField.setText(String.valueOf(weatherData.getWind()));
            windSpeed2TextField.setText(String.valueOf(weatherData.getWind()*3.6));
            pressureTextField.setText(String.valueOf(weatherData.getPressure()));
            humidityTextField.setText(String.valueOf(weatherData.getHumidity()));
            cloudCoverTextField.setText(String.valueOf(weatherData.getCloudCover()));
            descriptionTextField.setText(String.valueOf(weatherData.getDescription()));

            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data("Clouds", 0 + weatherData.getCloudCover()),
                            new PieChart.Data("Clear sky"  , 100 - weatherData.getCloudCover()));

            ObservableList<PieChart.Data> humChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data("Water vapor", 0 + weatherData.getHumidity()),
                            new PieChart.Data("Dry air" , 100 - weatherData.getHumidity()));

            cloudChart.setData(pieChartData);
            humidityChart.setData(humChartData);


            windGauge.setValue(weatherData.getWind());
            windGauge.setMaxValue(40);
            windGauge.setUnit("m / s");
            windGauge.setUnitColor(Color.DARKRED);
            windGauge.setNeedleColor(Color.DARKRED);
            windGauge.setNeedleSize(Gauge.NeedleSize.THIN);
            windGauge.setNeedleShape(Gauge.NeedleShape.FLAT);
            windGauge.setAnimated(true);


            windGauge2.setValue(weatherData.getWind()*3.6);
            windGauge2.setMaxValue(120);
            windGauge2.setUnit("km / h");
            windGauge2.setUnitColor(Color.DARKRED);
            windGauge2.setNeedleColor(Color.DARKRED);
            windGauge2.setNeedleSize(Gauge.NeedleSize.THIN);
            windGauge2.setNeedleShape(Gauge.NeedleShape.FLAT);
            windGauge2.setAnimated(true);

            pressureGauge.setValue(weatherData.getPressure()/10);
            pressureGauge.setMinValue(96.0);
            pressureGauge.setMaxValue(106.0);
            pressureGauge.setUnit("x 10 hpa");
            pressureGauge.setUnitColor(Color.DARKRED);
            pressureGauge.setNeedleColor(Color.DARKRED);
            pressureGauge.setNeedleSize(Gauge.NeedleSize.THIN);
            pressureGauge.setNeedleShape(Gauge.NeedleShape.FLAT);
            pressureGauge.setAnimated(true);

            tempGauge.setValue(weatherData.getTemperature());
            tempGauge.setMinValue(-40);
            tempGauge.setMaxValue(40);
            tempGauge.setUnit("Celsius");
            tempGauge.setUnitColor(Color.DARKRED);
            tempGauge.setNeedleColor(Color.DARKRED);
            tempGauge.setNeedleSize(Gauge.NeedleSize.THIN);
            tempGauge.setNeedleShape(Gauge.NeedleShape.FLAT);
            tempGauge.setAnimated(true);
        });


    }

}
