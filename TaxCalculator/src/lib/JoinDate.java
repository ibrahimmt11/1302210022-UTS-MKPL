package lib;

import java.time.Month;

public class JoinDate {

    private int year;
    private Month month;
    private int day;

    public JoinDate(int year, Month month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public JoinDate() {
        // Optional: Provide a default constructor with current date (use LocalDate.now())
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}

