package com.solid.notekeeperkotlin

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.solid.notekeeperkotlin.constants.NOTE_POSITION
import com.solid.notekeeperkotlin.constants.POSITION_NOT_SET
import com.solid.notekeeperkotlin.data.DataManager
import com.solid.notekeeperkotlin.databinding.ActivityMainBinding
import com.solid.notekeeperkotlin.model.CourseInfo
import com.solid.notekeeperkotlin.model.NoteInfo

class NoteActivity : AppCompatActivity() {

    private var notePosition = POSITION_NOT_SET

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val adapterCourses = ArrayAdapter<CourseInfo>(this,     //creating the adapter for the spinner and using the android spinner layout to handle display of our courses
                android.R.layout.simple_spinner_item,
                DataManager.courses.values.toList())
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)   //using the spinner dropdown item layout to handle data on click of dropdown arrow on spinner

        binding.mainContainer.spinnerCourses.adapter = adapterCourses //connecting the adapter to the spinner in our UI

//        Get the intent that started this activity and its extra using notePosition
//        If position is set, we know its a click on listView, else if position is not set, we know its from fab
        notePosition = savedInstanceState?.getInt(NOTE_POSITION, POSITION_NOT_SET) ?:
                       intent.getIntExtra(NOTE_POSITION, POSITION_NOT_SET)
        if (notePosition != POSITION_NOT_SET) displayNote()
        else {
            DataManager.notes.add(NoteInfo())
            notePosition = DataManager.notes.lastIndex
        }

    }

//    Function to display the notes when an item is clicked in the listView
    private fun displayNote() {
//        Get the note that corresponds to the notePosition from our DataManager notes collection
        val note = DataManager.notes[notePosition]
//        We set the note text and title textView to the values gotten
        binding.mainContainer.textNoteTitle.setText(note.title)
        binding.mainContainer.textNoteText.setText(note.text)

//      Next we display the course associated with that note
//      Course is displayed within a spinner so we need to select the appropriate position of the course within the spinner
        val coursePosition = DataManager.courses.values.indexOf(note.course)
        binding.mainContainer.spinnerCourses.setSelection(coursePosition)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        Inflate the menu; this adds menu to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
//        Handle action bar item clicks here. the action bar will
//        automatically handle clicks on the Home/Up button, so long
//        as you specify a parent activity in AndroidManifest.xml

        return when(item.itemId){
            R.id.action_settings -> true
            R.id.action_next -> {
                moveNext()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

//  This method gets called when the user moves from one note to the next(when next icon is pressed)
    private fun moveNext() {
        ++notePosition
        displayNote()
        invalidateOptionsMenu()
    }

//    Be sure this method gets called whenever we had need to change the appearance of our menu using invalidateOptionsMenu()
    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
    if (notePosition >= DataManager.notes.lastIndex){   //if user reaches the last note
        val menuItem = menu?.findItem(R.id.action_next)

        if(menuItem != null){
            menuItem.icon = getDrawable(R.drawable.ic_block_white_24) //change the icon to block
            menuItem.isEnabled = false      //disable the icon
        }
    }
        return super.onPrepareOptionsMenu(menu)
    }

//    save the note when the Activity moves away from the foreground
    override fun onPause() {
        super.onPause()
        saveNote()
    }

    private fun saveNote() {
//        Save the content from the screen into the note within our DataManager
        val note = DataManager.notes[notePosition]
//      get the text that is displayed on the spinner and the editTexts
        note.title = binding.mainContainer.textNoteTitle.text.toString()
        note.text = binding.mainContainer.textNoteText.text.toString()
        note.course = binding.mainContainer.spinnerCourses.selectedItem as CourseInfo
    }

//    ensuring the notePosition instance saves our state correctly
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(NOTE_POSITION, notePosition)

    }
}