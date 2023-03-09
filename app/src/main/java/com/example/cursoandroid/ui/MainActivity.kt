package com.example.cursoandroid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.cursoandroid.R

class MainActivity : AppCompatActivity() {
    private lateinit var texto:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Lifecycle", "onCreate ")
        setContentView(R.layout.activity_main)

        /*texto = findViewById<TextView>(R.id.txt_output)
        val boton = findViewById<Button>(R.id.btn_action)

        boton.setOnClickListener {
            //texto.text = "Curso Android"
            navegarSegundaActivity()
        }*/

        /*supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.fragment_container_view,PrimerFragment())
        }*/
    }

/*
    private fun navegarSegundaActivity(){
        val intent = Intent(this, SegundaActivity::class.java)
        intent.putExtra("nombre","Curso Android")
        //startActivity(intent)
        startActivityForResult(intent,1)
        initActivityForResult.launch(intent) // --> enviar datos a Segunda Activity y recibir datos de Segunda Activity
    }

    private val initActivityForResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                texto.text = it.data?.getStringExtra("nombre2")
            }
        }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==1){
            if(resultCode== RESULT_OK){
                val nombre = data?.getStringExtra("nombre2")
                texto.text = nombre
            }
        }
    }


    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "onPause")
    }
    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Lifecycle", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "onDestroy")
    }
     */
}