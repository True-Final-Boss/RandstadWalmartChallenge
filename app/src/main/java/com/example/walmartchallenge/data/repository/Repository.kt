package com.example.walmartchallenge.data.repository

import com.example.walmartchallenge.data.model.CountriesModel
import com.example.walmartchallenge.data.model.Country

interface Repository {
    suspend fun getCountries(): CountriesModel
}