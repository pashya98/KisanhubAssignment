package com.kisanhub.assignment.view.Dashboard.firstassignment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kisanhub.assignment.R

class FirstFragment : Fragment(){

    private var listener: OnFirstFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        activity!!.setTitle(getString(R.string.assigment_first));
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
       // listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFirstFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFirstFragmentInteractionListener {
        // TODO: Update argument type and name
        //fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                FirstFragment().apply {
                    arguments = Bundle().apply {
//                        putString(ARG_PARAM1, param1)
//                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
