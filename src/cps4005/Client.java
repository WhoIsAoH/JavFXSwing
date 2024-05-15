/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cps4005;

/**
 *
 * @author ajayp
 */
public class Client {
    private int client_id;
    private String client_name;
    private String client_address;
    private String client_phone;
    private String client_email;
  
    public Client(int client_id, String client_name, String client_address, String client_phone, String client_email){
        this.client_id = client_id;
        this.client_name = client_name;
        this.client_address = client_address;
        this.client_phone = client_phone;
        this.client_email = client_email;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_address() {
        return client_address;
    }

    public void setClient_address(String client_address) {
        this.client_address = client_address;
    }

    public String getClient_phone() {
        return client_phone;
    }

    public void setClient_phone(String client_phone) {
        this.client_phone = client_phone;
    }

    public String getClient_email() {
        return client_email;
    }

    public void setClient_email(String client_email) {
        this.client_email = client_email;
    }
}
