package com.example.whatch_to_movie_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class InviteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invite)


        findViewById<Button>(R.id.ready_button).setOnClickListener {
            val edit = findViewById<EditText>(R.id.edit_text_phone)
            val editStr = edit.editableText.toString()
            if (editStr.length != 11 || editStr[0] != '8') {
                setResult(RESULT_CANCELED)
            } else {
                setResult(RESULT_OK)
            }

            finish()
        }
    }


}