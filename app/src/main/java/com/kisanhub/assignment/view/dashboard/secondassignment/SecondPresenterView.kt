package com.kisanhub.assignment.view.dashboard.secondassignment

import com.kisanhub.assignment.model.PolygonInfo
import com.kisanhub.assignment.view.main.MainView

interface SecondPresenterView: MainView{
    fun setFromFieldDetails(farmFieldData:List<PolygonInfo>?)
}