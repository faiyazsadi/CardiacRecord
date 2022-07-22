package com.example.cardiacrecorder.Activity;

import static org.junit.Assert.*;

import androidx.annotation.NonNull;

import com.example.cardiacrecorder.Model.RecordModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailsActivityTest {
    public ArrayList<RecordModel> mockRecordList() {
        ArrayList<RecordModel> userRecords = new ArrayList<RecordModel>();
        userRecords.add(getMockRecord());
        return userRecords;
    }

    public RecordModel getMockRecord() {
        String date = "22-07-22";
        String times = "10:00 pm";
        String timestamp = "";
        String heartrate = "100";
        String systolic = "120";
        String diastolic = "80";
        String comment = "No Health Risks.";
        RecordModel userRecord = new RecordModel(date, times, timestamp, heartrate, systolic, diastolic, comment);
        return userRecord;
    }
    @Test
    public void testAddRecord() {
        ArrayList<RecordModel> userRecords = mockRecordList();
        assertEquals(1, userRecords.size());
        RecordModel userRecord = new RecordModel("23-07-22", "11:00 am", "", "80", "120", "85", "Nothing Unusual");
        userRecords.add(userRecord);
        assertEquals(2, userRecords.size());
        assertTrue(userRecords.contains(userRecord));
    }

    @Test
    public void testAddRecordException() {
        ArrayList<RecordModel> userRecords = mockRecordList();
        assertEquals(1, userRecords.size());

        RecordModel userRecord = getMockRecord();
        assertThrows(IllegalArgumentException.class, () -> {
            if(userRecords.contains(userRecord)) throw new IllegalArgumentException();
        });
    }

    @Test
    public void testGetMockRecord() {
        ArrayList<RecordModel> userRecords = mockRecordList();
        assertEquals(true, getMockRecord().equals(userRecords.get(0)));

        RecordModel userRecord = new RecordModel("24-07-22", "11:00 pm", "", "100", "100", "70", "Low Pressure");
        userRecords.add(userRecord);

        assertEquals(true, userRecord.equals(userRecords.get(1)));
    }

    @Test
    public void testRemoveRecord() {
        ArrayList<RecordModel> userRecords = mockRecordList();
        RecordModel userRecord = new RecordModel("24-07-22", "11:00 pm", "", "100", "100", "70", "Low Pressure");
        userRecords.add(userRecord);
        assertTrue(userRecords.contains(userRecord));
        userRecords.remove(userRecord);
        assertFalse(userRecords.contains(userRecord));
    }

    @Test
    public void testRemoveRecordException() {
        ArrayList<RecordModel> userRecords = mockRecordList();
        RecordModel userRecord = new RecordModel("24-07-22", "11:00 pm", "", "100", "100", "70", "Low Pressure");
        assertThrows(IllegalArgumentException.class, () -> {
            if(!userRecords.contains(userRecord)) throw new IllegalArgumentException();
        });
    }

    @Test
    public void testEdit() {
        ArrayList<RecordModel> userRecords = mockRecordList();
        RecordModel userRecord1 = new RecordModel("24-07-22", "11:00 pm", "", "100", "100", "70", "Low Pressure");
        userRecords.add(userRecord1);
        RecordModel userRecord2 = new RecordModel("25-07-22", "11:00 pm", "", "100", "100", "70", "Low Pressure");
        assertFalse(userRecords.contains(userRecord2));
        userRecord2 = userRecords.get(userRecords.size() - 1);
        userRecord2.setDate("24-07-22");
        assertTrue(userRecords.contains(userRecord2));
    }
}