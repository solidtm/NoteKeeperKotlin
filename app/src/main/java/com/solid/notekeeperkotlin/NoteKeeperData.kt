package com.solid.notekeeperkotlin

data class CourseInfo(val courseCode: String, val title: String) {
    override fun toString(): String {
        return title
    }
}

data class NoteInfo(var course: CourseInfo? = null, var title: String? = null, var text: String? = null)

