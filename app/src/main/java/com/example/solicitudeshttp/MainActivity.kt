package com.example.solicitudeshttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.solicituthttp.Network
import kotlinx.android.synthetic.main.activity_main.*

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
    }
}