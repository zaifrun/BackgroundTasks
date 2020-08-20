package org.pondar.backgroundtasks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.delay


//Examples of using co-routines and other background tasks methods
//like work manage.

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        viewModel.result.observe(this, androidx.lifecycle.Observer {
            Log.d("new value"," value : ${viewModel.result.value}")

        })

        Log.d("start","Starting coroutines")
        viewModel.updateValue()
        viewModel.getFromServer()
        viewModel.getFromInternet()


    }
}