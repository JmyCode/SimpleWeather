package example.com.weather.model;



public class CityModel{

    private static CityModel cityModel;

    private CityModel(){

    }

    public static CityModel create(){
        if (cityModel == null)
            cityModel = new CityModel();

        return cityModel;
    }



     static String cityName = "Tambov";



    public static String getCityName() {
        return cityName;
    }

    public static void setCityName(String cityName1) {
        cityName = cityName1;
    }

}
