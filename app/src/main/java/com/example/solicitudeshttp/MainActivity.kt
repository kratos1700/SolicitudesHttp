package com.example.solicitudeshttp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.solicitudeshttp.JSON.ActivityJSON
import com.example.solicituthttp.Network
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Call
import okhttp3.OkHttpClient
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity(), CompletadoListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // listener per al boton
        btHayRed.setOnClickListener(){
            // codi per validar la red
            if (Network.hayRed(this)){
                Toast.makeText(this,"Si que hi ha conexio!!", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"No hi ha conexio!!", Toast.LENGTH_LONG).show()
            }
        }
        // solicitud de forma asincrona nativa

        btSolicitudHttp.setOnClickListener(){

            if (Network.hayRed(this)){
              //Log.d("bsolicitutOnclic", descargarDatos("https://www.google.es"))
                DescargaURL(this).execute("https://www.google.es")
            }else{
                Toast.makeText(this,"No hi ha conexio!!", Toast.LENGTH_LONG).show()
            }

        }
        // solicitud con libreria Volley
        btVolley.setOnClickListener(){

            if (Network.hayRed(this)){
                solicitudHTTPVolley("https://www.google.es")

            }else{
                Toast.makeText(this,"No hi ha conexio!!", Toast.LENGTH_LONG).show()
            }

        }

        // Solicitud con libreria OKHTTP
     btOKHTTP.setOnClickListener(){
     if (Network.hayRed(this)){
         SolicitudHTTPOKHTTP("https://www.google.es")

     }else{
         Toast.makeText(this,"No hi ha conexio!!", Toast.LENGTH_LONG).show()
     }
 }


        // boto per anar a al activity de probes JSON
        btJson.setOnClickListener(){
            val intent = Intent(this,ActivityJSON::class.java)
            startActivity(intent)
        }


    }


    override fun descargaCompleta(resultado: String) {
        Log.d("DescargaCompleta",resultado)
    }

    // Metode per Volley
    // funciona com una cola
    private  fun solicitudHTTPVolley(url:String){
        val cola = Volley.newRequestQueue(this)
        val solicitud = StringRequest(Request.Method.GET, url, Response.Listener <String>{
            response ->
            try {
                Log.d("SolicitudHTTPVolley", response)
            }catch (e:Exception){

            }
        }, Response.ErrorListener {  })

        cola.add(solicitud)
    }


//Metode per OKHTTP
    //tambe funciona amb una cola, usa el fil principal
 private  fun SolicitudHTTPOKHTTP(url:String){
     val cliente = OkHttpClient()
     val solicitud= okhttp3.Request.Builder().url(url).build()
     cliente.newCall(solicitud).enqueue(object :okhttp3.Callback{
         override fun onFailure(call: Call?, e: IOException){
             //implementa el error
         }

         override fun onResponse(call: Call?, response: okhttp3.Response) {
            val resultado = response.body().string()
             // fa que el codi es pasi al thread principal
             this@MainActivity.runOnUiThread(){
                 try {
                     Log.d("solicitudHTTPOKHTTP", resultado)
                 }catch (e:Exception){

                 }
             }
         }
     })



 }
}


    /*     // :String vol dir que retorna un string
     // creem una Url per obrir una conexio

     @Throws(IOException::class)// capturem error per si perdem conexio o bufer erroni
     private fun descargarDatos(url:String):String {
         //S'hauria de fer amb un Thred amb programacio concurrent per que no doni error de seguretat aquesta funcio, pero ho arreglem amb la variable policy
         // fara que  s'executi al fil principal de l app
             val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
             StrictMode.setThreadPolicy(policy)

         var inputStream:InputStream?=null
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

     }*/
