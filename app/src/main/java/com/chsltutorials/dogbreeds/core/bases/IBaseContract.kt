package com.chsltutorials.dogbreeds.core.bases

interface IBaseContract {

    interface View<P : IBaseContract.Presenter> {
        fun createPresenter(): P
    }

    interface Presenter {
        fun onDetach()
        fun onAttach()
    }
}
