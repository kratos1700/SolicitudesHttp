package com.example.solicitudeshttp.JSON

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.solicitudeshttp.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_j_s_o_n.*
import org.json.JSONObject

class ActivityJSON : AppCompatActivity() {
    var listaPersonas:ArrayList<Persona>? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_j_s_o_n)

        var respuesta = "{ \"personas\" : [ " +
                "{" +
                " \"nombre\" : \"Marcos\" ," +
                " \"pais\" : \"México\" ," +
                " \"estado\" : \"soltero\" ," +
                " \"experiencia\" : 5}," +

                "{" +
                " \"nombre\" : \"Agustín\" ," +
                " \"pais\" : \"España\" ," +
                " \"estado\" : \"casado\" ," +
                " \"experiencia\" : 16}" +
                " ]" +
                " }"

        // obtenim estructura
        val json = JSONObject(respuesta)

        val personas = json.getJSONArray("personas")
        listaPersonas= ArrayList()
        for (i in 0..personas.length()-1){
            val nombre = personas.getJSONObject(i).getString("nombre")
            val pais = personas.getJSONObject(i).getString("pais")
            val estado = personas.getJSONObject(i).getString("estado")
            val experiencia = personas.getJSONObject(i).getInt("experiencia")
            //mapeix de valors a un objecte
            // val persona = Persona(nombre, pais, estado, experiencia)
            listaPersonas?.add(Persona(nombre, pais, estado, experiencia))
             //Log.d("PERSONA", persona.nombre)
        }
        Log.d("onCreate",listaPersonas?.count().toString())


        //********************** LIBRERIA GSON *********************
        btGson.setOnClickListener(){
            var respuesta = "{ \"personas\" : [ " +
                    "{" +
                    " \"nombre\" : \"Miquel\" ," +
                    " \"pais\" : \"Espanya\" ," +
                    " \"estado\" : \"soltero\" ," +
                    " \"experiencia\" : 5}," +

                    "{" +
                    " \"nombre\" : \"Jordi\" ," +
                    " \"pais\" : \"España\" ," +
                    " \"estado\" : \"casado\" ," +
                    " \"experiencia\" : 16}" +
                    " ]" +
                    " }"

            val gson = Gson() // creem el objecte Gson
            val res = gson.fromJson(respuesta,Personas::class.java) //li pasem la variable de dades i la clase a mapejar
            Log.d("GSON",res.personas?.count().toString())


        }


    }
}