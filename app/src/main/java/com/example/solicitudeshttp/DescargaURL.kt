package com.example.solicitudeshttp

import android.os.AsyncTask
import android.os.StrictMode
import java.io.IOException
import java.io.InputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

/**
 * Clase para hacer la peticion Http de forma asincrona, con otro hilo
 */

class DescargaURL(var completadoListener: CompletadoListener?):AsyncTask<String, Void, String>() {
    override fun doInBackground(vararg params: String): String? {
        try {
            return  descargarDatos(params[0])
        }catch (e:IOException){
            return null
        }
    }

    override fun onPostExecute(result: String) {
        try {
            completadoListener?.descargaCompleta(result)
        }catch (e:Exception){

        }
    }

    // :String vol dir que retorna un string
    // creem una Url per obrir una conexio
    @Throws(IOException::class)// capturem error per si perdem conexio o bufer erroni
    private fun descargarDatos(url:String):String {


        var inputStream: InputStream?=null
        try {
            val url= URL(url)
            val conexion= url.openConnection() as HttpURLConnection
            conexion.requestMethod="GET" // obtenim info de la url
            conexion.connect()
            inputStream=conexion.inputStream // guardem les dades  obtingudes al inputstrem
            return  inputStream.bufferedReader().use { it.readText() } //retornem el fluxe de dades en forma de String pel metode it.readText
        }finally {
            if(inputStream!=null){
                inputStream.close()
            }
        }

    }
}