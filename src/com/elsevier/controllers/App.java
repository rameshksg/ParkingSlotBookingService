package com.elsevier.controllers;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.HashMap;

import com.elsevier.model.ParkingSlotBooking;


public class App {

    //this below data goes to database table ParkingSlotBookings in the actual application
    private HashMap<String, ParkingSlotBooking> setOfVehVsParkingSlots = new HashMap<String, ParkingSlotBooking>();
    private HashMap<String, ParkingSlotBooking> setOfParkingSlotsVsVehs = new HashMap<String, ParkingSlotBooking>();
    private ArrayList<ParkingSlotBooking> listOfVehVsParkingSlots = new ArrayList<ParkingSlotBooking>();
    private HashMap<Integer, ParkingSlotBooking> setOfBookingIdVsVehSlots = new HashMap<Integer, ParkingSlotBooking>();
    
    private String[] slots = {"SLOT1","SLOT2","SLOT3","SLOT4","SLOT5"};
    private static int slotBookingId = 1;
    
    private static final int MIN_BOOKIG_DURATION_IN_HR = 1;
    private static final int MAX_BOOKIG_DURATION_IN_HR = 4;


    //@RequestMapping(value = "/allotVehToParkingSlot", method = RequestMethod.PUT)
    public ParkingSlotBooking allotVehToParkingSlot(String vehNo, int dur) throws Exception {
        ParkingSlotBooking slotBookingOutputObj = null;

        if(dur < MIN_BOOKIG_DURATION_IN_HR || dur > MAX_BOOKIG_DURATION_IN_HR){
            System.out.println("Slot can not be booked for the given duration.");
            return null;
        }

        //get all the available slots from DB "SLOTs"
        for(int i=0; i < slots.length; i++){
            //ArrayList<ParkingSlotBooking> listOfVehVsParkingSlotsForGivenSlot = (ArrayList<ParkingSlotBooking>) listOfVehVsParkingSlots.stream().filter( objParkingSlotBookingTmp -> objParkingSlotBookingTmp.getSlotNo().equals(slots[i]) ).collect(Collectors.toList());;
            ParkingSlotBooking objParkingSlotBooking = setOfParkingSlotsVsVehs.get(slots[i]);
            if(objParkingSlotBooking != null) {
                System.out.println(slots[i]+" slot is already booked.");
                continue;
            }else{
                //long startTimestampInEpochMillis = System.currentTimeMillis();  //or //currentTimestamp = Instant.now().toEpochMilli();
                
                LocalDateTime nowDateTime = LocalDateTime.now();
                ZoneId zoneId = ZoneId.systemDefault();
                long startTimestampInEpochMillis = nowDateTime.atZone(zoneId).toInstant().toEpochMilli();
                
                LocalDateTime endDateTime = nowDateTime.plusHours(dur);
                long endTimestampInEpochMillis = endDateTime.atZone(zoneId).toInstant().toEpochMilli();
                
                //assign one of the available slots to veh and create object of ParkingSlotBooking
                int slotBookingIdCurrent = slotBookingId++;
                ParkingSlotBooking objNewParkingSlotBooking = new ParkingSlotBooking(vehNo, slots[i], dur, startTimestampInEpochMillis, endTimestampInEpochMillis, slotBookingIdCurrent);
                System.out.println("New ParkingSlotBooking object is created : "+objNewParkingSlotBooking.toString());
                
                 //insert the ParkingSlotBooking object data into the DB, (in our case hashmap)
                updateRecords(objNewParkingSlotBooking);
                
                System.out.println("Success. "+slots[i]+" slot is allocated for the vehicle "+vehNo+" with BookingId "+slotBookingIdCurrent);
                slotBookingOutputObj = objNewParkingSlotBooking; //slotBookingIdCurrent+"-"+slots[i]+"-"+vehNo;
                break;
            }
        }

        if(slotBookingOutputObj == null) System.out.println("Failure. No slot is allocated for the vehicle "+vehNo);
        
        return slotBookingOutputObj;
    }

    //@RequestMapping(value = "/getParkingSlotInfo?slotBookingId=123", method = RequestMethod.GET)
    public ParkingSlotBooking getParkingSlotInfo(int slotBookingId) throws Exception {
        ParkingSlotBooking slotBookingOutput = null;

        System.out.println("List of slot bookings: ");
        setOfBookingIdVsVehSlots.keySet().forEach(i -> System.out.print("slotBookingId: "+i.intValue()+", "));
        System.out.println("");
        
        //ArrayList<ParkingSlotBooking> listOfVehVsParkingSlotsForGivenSlot = (ArrayList<ParkingSlotBooking>) listOfVehVsParkingSlots.stream().filter( objParkingSlotBookingTmp -> objParkingSlotBookingTmp.getSlotNo().equals(slots[i]) ).collect(Collectors.toList());;
        slotBookingOutput = setOfBookingIdVsVehSlots.get(Integer.valueOf(slotBookingId));
        if(slotBookingOutput != null) {
            System.out.println(slotBookingId+" slotBookingId record found.");
            return slotBookingOutput;
        }else{
            System.out.println(slotBookingId+" slotBookingId record NOT found.");
        }
    
        if(slotBookingOutput == null) System.out.println("Failure. No slot record found for the booking id "+slotBookingId);
        
        return slotBookingOutput;
    }

    //@RequestMapping(value = "/getParkingSlotInfo?vehNo=NY12345", method = RequestMethod.GET)
    public ParkingSlotBooking getParkingSlotInfoByVehNo(String vehNo) throws Exception {
        ParkingSlotBooking slotBookingOutput = null;

        setOfVehVsParkingSlots.keySet().forEach(i -> System.out.println("vehNo: "+i));
        
        //ArrayList<ParkingSlotBooking> listOfVehVsParkingSlotsForGivenSlot = (ArrayList<ParkingSlotBooking>) listOfVehVsParkingSlots.stream().filter( objParkingSlotBookingTmp -> objParkingSlotBookingTmp.getSlotNo().equals(slots[i]) ).collect(Collectors.toList());;
        slotBookingOutput = setOfVehVsParkingSlots.get(vehNo);
        if(slotBookingOutput != null) {
            System.out.println(vehNo+" vehNo record found in parking slots.");
            return slotBookingOutput;
        }else{
            System.out.println(vehNo+" vehNo record NOT found in parking slots.");
        }
    
        if(slotBookingOutput == null) System.out.println("Failure. No slot record found for the vehNo "+vehNo);
        
        return slotBookingOutput;
    }

    //@RequestMapping(value = "/increaseTheSlotDur?slotBookingId=123&additionalDurationInHrs=3", method = RequestMethod.PATCH)
    public ParkingSlotBooking increaseTheSlotDur(int slotBookingId, int additionalDurationInHrs) throws Exception {
        ParkingSlotBooking objParkingSlotBooking = getParkingSlotInfo(slotBookingId);
        if(objParkingSlotBooking == null){
            System.out.println("Failure. No slot record found for the booking id "+slotBookingId);
            return null;
        }
        if(additionalDurationInHrs < MIN_BOOKIG_DURATION_IN_HR || additionalDurationInHrs > MAX_BOOKIG_DURATION_IN_HR){
            System.out.println("Slot can not be booked for the given duration because it is not in between 1hr to 4hr.");
            return null;
        }

        int durationInHrs = objParkingSlotBooking.getDurationInHrs();
        int updatedDurationInHrs = durationInHrs + additionalDurationInHrs;
        objParkingSlotBooking.setDurationInHrs(updatedDurationInHrs);
        
        LocalDateTime ldtEnd = Instant.ofEpochMilli(objParkingSlotBooking.getEndTimestampInEpochMillis()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime ldtEndNew = ldtEnd.plusHours(additionalDurationInHrs);
        long endTimestampInEpochMillis = ldtEndNew.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        objParkingSlotBooking.setEndTimestampInEpochMillis(endTimestampInEpochMillis);

        System.out.println("Added new duration of "+additionalDurationInHrs+"hrs to existing duration of VehicleParkingSlot : "+objParkingSlotBooking.toString());
        
        if( endTimestampInEpochMillis <= LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() ){
            System.out.println("###### Warning: The end time is less than the current time in clock. You may need to add more addtional hours of duration");
        }

        updateRecords(objParkingSlotBooking);
        
        return objParkingSlotBooking;
    }

    //@RequestMapping(value = "/releaseTheSlotByBkngId?slotBookingId=123", method = RequestMethod.DELETE)
    public String releaseTheSlotByBkngId(int slotBookingId) throws Exception {
        ParkingSlotBooking objParkingSlotBooking = getParkingSlotInfo(slotBookingId);
        if(objParkingSlotBooking == null){
            System.out.println("Failure. No slot record found for the booking id "+slotBookingId);
            return null;
        }

        String occupiedSlotNo = objParkingSlotBooking.getSlotNo();
        removeRecords(objParkingSlotBooking);
        
        return "Successfully released the vehicle from parking slot "+occupiedSlotNo;
    }

    //@RequestMapping(value = "/releaseTheSlotByVehNo?vehNo=NY12345", method = RequestMethod.DELETE)
    public String releaseTheSlotByVehNo(String vehNo) throws Exception {
        ParkingSlotBooking objParkingSlotBooking = getParkingSlotInfoByVehNo(vehNo);
        if(objParkingSlotBooking == null){
            System.out.println("Failure. No slot record found for the vehNo "+vehNo);
            return null;
        }

        String occupiedSlotNo = objParkingSlotBooking.getSlotNo();
        removeRecords(objParkingSlotBooking);
        
        return "Successfully released the vehicle from parking slot "+occupiedSlotNo;
    }

    public void updateRecords(ParkingSlotBooking objParkingSlotBooking){
        setOfVehVsParkingSlots.put(objParkingSlotBooking.getVehNo(), objParkingSlotBooking);
        setOfParkingSlotsVsVehs.put(objParkingSlotBooking.getSlotNo(), objParkingSlotBooking);
        listOfVehVsParkingSlots.add(objParkingSlotBooking);
        setOfBookingIdVsVehSlots.put(Integer.valueOf(objParkingSlotBooking.getSlotBookingId()), objParkingSlotBooking);
    }

    public void removeRecords(ParkingSlotBooking objParkingSlotBooking){
        setOfVehVsParkingSlots.remove(objParkingSlotBooking.getVehNo());
        setOfParkingSlotsVsVehs.remove(objParkingSlotBooking.getSlotNo());
        listOfVehVsParkingSlots.remove(objParkingSlotBooking);
        setOfBookingIdVsVehSlots.remove(Integer.valueOf(objParkingSlotBooking.getSlotBookingId()));
    }

    public int getTotalNumberOfParkingSlots(){
        return slots.length;
    }


    /*public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        App obj = new App();

        String vehNoCurrent = "NY12345A";
        System.out.println("Going to allot vehicle "+vehNoCurrent+" to parking slot");
        ParkingSlotBooking slotBookingOutput = obj.allotVehToParkingSlot(vehNoCurrent, 3);
        System.out.println("Output of call: "+slotBookingOutput);
        System.out.println("--------------");

        String vehNoCurrent1 = "NY12345B";
        System.out.println("Going to allot vehicle "+vehNoCurrent1+" to parking slot");
        ParkingSlotBooking slotBookingOutput1 = obj.allotVehToParkingSlot(vehNoCurrent1, 1);
        System.out.println("Output of call: "+slotBookingOutput1);
        System.out.println("--------------");

        String vehNoCurrent2 = "NY12345C";
        System.out.println("Going to allot vehicle "+vehNoCurrent2+" to parking slot");
        ParkingSlotBooking slotBookingOutput2 = obj.allotVehToParkingSlot(vehNoCurrent2, 2);
        System.out.println("Output of call: "+slotBookingOutput2);
        System.out.println("--------------");

        String vehNoCurrent3 = "NY12345D";
        System.out.println("Going to allot vehicle "+vehNoCurrent3+" to parking slot");
        ParkingSlotBooking slotBookingOutput3 = obj.allotVehToParkingSlot(vehNoCurrent3, 2);
        System.out.println("Output of call: "+slotBookingOutput3);
        System.out.println("--------------");

        String vehNoCurrent4 = "NY12345E";
        System.out.println("Going to allot vehicle "+vehNoCurrent4+" to parking slot");
        ParkingSlotBooking slotBookingOutput4 = obj.allotVehToParkingSlot(vehNoCurrent4, 1);
        System.out.println("Output of call: "+slotBookingOutput4);
        System.out.println("--------------");

        String vehNoCurrent5 = "NY12345F";
        System.out.println("Going to allot vehicle "+vehNoCurrent5+" to parking slot");
        ParkingSlotBooking slotBookingOutput5 = obj.allotVehToParkingSlot(vehNoCurrent5, 4);
        System.out.println("Output of call: "+slotBookingOutput5);
        System.out.println("--------------");

        System.out.println("Going to get parking slot booking info");
        ParkingSlotBooking objParkingSlotBookingRetrieved = obj.getParkingSlotInfo(4);
        System.out.println(objParkingSlotBookingRetrieved.toString());
    }*/

}