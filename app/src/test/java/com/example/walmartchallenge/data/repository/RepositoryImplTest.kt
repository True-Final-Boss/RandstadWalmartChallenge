package com.example.walmartchallenge.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.walmartchallenge.data.model.CountriesModel
import com.example.walmartchallenge.data.model.Country
import com.example.walmartchallenge.data.model.Currency
import com.example.walmartchallenge.data.model.Language
import com.example.walmartchallenge.data.remote.ApiClient
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class RepositoryImplTest {

    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var apiClient: ApiClient


    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUP() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `test getCountries returns expected result`(): Unit = runBlocking {
        // Arrange: Set up the expected result from the mocked ApiClient
        val repository: Repository = RepositoryImpl(apiClient)

        val expectedCountries = CountriesModel().apply {
            add(
                Country(
                    name = "india",
                    region = "IN",
                    code = "91",
                    capital = "Indian",
                    language = Language("hi", "", "Hindi", ""),
                    currency = Currency("INR", "Indian rupee", "₹"),
                    demonym = "",
                    flag = "https://restcountries.eu/data/ind.svg"
                )
            )
            add(
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
        }
        `when`(apiClient.getCountries()).thenReturn(expectedCountries)

        // Act: Call the method under test
        val result = repository.getCountries()

        // Assert: Verify that the result matches the expected output
        assertEquals(expectedCountries, result)

        // Optional: Verify that the apiClient's getCountries method was called exactly once
        verify(apiClient, times(1)).getCountries()
    }
}