package com.solid.notekeeperkotlin

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.solid.notekeeperkotlin.databinding.ActivityNoteListBinding

class NoteListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNoteListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

//      When the fab button is clicked, start the next activity to create a new note
        binding.fab.setOnClickListener {
            val activityIntent = Intent(this, MainActivity::class.java)
            startActivity(activityIntent)
        }

//        Get the adapter for the listNotes listView and set it to display the notes arraylist
        binding.notelistContainer.listNotes.adapter = ArrayAdapter(this,
                    android.R.layout.simple_list_item_1,
                    DataManager.notes)

//      If user click an item on the listView, we want to start the second activity and pass information to the other activity using intents thus
        binding.notelistContainer.listNotes.setOnItemClickListener{parent, view, position, id ->
            val activityIntent = Intent(this, MainActivity::class.java)
            activityIntent.putExtra(NOTE_POSITION, position)
            startActivity(activityIntent)
        }
    }

//    notify the adapter for the listView that new notes were added to populate the listview
    override fun onResume(){
        super.onResume()
    (binding.notelistContainer.listNotes.adapter as ArrayAdapter<NoteInfo>).notifyDataSetChanged()
    }
}