package com.chsltutorials.dogbreeds.model.repository

import com.chsltutorials.dogbreeds.model.IDogCeoModelContract
import com.chsltutorials.dogbreeds.model.entity.DogCeoResponse
import com.chsltutorials.dogbreeds.model.service.DogCeoApi
import com.chsltutorials.dogbreeds.model.service.DogCeoService
import io.reactivex.Observable

class DogCeoRepository : IDogCeoModelContract.Repository {

    override fun getDogCeo(): Observable<DogCeoResponse> =
        DogCeoService.getRetrofit().create(DogCeoApi::class.java).getBreedList()

}