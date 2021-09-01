package com.solid.notekeeperkotlin.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.solid.notekeeperkotlin.activity.NoteActivity
import com.solid.notekeeperkotlin.R
import com.solid.notekeeperkotlin.constants.NOTE_POSITION
import com.solid.notekeeperkotlin.model.NoteInfo

class NoteRecyclerAdapter(private val context: Context, private val notes: List<NoteInfo>):
                RecyclerView.Adapter<NoteRecyclerAdapter.ViewHolder>(){

//    Property to inflate the item_note_list layout
    private val layoutInflater = LayoutInflater.from(context)

//    Method responsible for creating the itemView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_note_list, parent, false)

        return ViewHolder(itemView)
    }

//    Method responsible for displaying the data on our recyclerview
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]

        holder.textCourse.text = note.course?.title
        holder.textTitle.text = note.title

//    The holder will always have the position the click is associated with
        holder.notePosition = position
    }

    override fun getItemCount() = notes.size


  inner  class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
//  Here we explicitly write code to get references to the views we want to interact with from item_note_list
        val textCourse: TextView = itemView.findViewById(R.id.textCourse)
        val textTitle: TextView = itemView.findViewById(R.id.textTitle)

//  Notify handle response for when the user clicks on a particular item for each note it is associate with
        var notePosition = 0

//      If user click an item on the Recyclerview, we want to start the second activity(NoteActivity) and pass information to the other activity using intents thus
        init {
            itemView.setOnClickListener {
                val intent = Intent(context, NoteActivity::class.java)
                intent.putExtra(NOTE_POSITION, notePosition)
                context.startActivity(intent)
            }
        }
    }
}