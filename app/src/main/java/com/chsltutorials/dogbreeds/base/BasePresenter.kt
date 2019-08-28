package com.chsltutorials.dogbreeds.base

import io.reactivex.disposables.CompositeDisposable


abstract class BasePresenter : IBaseContract.Presenter {

    protected var compositeDisposable: CompositeDisposable? = null

    override fun onAttach() {
        compositeDisposable = CompositeDisposable()
    }

    override fun onDetach() {
        if (compositeDisposable != null) {
            compositeDisposable!!.clear()
        }
    }
}
