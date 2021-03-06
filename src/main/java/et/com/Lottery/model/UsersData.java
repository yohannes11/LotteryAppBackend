package et.com.Lottery.model;
// Generated Jan 16, 2020 3:36:11 PM by Hibernate Tools 5.2.11.Final


import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * UsersData generated by hbm2java
 */
@Entity
@Table(name = "usersData"

)
public class UsersData implements java.io.Serializable {


    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public UsersData() {
    }


    public UsersData(long id) {
        this.id = id;
    }

    public UsersData(long id, String firstName, String lastName, String email, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
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


    @Column(name = "firstName")
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    @Column(name = "lastName")
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Column(name = "email")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Column(name = "phoneNumber")
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}


