package com.elsevier.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ParkingSlotBooking {
    String vehNo;
    String slotNo; 
    int durationInHrs;
    long startTimestampInEpochMillis;
    long endTimestampInEpochMillis;
    int slotBookingId;

    public ParkingSlotBooking(String vehNo, String slotNo, int durationInHrs, long startTimestampInEpochMillis, int slotBookingId) {
        this.vehNo = vehNo;
        this.slotNo = slotNo;
        this.durationInHrs = durationInHrs;
        this.startTimestampInEpochMillis = startTimestampInEpochMillis;
        this.slotBookingId = slotBookingId;
    }

    public ParkingSlotBooking(String vehNo, String slotNo, int durationInHrs, long startTimestampInEpochMillis, long endTimestampInEpochMillis, int slotBookingId) {
        this.vehNo = vehNo;
        this.slotNo = slotNo;
        this.durationInHrs = durationInHrs;
        this.startTimestampInEpochMillis = startTimestampInEpochMillis;
        this.endTimestampInEpochMillis = endTimestampInEpochMillis;
        this.slotBookingId = slotBookingId;
    }

    public String getVehNo() {
        return vehNo;
    }

    public void setVehNo(String vehNo) {
        this.vehNo = vehNo;
    }

    public String getSlotNo() {
        return slotNo;
    }

    public void setSlotNo(String slotNo) {
        this.slotNo = slotNo;
    }

    public int getDurationInHrs() {
        return durationInHrs;
    }

    public void setDurationInHrs(int durationInHrs) {
        this.durationInHrs = durationInHrs;
    }

    public long getStartTimestampInEpochMillis() {
        return startTimestampInEpochMillis;
    }

    public void setStartTimestampInEpochMillis(long startTimestampInEpochMillis) {
        this.startTimestampInEpochMillis = startTimestampInEpochMillis;
    }

    public long getEndTimestampInEpochMillis() {
        return endTimestampInEpochMillis;
    }

    public void setEndTimestampInEpochMillis(long endTimestampInEpochMillis) {
        this.endTimestampInEpochMillis = endTimestampInEpochMillis;
    }

    public int getSlotBookingId() {
        return slotBookingId;
    }

    public void setSlotBookingId(int slotBookingId) {
        this.slotBookingId = slotBookingId;
    }

    @Override
    public String toString() {
        /*Date dtNow = new Date(currentTimestampInEpochMillis);
        System.out.println(dtNow);*/

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"); 
        LocalDateTime ldtStart = Instant.ofEpochMilli(startTimestampInEpochMillis).atZone(ZoneId.systemDefault()).toLocalDateTime();
        String strLtdNow = dtf.format( ldtStart );
        LocalDateTime ldtEnd = Instant.ofEpochMilli(endTimestampInEpochMillis).atZone(ZoneId.systemDefault()).toLocalDateTime();
        String strLtdEnd = dtf.format( ldtEnd );
        
        return "ParkingSlotBooking [vehNo=" + vehNo + ", slotNo=" + slotNo + ", durationInHrs=" + durationInHrs
                + ", startTimestampInEpochMillis=" + startTimestampInEpochMillis 
                + ", startTimestampInString=" + strLtdNow 
                + ", endTimestampInEpochMillis=" + endTimestampInEpochMillis 
                + ", endTimestampInString=" + strLtdEnd 
                + ", slotBookingId=" + slotBookingId + "]";
    }
}
