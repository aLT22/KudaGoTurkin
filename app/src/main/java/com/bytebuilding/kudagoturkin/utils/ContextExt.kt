package com.bytebuilding.kudagoturkin.utils

import android.content.Context
import com.bytebuilding.kudagoturkin.data.model.City
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.nio.charset.Charset
import java.util.*


/**
 * SharedPreferences
 * */
//Defaults
const val SET_CITY_TAG = "SET_DEFAULT_CITY"
const val DEFAULT_CITY_JSON = "{\n" +
        "      \"id\": 0,\n" +
        "      \"name\": \"Moscow\"\n" +
        "    }"

//Preferences name
const val SHARED_PREFERENCES = "KudaGoTurkin"

//Preferences keys
const val KEY_DEFAULT_CITY = "DEFAULT_CITY"

//Assets filename
const val ASSET_CITIES = "cities.json"

//Charsets
const val DEFAULT_CHARSET = "UTF-8"

fun Context.setDefaultCity(defaultCity: City?) {
    /**
     *                 -- defaultCity --
     *            null|                 |not null
     *                v                 v
     *              fetch            save parameter (defaultCity)
     *              from
     *              assets
     *          ---       ---
     *     null|             |not null
     *         v             v
     *      save city       get first city and save him
     *      from default
     *      json const
     * */
    if (defaultCity != null) {
        //Convert object to Json
        val defaultCityJson = Gson().toJson(defaultCity)

        val editor = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE).edit()
        editor.putString(KEY_DEFAULT_CITY, defaultCityJson)

        editor.apply()
    } else {
        //Fetch first city from assets
        val cities = readCitiesFromAssets(this)

        if (cities != null) {
            val listType = object : TypeToken<LinkedList<City>>() {}.type

            val citiesList = Gson().fromJson<LinkedList<City>>(cities, listType)

            //Convert object to Json
            val defaultCityJson = Gson().toJson(citiesList.first)

            val editor = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE).edit()
            editor.putString(KEY_DEFAULT_CITY, defaultCityJson)

            editor.apply()
        } else {
            val editor = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE).edit()
            editor.putString(KEY_DEFAULT_CITY, DEFAULT_CITY_JSON)

            editor.apply()
        }
    }
}

private fun readCitiesFromAssets(context: Context): String? =
    try {
        val inputStream = context.assets.open(ASSET_CITIES)
        val inputStreamSize = inputStream.available()
        val buffer = ByteArray(inputStreamSize)

        inputStream.read(buffer)

        inputStream.close()

        String(buffer, Charset.forName(DEFAULT_CHARSET))
    } catch (th: Throwable) {
        loge(SET_CITY_TAG, "Cities weren't fetched from assets", th)
        null
    }

fun Context.getDefaultCity() =
    Gson()
        .fromJson<City>(
            getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE).getString(KEY_DEFAULT_CITY, DEFAULT_CITY_JSON),
            City::class.java
        )
        .name
/**
 * SharedPreferences
 * */