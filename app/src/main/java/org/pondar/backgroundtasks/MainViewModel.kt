package org.pondar.backgroundtasks

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class MainViewModel(application:Application) : AndroidViewModel(application) {

    var result : MutableLiveData<String> = MutableLiveData("Start")

    suspend fun loadFromServer()
    {
        delay(5000)
    }

    suspend fun getResultFromInternet() : String
    {

        delay(15000)
        return "News from the Internet"
    }


    suspend fun getResultFromInternet2() : String {


            delay(20000)
            return " : Trumps loses election!t"

    }


    //will NOT bloack the main thread - but will resume after 10 seconds.
    fun updateValue() {
        // launch a coroutine in viewModelScope
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                    /// bla bla

            }
        }
        viewModelScope.launch {
            // suspend this coroutine for one second
            delay(10000)
            // resume in the main dispatcher
            // _snackbar.value can be called directly from main thread
            result.postValue("Waited 10 seconds")
        }
    }

    fun getFromServer()
    {
        //we want to call the loadFromInternet function, but we have indicated that this
        //is doing some heavy work with the suspend keyword.
        //loadFromInternet()  //not possible to call directly
        viewModelScope.launch {
            loadFromServer() //this is possible
            result.postValue("loadfromServer is done")

        }
    }

    fun getFromInternet()
    {
        viewModelScope.launch {
            val fromNet = async {
            getResultFromInternet()
        }
            val fromNet2 = async {
                getResultFromInternet2()
            }

            result.postValue(fromNet.await()+fromNet2.await())
        }

    }

}