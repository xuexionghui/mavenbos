package cn.itcast.bos.domain.bc;

import java.util.HashSet;
import java.util.Set;


/**
 * Staff entity. @author MyEclipse Persistence Tools
 */

public class Staff  implements java.io.Serializable {


    // Fields    

     private String id;
     private Standard standard;
     private String name;
     private String telephone;
     private String haspda;
     private String deltag;
     private String station;
     private Set decidedZones = new HashSet(0);


    // Constructors

    /** default constructor */
    public Staff() {
    }

	/** minimal constructor */
    public Staff(String id) {
        this.id = id;
    }
    
    /** full constructor */
    public Staff(String id, Standard standard, String name, String telephone, String haspda, String deltag, String station, Set decidedZones) {
        this.id = id;
        this.standard = standard;
        this.name = name;
        this.telephone = telephone;
        this.haspda = haspda;
        this.deltag = deltag;
        this.station = station;
        this.decidedZones = decidedZones;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public Standard getStandard() {
        return this.standard;
    }
    
    public void setStandard(Standard standard) {
        this.standard = standard;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return this.telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getHaspda() {
        return this.haspda;
    }
    
    public void setHaspda(String haspda) {
        this.haspda = haspda;
    }

    public String getDeltag() {
        return this.deltag;
    }
    
    public void setDeltag(String deltag) {
        this.deltag = deltag;
    }

    public String getStation() {
        return this.station;
    }
    
    public void setStation(String station) {
        this.station = station;
    }

    public Set getDecidedZones() {
        return this.decidedZones;
    }
    
    public void setDecidedZones(Set decidedZones) {
        this.decidedZones = decidedZones;
    }
   








}