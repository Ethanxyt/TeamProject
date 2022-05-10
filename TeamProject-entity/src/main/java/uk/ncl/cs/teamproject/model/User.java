package uk.ncl.cs.teamproject.model;

import javax.persistence.*;

@Entity
@javax.persistence.Table(name = "T_USER")
public class User {
    @Id
    @Column(name = "C_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "C_NAME", length = 32)
    private String name;
    @Column(name = "C_PHONE_NUMBER", length = 32)
    private String phoneNumber;
    @Column(name = "C_EMAIL", length = 32)
    private String email;
    @Column(name = "C_PASSWORD", length = 32)
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setE_mail(String email) {
        this.email = email;
    }
}
