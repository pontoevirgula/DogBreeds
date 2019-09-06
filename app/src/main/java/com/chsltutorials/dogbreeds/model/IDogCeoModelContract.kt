package com.chsltutorials.dogbreeds.model

import com.chsltutorials.dogbreeds.model.entity.DogCeoResponse
import io.reactivex.Observable

interface IDogCeoModelContract {


    interface Repository {
        fun getDogCeo() : Observable<DogCeoResponse>
    }

}