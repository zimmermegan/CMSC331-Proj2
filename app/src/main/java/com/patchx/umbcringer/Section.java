package com.patchx.umbcringer;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by frank on 12/3/14.
 */
public class Section {
    private String classNumber;
    private String sectionNumber;
    private String subject;
    private String courseNumber;
    private String description;
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;
    private int [] days = new int[7];



    public Section(){

    }

    public int[] getDays() {
        return days;
    }

    public void setDays(int[] days) {


        for (int i = 0; i < days.length; i++) {
            this.days[i] = days[i];
        }
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public String getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(String sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getStartMinute() {
        return startMinute;
    }

    public void setStartMinute(int startMinute) {
        this.startMinute = startMinute;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public int getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(int endMinute) {
        this.endMinute = endMinute;
    }

    public String getTimes(){

        String sh, sm, eh, em, timey, smeridian, emeridian, tdays;

        if (startHour > 12){
            sh = "" + (startHour - 12);
            smeridian = "PM";
            } else if(startHour == 12) {
            sh = "" + 12;
            smeridian = "PM";
        } else {
            sh = "" + startHour;
            smeridian = "AM";
        }

        if (startMinute < 10){
            sm = "0" + startMinute;
        }   else {
            sm = "" + startMinute;
        }

        if (endHour > 12){
            eh = "" + (endHour - 12);
            emeridian = "PM";
        } else if (endHour == 12){
            eh = "" + 12;
            emeridian = "PM";
        } else {
            eh = "" + endHour;
            emeridian = "AM";
        }

        if (endMinute < 10) {
            em = "0" + endMinute;
        } else {
            em = "" + endMinute;
        }

        tdays = "";

        for (int i = 0; i < days.length; i ++) {
            switch (days[i]) {
                case 0:
                    break;

                case 1:
                    tdays = tdays + "Su ";
                    break;

                case 2:
                    tdays = tdays + "Mo ";
                    break;
                case 3:
                    tdays = tdays + "Tu ";
                    break;

                case 4:
                    tdays = tdays + "We ";
                    break;

                case 5:
                    tdays = tdays + "Th ";
                    break;

                case 6:
                    tdays = tdays + "Fr ";
                    break;

                case 7:
                    tdays = tdays + "Sa ";
                    break;
            }

        }

        timey = sh + ":" + sm + smeridian +" - " + eh + ":" + em + emeridian + " " + tdays;


        return timey;


    }

}
