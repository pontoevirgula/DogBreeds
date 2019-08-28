package com.chsltutorials.dogbreeds.presentation.view.activity

import android.os.Bundle
import com.chsltutorials.dogbreeds.base.BaseActivity
import com.chsltutorials.dogbreeds.R
import com.chsltutorials.dogbreeds.presentation.view.fragment.BreedFragment

class BreedActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breeds)

        navigateTo(BreedFragment(),R.id.contentFrameActivity,BreedFragment.TAG)
    }

    override fun onBackPressed() {

    }


}
