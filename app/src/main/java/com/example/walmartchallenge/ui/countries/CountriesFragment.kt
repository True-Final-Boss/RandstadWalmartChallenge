package com.example.walmartchallenge.ui.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.walmartchallenge.data.model.CountriesModel
import com.example.walmartchallenge.databinding.FragmentCountriesBinding
import com.example.walmartchallenge.ui.errorhandling.ErrorHandling.doIfFailure
import com.example.walmartchallenge.ui.errorhandling.ErrorHandling.doIfSuccess

class CountriesFragment : Fragment() {

    private var _binding: FragmentCountriesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel by viewModels<CountriesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCountriesBinding.inflate(inflater, container, false)
        viewModel.countries.observe(viewLifecycleOwner, Observer {result->
            result.doIfSuccess {items ->
                setUpUI(items)
            }

            result.doIfFailure {message, throwable ->
                showErrorPopup(message ?: "Unknown error message")
            }
        })
        viewModel.getCountries()
        return binding.root
    }

    fun setUpUI(countries: CountriesModel){
        val countriesAdapter= CountriesAdapter(countries)
        binding.rvCountries.apply {
            layoutManager=LinearLayoutManager(context)
            adapter= countriesAdapter
        }
    }

    private fun showErrorPopup(message: String) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Error")
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}