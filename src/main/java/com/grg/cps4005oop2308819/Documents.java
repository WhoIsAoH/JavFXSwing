package com.grg.cps4005oop2308819;

public class Documents {

  private int document_id;
  private Case case_id;
  private String document_name;
  private String document_type;
  private String document_path;

  public Documents(int document_id, Case case_id, String document_name, String document_type, String document_path) {
    this.document_id = document_id;
    this.case_id = case_id;
    this.document_name = document_name;
    this.document_type = document_type;
    this.document_path = document_path;
  }

  public int getDocument_id() {
    return document_id;
  }

  public void setDocument_id(int document_id) {
    this.document_id = document_id;
  }

  public String getDocument_name() {
    return document_name;
  }

  public void setDocument_name(String document_name) {
    this.document_name = document_name;
  }

  public String getDocument_type() {
    return document_type;
  }

  public void setDocument_type(String document_type) {
    this.document_type = document_type;
  }

  public String getDocument_path() {
    return document_path;
  }

  public void setDocument_path(String document_path) {
    this.document_path = document_path;
  }

  public Case getCase_id() {
    return case_id;
  }

  public void setCase_id(Case case_id) {
    this.case_id = case_id;
  }

}
