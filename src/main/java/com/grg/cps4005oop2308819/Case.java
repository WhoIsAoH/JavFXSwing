package com.grg.cps4005oop2308819;

import java.sql.Date;

public class Case {

  private int case_id;
  private String case_number;
  private String case_title;
  private String case_description;
  private String case_status;
  private Date date_filed;
  private Date date_closed;
  private Client client_id;

  public Case(int case_id, String case_number, String case_title, String case_description, String case_status, Date date_filed, Date date_closed, Client client_id) {
    this.case_id = case_id;
    this.case_number = case_number;
    this.case_title = case_title;
    this.case_description = case_description;
    this.case_status = case_status;
    this.date_filed = date_filed;
    this.date_closed = date_closed;
    this.client_id = client_id;
  }

  public int getCase_id() {
    return case_id;
  }

  public void setCase_id(int case_id) {
    this.case_id = case_id;
  }

  public String getCase_number() {
    return case_number;
  }

  public void setCase_number(String case_number) {
    this.case_number = case_number;
  }

  public String getCase_title() {
    return case_title;
  }

  public void setCase_title(String case_title) {
    this.case_title = case_title;
  }

  public String getCase_description() {
    return case_description;
  }

  public void setCase_description(String case_description) {
    this.case_description = case_description;
  }

  public String getCase_status() {
    return case_status;
  }

  public void setCase_status(String case_status) {
    this.case_status = case_status;
  }

  public Date getDate_filed() {
    return date_filed;
  }

  public void setDate_filed(Date date_filed) {
    this.date_filed = date_filed;
  }

  public Date getDate_closed() {
    return date_closed;
  }

  public void setDate_closed(Date date_closed) {
    this.date_closed = date_closed;
  }

  public Client getClient_id() {
    return client_id;
  }

  public void setClient_id(Client client_id) {
    this.client_id = client_id;
  }

}
