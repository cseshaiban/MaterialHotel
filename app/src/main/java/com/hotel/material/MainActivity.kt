package com.hotel.material

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.app.AppCompatActivity
import android.view.View

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_main.view.*

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        destination.iv_icon.setImageResource(R.drawable.ic_location_on_black_24dp)
        destination.tv_hint.setText("Destination")
        destination.tv_result.setText("Yogyakarta")

        dates.iv_icon.setImageResource(R.drawable.ic_date_range_black_24dp)
        dates.tv_hint.setText("Dates")
        dates.tv_result.setText("31 Dec 2018")

        guest.iv_icon.setImageResource(R.drawable.ic_person_outline_black_24dp)
        guest.tv_hint.setText("Guest")
        guest.tv_result.setText("1")

        room.iv_icon.setImageResource(R.drawable.ic_airline_seat_legroom_extra_black_24dp)
        room.tv_hint.setText("Room")
        room.tv_result.setText("1")

        filter.iv_icon.setImageResource(R.drawable.ic_filter_list_black_24dp)
        filter.tv_hint.setText("Filter")
        filter.tv_result.setText("Rp 310000 - 410000")

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Hotels")

        destination.setOnClickListener {
            ll_transition.transitionName = "ll_transition"
            val intent = Intent(this, LocationActivity::class.java);
            val viewPairs = arrayOf<Pair<View, String>>(Pair.create(ll_transition, "ll_transition"))
            this@MainActivity.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity, *viewPairs).toBundle())
        }

        guest.setOnClickListener { expandView() }
        room.setOnClickListener { expandView() }
        iv_occupancy_close.setOnClickListener { collapseView() }
    }

    fun expandView() {
        ll_occupancy_collapse.visibility = View.GONE
        ll_occupancy_expand.visibility = View.VISIBLE
    }

    fun collapseView() {
        ll_occupancy_expand.visibility = View.GONE
        ll_occupancy_collapse.visibility = View.VISIBLE
    }

}

