/**************************************************************
 * File:    Section.java
 * Project: CMSC 331 - Project 2
 * Author : Frank Zastawnik
 * Date   : 10-December-2014
 * Section: Lecture-01
 * E-mail:  frankz2@umbc.edu
 *
 * This holds the information about each section.
 *************************************************************/
package com.patchx.umbcringer;




public class Section {
    private int classNumber;            // The registrar's number for the section eg 5423
    private String sectionNumber;       // The section of the class eg. 03
    private String subject;             // The subject. eg CMSC
    private String courseNumber;        // The course number eg 201
    private String description;         // The description of the course.
    private int startHour;              // The starting hour (24 hour format)
    private int startMinute;            // The starting minute
    private int endHour;                // The ending hour (24 hour format)
    private int endMinute;              // The ending minute
    private int [] days = new int[7];   // Days of the week the class meets.  1= Sunday




    public Section(){

    }

    public int[] getDays() {
        return days;
    }

    public void setDays(int[] days) {

        // Do a deep copy of the array
        for (int i = 0; i < days.length; i++) {
            this.days[i] = days[i];
        }
    }

    public int getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(int classNumber) {
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


    // This returns a formated string specifically for the buttons to display the
    // Time and day information for a section.
    public String getTimes(){

        String sh, sm, eh, em, timey, smeridian, emeridian, tdays;

        // Deal with 24hour to 12hour conversions
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
        //Pad with a leading 0
        if (startMinute < 10){
            sm = "0" + startMinute;
        }   else {
            sm = "" + startMinute;
        }

        // Deal with 24hour to 12hour conversions
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
        //Pad with a leading 0
        if (endMinute < 10) {
            em = "0" + endMinute;
        } else {
            em = "" + endMinute;
        }

        tdays = "";

        // Get short versions of the days
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

        // Put it all together
        timey = sh + ":" + sm + smeridian +" - " + eh + ":" + em + emeridian + " " + tdays;


        return timey;


    }

    // These generate the AlarmManager IDs used so they are predictable if we need to cancel or
    // update.
    public int getStartID(int day){
        return (day * 100000 + 10000 +classNumber);
    }

    public int getEndID(int day){
        return (day * 100000 + classNumber);
    }

}
