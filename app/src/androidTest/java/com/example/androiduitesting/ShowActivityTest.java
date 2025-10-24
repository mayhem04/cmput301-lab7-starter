package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ShowActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testActivitySwitch() {
        // Add a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(androidx.test.espresso.action.ViewActions.typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Click on the city
        onView(withText("Edmonton")).perform(click());

        // Verify ShowActivity is displayed
        onView(withId(R.id.text_city_name)).check(matches(isDisplayed()));
    }

    @Test
    public void testCityNameConsistency() {
        // Add a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(androidx.test.espresso.action.ViewActions.typeText("Calgary"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Click on the city
        onView(withText("Calgary")).perform(click());

        // Check that the same city name is displayed
        onView(withId(R.id.text_city_name)).check(matches(withText("Calgary")));
    }

    @Test
    public void testBackButton() {
        // Add a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(androidx.test.espresso.action.ViewActions.typeText("Toronto"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Click on the city
        onView(withText("Toronto")).perform(click());

        // Click on the back button
        onView(withId(R.id.button_back)).perform(click());

        // Check that MainActivity list is visible again
        onView(withId(R.id.city_list)).check(matches(isDisplayed()));
    }
}
