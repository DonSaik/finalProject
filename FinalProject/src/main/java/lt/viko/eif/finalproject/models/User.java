/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.finalproject.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author donatas
 */
@Entity
@Table(name = "User")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "Nick")
    private String nick;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Lat")
    private double lat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Lng")
    private double lng;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Mass")
    private double mass;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Height")
    private double height;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "BMI")
    private BigDecimal bmi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Category")
    private String category;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Log> logList;

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
