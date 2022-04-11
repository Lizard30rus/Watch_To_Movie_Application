package com.example.whatch_to_movie_application.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.whatch_to_movie_application.R

class InviteFragment: Fragment() {

    private lateinit var readyButton : Button
    private lateinit var editTextPhoneNumber : EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_invite, container,false)
        readyButton = view.findViewById(R.id.ready_button)
        editTextPhoneNumber  = view.findViewById(R.id.edit_text_phone)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val editStr = editTextPhoneNumber.editableText.toString()
        readyButton.setOnClickListener {
            if (editStr.length != 11 || editStr[0] != '8') {
                Toast.makeText(context?.applicationContext, "Wrong number!", Toast.LENGTH_SHORT).show()
                parentFragmentManager.popBackStack()
            } else {
                Toast.makeText(context?.applicationContext, "Invitation sent!", Toast.LENGTH_SHORT).show()
                parentFragmentManager.popBackStack()
            }
        }
    }
}