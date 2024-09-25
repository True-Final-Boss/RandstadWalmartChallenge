package com.example.walmartchallenge.data.repository

import com.example.walmartchallenge.data.model.CountriesModel
import com.example.walmartchallenge.data.model.Country
import com.example.walmartchallenge.data.remote.ApiClient

class RepositoryImpl(
    val apiClient: ApiClient
) : Repository {
    override suspend fun getCountries(): CountriesModel {
        return apiClient.getCountries()
    }
}