package com.hk.intentsall

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.hk.intentsall.databinding.ActivityMainBinding
import com.hk.intentsall.databinding.ActivityParkPlusBinding


//www.parkplus.in/monika?order_id=---
class ParkPlusActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityParkPlusBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_park_plus)


        dataBinding.btnSendOrderDetails.setOnClickListener {
            val orderId = intent.data?.getQueryParameters("order_id")
            if (orderId?.isNotEmpty() == true) {
                setResult(
                    RESULT_OK,
                    Intent().putExtra("order_details", "Hello here is order_details.")
                )
            }
            finish()
        }


    }
}