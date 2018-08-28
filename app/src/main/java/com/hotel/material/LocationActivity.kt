package com.hotel.material

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_location.*

class LocationActivity : AppCompatActivity() {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Destinations")
        toolbar?.setNavigationIcon(R.drawable.ic_close_black_24dp)
        toolbar?.setNavigationOnClickListener {
            onBackPressed()
        }
        ll_transition.transitionName = "ll_transition"
    }
}
