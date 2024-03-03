## Getting Started

Welcome to the ParkingSlotBookingService. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.





## Instructions:

# Step1 : 

    Clone or download the codebase repo

# Step2:  

    Open the VsCode editor (or any other IDE). Hear I explained using in VsCode IDE

# Step3: 

    Right click on the Explorer area on left side, and select "Add Folder to Workspace", choose the repo downloaed in step1.

# Step4: 

    Compile the src folder and its java files (including testcase files) in VsCode if not automatically compiled by VsCode.

# Step5:

    Open the file src/com/elsevier/test/TestApp.java, right click any where in side the file, select "Run Tests in current File". 
    Note: you can find execution sample screenshots in <root-dir>/guide folder.

    OR

# Step6: (alternative to Step5)

    - To compile from command line, use below commands

    `<root-dir-of-codebase>$ javac -cp ./lib/junit4-4.8.2.jar:./lib/hamcrest-core-1.3.jar:./bin  -d ./bin src/com/elsevier/controllers/App.java`

    `<root-dir-of-codebase>$ javac -cp ./lib/junit4-4.8.2.jar:./lib/hamcrest-core-1.3.jar:./bin  -d ./bin src/com/elsevier/model/ParkingSlotBooking.java`

    `<root-dir-of-codebase>$ javac -cp ./lib/junit4-4.8.2.jar:./lib/hamcrest-core-1.3.jar:./bin  -d ./bin src/com/elsevier/test/TestApp.java`

    
    - To run test cases from command line, use below commands

    `<root-dir-of-codebase>$ java -cp ./lib/junit4-4.8.2.jar:./lib/hamcrest-core-1.3.jar:./bin  org.junit.runner.JUnitCore  com.elsevier.test.TestApp`



## Sample outputs of running the testcases:

`rameshbondiga@rameshbondiga-HP-ProBook-430-G6:~/_2RamWrkspc-bkdp/SampleJava/ParkingSlotBookingService$ javac -cp ./lib/junit4-4.8.2.jar:./lib/hamcrest-core-1.3.jar:./bin  -d ./bin src/com/elsevier/test/TestApp.java `


`rameshbondiga@rameshbondiga-HP-ProBook-430-G6:~/_2RamWrkspc-bkdp/SampleJava/ParkingSlotBookingService$ java -cp ./lib/junit4-4.8.2.jar:./lib/hamcrest-core-1.3.jar:./bin  org.junit.runner.JUnitCore  com.elsevier.test.TestApp`

`       JUnit version 4.8.2
        --------------------------Start : testAllotAndRetrieve
        New ParkingSlotBooking object is created : ParkingSlotBooking [vehNo=NY12345B, slotNo=SLOT1, durationInHrs=1, startTimestampInEpochMillis=1709496205411, startTimestampInString=03/04/2024 01:33:25, endTimestampInEpochMillis=1709499805411, endTimestampInString=03/04/2024 02:33:25, slotBookingId=1]
        Success. SLOT1 slot is allocated for the vehicle NY12345B with BookingId 1
        List of slot bookings: 
        slotBookingId: 1, 
        1 slotBookingId record found.
        ParkingSlotBooking [vehNo=NY12345B, slotNo=SLOT1, durationInHrs=1, startTimestampInEpochMillis=1709496205411, startTimestampInString=03/04/2024 01:33:25, endTimestampInEpochMillis=1709499805411, endTimestampInString=03/04/2024 02:33:25, slotBookingId=1]
        -------------------------------------End : testAllotAndRetrieve
        --------------------------Start : testMaxSlotsAllotment
        New ParkingSlotBooking object is created : ParkingSlotBooking [vehNo=NY12345-1, slotNo=SLOT1, durationInHrs=4, startTimestampInEpochMillis=1709496205574, startTimestampInString=03/04/2024 01:33:25, endTimestampInEpochMillis=1709510605574, endTimestampInString=03/04/2024 05:33:25, slotBookingId=2]
        Success. SLOT1 slot is allocated for the vehicle NY12345-1 with BookingId 2
        SLOT1 slot is already booked.
        New ParkingSlotBooking object is created : ParkingSlotBooking [vehNo=NY12345-2, slotNo=SLOT2, durationInHrs=4, startTimestampInEpochMillis=1709496205581, startTimestampInString=03/04/2024 01:33:25, endTimestampInEpochMillis=1709510605581, endTimestampInString=03/04/2024 05:33:25, slotBookingId=3]
        Success. SLOT2 slot is allocated for the vehicle NY12345-2 with BookingId 3
        SLOT1 slot is already booked.
        SLOT2 slot is already booked.
        New ParkingSlotBooking object is created : ParkingSlotBooking [vehNo=NY12345-3, slotNo=SLOT3, durationInHrs=4, startTimestampInEpochMillis=1709496205582, startTimestampInString=03/04/2024 01:33:25, endTimestampInEpochMillis=1709510605582, endTimestampInString=03/04/2024 05:33:25, slotBookingId=4]
        Success. SLOT3 slot is allocated for the vehicle NY12345-3 with BookingId 4
        SLOT1 slot is already booked.
        SLOT2 slot is already booked.
        SLOT3 slot is already booked.
        New ParkingSlotBooking object is created : ParkingSlotBooking [vehNo=NY12345-4, slotNo=SLOT4, durationInHrs=4, startTimestampInEpochMillis=1709496205583, startTimestampInString=03/04/2024 01:33:25, endTimestampInEpochMillis=1709510605583, endTimestampInString=03/04/2024 05:33:25, slotBookingId=5]
        Success. SLOT4 slot is allocated for the vehicle NY12345-4 with BookingId 5
        SLOT1 slot is already booked.
        SLOT2 slot is already booked.
        SLOT3 slot is already booked.
        SLOT4 slot is already booked.
        New ParkingSlotBooking object is created : ParkingSlotBooking [vehNo=NY12345-5, slotNo=SLOT5, durationInHrs=4, startTimestampInEpochMillis=1709496205584, startTimestampInString=03/04/2024 01:33:25, endTimestampInEpochMillis=1709510605584, endTimestampInString=03/04/2024 05:33:25, slotBookingId=6]
        Success. SLOT5 slot is allocated for the vehicle NY12345-5 with BookingId 6
        SLOT1 slot is already booked.
        SLOT2 slot is already booked.
        SLOT3 slot is already booked.
        SLOT4 slot is already booked.
        SLOT5 slot is already booked.
        Failure. No slot is allocated for the vehicle NY12345-6
        -----------------------------------End : testMaxSlotsAllotment
        --------------------------Start : testAllotForIncorrectDuration
        Going to allot vehicle to parking slot with duration 0 Hrs
        Slot can not be booked for the given duration.
        ---------------------------------------End : testAllotForIncorrectDuration
        --------------------------Start : testAllot3ForIncorrectDuration
        Going to allot vehicle to parking slot with duration 5 Hrs
        Slot can not be booked for the given duration.
        -------------------------------------End : testAllot3ForIncorrectDuration
        --------------------------Start : testIncreaseSlotDuration
        New ParkingSlotBooking object is created : ParkingSlotBooking [vehNo=NY12345B-INCR, slotNo=SLOT1, durationInHrs=4, startTimestampInEpochMillis=1709496205588, startTimestampInString=03/04/2024 01:33:25, endTimestampInEpochMillis=1709510605588, endTimestampInString=03/04/2024 05:33:25, slotBookingId=7]
        Success. SLOT1 slot is allocated for the vehicle NY12345B-INCR with BookingId 7
        List of slot bookings: 
        slotBookingId: 7, 
        7 slotBookingId record found.
        ## objParkingSlotBooking Retrieved: ParkingSlotBooking [vehNo=NY12345B-INCR, slotNo=SLOT1, durationInHrs=4, startTimestampInEpochMillis=1709496205588, startTimestampInString=03/04/2024 01:33:25, endTimestampInEpochMillis=1709510605588, endTimestampInString=03/04/2024 05:33:25, slotBookingId=7]
        List of slot bookings: 
        slotBookingId: 7, 
        7 slotBookingId record found.
        Added new duration of 3hrs to existing duration of VehicleParkingSlot : ParkingSlotBooking [vehNo=NY12345B-INCR, slotNo=SLOT1, durationInHrs=7, startTimestampInEpochMillis=1709496205588, startTimestampInString=03/04/2024 01:33:25, endTimestampInEpochMillis=1709521405588, endTimestampInString=03/04/2024 08:33:25, slotBookingId=7]
        ## objParkingSlotBooking after increasing duration: ParkingSlotBooking [vehNo=NY12345B-INCR, slotNo=SLOT1, durationInHrs=7, startTimestampInEpochMillis=1709496205588, startTimestampInString=03/04/2024 01:33:25, endTimestampInEpochMillis=1709521405588, endTimestampInString=03/04/2024 08:33:25, slotBookingId=7]
        ------------------------------------End : testIncreaseSlotDuration
        --------------------------Start : testReleaseSlot
        New ParkingSlotBooking object is created : ParkingSlotBooking [vehNo=NY12345B-RLS, slotNo=SLOT1, durationInHrs=1, startTimestampInEpochMillis=1709496205611, startTimestampInString=03/04/2024 01:33:25, endTimestampInEpochMillis=1709499805611, endTimestampInString=03/04/2024 02:33:25, slotBookingId=8]
        Success. SLOT1 slot is allocated for the vehicle NY12345B-RLS with BookingId 8
        List of slot bookings: 
        slotBookingId: 8, 
        8 slotBookingId record found.
        ## objParkingSlotBookingRetrieved: ParkingSlotBooking [vehNo=NY12345B-RLS, slotNo=SLOT1, durationInHrs=1, startTimestampInEpochMillis=1709496205611, startTimestampInString=03/04/2024 01:33:25, endTimestampInEpochMillis=1709499805611, endTimestampInString=03/04/2024 02:33:25, slotBookingId=8]
        List of slot bookings: 
        slotBookingId: 8, 
        8 slotBookingId record found.
        ## strParkingSlotReleaseOutput: Successfully released the vehicle from parking slot SLOT1
        List of slot bookings: 

        8 slotBookingId record NOT found.
        Failure. No slot record found for the booking id 8
        --------------------------------------End : testReleaseSlot
        -------------------------Start : testAllotSlot
        New ParkingSlotBooking object is created : ParkingSlotBooking [vehNo=NY12345B, slotNo=SLOT1, durationInHrs=1, startTimestampInEpochMillis=1709496205617, startTimestampInString=03/04/2024 01:33:25, endTimestampInEpochMillis=1709499805617, endTimestampInString=03/04/2024 02:33:25, slotBookingId=9]
        Success. SLOT1 slot is allocated for the vehicle NY12345B with BookingId 9
        ------------------------------------------End : testAllotSlot

        Time: 0.243

        OK (7 tests)
`


