package com.chsltutorials.dogbreeds.base

interface IBaseContract {

    interface View<P : IBaseContract.Presenter> {
        fun createPresenter(): P
    }

    interface Presenter {
        fun onDetach()
        fun onAttach()
    }
}
