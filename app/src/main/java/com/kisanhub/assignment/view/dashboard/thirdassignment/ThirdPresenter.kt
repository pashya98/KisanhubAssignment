package com.kisanhub.assignment.view.dashboard.thirdassignment

import com.kisanhub.assignment.model.ArticlesInfo
import com.kisanhub.assignment.model.PolygonInfo
import com.kisanhub.assignment.remote.ApiClient
import com.kisanhub.assignment.remote.ApiInterface
import com.kisanhub.assignment.view.dashboard.secondassignment.SecondPresenterView
import com.kisanhub.assignment.view.main.MainView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThirdPresenter(private var mainView: MainView, private var thirdView: ThirdPresenterView) {
    fun getArticlesListDetails() {
        mainView.showProgress()

        val apiService = ApiClient.getClient()!!.create(ApiInterface::class.java)

        val call = apiService.articlesInfoService
        call.enqueue(object : Callback<ArticlesInfo> {
            override fun onResponse(call: Call<ArticlesInfo>, response: Response<ArticlesInfo>) {
                mainView.hideProgress()
                thirdView.setArticlesDetails(response.body())
            }

            override fun onFailure(call: Call<ArticlesInfo>, t: Throwable) {
                mainView.hideProgress()
                mainView.showMessage(t.toString())
            }
        })
    }
}