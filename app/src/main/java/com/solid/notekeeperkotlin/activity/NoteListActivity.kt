package com.solid.notekeeperkotlin.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.solid.notekeeperkotlin.adapter.NoteRecyclerAdapter
import com.solid.notekeeperkotlin.data.DataManager
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
            val activityIntent = Intent(this, NoteActivity::class.java)
            startActivity(activityIntent)
        }

//      Specify the layout manager for our RecyclerView items
        binding.notelistContainer.listItems.layoutManager = LinearLayoutManager(this)

//        Get the adapter for the listNotes Recyclerview and set it to display the notes arraylist
        binding.notelistContainer.listItems.adapter = NoteRecyclerAdapter(this, DataManager.notes)

    }

//    notify the adapter for the listView that new notes were added to populate the RecyclerView
    override fun onResume(){
        super.onResume()

        binding.notelistContainer.listItems.adapter?.notifyDataSetChanged()
    }
}