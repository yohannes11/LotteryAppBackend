package et.com.Lottery.model;
// Generated Jan 16, 2020 3:36:11 PM by Hibernate Tools 5.2.11.Final


import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * CompanySetting generated by hbm2java
 */
@Entity
@Table(name = "companysetting"

)
public class CompanySetting implements java.io.Serializable {


    private Long id;
    private String name;
    private String value;
    private String additionalValue;
    private Date registeredOn;
    private Date updatedOn;
    private String displayName;
    private String additionalValue2;

    public CompanySetting() {
    }

    public CompanySetting(String name, String value, String additionalValue, Date registeredOn, Date updatedOn, String displayName, String additionalValue2) {
        this.name = name;
        this.value = value;
        this.additionalValue = additionalValue;
        this.registeredOn = registeredOn;
        this.updatedOn = updatedOn;
        this.displayName = displayName;
        this.additionalValue2 = additionalValue2;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Column(name = "name", length = 1024)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Column(name = "value", length = 1024)
    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    @Column(name = "additionalValue", length = 1024)
    public String getAdditionalValue() {
        return this.additionalValue;
    }

    public void setAdditionalValue(String additionalValue) {
        this.additionalValue = additionalValue;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "registeredOn", length = 26)
    public Date getRegisteredOn() {
        return this.registeredOn;
    }

    public void setRegisteredOn(Date registeredOn) {
        this.registeredOn = registeredOn;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updatedOn", length = 26)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }


    @Column(name = "displayName", length = 1024)
    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }


    @Column(name = "additionalValue2", length = 1024)
    public String getAdditionalValue2() {
        return this.additionalValue2;
    }

    public void setAdditionalValue2(String additionalValue2) {
        this.additionalValue2 = additionalValue2;
    }

    @Override
    public String toString() {
        return "CompanySetting{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", additionalValue='" + additionalValue + '\'' +
                ", registeredOn=" + registeredOn +
                ", updatedOn=" + updatedOn +
                ", displayName='" + displayName + '\'' +
                ", additionalValue2='" + additionalValue2 + '\'' +
                '}';
    }
}


