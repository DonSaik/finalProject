/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.finalproject.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.maps.model.PlaceType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lt.viko.eif.finalproject.resources.Link;

/**
 * User entity class.
 * @author donatas
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nick;
    private double lat;
    private double lng;
    private double mass;
    private double height;
    private BigDecimal bmi;
    private String category;
    @JsonInclude(Include.NON_NULL)
    private List<Log> logList;
    
    private List <PlaceType> activities = new ArrayList<>();
    
    private List<Link> links = new ArrayList<>();

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public List<Link> getLinks() {
        return links;
    }
    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, double lat, double lng, double mass, double height, BigDecimal bmi, String category) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.mass = mass;
        this.height = height;
        this.bmi = bmi;
        this.category = category;
    }
    public User(Integer id, String nick, double lat, double lng, double mass, double height, BigDecimal bmi, String category) {
        this.id = id;
        this.nick = nick;
        this.lat = lat;
        this.lng = lng;
        this.mass = mass;
        this.height = height;
        this.bmi = bmi;
        this.category = category;
    }

    public User(int aInt, String string, double aDouble, double aDouble0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public BigDecimal getBmi() {
        return bmi;
    }

    public void setBmi(BigDecimal bmi) {
        this.bmi = bmi;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Log> getLogList() {
        return logList;
    }

    public void setLogList(List<Log> logList) {
        this.logList = logList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lt.viko.eif.finalproject.api.User[ id=" + id + " ]";
    }
    
}
