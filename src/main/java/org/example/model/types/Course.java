package org.example.model.types;

import org.example.io.DataWriter;
import org.example.util.Validation;

public class Course {
    private String ccode;  // Course Code
    private String scode;  // Subject Code
    private String sname;  // Subject Name
    private String semester;
    private String year;
    private int seats;
    private int registered;
    private double price;

    private boolean isInvalidRegistered(int seats, int registered) {
        return registered > seats;
    }

    public Course() {
    }

    public Course(String ccode, String scode, String sname, String semester, String year, int seats, int registered, double price) {
        this.ccode = ccode;
        this.scode = scode;
        this.sname = sname;
        this.semester = semester;
        this.year = year;

        if (isInvalidRegistered(seats, registered)) {
            throw new IllegalArgumentException();
        }

        if (!Validation.isNonNegative(price)) {
            throw new IllegalArgumentException();
        }

        this.price = price;
        this.seats = seats;
        this.registered = registered;
    }

    public Course(Course course) {
        this(
                course.ccode, course.scode, course.sname, course.semester,
                course.year, course.seats, course.registered, course.price
        );
    }

    public String getCcode() {
        return ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public String getScode() {
        return scode;
    }

    public void setScode(String scode) {
        this.scode = scode;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        if (Validation.isNonNegative(seats)) {
            throw new IllegalArgumentException();
        }
        this.seats = seats;
    }

    public int getRegistered() {
        return registered;
    }

    public void setRegistered(int registered) {
        if (!Validation.isNonNegative(registered)) {
            throw new IllegalArgumentException();
        }
        if (isInvalidRegistered(this.seats, registered)) {
            throw new IllegalArgumentException();
        }
        this.registered = registered;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (!Validation.isNonNegative(price)) {
            throw new IllegalArgumentException();
        }
        this.price = price;
    }

    public void updateSeatAndRegister(int seatChange, int courseChange) {
        int newSeat = this.seats + seatChange;
        int newRegistration = this.registered + courseChange;

        if (isInvalidRegistered(newSeat, newRegistration)) {
            System.out.println("Registered students cannot exceed the available seats.");
        }

        this.seats = newSeat;
        this.registered = newRegistration;
    }

    @Override
    public String toString() {
        return "Course{" +
                "ccode='" + ccode + '\'' +
                ", scode='" + scode + '\'' +
                ", sname='" + sname + '\'' +
                ", semester='" + semester + '\'' +
                ", year='" + year + '\'' +
                ", seats=" + seats +
                ", registered=" + registered +
                ", price=" + price +
                '}';
    }

    public String toDataString() {
        // T ko hối hận j hết

        return String.format(
                "%s%s%s%s%s%s%s%s%s%s%d%s%d%s%f",
                this.ccode, DataWriter.PROPERTY_SEPARATOR,
                this.scode, DataWriter.PROPERTY_SEPARATOR,
                this.sname, DataWriter.PROPERTY_SEPARATOR,
                this.semester, DataWriter.PROPERTY_SEPARATOR,
                this.year, DataWriter.PROPERTY_SEPARATOR,
                this.seats, DataWriter.PROPERTY_SEPARATOR,
                this.registered, DataWriter.PROPERTY_SEPARATOR,
                this.price
        );
    }

    public void displayCourseInfo() {
        System.out.println("Course Code: " + ccode);
        System.out.println("Subject Code: " + scode);
        System.out.println("Subject Name: " + sname);
        System.out.println("Semester: " + semester);
        System.out.println("Year: " + year);
        System.out.println("Seats: " + seats);
        System.out.println("Registered: " + registered);
        System.out.println("Price: " + price);
    }
}
