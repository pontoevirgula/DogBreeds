package com.chsltutorials.dogbreeds.model.usecase

import android.content.Context
import com.chsltutorials.dogbreeds.model.IDogCeoModelContract

class BreedUseCase(cxt: Context) : IDogCeoModelContract.UseCase {

    private var context = cxt

    init {
        //var service = DogCeoService(context)
    }
}