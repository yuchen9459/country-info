package com.acc.restcountries.entity;

import java.util.Set;

public class Country {

    private Name name;
    private String cca3;
    private double area;
    private int population;
    private Set<String> borders;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getCca3() {
        return cca3;
    }

    public void setCca3(String cca3) {
        this.cca3 = cca3;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public Set<String> getBorders() {
        return borders;
    }

    public void setBorders(Set<String> borders) {
        this.borders = borders;
    }
}
