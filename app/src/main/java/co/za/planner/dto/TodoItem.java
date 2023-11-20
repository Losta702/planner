package co.za.planner.dto;

import java.util.Date;

public class TodoItem {

    private int id;
    private Date date;
    private String mustDo;
    private String appointment;
    private String toDoList;
    private String note;
    private boolean isChecked;


    public TodoItem(int id, Date date, String mustDo, String appointment, String toDoList, String note, boolean isChecked) {
        this.id=id;
        this.date = date;
        this.mustDo = mustDo;
        this.appointment = appointment;
        this.toDoList = toDoList;
        this.note = note;
        this.isChecked = isChecked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMustDo() {
        return mustDo;
    }

    public void setMustDo(String mustDo) {
        this.mustDo = mustDo;
    }

    public String getAppointment() {
        return appointment;
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }

    public String getToDoList() {
        return toDoList;
    }

    public void setToDoList(String toDoList) {
        this.toDoList = toDoList;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}

