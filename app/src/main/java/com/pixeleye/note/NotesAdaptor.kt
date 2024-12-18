package com.pixeleye.note

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class NotesAdaptor (private var notes:List<Note>,context:Context):
    RecyclerView.Adapter<NotesAdaptor.NoteViewHolder>() {

        private val db:NoteDatabaseHelper = NoteDatabaseHelper(context)
        class NoteViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
            val titleTextView:TextView = itemView.findViewById(R.id.titleTextView)
            val contentTextView:TextView = itemView.findViewById(R.id.contentTextView)
            val updateButton:ImageView = itemView.findViewById(R.id.updateButton)
            val deleteButton:ImageView = itemView.findViewById(R.id.deleteButton)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int = notes.size
    @SuppressLint("NotifyDataSetChanged")
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
        holder.deleteButton.setOnClickListener{
            db.deleteNote(note.id)
            refreshData(db.getAllNotes())
            Toast.makeText(holder.itemView.context,"Note deleted",Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refreshData(newNotes:List<Note>){
        notes = newNotes
        notifyDataSetChanged()
    }
}