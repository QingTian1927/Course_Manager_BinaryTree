package org.example.model.types;

import org.example.io.DataWriter;
import java.time.LocalDate;

public class Register {
    private String ccode;  // Course Code
    private String scode;  // Student Code
    private LocalDate bdate;  // Registration Date;
    private double mark;
    private int state;

    private boolean isInvalidMark(double mark) {
        return !(0 <= mark) || !(mark <= 10);
    }

    public Register() {
    }

    public Register(String ccode, String scode, LocalDate bdate, double mark, int state) {
        this.ccode = ccode;
        this.scode = scode;
        this.bdate = bdate;

        if (isInvalidMark(mark)) {
            throw new IllegalArgumentException();
        }
        this.mark = mark;

        this.state = (mark >= 5) ? 1 : 0;
    }

    public Register(Register register) {
        this(register.ccode, register.scode, register.bdate, register.mark, register.state);
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

    public LocalDate getBdate() {
        return bdate;
    }

    public void setBdate(LocalDate bdate) {
        this.bdate = bdate;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        if (isInvalidMark(mark)) {
            throw new IllegalArgumentException();
        }

        this.mark = mark;
        this.state = (mark >= 5) ? 1 : 0;
    }

    public int getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Register{" +
                "ccode='" + ccode + '\'' +
                ", scode='" + scode + '\'' +
                ", bdate=" + bdate +
                ", mark=" + mark +
                ", state=" + state +
                '}';
    }

    public String toDataString() {
        // what the fuck?
        return String.format(
                "%s%s%s%s%s%s%f%s%d",
                this.ccode, DataWriter.PROPERTY_SEPARATOR,
                this.scode, DataWriter.PROPERTY_SEPARATOR,
                this.bdate.toString(), DataWriter.PROPERTY_SEPARATOR,
                this.mark, DataWriter.PROPERTY_SEPARATOR,
                this.state
        );
    }
}
