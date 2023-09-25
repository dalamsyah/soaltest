package com.example.soaltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.room.Room
import com.example.soaltest.db.AppDatabase
import com.example.soaltest.fragment.LoginFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.frame, LoginFragment())
        ft.commit()

    }
}