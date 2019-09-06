package com.chsltutorials.dogbreeds.presentation

import com.chsltutorials.dogbreeds.core.bases.IBaseContract
import com.chsltutorials.dogbreeds.core.util.Empty
import com.chsltutorials.dogbreeds.core.util.Failure
import com.chsltutorials.dogbreeds.core.util.Loading
import com.chsltutorials.dogbreeds.core.util.Success

interface IBreedContract {

    interface BreedView : IBaseContract.View<IBreedContract.BreedPresenter>{
        fun showSuccess(resource: Success<List<String>>)
        fun showEmptySuccess(resource: Empty<List<String>>)
        fun showError(resource : Failure)
        fun showLoading(loading: Loading)
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