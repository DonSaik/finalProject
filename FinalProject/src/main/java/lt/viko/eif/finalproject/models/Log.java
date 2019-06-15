/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.finalproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lt.viko.eif.finalproject.models.User;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.google.maps.model.OpeningHours;

/**
 *
 * @author donatas
 */
@Entity
@Table(name = "Log")
@NamedQueries({
    @NamedQuery(name = "Log.findAll", query = "SELECT l FROM Log l")})
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "City")
    private String city;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Address")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "PlaceType")
    private String placeType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "PlaceName")
    private String placeName;
    @JoinColumn(name = "UserID", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User user;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private OpeningHours openingHours;

    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    public OpeningHours getOpeningHours() {
        return openingHours;
    }
    
    public Log() {
    }

    public Log(Integer id) {
        this.id = id;
    }

    public Log(Integer id, String city, String address, String placeType, String placeName) {
        this.id = id;
        this.city = city;
        this.address = address;
        this.placeType = placeType;
        this.placeName = placeName;
    }
    public Log(Integer id, String city, String address, String placeType, String placeName, User user) {
        this.id = id;
        this.city = city;
        this.address = address;
        this.placeType = placeType;
        this.placeName = placeName;
        this.user = user;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
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
        if (!(object instanceof Log)) {
            return false;
        }
        Log other = (Log) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lt.viko.eif.finalproject.api.Log[ id=" + id + " ]";
    }
    
}
