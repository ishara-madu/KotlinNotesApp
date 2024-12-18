package com.pixeleye.note

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdaptor (private var notes:List<Note>,context:Context):
    RecyclerView.Adapter<NotesAdaptor.NoteViewHolder>() {

        class NoteViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
            val titleTextView:TextView = itemView.findViewById(R.id.titleTextView)
            val contentTextView:TextView = itemView.findViewById(R.id.contentTextView)
            val updateButton:ImageView = itemView.findViewById(R.id.updateButton)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int = notes.size
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.titleTextView.text = note.title
        holder.contentTextView.text = note.content
        holder.updateButton.setOnClickListener{
            val intent = Intent(it.context,UpdateNoteActivity::class.java).apply {
                putExtra("note_id",note.id)
            }
            it.context.startActivity(intent)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refreshData(newNotes:List<Note>){
        notes = newNotes
        notifyDataSetChanged()
    }
}