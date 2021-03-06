package com.solid.notekeeperkotlin.data

import com.solid.notekeeperkotlin.model.CourseInfo
import com.solid.notekeeperkotlin.model.NoteInfo

// DataManager class is a singleton
object DataManager {
    //Properties to hold a collection of courses and notes
    val courses = HashMap<String, CourseInfo>()
    val notes = ArrayList<NoteInfo>()

    init {
        initializeCourses()
        initializeNotes()
    }

    private fun initializeCourses(){
        var course = CourseInfo("Android_Intents", "Android Programming with Intents")
        courses[course.courseCode] = course

        course = CourseInfo("Android_Async", "Android Async Programming and Services")
        courses[course.courseCode] = course

        course = CourseInfo("Java Language", "Java Fundamentals: The Java Language")
        courses[course.courseCode] = course

        course = CourseInfo("Core Java", "Java Fundamentals: The Core Platform")
        courses[course.courseCode] = course
    }

    private fun initializeNotes() {
        var course = courses["Android_Intents"]!!
        var note = NoteInfo(course, "Dynamic intent resolution",
            "Wow, intents allow components to be resolved at runtime")
        notes.add(note)
        note = NoteInfo(course, "Delegating intents",
            "PendingIntents are powerful; they delegate much more than just a component invocation")
        notes.add(note)

        course = courses["Android_Async"]!!
        note = NoteInfo(course, "Service default threads",
            "Did you know that by default an Android Service will tie up the UI thread?")
        notes.add(note)
        note = NoteInfo(course, "Long running operations",
            "Foreground Services can be tied to a notification icon")
        notes.add(note)

        course = courses["Java Language"]!!
        note = NoteInfo(course, "Parameters",
            "Leverage variable-length parameter lists")
        notes.add(note)
        note = NoteInfo(course, "Anonymous classes",
            "Anonymous classes simplify implementing one-use types")
        notes.add(note)

        course = courses["Core Java"]!!
        note = NoteInfo(course, "Compiler options",
            "The -jar option isn't compatible with with the -cp option")
        notes.add(note)
        note = NoteInfo(course, "Serialization",
            "Remember to include SerialVersionUID to assure version compatibility")
        notes.add(note)
    }
}