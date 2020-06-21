package com.example.linkdevtask.util

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Activity.showToast(msg:String)
   {
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
   }
fun Fragment.showToast(msg:String) {
    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
}



