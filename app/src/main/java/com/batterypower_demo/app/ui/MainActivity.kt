package com.batterypower_demo.app.ui

import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.batterypower_demo.app.R
import com.batterypower_demo.app.api.ApiInterface
import com.batterypower_demo.app.api.ApiUtiliities
import com.batterypower_demo.app.repo.CountriesRepo
import com.batterypower_demo.app.viewmodel.CountriesViewModel
import com.batterypower_demo.app.viewmodel.CountryViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CountriesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar.visibility = VISIBLE
      //  showBottomNavigation()
        initAllVar()
     //   initlizeDummyData();
    }

    fun initAllVar()
    {
        val apiInterface = ApiUtiliities.getInstance().create(ApiInterface::class.java)
        val coutryRepo = CountriesRepo(apiInterface)
        viewModel = ViewModelProvider(this, CountryViewModelFactory(coutryRepo)).get(CountriesViewModel::class.java)

        viewModel.countries.observe(this) { it ->

            val countryList = ArrayList<String>()

            it.data?.forEach {
                it?.country?.let { it1 -> countryList.add(it1) }
            }
            initlizeCountryData(countryList)
        }
        viewModel.city.observe(this) { it ->

            val cityList = ArrayList<String>()

            it.cities?.forEach {
               // Log.d("aksdjfl1", "city = ${it}")
                it?.let { it1 -> cityList.add(it1) }
            }
            initlizeCityList(cityList)
        }

    }
    private fun initlizeCountryData(countryList :ArrayList<String>) {
        val userAdapter : ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,countryList)
        progressBar.visibility = GONE
        countryListView.adapter = userAdapter
        countryListView.visibility = GONE
        searchView.isIconifiedByDefault = false

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                if(countryList.contains(query))
                {
                    userAdapter.filter.filter(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                userAdapter.filter.filter(newText)
                changeVisbiility()
                return false
            }
        })




        countryListView.setOnItemClickListener { parent, view, position, id ->
            val value = parent.getItemAtPosition(position) as String
            Toast.makeText(this, value, Toast.LENGTH_SHORT).show()
            searchView.setQuery(value, false)
            viewModel.getCityFromCountry(value)
            countryListView.visibility = GONE
            searchCityView.visibility = VISIBLE
            // Do something with the value
        }
    }

    fun changeVisbiility()
    {
        cityListView.visibility = GONE
        countryListView.visibility=VISIBLE
        searchCityView.visibility = GONE
        searchCityView.setQuery("",false)
    }

    private fun initlizeCityList(cityList: ArrayList<String>) {
        val cityAdapter : ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,cityList)
        cityListView.adapter = cityAdapter
        cityListView.visibility = GONE
        searchCityView.isIconifiedByDefault = false
        searchCityView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchCityView.clearFocus()

                if(cityList.contains(query))
                {
                    cityAdapter.filter.filter(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                cityAdapter.filter.filter(newText)
                cityListView.visibility=VISIBLE
                return false
            }
        })

        cityListView.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
            val value = parent.getItemAtPosition(position) as String
            Toast.makeText(this, value, Toast.LENGTH_SHORT).show()
            searchCityView.setQuery(value,false)
            cityListView.visibility = GONE
            searchCityView.clearFocus()
            showBottomNavigation()
        })
    }

    private fun showBottomNavigation() {
        val bottomSheet = BottomSheetDialog()
        bottomSheet.show(
            supportFragmentManager,
            "ModalBottomSheet"
        )
    }
}