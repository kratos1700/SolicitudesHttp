package com.example.solicitudeshttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Toast
import com.example.solicituthttp.Network
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
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
        // solicitud de forma nativa

        btSolicitudHttp.setOnClickListener(){

            if (Network.hayRed(this)){
              Log.d("bsolicitutOnclic", descargarDatos("https://www.google.es"))

            }else{
                Toast.makeText(this,"No hi ha conexio!!", Toast.LENGTH_LONG).show()
            }

        }


    }
        // :String vol dir que retorna un string
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

    }
}