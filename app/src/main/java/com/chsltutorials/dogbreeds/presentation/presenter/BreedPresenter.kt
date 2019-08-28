package com.chsltutorials.dogbreeds.presentation.presenter

import android.util.Log
import com.chsltutorials.dogbreeds.base.BasePresenter
import com.chsltutorials.dogbreeds.model.entity.Breed
import com.chsltutorials.dogbreeds.model.service.DogCeoApi
import com.chsltutorials.dogbreeds.model.service.DogCeoService
import com.chsltutorials.dogbreeds.presentation.IBreedContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BreedPresenter(private val view: IBreedContract.BreedView)
                            : BasePresenter(), IBreedContract.BreedPresenter {

    override fun fetchBreedList(){
        val observable = DogCeoService.getRetrofit().create(DogCeoApi::class.java).getBreedList()
        view.showLoading()
        val disposable = observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.message!!.isNotEmpty()){
                    view.showSuccess(it.message!!)
                }else {
                    Log.i("ATENÇÃO","Lista vazia")
                }
            },
            {
                view.showError()
            }
        )
        compositeDisposable?.add(disposable)
    }





}