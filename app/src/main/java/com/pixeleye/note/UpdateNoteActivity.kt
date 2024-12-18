package com.pixeleye.note

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pixeleye.note.databinding.ActivityUpdateNoteBinding

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityUpdateNoteBinding
    private lateinit var db : NoteDatabaseHelper
    private var noteId:Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)

        noteId = intent.getIntExtra("note_id",-1)
        if (noteId == -1){
            finish()
            return
        }

        val note = db.getNoteById(noteId)

        binding.updateTittleEditText.setText(note.title)
        binding.updateContentEditText.setText(note.content)

        binding.updateSaveButton.setOnClickListener{
            val updatedTitle = binding.updateTittleEditText.text.toString()
            val updatedContent = binding.updateContentEditText.text.toString()

            db.updateNote(Note(noteId,updatedTitle,updatedContent))
            finish()
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
        }


    }
}