
package example.com.weather.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("temp")
    @Expose
    private float temp;
    @SerializedName("temp_min")
    @Expose
    private float temp_min;
    @SerializedName("temp_max")
    @Expose
    private float temp_max;
    @SerializedName("pressure")
    @Expose
    private float pressure;
    @SerializedName("sea_level")
    @Expose
    private float sea_level;
    @SerializedName("grnd_level")
    @Expose
    private float grnd_level;
    @SerializedName("humidity")
    @Expose
    private int humidity;
    @SerializedName("temp_kf")
    @Expose
    private int temp_kf;

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(float temp_min) {
        this.temp_min = temp_min;
    }

    public float getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(float temp_max) {
        this.temp_max = temp_max;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getSea_level() {
        return sea_level;
    }

    public void setSea_level(float sea_level) {
        this.sea_level = sea_level;
    }

    public float getGrnd_level() {
        return grnd_level;
    }

    public void setGrnd_level(float grnd_level) {
        this.grnd_level = grnd_level;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getTemp_kf() {
        return temp_kf;
    }

    public void setTemp_kf(int temp_kf) {
        this.temp_kf = temp_kf;
    }

}
