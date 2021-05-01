package com.hk.intentsall

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.hk.intentsall.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {


    private lateinit var dataBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        dataBinding.btnOpenEmail.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_open_email -> {
                openEmail()
            }
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun openEmail() {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.type = "*/*"
        val arrayOfReceipients = arrayListOf<String>()
        arrayOfReceipients.add(dataBinding.txtEmailTo.text.toString())
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOfReceipients);
        intent.putExtra(Intent.EXTRA_TEXT, dataBinding.txtEmailBody.text.toString());
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent);
        }
    }
}