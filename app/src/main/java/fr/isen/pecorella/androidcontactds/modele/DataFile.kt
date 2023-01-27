package fr.isen.pecorella.androidcontactds

import com.google.gson.annotations.SerializedName


data class DataFile (

  @SerializedName("results" ) var results : ArrayList<Results> = arrayListOf(),
  @SerializedName("info"    ) var info    : Info?              = Info()

)