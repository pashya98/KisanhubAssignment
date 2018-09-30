package com.kisanhub.assignment.view.Dashboard.thirdassignment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kisanhub.assignment.R



class ThirdFragment : Fragment() {

    private var listener: OnThirdFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        activity!!.setTitle(getString(R.string.assigment_third));

        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    fun onButtonPressed(uri: Uri) {
        //listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnThirdFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnThirdFragmentInteractionListener {
       // fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ThirdFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}
