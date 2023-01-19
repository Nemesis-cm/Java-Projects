package com.sf.superherosightings.dto;

import javax.xml.transform.Source;
import java.math.BigDecimal;
import java.util.Objects;

public class Location implements Source {
    private int id;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private int zip;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public Location(String name, String description, String address, BigDecimal bigDecimal, BigDecimal bigDecimal1) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return id == location.id && zip == location.zip && Double.compare(location.latitude, latitude) == 0 && Double.compare(location.longitude, longitude) == 0 && Objects.equals(address1, location.address1) && Objects.equals(address2, location.address2) && Objects.equals(city, location.city) && Objects.equals(state, location.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address1, address2, city, state, zip, latitude, longitude);
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip=" + zip +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}