package pl.coderslab.dto;

import pl.coderslab.entity.League;

import java.util.ArrayList;
import java.util.List;

public class CountryDto {

    private int id;
    private String name;
    private List<League> leagues = new ArrayList<>();

    public CountryDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<League> getLeagues() {
        return leagues;
    }

    public void setLeagues(List<League> leagues) {
        this.leagues = leagues;
    }
}
