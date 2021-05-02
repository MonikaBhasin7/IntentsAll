package com.hk.intentsall

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.hk.intentsall.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {


    val TAG = MainActivity::class.java.simpleName
    private lateinit var dataBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        dataBinding.btnOpenEmail.setOnClickListener(this)
        dataBinding.btnOpenGallery.setOnClickListener(this)
        dataBinding.btnOpenWebPage.setOnClickListener(this)
        dataBinding.btnOpenParkplus.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_open_email -> {
                openEmail()
            }
            R.id.btn_open_gallery -> {
                openGallery()
            }
            R.id.btn_open_web_page -> {
                openWebPage()
            }
            R.id.btn_open_parkplus -> {
                openParkPlus()
            }
        }
    }

    val order_details_from_parkplus = 2
    private fun openParkPlus() {
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = Uri.parse("https://www.parkplus.in/monika?order_id=1234")
        //intent.putExtra("order_id", "1234")
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, order_details_from_parkplus)
        }
    }

    private fun openWebPage() {
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = Uri.parse("https:://www.google.com")
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    val PHOTO_RETURNED = 1
    private fun openGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, PHOTO_RETURNED)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PHOTO_RETURNED && resultCode == Activity.RESULT_OK) {
            val thumbnail: Bitmap? = data?.getParcelableExtra("data")
            val fullPhotoUri: Uri? = data?.data
            Log.d(TAG, "photo uri $fullPhotoUri")
        } else if(requestCode == order_details_from_parkplus && resultCode == Activity.RESULT_OK) {
            val order_details = data?.getStringExtra("order_details")
            dataBinding.txtOrderDetailsFromParkplus.text = order_details
        }
        super.onActivityResult(requestCode, resultCode, data)
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