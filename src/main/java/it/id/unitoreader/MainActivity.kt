package it.id.unitoreader

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.logging.Logger


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtView: TextView = findViewById<TextView>(R.id.txtData)

        UnitoReaderProvider().getDataAsync(object: UnitoReaderResult {
            override fun onResult(data: UnitoData) {
                txtView.text = data.toString()
            }
        })

    }

}