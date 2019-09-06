package com.chsltutorials.dogbreeds.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.chsltutorials.dogbreeds.R
import com.chsltutorials.dogbreeds.core.bases.BaseFragment
import com.chsltutorials.dogbreeds.presentation.IBreedContract
import com.chsltutorials.dogbreeds.presentation.presenter.DogDetailPresenter
import kotlinx.android.synthetic.main.fragment_dog_detail.*

class DogDetailFragment : BaseFragment<IBreedContract.DogDetailPresenter>(), IBreedContract.DogDetailView {

    override fun createPresenter() = DogDetailPresenter(this)

    override fun showSuccess(url : String) {
        clDog.visibility = View.VISIBLE
        pbLoadingDog.visibility = View.GONE
        app_bar.visibility = View.VISIBLE
        nsvDog.visibility = View.VISIBLE

       Glide.with(context!!)
           .load(url)
           .into(toolbarImageDog)


    }

    override fun showError() {
        clDog.visibility = View.GONE
        pbLoadingDog.visibility = View.GONE
        app_bar.visibility = View.GONE
        nsvDog.visibility = View.GONE
    }

    override fun showLoading() {
        clDog.visibility = View.GONE
        pbLoadingDog.visibility = View.VISIBLE
        app_bar.visibility = View.GONE
        nsvDog.visibility = View.GONE
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dog_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let{
            val breedName = it.getString("name")
            presenter.fetchBreedImage(breedName!!)
        }
    }

    override fun onResume() {
        super.onResume()
        llDescription.setOnClickListener {
            val ft = fragmentManager?.beginTransaction()
            ft?.replace(R.id.contentFrameActivity, BreedFragment(), BreedFragment.TAG)
            ft?.commit()
        }
    }


}
