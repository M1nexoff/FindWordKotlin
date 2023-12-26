package com.example.findwordkotlin.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.findwordkotlin.R
import com.example.findwordkotlin.domain.AppController
import com.example.findwordkotlin.presentation.main.MainActivity


class Finish : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)
        findViewById<View>(R.id.finish).setOnClickListener { v: View? -> restartGame() }
        findViewById<View>(R.id.menu).setOnClickListener { v: View? -> menu() }
    }

    private fun restartGame() {
        val appController: AppController = AppController.getInstance(this as Context)
        appController.saveLastLevel(0)
        val intent = Intent(this@Finish, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun menu() {
        val appController: AppController = AppController.getInstance(this as Context)
        appController.saveLastLevel(0)
        finish()
    }
}