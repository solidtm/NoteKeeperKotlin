package com.solid.notekeeperkotlin

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.Espresso.*
import org.hamcrest.Matchers.*
import org.junit.Rule
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import com.solid.notekeeperkotlin.adapter.CourseRecyclerAdapter
import com.solid.notekeeperkotlin.adapter.NoteRecyclerAdapter


@RunWith(AndroidJUnit4::class)
class NavigationTest {
//    Provide rule that will start the activity that we wanna test
    @Rule @JvmField
    val itemsActivity = ActivityTestRule(ItemsActivity::class.java)

//    Test functions to verify the behaviour of our Navigation Drawer and RecyclerView when the user makes a selection for courses and notes
    @Test
    fun selectNoteAfterNavigationDrawerChanged(){
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open()) //open up navigation drawer
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_courses)) //display list of courses

        val coursePosition = 0
        onView(withId(R.id.listItems)).perform(
            RecyclerViewActions.actionOnItemPosition<CourseRecyclerAdapter.ViewHolder>(coursePosition, click())) //test selection of a course


    onView(withId(R.id.drawer_layout)).perform(DrawerActions.open()) //open up navigation drawer
    onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_notes)) //display list of notes

    val notePosition = 0
    onView(withId(R.id.listItems)).perform(
        RecyclerViewActions.actionOnItemPosition<NoteRecyclerAdapter.ViewHolder>(coursePosition, click())) //test selection of a note
  }



}