package example.com.weather.model;

public interface CallbackWeather<T> {
    void successWeather(T weatherDate);
    void failWeather();
}
