package com.uh.fuelratecheck;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
@Entity
public class ClientEntity {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  private String username;

  private String password;

 //private Integer client_info_entity_id; //this is the foreign key that maps to the primary key of the clientInfoEntity

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "client_info_entity_id", referencedColumnName = "id")
  private ClientInfoEntity clientInfoEntity;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return username;
  }

  public void setName(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  
}