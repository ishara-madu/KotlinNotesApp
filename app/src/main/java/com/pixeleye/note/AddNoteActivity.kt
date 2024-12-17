package com.pixeleye.note

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pixeleye.note.databinding.ActivityAddNoteBinding
import com.pixeleye.note.databinding.ActivityMainBinding

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding:ActivityAddNoteBinding
    private lateinit var db:NoteDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)

        binding.saveButton.setOnClickListener{
            val title = binding.tittleEditText.text.toString()
            val content = binding.contentEditText.text.toString()
            val note = Note(0,title, content)
            db.insertNote(note)
            finish()
            Toast.makeText(this,"You are added the note",Toast.LENGTH_SHORT).show()
        }
    }
}