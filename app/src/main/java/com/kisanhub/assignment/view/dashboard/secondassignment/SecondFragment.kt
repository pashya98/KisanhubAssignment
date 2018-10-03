package com.kisanhub.assignment.view.dashboard.secondassignment

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
import com.kisanhub.assignment.model.FieldsItem
import com.google.android.gms.maps.model.Gap
import com.google.android.gms.maps.model.Dash
import com.google.android.gms.maps.model.Dot
import java.util.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.CameraUpdateFactory
import com.kisanhub.assignment.model.FarmsItem
import com.kisanhub.assignment.utility.CommonUtility

class SecondFragment : Fragment(), OnMapReadyCallback, SecondPresenterView {

    private var dialog: ProgressDialog? = null
    private lateinit var mGoogleMap: GoogleMap
    private lateinit var mapView: MapView
    private lateinit var mSecondPresenter: SecondPresenter

    private val POLYGON_STROKE_WIDTH_PX = 8.0f
    private val PATTERN_DASH_LENGTH_PX = 20.0f
    private val PATTERN_GAP_LENGTH_PX = 20.0f

    private val DASH = Dash(PATTERN_DASH_LENGTH_PX)
    private val GAP = Gap(PATTERN_GAP_LENGTH_PX)

    private val PATTERN_POLYGON_ALPHA = Arrays.asList(GAP, DASH)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        activity!!.setTitle(getString(R.string.assigment_second));
        mSecondPresenter = SecondPresenter(this, this)
        mapView = view?.findViewById(R.id.map_view) as MapView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
        return view
    }


    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onMapReady(map: GoogleMap) {
        mGoogleMap = map;
        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
        if (CommonUtility.isNetworkAvailable(activity as Context)) {
            mSecondPresenter.getFromFieldDetails()
        } else {
            showMessage(getString(R.string.no_internet))
        }
    }

    fun drawMarker(farmsInfo: List<PolygonInfo>) {
        for (item: FarmsItem in farmsInfo[0].farms) {
            item.geometry.coordinates
            mGoogleMap.addMarker(MarkerOptions()
                    .position(LatLng(item.geometry.coordinates[1], item.geometry.coordinates[0])))

        }
    }

    fun drawPolygon(fieldsInfo: List<PolygonInfo>) {
        for (item: FieldsItem in fieldsInfo[0].fields) {
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

    override fun setFromFieldDetails(farmFieldData: List<PolygonInfo>?) {
        if (farmFieldData != null && !farmFieldData.isEmpty()) {
            drawMarker(farmFieldData)
            drawPolygon(farmFieldData)
        }
    }

}

