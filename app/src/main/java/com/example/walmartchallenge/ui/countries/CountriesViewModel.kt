package com.example.walmartchallenge.ui.countries

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walmartchallenge.data.errorhandling.Result
import com.example.walmartchallenge.data.model.CountriesModel
import com.example.walmartchallenge.data.remote.ApiClient
import com.example.walmartchallenge.data.remote.RetrofitHelper
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class CountriesViewModel() : ViewModel() {

    val countries= MutableLiveData< Result<CountriesModel>>()

    fun getCountries()
    {
        viewModelScope.launch {
            try{
                val retrofit= RetrofitHelper.getRetrofitInstance().create(ApiClient::class.java)
                val result=retrofit.getCountries()
                countries.postValue(Result.Success(result))
            }catch (ioe: IOException){
                countries.postValue(Result.Failure("[IO] error please retry", ioe))
            }catch (he: HttpException){
                countries.postValue(Result.Failure("[HTTP] error please retry", he))
            }

        }
    }

}