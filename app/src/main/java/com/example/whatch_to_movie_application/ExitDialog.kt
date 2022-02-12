package com.example.whatch_to_movie_application

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import java.lang.IllegalStateException

class ExitDialog: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder
                .setMessage("Are you want to exit?")
                .setPositiveButton("Yes") {
                    dialog, which -> it.finish()
                }
                .setNegativeButton("No") {
                        dialog, which -> dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity not be null")


    }



}