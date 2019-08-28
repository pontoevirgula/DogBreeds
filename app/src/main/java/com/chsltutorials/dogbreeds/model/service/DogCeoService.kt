package com.chsltutorials.dogbreeds.model.service

import com.chsltutorials.dogbreeds.model.entity.DogCeoImageResponse
import com.chsltutorials.dogbreeds.model.entity.DogCeoResponse
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface DogCeoApi {

    @GET("breeds/list")
    fun getBreedList(): Observable<DogCeoResponse>

    @GET("breed/{breedName}/images/random")
    fun getBreedImage(
                @Path("breedName")breedName : String
    ): Observable<DogCeoImageResponse>
}


object DogCeoService{

    private var retrofit: Retrofit? = null
    private var URL_BASE = "https://dog.ceo/api/"

    fun getRetrofit() : Retrofit{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
        client.addInterceptor(interceptor)

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client.build())
                .build()
        }
        return retrofit!!
    }

}



