package com.kisanhub.assignment.view.dashboard.thirdassignment

import com.kisanhub.assignment.model.ArticlesInfo
import com.kisanhub.assignment.model.PolygonInfo
import com.kisanhub.assignment.view.main.MainView

interface ThirdPresenterView :MainView{
    fun setArticlesDetails(articlesInfo: ArticlesInfo?)
}