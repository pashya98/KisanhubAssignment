package com.kisanhub.assignment.view.dashboard.secondassignment

import com.kisanhub.assignment.model.PolygonInfo
import com.kisanhub.assignment.remote.ApiClient
import com.kisanhub.assignment.remote.ApiInterface
import com.kisanhub.assignment.view.main.MainView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecondPresenter(private var mainView:MainView,private var secondView: SecondPresenterView){

    fun getFromFieldDetails() {
        mainView.showProgress()

        val apiService = ApiClient.getClient()!!.create(ApiInterface::class.java)

        val call = apiService.polygonInfoService
        call.enqueue(object : Callback<List<PolygonInfo>> {
            override fun onResponse(call: Call<List<PolygonInfo>>, response: Response<List<PolygonInfo>>) {
                mainView.hideProgress()
                secondView.setFromFieldDetails(response.body())
            }

            override fun onFailure(call: Call<List<PolygonInfo>>, t: Throwable) {
                mainView.hideProgress()
                mainView.showMessage(t.toString())


            }
        })
    }
}