package com.yize.miniweather.bean;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class WeatherBean implements Serializable {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                "\"data\":\"" + data + "\"" +
                "}";
    }

    public class Data implements Serializable{
        @SerializedName("forecast_1h")
        private Map<String,ForecastHour> forecastHour;
        private Air air;
        @SerializedName("forecast_24h")
        private Map<String,ForecastDay> forecastDay;
        private Indexs index;
        private Observe observe;
        private Map<String,Rise> rise;
        private Map<String,Tips> tips;

        public Map<String, ForecastHour> getForecastHour() {
            return forecastHour;
        }

        public void setForecastHour(Map<String, ForecastHour> forecastHour) {
            this.forecastHour = forecastHour;
        }

        public Air getAir() {
            return air;
        }

        public void setAir(Air air) {
            this.air = air;
        }

        public Indexs getIndex() {
            return index;
        }

        public void setIndex(Indexs index) {
            this.index = index;
        }

        public Map<String, ForecastDay> getForecastDay() {
            return forecastDay;
        }

        public void setForecastDay(Map<String, ForecastDay> forecastDay) {
            this.forecastDay = forecastDay;
        }

        public Observe getObserve() {
            return observe;
        }

        public void setObserve(Observe observe) {
            this.observe = observe;
        }

        public Map<String, Rise> getRise() {
            return rise;
        }

        public void setRise(Map<String, Rise> rise) {
            this.rise = rise;
        }

        public Map<String, Tips> getTips() {
            return tips;
        }

        public void setTips(Map<String, Tips> tips) {
            this.tips = tips;
        }

        @Override
        public String toString() {
            return "{" +
                    "\"forecastHour\":\"" + forecastHour + "\"" +
                    ", \"air\":\"" + air + "\"" +
                    ", \"forecastDay\":\"" + forecastDay + "\"" +
                    ", \"index\":\"" + index + "\"" +
                    ", \"observe\":\"" + observe + "\"" +
                    ", \"rise\":\"" + rise + "\"" +
                    ", \"tips\":\"" + tips + "\"" +
                    "}";
        }
    }
    public class ForecastHour implements Serializable{
        private String degree;
        @SerializedName("update_time")
        private String updateTime;
        private String weather;
        @SerializedName("weather_code")
        private String weatherCode;
        @SerializedName("weather_short")
        private String weatherShort;
        @SerializedName("wind_direction")
        private String windDirction;
        @SerializedName("wind_power")
        private String windPower;

        public String getDegree() {
            return degree;
        }

        public void setDegree(String degree) {
            this.degree = degree;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getWeatherCode() {
            return weatherCode;
        }

        public void setWeatherCode(String weatherCode) {
            this.weatherCode = weatherCode;
        }

        public String getWeatherShort() {
            return weatherShort;
        }

        public void setWeatherShort(String weatherShort) {
            this.weatherShort = weatherShort;
        }

        public String getWindDirction() {
            return windDirction;
        }

        public void setWindDirction(String windDirction) {
            this.windDirction = windDirction;
        }

        public String getWindPower() {
            return windPower;
        }

        public void setWindPower(String windPower) {
            this.windPower = windPower;
        }

        @Override
        public String toString() {
            return "{" +
                    "\"degree\":\"" + degree + "\"" +
                    ", \"updateTime\":\"" + updateTime + "\"" +
                    ", \"weather\":\"" + weather + "\"" +
                    ", \"weatherCode\":\"" + weatherCode + "\"" +
                    ", \"weatherShort\":\"" + weatherShort + "\"" +
                    ", \"windDirction\":\"" + windDirction + "\"" +
                    ", \"windPower\":\"" + windPower + "\"" +
                    "}";
        }
    }

    public class Air implements Serializable{
        private int aqi;
        @SerializedName("aqi_level")
        private int aqiLevel;
        @SerializedName("aqi_name")
        private String aqiName;
        private String co;
        private String no2;
        private String o3;
        private String pm10;
        @SerializedName("pm2.5")
        private String pm25;
        private String so2;
        @SerializedName("update_time")
        private String updateTime;

        public int getAqi() {
            return aqi;
        }

        public void setAqi(int aqi) {
            this.aqi = aqi;
        }

        public int getAqiLevel() {
            return aqiLevel;
        }

        public void setAqiLevel(int aqiLevel) {
            this.aqiLevel = aqiLevel;
        }

        public String getAqiName() {
            return aqiName;
        }

        public void setAqiName(String aqiName) {
            this.aqiName = aqiName;
        }

        public String getCo() {
            return co;
        }

        public void setCo(String co) {
            this.co = co;
        }

        public String getNo2() {
            return no2;
        }

        public void setNo2(String no2) {
            this.no2 = no2;
        }

        public String getO3() {
            return o3;
        }

        public void setO3(String o3) {
            this.o3 = o3;
        }

        public String getPm10() {
            return pm10;
        }

        public void setPm10(String pm10) {
            this.pm10 = pm10;
        }

        public String getPm25() {
            return pm25;
        }

        public void setPm25(String pm25) {
            this.pm25 = pm25;
        }

        public String getSo2() {
            return so2;
        }

        public void setSo2(String so2) {
            this.so2 = so2;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        @Override
        public String toString() {
            return "{" +
                    "\"aqi\":\"" + aqi + "\"" +
                    ", \"aqiLevel\":\"" + aqiLevel + "\"" +
                    ", \"aqiName\":\"" + aqiName + "\"" +
                    ", \"co\":\"" + co + "\"" +
                    ", \"no2\":\"" + no2 + "\"" +
                    ", \"o3\":\"" + o3 + "\"" +
                    ", \"pm10\":\"" + pm10 + "\"" +
                    ", \"pm25\":\"" + pm25 + "\"" +
                    ", \"so2\":\"" + so2 + "\"" +
                    ", \"updateTime\":\"" + updateTime + "\"" +
                    "}";
        }
    }

    public class ForecastDay implements Serializable{
        @SerializedName("day_weather")
        private String dayWeather;
        @SerializedName("day_weather_code")
        private String dayWeatherCode;
        @SerializedName("day_weather_short")
        private String dayWeatherShort;
        @SerializedName("day_wind_direction")
        private String dayWindDirection;
        @SerializedName("day_wind_direction_code")
        private String dayWindDirectionCode;
        @SerializedName("day_wind_power")
        private String dayWindPower;
        @SerializedName("day_wind_power_code")
        private String dayWindPowerCode;
        @SerializedName("max_degree")
        private String maxDegree;
        @SerializedName("min_degree")
        private String minDegree;
        @SerializedName("night_weather")
        private String nightWeather;
        @SerializedName("night_weather_code")
        private String nightWeatherCode;
        @SerializedName("night_weather_short")
        private String nightWeatherShort;
        @SerializedName("night_wind_direction")
        private String nightWindDirection;
        @SerializedName("night_wind_direction_code")
        private String nightWindDirectionCode;
        @SerializedName("night_wind_power")
        private String nightWindPower;
        @SerializedName("night_wind_power_code")
        private String nightWindPowerCode;
        @SerializedName("time")
        private String time;

        public String getDayWeather() {
            return dayWeather;
        }

        public void setDayWeather(String dayWeather) {
            this.dayWeather = dayWeather;
        }

        public String getDayWeatherCode() {
            return dayWeatherCode;
        }

        public void setDayWeatherCode(String dayWeatherCode) {
            this.dayWeatherCode = dayWeatherCode;
        }

        public String getDayWeatherShort() {
            return dayWeatherShort;
        }

        public void setDayWeatherShort(String dayWeatherShort) {
            this.dayWeatherShort = dayWeatherShort;
        }

        public String getDayWindDirection() {
            return dayWindDirection;
        }

        public void setDayWindDirection(String dayWindDirection) {
            this.dayWindDirection = dayWindDirection;
        }

        public String getDayWindDirectionCode() {
            return dayWindDirectionCode;
        }

        public void setDayWindDirectionCode(String dayWindDirectionCode) {
            this.dayWindDirectionCode = dayWindDirectionCode;
        }

        public String getDayWindPower() {
            return dayWindPower;
        }

        public void setDayWindPower(String dayWindPower) {
            this.dayWindPower = dayWindPower;
        }

        public String getDayWindPowerCode() {
            return dayWindPowerCode;
        }

        public void setDayWindPowerCode(String dayWindPowerCode) {
            this.dayWindPowerCode = dayWindPowerCode;
        }

        public String getMaxDegree() {
            return maxDegree;
        }

        public void setMaxDegree(String maxDegree) {
            this.maxDegree = maxDegree;
        }

        public String getMinDegree() {
            return minDegree;
        }

        public void setMinDegree(String minDegree) {
            this.minDegree = minDegree;
        }

        public String getNightWeather() {
            return nightWeather;
        }

        public void setNightWeather(String nightWeather) {
            this.nightWeather = nightWeather;
        }

        public String getNightWeatherCode() {
            return nightWeatherCode;
        }

        public void setNightWeatherCode(String nightWeatherCode) {
            this.nightWeatherCode = nightWeatherCode;
        }

        public String getNightWeatherShort() {
            return nightWeatherShort;
        }

        public void setNightWeatherShort(String nightWeatherShort) {
            this.nightWeatherShort = nightWeatherShort;
        }

        public String getNightWindDirection() {
            return nightWindDirection;
        }

        public void setNightWindDirection(String nightWindDirection) {
            this.nightWindDirection = nightWindDirection;
        }

        public String getNightWindDirectionCode() {
            return nightWindDirectionCode;
        }

        public void setNightWindDirectionCode(String nightWindDirectionCode) {
            this.nightWindDirectionCode = nightWindDirectionCode;
        }

        public String getNightWindPower() {
            return nightWindPower;
        }

        public void setNightWindPower(String nightWindPower) {
            this.nightWindPower = nightWindPower;
        }

        public String getNightWindPowerCode() {
            return nightWindPowerCode;
        }

        public void setNightWindPowerCode(String nightWindPowerCode) {
            this.nightWindPowerCode = nightWindPowerCode;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "ForecastDay{" +
                    "dayWeather='" + dayWeather + '\'' +
                    ", dayWeatherCode='" + dayWeatherCode + '\'' +
                    ", dayWeatherShort='" + dayWeatherShort + '\'' +
                    ", dayWindDirection='" + dayWindDirection + '\'' +
                    ", dayWindDirectionCode='" + dayWindDirectionCode + '\'' +
                    ", dayWindPower='" + dayWindPower + '\'' +
                    ", dayWindPowerCode='" + dayWindPowerCode + '\'' +
                    ", maxDegree='" + maxDegree + '\'' +
                    ", minDegree='" + minDegree + '\'' +
                    ", nightWeather='" + nightWeather + '\'' +
                    ", nightWeatherCode='" + nightWeatherCode + '\'' +
                    ", nightWeatherShort='" + nightWeatherShort + '\'' +
                    ", nightWindDirection='" + nightWindDirection + '\'' +
                    ", nightWindDirectionCode='" + nightWindDirectionCode + '\'' +
                    ", nightWindPower='" + nightWindPower + '\'' +
                    ", nightWindPowerCode='" + nightWindPowerCode + '\'' +
                    ", time='" + time + '\'' +
                    '}';
        }
    }

    public class Indexs implements Serializable{
        //空气指数
        private Index airconditioner;
        //洗车指数
        private Index carwash;
        //穿衣指数
        private Index clothes;
        //晾晒指数
        private Index drying;
        //运动指数
        private Index sports;
        //旅游指数
        private Index tourism;
        //紫外线指数
        private Index ultraviolet;

        public Index getAirconditioner() {
            return airconditioner;
        }

        public void setAirconditioner(Index airconditioner) {
            this.airconditioner = airconditioner;
        }

        public Index getCarwash() {
            return carwash;
        }

        public void setCarwash(Index carwash) {
            this.carwash = carwash;
        }

        public Index getClothes() {
            return clothes;
        }

        public void setClothes(Index clothes) {
            this.clothes = clothes;
        }

        public Index getDrying() {
            return drying;
        }

        public void setDrying(Index drying) {
            this.drying = drying;
        }

        public Index getSports() {
            return sports;
        }

        public void setSports(Index sports) {
            this.sports = sports;
        }

        public Index getTourism() {
            return tourism;
        }

        public void setTourism(Index tourism) {
            this.tourism = tourism;
        }

        public Index getUltraviolet() {
            return ultraviolet;
        }

        public void setUltraviolet(Index ultraviolet) {
            this.ultraviolet = ultraviolet;
        }

        @Override
        public String toString() {
            return "{" +
                    "\"airconditioner\":\"" + airconditioner + "\"" +
                    ", \"carwash\":\"" + carwash + "\"" +
                    ", \"clothes\":\"" + clothes + "\"" +
                    ", \"drying\":\"" + drying + "\"" +
                    ", \"sports\":\"" + sports + "\"" +
                    ", \"tourism\":\"" + tourism + "\"" +
                    ", \"ultraviolet\":\"" + ultraviolet + "\"" +
                    "}";
        }
    }

    public class Index implements Serializable{
        private String detail;
        private String info;
        private String name;

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "{" +
                    "\"detail\":\"" + detail + "\"" +
                    ", \"info\":\"" + info + "\"" +
                    ", \"name\":\"" + name + "\"" +
                    "}";
        }
    }

    public class Observe implements Serializable{
        private String degree;
        private String humidity;
        private String precipitation;
        private String pressure;
        @SerializedName("update_time")
        private String updateTime;
        private String weather;
        @SerializedName("weather_code")
        private String weatherCode;
        @SerializedName("weather_short")
        private String weatherShort;
        @SerializedName("wind_direction")
        private String windDirection;
        @SerializedName("wind_power")
        private String windPower;

        public String getDegree() {
            return degree;
        }

        public void setDegree(String degree) {
            this.degree = degree;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getPrecipitation() {
            return precipitation;
        }

        public void setPrecipitation(String precipitation) {
            this.precipitation = precipitation;
        }

        public String getPressure() {
            return pressure;
        }

        public void setPressure(String pressure) {
            this.pressure = pressure;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getWeatherCode() {
            return weatherCode;
        }

        public void setWeatherCode(String weatherCode) {
            this.weatherCode = weatherCode;
        }

        public String getWeatherShort() {
            return weatherShort;
        }

        public void setWeatherShort(String weatherShort) {
            this.weatherShort = weatherShort;
        }

        public String getWindDirection() {
            return windDirection;
        }

        public void setWindDirection(String windDirection) {
            this.windDirection = windDirection;
        }

        public String getWindPower() {
            return windPower;
        }

        public void setWindPower(String windPower) {
            this.windPower = windPower;
        }

        @Override
        public String toString() {
            return "{" +
                    "\"degree\":\"" + degree + "\"" +
                    ", \"humidity\":\"" + humidity + "\"" +
                    ", \"precipitation\":\"" + precipitation + "\"" +
                    ", \"pressure\":\"" + pressure + "\"" +
                    ", \"updateTime\":\"" + updateTime + "\"" +
                    ", \"weather\":\"" + weather + "\"" +
                    ", \"weatherCode\":\"" + weatherCode + "\"" +
                    ", \"weatherShort\":\"" + weatherShort + "\"" +
                    ", \"windDirection\":\"" + windDirection + "\"" +
                    ", \"windPower\":\"" + windPower + "\"" +
                    "}";
        }
    }

    public class Rise implements Serializable{
        @SerializedName("sunrise")
        private String sunRise;
        @SerializedName("sunset")
        private String sunSet;
        @SerializedName("time")
        private String time;

        public String getSunRise() {
            return sunRise;
        }

        public void setSunRise(String sunRise) {
            this.sunRise = sunRise;
        }

        public String getSunSet() {
            return sunSet;
        }

        public void setSunSet(String sunSet) {
            this.sunSet = sunSet;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "{" +
                    "\"sunRise\":\"" + sunRise + "\"" +
                    ", \"sunSet\":\"" + sunSet + "\"" +
                    ", \"time\":\"" + time + "\"" +
                    "}";
        }
    }

    public class Tips implements Serializable{
        @SerializedName("0")
        private String tip0;
        @SerializedName("1")
        private String tip1;

        public String getTip0() {
            return tip0;
        }

        public void setTip0(String tip0) {
            this.tip0 = tip0;
        }

        public String getTip1() {
            return tip1;
        }

        public void setTip1(String tip1) {
            this.tip1 = tip1;
        }
    }
}