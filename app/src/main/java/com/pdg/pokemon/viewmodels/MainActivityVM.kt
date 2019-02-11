package com.pdg.pokemon.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import android.util.Log
import com.google.gson.Gson
import com.pdg.pokemon.models.BasicPokemon
import com.pdg.pokemon.models.Pokemon
import java.io.BufferedOutputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class MainActivityVM : ViewModel() {

//    lateinit var pokemons: LiveData<List<Pokemon>>

    lateinit var pokemons: MutableLiveData<List<BasicPokemon>>

    init {
        getInitialList()
    }

    private fun getInitialList(): MutableLiveData<List<BasicPokemon>> {
        pokemons = MutableLiveData()

        var resp = NetWorkHelper().execute().get()
        Log.i("POKEMON", "🦄 Response: $resp")

        val json = Gson().fromJson(resp,Pokemon::class.java)
        Log.i("POKEMON", "🦄 as jsonResult: ${json}")

        val i= ArrayList<BasicPokemon>()
        for(p in json.results){
            i.add(p)
        }
        pokemons.value = i


        return pokemons
    }

    internal class NetWorkHelper() : AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg params: Void?): String? {
            Log.i("POKEMON", "Downloading from url")
            return downloadUrl("https://pokeapi.co/api/v2/pokemon?limit=26")
        }

        @Throws(IOException::class)
        fun downloadUrl(address: String): String? {

            var inputStream: InputStream? = null
            try {

                val url = URL(address)
                val conn = url.openConnection() as HttpURLConnection
                conn.readTimeout = 10000
                conn.connectTimeout = 15000
                conn.requestMethod = "GET"
                conn.doInput = true
                conn.connect()

                val responseCode = conn.responseCode
                if (responseCode != 200) {
                    throw IOException("Got response code $responseCode")
                }
                inputStream = conn.inputStream
                return readStream(inputStream!!)

            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                inputStream?.close()
            }
            return null
        }

        @Throws(IOException::class)
        private fun readStream(stream: InputStream): String? {

            val buffer = ByteArray(1024)
            val byteArray = ByteArrayOutputStream()
            var out: BufferedOutputStream? = null
            try {
                out = BufferedOutputStream(byteArray)
                var length: Int
                while (stream.read(buffer).also { length = it } >= 0) {
                    out.write(buffer, 0, length)
                }
                out.flush()
                return byteArray.toString()
            } catch (e: IOException) {
                e.printStackTrace()
                return null
            } finally {
                out?.close()
            }
        }
    }
}

