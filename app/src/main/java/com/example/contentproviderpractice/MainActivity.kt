package com.example.contentproviderpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val adapter = MyContactsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myViewModelFactory =
            MyViewModelFactory(application)
        val viewModel = ViewModelProvider(this, myViewModelFactory).get(MainViewModel::class.java)
        setContentView(R.layout.activity_main)
        parentView.layoutManager = LinearLayoutManager(this)
        parentView.adapter = adapter
        viewModel.myContacts.observe(this, Observer {
            adapter.submitList(it)
        })
    }
}
