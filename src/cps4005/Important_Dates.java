/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cps4005;

import java.sql.Date;

/**
 *
 * @author ajayp
 */
public class Important_Dates {

    private int date_id;
    private Case case_id;
    private Date event_date;
    private String event_description;

    public Important_Dates(int date_id, Case case_id, Date event_date, String event_description) {
        this.date_id = date_id;
        this.case_id = case_id;
        this.event_date = event_date;
        this.event_description = event_description;
    }

    public int getDate_id() {
        return date_id;
    }

    public void setDate_id(int date_id) {
        this.date_id = date_id;
    }

    public Case getCase_id() {
        return case_id;
    }

    public void setCase_id(Case case_id) {
        this.case_id = case_id;
    }

    public Date getEvent_date() {
        return event_date;
    }

    public void setEvent_date(Date event_date) {
        this.event_date = event_date;
    }

    public String getEvent_description() {
        return event_description;
    }

    public void setEvent_description(String event_description) {
        this.event_description = event_description;
    }

}
