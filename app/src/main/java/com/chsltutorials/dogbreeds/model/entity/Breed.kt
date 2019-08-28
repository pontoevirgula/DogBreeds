package com.chsltutorials.dogbreeds.model.entity

data class Breed(var nome : String)

data class DogCeoResponse (var status: String? = null, var message: List<String>? = null)

data class DogCeoImageResponse (var status: String? = null, var message: String? = null)