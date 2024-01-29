package com.example.valorant5;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity
public class Habit {

    @PrimaryKey(autoGenerate = true)
    int id;
    String title;
    int frequency;
    @ColumnInfo(name = "start_date")
    LocalDate startDate;
    @ColumnInfo(name = "checked")
    boolean checked;

    @ColumnInfo(name = "days_of_week")
    int daysOfWeek;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
    public int getFrequency() {
        return frequency;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setDaysOfWeek(int daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public boolean isDaySelected(int day) {
        return ((daysOfWeek >> day) & 1) == 1;
    }

    public void setDaySelected(int day, boolean selected) {
        if (selected) {
            daysOfWeek |= (1 << day);
        } else {
            daysOfWeek &= ~(1 << day);
        }
    }
    public Habit(int id, String title, int frequency, LocalDate startDate) {
        this.id = id;
        this.title = title;
        this.frequency = frequency;
        this.startDate = startDate;
    }

    @Ignore
    public Habit(String title) {
        this.title = title;
    }
}