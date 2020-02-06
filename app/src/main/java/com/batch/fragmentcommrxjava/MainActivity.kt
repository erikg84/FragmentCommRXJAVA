package com.batch.fragmentcommrxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init_person()
    }

    private fun init_person() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container_a, TopFragment())
            .addToBackStack(null)
            .commit()

        supportFragmentManager.beginTransaction()
            .add(R.id.container_b, BottomFragment())
            .addToBackStack(null)
            .commit()
    }
}
