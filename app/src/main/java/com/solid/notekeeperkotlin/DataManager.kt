package com.solid.notekeeperkotlin

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

        course = CourseInfo("Java Fundamentals: The Java Language", "Java Language")
        courses[course.courseCode] = course

        course = CourseInfo("Core Java", "Java Fundamentals: The Core Platform")
        courses[course.courseCode] = course
    }

    private fun initializeNotes() {
        for(course in courses){
            notes.add(NoteInfo(course.value, course.key, null))
        }
    }
}