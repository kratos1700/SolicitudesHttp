package com.example.solicitudeshttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.solicituthttp.Network
import kotlinx.android.synthetic.main.activity_main.*
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
}