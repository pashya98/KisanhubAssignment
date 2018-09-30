package com.kisanhub.assignment.view.Dashboard.secondassignment

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*

import com.kisanhub.assignment.R
import com.kisanhub.assignment.model.PolygonInfo
import com.kisanhub.assignment.remote.ApiClient
import com.kisanhub.assignment.remote.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.kisanhub.assignment.model.FieldsItem
import com.google.android.gms.maps.model.Gap
import com.google.android.gms.maps.model.Dash
import com.google.android.gms.maps.model.Dot
import java.util.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.CameraUpdateFactory
import com.kisanhub.assignment.model.FarmsItem
import com.kisanhub.assignment.utility.CommonUtility


class SecondFragment : Fragment(), OnMapReadyCallback {
    private var dialog: ProgressDialog? = null
    private var listener: OnSecondFragmentInteractionListener? = null
    private lateinit var mGoogleMap: GoogleMap
    var mapView: MapView? = null
    var polygonInfo: List<PolygonInfo>? = null
    private val COLOR_BLACK_ARGB = -0x1000000
    private val COLOR_WHITE_ARGB = -0xFF00000
    private val POLYGON_STROKE_WIDTH_PX = 8.0f
    private val PATTERN_DASH_LENGTH_PX = 20.0f
    private val PATTERN_GAP_LENGTH_PX = 20.0f

    private val DOT = Dot()
    private val DASH = Dash(PATTERN_DASH_LENGTH_PX)
    private val GAP = Gap(PATTERN_GAP_LENGTH_PX)

    private val PATTERN_POLYGON_ALPHA = Arrays.asList(GAP, DASH)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        activity!!.setTitle(getString(R.string.assigment_second));
        mapView = view?.findViewById(R.id.map_view) as MapView
        mapView?.onCreate(savedInstanceState)
        mapView?.getMapAsync(this)

        dialog = ProgressDialog(context)
        dialog?.setCancelable(false)
        dialog?.setMessage(getString(R.string.please_wait))
        dialog?.setIndeterminate(true)
        return view
    }


    fun onButtonPressed(uri: Uri) {
        //  listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSecondFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnSecondFragmentInteractionListener {
        // TODO: Update argument type and name
        // fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                SecondFragment().apply {
                    arguments = Bundle().apply {
                        //                        putString(ARG_PARAM1, param1)
//                        putString(ARG_PARAM2, param2)
                    }
                }
    }


    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onMapReady(map: GoogleMap) {
        mGoogleMap = map;
        // googleMap.addMarker(MarkerOptions().position(LatLng(0.0, 0.0)).title("Marker"))
        // mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
        //  mGoogleMap.addMarker(MarkerOptions().position(/*some location*/));
        // mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(/*some location*/, 10));
        if(CommonUtility.isNetworkAvailable(activity as Context)){
            getMovieDetials()
        }else{
            Toast.makeText(activity,getString(R.string.no_internet),Toast.LENGTH_LONG).show()
        }

    }

    fun drawMarker(){
        if (polygonInfo != null && !polygonInfo!!.isEmpty()) {

            for (item: FarmsItem in polygonInfo!![0].farms) {
                item.geometry.coordinates
                var marker:Marker = mGoogleMap.addMarker(MarkerOptions()
                        .position(LatLng(item.geometry.coordinates[1], item.geometry.coordinates[0])))
                        //.setTag(0));
            }
        }
    }

    fun drawPolygon() {
        if (polygonInfo != null && !polygonInfo!!.isEmpty()) {
            for (item: FieldsItem in polygonInfo!![0].fields) {
                val rectOptions = PolygonOptions()
                for (coordinates: List<Double> in item.geometry.coordinates[0]) {
                    rectOptions.add(LatLng(coordinates[1], coordinates[0]))
                }
                val polygon = mGoogleMap.addPolygon(rectOptions)
                polygon.setStrokePattern(PATTERN_POLYGON_ALPHA);
                polygon.setStrokeWidth(POLYGON_STROKE_WIDTH_PX);
                polygon.setStrokeColor(Color.BLACK);
                polygon.setFillColor(Color.GREEN);
                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(item.geometry.coordinates[0].get(0).get(1), item.geometry.coordinates[0].get(0).get(0)), 11f))
            }
        }
    }


    private fun getMovieDetials() {

        dialog?.show()

        val apiService = ApiClient.getClient()!!.create(ApiInterface::class.java)

        val call = apiService.polygonInfoService
        call.enqueue(object : Callback<List<PolygonInfo>> {
            override fun onResponse(call: Call<List<PolygonInfo>>, response: Response<List<PolygonInfo>>) {
                if (dialog!!.isShowing()) {
                    dialog!!.dismiss()
                }
                polygonInfo = response.body();
                drawPolygon()
                drawMarker()
            }

            override fun onFailure(call: Call<List<PolygonInfo>>, t: Throwable) {
                Toast.makeText(activity, t.toString(), Toast.LENGTH_LONG).show()
                if (dialog!!.isShowing()) {
                    dialog?.dismiss()
                }
            }
        })
    }
}

