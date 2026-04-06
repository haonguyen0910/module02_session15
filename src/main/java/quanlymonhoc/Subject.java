package quanlymonhoc;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Subject {
    private String code;
    private String name;
    private int credits;
    private Date startDate;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public Subject(String code, String name, int credits, Date startDate) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.startDate = startDate;
    }

    // Getters
    public String getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    public int getCredits() {
        return credits;
    }
    public Date getStartDate() {
        return startDate;
    }

    // Setters
    public void setCode(String code) {
        this.code = code;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCredits(int credits) {
        this.credits = credits;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return String.format("Mã: %s | Tên: %s | Tín chỉ: %d | Ngày bắt đầu: %s",
                code, name, credits, DATE_FORMAT.format(startDate));
    }
}