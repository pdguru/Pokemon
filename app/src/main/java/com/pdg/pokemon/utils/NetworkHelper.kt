package com.pdg.pokemon.utils

import android.os.AsyncTask
import android.util.Log
import java.io.BufferedOutputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

//Original code: git.io/v13pg

class NetworkHelper : AsyncTask<String?, Void, String>() {
    override fun doInBackground(vararg params: String?): String? {
        Log.i("POKEMON", "Downloading from url")
        return downloadUrl(params[0]!!)
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
            var length:Int = 0
            while(stream.read(buffer).also { length = it } >=0) {
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
