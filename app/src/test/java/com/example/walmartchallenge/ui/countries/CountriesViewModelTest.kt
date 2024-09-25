package com.example.walmartchallenge.ui.countries

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.walmartchallenge.data.model.CountriesModel
import com.example.walmartchallenge.data.model.Country
import com.example.walmartchallenge.data.model.Currency
import com.example.walmartchallenge.data.model.Language
import com.example.walmartchallenge.data.remote.ApiClient
import com.example.walmartchallenge.ui.errorhandling.ErrorHandling.doIfSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CountriesViewModelTest{

    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var apiClient: ApiClient


    @Before
    fun setUP() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun testGetCountries_emptyResponse()= runTest {
        val countries=CountriesModel()

        Mockito.`when`(apiClient.getCountries()).thenReturn(countries)
        val viewModel=CountriesViewModel()
        viewModel.getCountries()

        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        viewModel.countries.value?.doIfSuccess {  assertEquals(0,it.size) }

    }

    @Test
    fun `getCountries with vaild response`()= runTest {
        val countries= listOf(
            Country(
                name = "india",
                region = "IN",
                code = "91",
                capital = "Indian",
                language = Language("hi", "", "Hindi", ""),
                currency = Currency("INR", "Indian rupee", "₹"),
                demonym = "",
                flag = "https://restcountries.eu/data/ind.svg"
            ),
            Country(
                name = "United Kingdom",
                region = "UK",
                code = "44",
                capital = "London",
                language = Language("en", "", "English", ""),
                currency = Currency("GB", "British pund", "£"),
                demonym = "",
                flag = "https://restcountries.eu/data/gbr.svg"
            )
        )
        val countries_sample= CountriesModel()
        countries_sample.addAll(countries)
        Mockito.`when`(apiClient.getCountries()).thenReturn(countries_sample)
        val viewModel=CountriesViewModel()
        viewModel.getCountries()

        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.countries.value?.doIfSuccess {  assertEquals(2,it.size) }

    }

}

