package com.kisanhub.assignment.view.dashboard.thirdassignment

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.kisanhub.assignment.R
import com.kisanhub.assignment.model.ArticlesDetails
import com.kisanhub.assignment.model.ArticlesInfo
import com.kisanhub.assignment.utility.CommonUtility
import kotlinx.android.synthetic.main.fragment_third.view.*


class ThirdFragment : Fragment(), ThirdPresenterView, ArticlesAdapter.OnShowArticlesDetails {

    private var dialog: ProgressDialog? = null
    private lateinit var mThirdPresenter: ThirdPresenter
    private lateinit var mArticlesRecyclerView: RecyclerView
    private lateinit var mAdapter: ArticlesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        activity!!.setTitle(getString(R.string.assigment_third));
        val view = inflater.inflate(R.layout.fragment_third, container, false)
        mArticlesRecyclerView = view.recycler_view
        mArticlesRecyclerView.setLayoutManager(LinearLayoutManager(activity))
        mArticlesRecyclerView.setItemAnimator(DefaultItemAnimator())
        mThirdPresenter = ThirdPresenter(this, this)
        if (CommonUtility.isNetworkAvailable(activity as Context)) {
            mThirdPresenter.getArticlesListDetails()
        } else {
            showMessage(getString(R.string.no_internet))
        }
        return view
    }

    override fun setArticlesDetails(articlesInfo: ArticlesInfo?) {
        if (articlesInfo != null && !articlesInfo.data.isEmpty()) {
            mAdapter = ArticlesAdapter(activity as Activity, articlesInfo.data, this)
            mArticlesRecyclerView.setAdapter(mAdapter)
        }
    }

    override fun showProgress() {
        if (dialog == null) {
            dialog = ProgressDialog(context)
            dialog?.setCancelable(false)
            dialog?.setMessage(getString(R.string.please_wait))
            dialog?.setIndeterminate(true)
        }
        if (dialog != null) {
            dialog?.show()
        }
    }

    override fun hideProgress() {
        if (dialog!!.isShowing()) {
            dialog?.dismiss()
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun onClickShowDetails(articlesDetails: ArticlesDetails) {

    }

}
