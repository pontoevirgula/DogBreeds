package com.chsltutorials.dogbreeds.base

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity


abstract class BaseActivity : AppCompatActivity(){

    fun navigateTo(fragment: Fragment, layoutContainerId : Int, tag : String = ""){
        supportFragmentManager.beginTransaction().add(layoutContainerId, fragment, tag).commit()
    }


}

