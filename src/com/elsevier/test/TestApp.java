package com.elsevier.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.junit.BeforeClass;
import org.junit.Test;

import com.elsevier.controllers.App;
import com.elsevier.model.ParkingSlotBooking;

public class TestApp {

    public static App obj = null;

    @BeforeClass 
    public static void testSetup() throws Exception {
        obj = new App();
    }

    
    @Test  
    public void testAllotSlot() throws Exception {
        System.out.println("--------------------------Start : testAllotSlot");
        App obj1 = new App();
        String vehNoCurrent1 = "NY12345B";
        ParkingSlotBooking slotBookingOutput1 = obj1.allotVehToParkingSlot(vehNoCurrent1, 1);
        assertNotNull(slotBookingOutput1);
        System.out.println("------------------------------------------End : testAllotSlot");
    }

    @Test  
    public void testAllotAndRetrieve() throws Exception {
        System.out.println("--------------------------Start : testAllotAndRetrieve");
        App obj1 = new App();
        String vehNoCurrent1 = "NY12345B";
        //-----Going to allot vehicle NY12345B to parking slot
        ParkingSlotBooking slotBookingOutput1 = obj1.allotVehToParkingSlot(vehNoCurrent1, 1);
        assertNotNull(slotBookingOutput1);


        //----Going to get parking slot booking info for previous Booking Id");
        ParkingSlotBooking objParkingSlotBookingRetrieved = obj1.getParkingSlotInfo(slotBookingOutput1.getSlotBookingId());
        assertNotNull(objParkingSlotBookingRetrieved);
        System.out.println(objParkingSlotBookingRetrieved.toString());
        System.out.println("-------------------------------------End : testAllotAndRetrieve");
    }

    @Test  
    public void testMaxSlotsAllotment() throws Exception {
        System.out.println("--------------------------Start : testMaxSlotsAllotment");
        App objLocal = new App();

        assertEquals(objLocal.getTotalNumberOfParkingSlots(), 5);
        int totalNumberOfParkingSlots = objLocal.getTotalNumberOfParkingSlots();

        for(int i=0; i < totalNumberOfParkingSlots; i++){
            int j=i+1;
            String vehNoCurrent1 = "NY12345-"+j;
            //---- Going to allot vehicle NY12345-1/2/3/4/5 to parking slot
            ParkingSlotBooking slotBookingOutput1 = objLocal.allotVehToParkingSlot(vehNoCurrent1, 4);
            assertNotNull(slotBookingOutput1);
        }

        String vehNoCurrent6 = "NY12345-6";
        //---- Going to allot vehicle NY12345-6 to parking slot
        ParkingSlotBooking slotBookingOutput6 = objLocal.allotVehToParkingSlot(vehNoCurrent6, 4);
        assertNull(slotBookingOutput6);
        System.out.println("-----------------------------------End : testMaxSlotsAllotment");
    }

    @Test  
    public void testAllotForIncorrectDuration() throws Exception {
        System.out.println("--------------------------Start : testAllotForIncorrectDuration");
        App obj1 = new App();
        String vehNoCurrent1 = "NY12345B-DUR1";
        System.out.println("Going to allot vehicle to parking slot with duration 0 Hrs");
        ParkingSlotBooking slotBookingOutput1 = obj1.allotVehToParkingSlot(vehNoCurrent1, 0);
        assertNull(slotBookingOutput1);
        System.out.println("---------------------------------------End : testAllotForIncorrectDuration");
    }

    @Test  
    public void testAllot3ForIncorrectDuration() throws Exception {
        System.out.println("--------------------------Start : testAllot3ForIncorrectDuration");
        App obj1 = new App();
        String vehNoCurrent1 = "NY12345B-DUR2";
        System.out.println("Going to allot vehicle to parking slot with duration 5 Hrs");
        ParkingSlotBooking slotBookingOutput1 = obj1.allotVehToParkingSlot(vehNoCurrent1, 5);
        assertNull(slotBookingOutput1);
        System.out.println("-------------------------------------End : testAllot3ForIncorrectDuration");
    }

    @Test  
    public void testIncreaseSlotDuration() throws Exception {
        System.out.println("--------------------------Start : testIncreaseSlotDuration");
        App obj1 = new App();
        String vehNoCurrent1 = "NY12345B-INCR";
        int initialDurationInHrs = 4;
        int addtionalDurationInHrs = 3;

        
        //-----Going to allot vehicle NY12345B to parking slot
        ParkingSlotBooking slotBookingOutput1 = obj1.allotVehToParkingSlot(vehNoCurrent1, initialDurationInHrs);
        assertNotNull(slotBookingOutput1);

        //----Going to get parking slot booking info for previous Booking Id");
        ParkingSlotBooking objParkingSlotBookingRetrieved = obj1.getParkingSlotInfo(slotBookingOutput1.getSlotBookingId());
        assertNotNull(objParkingSlotBookingRetrieved);
        System.out.println("## objParkingSlotBooking Retrieved: "+objParkingSlotBookingRetrieved.toString());


        //----Going to release parking slot booking for previous Booking Id");
        ParkingSlotBooking slotDurIncreaseOutput1 = obj1.increaseTheSlotDur(slotBookingOutput1.getSlotBookingId(), addtionalDurationInHrs);
        assertNotNull(slotDurIncreaseOutput1);
        System.out.println("## objParkingSlotBooking after increasing duration: "+slotDurIncreaseOutput1.toString());

        int finalDurationInHrs = slotDurIncreaseOutput1.getDurationInHrs();
        assertTrue( finalDurationInHrs == (initialDurationInHrs+addtionalDurationInHrs) );

        long startTimestampInEpochMillis = slotDurIncreaseOutput1.getStartTimestampInEpochMillis();
        LocalDateTime ldtStart = Instant.ofEpochMilli(startTimestampInEpochMillis).atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime ldtEndAfterAddingHrsToStartTime = ldtStart.plusHours( (initialDurationInHrs+addtionalDurationInHrs) );
        long ldtEndAfterAddingHrsToStartTimeInEpoch = ldtEndAfterAddingHrsToStartTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long ldtEndFromObjInEpoch = slotDurIncreaseOutput1.getEndTimestampInEpochMillis();
        assertTrue(ldtEndAfterAddingHrsToStartTimeInEpoch == ldtEndFromObjInEpoch);
        System.out.println("------------------------------------End : testIncreaseSlotDuration");
    }

    @Test  
    public void testReleaseSlot() throws Exception {
        System.out.println("--------------------------Start : testReleaseSlot");
        App obj1 = new App();
        String vehNoCurrent1 = "NY12345B-RLS";
        
        //-----Going to allot vehicle NY12345B to parking slot
        ParkingSlotBooking slotBookingOutput1 = obj1.allotVehToParkingSlot(vehNoCurrent1, 1);
        assertNotNull(slotBookingOutput1);

        //----Going to get parking slot booking info for previous Booking Id");
        ParkingSlotBooking objParkingSlotBookingRetrieved = obj1.getParkingSlotInfo(slotBookingOutput1.getSlotBookingId());
        assertNotNull(objParkingSlotBookingRetrieved);
        System.out.println("## objParkingSlotBookingRetrieved: "+objParkingSlotBookingRetrieved.toString());


        //----Going to release parking slot booking for previous Booking Id");
        String strParkingSlotReleaseOutput = obj1.releaseTheSlotByBkngId(slotBookingOutput1.getSlotBookingId());
        assertNotNull(strParkingSlotReleaseOutput);
        System.out.println("## strParkingSlotReleaseOutput: "+strParkingSlotReleaseOutput);

        //----Going to get parking slot booking info for previous Booking Id");
        ParkingSlotBooking objParkingSlotBookingRetrieved1 = obj1.getParkingSlotInfo(slotBookingOutput1.getSlotBookingId());
        assertNull(objParkingSlotBookingRetrieved1);
        
        System.out.println("--------------------------------------End : testReleaseSlot");
    }

}


