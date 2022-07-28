package com.example.cardiacrecorder;

import static android.service.autofill.Validators.not;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

import android.app.Instrumentation;
import android.content.Intent;
import android.os.SystemClock;
import android.service.autofill.UserData;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
//import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.cardiacrecorder.Activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest


public class MainActivityTest {



    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);



    @Test
    public void testCheckAppName(){
        Espresso.onView(withText("CardiacRecorder")).check(matches(isDisplayed()));
    }


    @Test
    public void testAddNewRecord()
    {
        SystemClock.sleep(5000);
        Espresso.onView(withId(R.id.addNewRecord)).perform(click());
        Espresso.onView(withId(R.id.Date)).perform(ViewActions.clearText());
        Espresso.onView(withId(R.id.Date)).perform(ViewActions.typeText("11/07/22"));
        Espresso.onView(withId(R.id.Time)).perform(ViewActions.clearText());
        Espresso.onView(withId(R.id.Time)).perform(ViewActions.typeText("20:40 pm"));
        Espresso.onView(withId(R.id.Heartrate)).perform(ViewActions.typeText("70"));
        Espresso.onView(withId(R.id.Systolic)).perform(ViewActions.typeText("140"));
        Espresso.pressBack();
        SystemClock.sleep(2000);
        Espresso.onView(withId(R.id.Diastolic)).perform(click());
        Espresso.onView(withId(R.id.Diastolic)).perform(ViewActions.typeText("80"));
        Espresso.pressBack();
        Espresso.onView(withId(R.id.Comment)).perform(click());
        Espresso.onView(withId(R.id.Comment)).perform(ViewActions.typeText("You should control your blood pressure"));
        Espresso.pressBack();
        Espresso.onView(withId(R.id.addRecordButton)).perform(click());
        SystemClock.sleep(2000);
        Espresso.onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));

    }


    @Test
    public void testUpdateRecord() {
        SystemClock.sleep(5000);
        Espresso.onView(withId(R.id.recyclerView)).perform(click());
        SystemClock.sleep(2000);
        Espresso.onView(withId(R.id.systolicForDetails)).perform(ViewActions.clearText());
        Espresso.onView(withId(R.id.systolicForDetails)).perform(ViewActions.typeText("130"));
        Espresso.pressBack();
        Espresso.onView(withId(R.id.diastolicForDetails)).perform(click());
        Espresso.onView(withId(R.id.diastolicForDetails)).perform(ViewActions.clearText());
        Espresso.onView(withId(R.id.diastolicForDetails)).perform(ViewActions.typeText("70"));
        Espresso.pressBack();
        Espresso.onView(withId(R.id.saveForDetails)).perform(click());
        SystemClock.sleep(2000);
        Espresso.onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
    }

    @Test
    public void testDeleteRecord() {
        SystemClock.sleep(5000);
        Espresso.onView(withId(R.id.recyclerView)).perform(click());
        Espresso.onView(withId(R.id.deleteRecordButtonDetails)).perform(click());
        SystemClock.sleep(2000);
        Espresso.onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
    }































}