package com.example.walmartchallenge.data.remote

import com.example.walmartchallenge.data.model.CountriesModel
import retrofit2.http.GET

interface ApiClient {
    @GET(ApiDetails.ENDPOINT_COUNTRIES)
    suspend fun getCountries(): CountriesModel
}