package com.example.contentproviderpractice

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val adapter = MyContactsAdapter()
    private lateinit var viewModel: MainViewModel

    private val PERMISSIONS_REQUEST_READ_CONTACTS = 1001;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) !=
                PackageManager.PERMISSION_GRANTED
            ) ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), PERMISSIONS_REQUEST_READ_CONTACTS);
            else showContacts()
        } else showContacts()

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS && grantResults[0] == PackageManager.PERMISSION_GRANTED) showContacts()
        else Toast.makeText(this, "앱의 주소록 접근 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
    }

    private fun showContacts() {
        viewModel = ViewModelProvider(this, MyViewModelFactory(application)).get(MainViewModel::class.java)

        parentView.layoutManager = LinearLayoutManager(this)
        parentView.adapter = adapter
        viewModel.myContacts.observe(this, Observer {
            adapter.submitList(it)
            Log.d("cccccccc", "@Activity - Obserced muContacts's changing")
        })
    }
}
