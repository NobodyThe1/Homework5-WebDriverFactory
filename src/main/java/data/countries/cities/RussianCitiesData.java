package data.countries.cities;

import data.countries.CountryData;
import data.countries.ICityData;

public enum RussianCitiesData implements ICityData {
    MOSCOW("Москва", CountryData.RUSSIA);

    private String name;
    private CountryData countryData;

    RussianCitiesData (String name, CountryData countryData) {
        this.name = name;
        this.countryData = countryData;
    }

    public String getName() {
        return name;
    }

    public CountryData getCountryData() {
        return countryData;
    }
}
