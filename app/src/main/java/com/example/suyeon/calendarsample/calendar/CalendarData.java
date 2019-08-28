package com.example.suyeon.calendarsample.calendar;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class CalendarData implements Parcelable {

    public CalendarData(ArrayList<Integer> dayList, int sday, int eday, int year, int month) {
        this.dayList = dayList;
        this.sday = sday;
        this.eday = eday;
        this.year = year;
        this.month = month;
    }

    private ArrayList<Integer> dayList;
    private int sday;
    private int eday;
    private int year;
    private int month;

    protected CalendarData(Parcel in) {
        sday = in.readInt();
        eday = in.readInt();
        year = in.readInt();
        month = in.readInt();
    }

    public static final Creator<CalendarData> CREATOR = new Creator<CalendarData>() {
        @Override
        public CalendarData createFromParcel(Parcel in) {
            return new CalendarData(in);
        }

        @Override
        public CalendarData[] newArray(int size) {
            return new CalendarData[size];
        }
    };

    public ArrayList<Integer> getDayList() {
        return dayList;
    }

    public void setDayList(ArrayList<Integer> dayList) {
        this.dayList = dayList;
    }

    public int getSday() {
        return sday;
    }

    public void setSday(int sday) {
        this.sday = sday;
    }

    public int getEday() {
        return eday;
    }

    public void setEday(int eday) {
        this.eday = eday;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.dayList);
        parcel.writeInt(this.sday);
        parcel.writeInt(this.eday);
        parcel.writeInt(this.year);
        parcel.writeInt(this.month);
    }
}
