package com.chsltutorials.dogbreeds.presentation.presenter

import android.util.Log
import com.chsltutorials.dogbreeds.core.bases.BasePresenter
import com.chsltutorials.dogbreeds.model.service.DogCeoApi
import com.chsltutorials.dogbreeds.model.service.DogCeoService
import com.chsltutorials.dogbreeds.presentation.IBreedContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DogDetailPresenter(private val view: IBreedContract.DogDetailView)
                            : BasePresenter(), IBreedContract.DogDetailPresenter {

    override fun fetchBreedImage(breedName : String) {
        val observable = DogCeoService.getRetrofit().create(DogCeoApi::class.java).getBreedImage(breedName)
        view.showLoading()
        val disposable = observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.message != null && it.message != "") {
                    view.showSuccess(it.message!!)
                } else {
                    Log.i("ATENÇÃO", "Lista vazia")
                }
            },
                {
                    view.showError()
                }
            )
        compositeDisposable?.add(disposable)
    }

}