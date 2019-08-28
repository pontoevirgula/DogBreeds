package com.chsltutorials.dogbreeds.presentation

import com.chsltutorials.dogbreeds.base.IBaseContract

interface IBreedContract {

    interface BreedView : IBaseContract.View<IBreedContract.BreedPresenter>{
        fun showSuccess(names: List<String>)
        fun showError()
        fun showLoading()
    }

    interface DogDetailView : IBaseContract.View<IBreedContract.DogDetailPresenter>{
        fun showSuccess(url : String)
        fun showError()
        fun showLoading()
    }

    interface BreedPresenter : IBaseContract.Presenter{
        fun fetchBreedList()
    }

    interface DogDetailPresenter : IBaseContract.Presenter{
        fun fetchBreedImage(breedName : String)
    }

}