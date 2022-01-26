package com.example.whatch_to_movie_application

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.invite_button).setOnClickListener {
            val intent = Intent(this, InviteActivity::class.java)
            startActivityForResult(intent, REQ_CODE)
        }
        val button1 = findViewById<Button>(R.id.details_Button_1)
        val button2 = findViewById<Button>(R.id.details_Button_2)
        val button3 = findViewById<Button>(R.id.details_Button_3)
        val button4 = findViewById<Button>(R.id.details_Button_4)
        val button5 = findViewById<Button>(R.id.details_Button_5)

        val clickListener: View.OnClickListener = View.OnClickListener { view ->
            when (view.id) {
                R.id.details_Button_1 -> {
                    val previewOfNewListTitle: String by lazy{
                        resources.getString(R.string.description_Film_1) }
                    val imageResourse = R.drawable.gentl_image
                    findViewById<TextView>(R.id.name_Film_1).setTextColor(R.color.click)
                    FilmDescriptionAct.start(this,
                        DescriptionScreen(previewOfNewListTitle, imageResourse))
                }

                R.id.details_Button_2 -> {
                    val previewOfNewListTitle: String by lazy{
                        resources.getString(R.string.description_Film_2) }
                    val imageResourse = R.drawable.cards_cash_two_guns
                    findViewById<TextView>(R.id.name_Film_2).setTextColor(R.color.click)
                    FilmDescriptionAct.start(this,
                        DescriptionScreen(previewOfNewListTitle, imageResourse))
                }

                R.id.details_Button_3 -> {
                    val previewOfNewListTitle: String by lazy{
                        resources.getString(R.string.description_Film_3) }
                    val imageResourse = R.drawable.revolver
                    findViewById<TextView>(R.id.name_Film_3).setTextColor(R.color.click)
                    FilmDescriptionAct.start(this,
                        DescriptionScreen(previewOfNewListTitle, imageResourse))
                }

                R.id.details_Button_4 -> {
                    val previewOfNewListTitle: String by lazy{
                        resources.getString(R.string.description_Film_4) }
                    val imageResourse = R.drawable.big_score
                    findViewById<TextView>(R.id.name_Film_4).setTextColor(R.color.click)
                    FilmDescriptionAct.start(this,
                        DescriptionScreen(previewOfNewListTitle, imageResourse))
                }

                R.id.details_Button_5 -> {
                    val previewOfNewListTitle: String by lazy{
                        resources.getString(R.string.description_Film_5) }
                    val imageResourse = R.drawable.attraction
                    findViewById<TextView>(R.id.name_Film_5).setTextColor(R.color.click)
                    FilmDescriptionAct.start(this,
                        DescriptionScreen(previewOfNewListTitle, imageResourse))
                }
            }

        }
        button1.setOnClickListener(clickListener)
        button2.setOnClickListener(clickListener)
        button3.setOnClickListener(clickListener)
        button4.setOnClickListener(clickListener)
        button5.setOnClickListener(clickListener)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQ_CODE)
        {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "the invitation is sent!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Wrong! :(", Toast.LENGTH_SHORT).show()
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }



    private companion object {
        const val REQ_CODE = 234
    }
}