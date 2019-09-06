package com.chsltutorials.dogbreeds.presentation.presenter

import android.util.Log
import com.chsltutorials.dogbreeds.core.bases.BasePresenter
import com.chsltutorials.dogbreeds.core.util.Empty
import com.chsltutorials.dogbreeds.core.util.Failure
import com.chsltutorials.dogbreeds.core.util.Loading
import com.chsltutorials.dogbreeds.model.repository.DogCeoRepository
import com.chsltutorials.dogbreeds.presentation.IBreedContract
import com.chsltutorials.dogbreeds.core.util.Success
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BreedPresenter(private val view: IBreedContract.BreedView)
                            : BasePresenter(), IBreedContract.BreedPresenter {

    private val repository : DogCeoRepository by lazy { DogCeoRepository() }


    override fun fetchBreedList(){
        val observable = repository.getDogCeo()
        view.showLoading(Loading(null))
        val disposable = observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.message!!.isNotEmpty()){
                    view.showSuccess(resource = Success(it.message!!))
                }else {
                    view.showEmptySuccess(resource = Empty(it.message!!))
                    Log.i("ATENÇÃO","Lista vazia")
                }
            },
            {
                view.showError(Failure(error = Throwable()))
            }
        )
        compositeDisposable?.add(disposable)
    }





}