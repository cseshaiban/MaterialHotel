package com.hotel.material

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_main.view.*

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportPostponeEnterTransition()

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

        /*Handler().postDelayed(object : Runnable {
            override fun run() {
                supportStartPostponedEnterTransition()
            }
        }, 500)*/

        guest.setOnClickListener { expand() }
        room.setOnClickListener { expand() }
        iv_occupancy_close.setOnClickListener { collapse() }
    }

    fun expand() {
        val initialHeight = ll_occupancy_collapse.measuredHeight
        val targetHeight = convertDpToPixel(this, 155f) //In Dp

        ll_occupancy_expand.visibility = View.VISIBLE

        ll_occupancy_expand.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        ll_occupancy_expand.layoutParams.height = 1

        ll_occupancy_expand.alpha = 0.0f

        val animation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                var height = 0
                if (interpolatedTime == 1f) {
                    height = ViewGroup.LayoutParams.WRAP_CONTENT
                } else {
                    height = initialHeight + (initialHeight * interpolatedTime).toInt()
                    if (height > targetHeight) {
                        ll_occupancy_expand.alpha = 1.0f
                        ll_occupancy_collapse.visibility = View.GONE
                        cancel()
                        return
                    }
                }
                ll_occupancy_expand.layoutParams.height = height
                ll_occupancy_expand.requestLayout()

                ll_occupancy_expand.alpha = interpolatedTime
            }

            override fun willChangeBounds() = true
        }
        animation.duration = 300
        ll_occupancy_expand.startAnimation(animation)
    }

    fun collapse() {
        val initialHeight = ll_occupancy_expand.measuredHeight
        val finalHeight = ll_occupancy_collapse.measuredHeight

        val animation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                val height = initialHeight - (initialHeight * interpolatedTime).toInt();
                if (height >= finalHeight) {
                    ll_occupancy_expand.layoutParams.height = height
                    ll_occupancy_expand.requestLayout()
                } else {
                    ll_occupancy_collapse.visibility = View.VISIBLE
                    ll_occupancy_expand.visibility = View.GONE
                    cancel()
                }
            }
            override fun willChangeBounds() = true
        }
        animation.duration = 300
        ll_occupancy_expand.startAnimation(animation)
    }

    fun convertDpToPixel(context: Context, dp: Float): Float {
        val resources = context.resources
        val metrics = resources.displayMetrics
        return dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

}

