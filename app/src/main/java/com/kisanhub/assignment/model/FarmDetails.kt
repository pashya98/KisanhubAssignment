package com.kisanhub.assignment.model



data class PolygonInfo(val farms: List<FarmsItem>,
                val fields: List<FieldsItem>)

data class FarmsItem(val geometry: FarmGeometry,
                       val type: String,
                       val properties: Properties)

data class FieldsItem(val geometry: FieldGeometry,
                           val type: String,
                           val properties: Properties)

data class FarmGeometry(val coordinates: List<Double>,
                    val type: String)

data class FieldGeometry(val coordinates: List<List<List<Double>>>,
                    val type: String)

data class Properties(val farmLatitude: Double,
                      val ownership: Boolean,
                      val farmLongitude: Double,
                      val farmSlug: String,
                      val permission: Boolean,
                      val teamId: Int,
                      val farmLocation: String,
                      val locationSlug: String,
                      val farmName: String)

