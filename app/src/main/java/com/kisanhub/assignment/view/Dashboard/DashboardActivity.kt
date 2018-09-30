package com.kisanhub.assignment.view.Dashboard

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.kisanhub.assignment.R
import com.kisanhub.assignment.view.Dashboard.firstassignment.FirstFragment
import com.kisanhub.assignment.view.Dashboard.secondassignment.SecondFragment
import com.kisanhub.assignment.view.Dashboard.thirdassignment.ThirdFragment
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.app_bar_dashboard.*

class DashboardActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
        FirstFragment.OnFirstFragmentInteractionListener,
        SecondFragment.OnSecondFragmentInteractionListener,
        ThirdFragment.OnThirdFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        nav_view.setCheckedItem(R.id.nav_second)
        openFragment(SecondFragment())
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        when (item.itemId) {
            R.id.nav_first -> {
                fragment = FirstFragment()
            }
            R.id.nav_second -> {
                fragment = SecondFragment()
            }
            R.id.nav_third -> {
                fragment = ThirdFragment()
            }
        }
        openFragment(fragment!!)

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun openFragment(fragment:Fragment){
        if (fragment != null) {
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.content_frame, fragment)
            ft.commit()
        }

    }
}
