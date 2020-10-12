package com.example.testapp

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.drawable.ClipDrawable
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URLEncoder


class MainActivity : AppCompatActivity() {
    fun sendPostRequest(xp:String, yp:String, zp:String) {
//        var reqParam = URLEncoder.encode("x", "UTF-8") + "=" + URLEncoder.encode(xp, "UTF-8")
//        reqParam += "&" + URLEncoder.encode("y", "UTF-8") + "=" + URLEncoder.encode(yp, "UTF-8")
//        reqParam += "&" + URLEncoder.encode("z", "UTF-8") + "=" + URLEncoder.encode(zp, "UTF-8")
        val BASE_URL = "http://doge.saargrin.com:8080/generate_doge_get"
        val uri = Uri.parse(BASE_URL)
            .buildUpon()
            .appendQueryParameter("x",xp)
            .appendQueryParameter("y",yp)
            .appendQueryParameter("z",zp)
            .build()

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(uri.toString())
            .get()
            .build()

        AsyncTask.execute {
            val response = client.newCall(request).execute()
            val successful = response.isSuccessful.toString()
//            val res = response.code.toString()
//            val body = response.body
//            runOnUiThread {
//                Toast.makeText(this, "Code: $res $uri", Toast.LENGTH_SHORT).show()
//            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // finding the button
        val showButton = findViewById<Button>(R.id.generate)
        val dogei: ImageView = findViewById(R.id.dogeImage)

        // finding the edit text
        val xin = findViewById<EditText>(R.id.editText1)
        val yin = findViewById<EditText>(R.id.editText2)
        val zin = findViewById<EditText>(R.id.editText3)

        // Setting On Click Listener
        showButton.setOnClickListener {

            // Getting the user input
            val xtext = xin.text.toString()
            val ytext = yin.text.toString()
            val ztext = zin.text.toString()
//            sendPostRequest(xp=xtext.toString(), yp=ytext.toString(),zp=ztext.toString())
            val BASE_URL = "http://doge.saargrin.com:8080/generate_doge_get"
            val uri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendQueryParameter("x",xtext)
                .appendQueryParameter("y",ytext)
                .appendQueryParameter("z",ztext)
                .build()
            val furi = URLEncoder.encode(uri.toString(),"utf-8")
            val picasso = Picasso.get()
            Toast.makeText(this, "Code: $furi", Toast.LENGTH_SHORT).show()
            picasso.load(uri).into(dogeImage)
        }
        dogei.setOnClickListener {
            Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show()
            val xtext = xin.text.toString()
            val ytext = yin.text.toString()
            val ztext = zin.text.toString()
//            sendPostRequest(xp=xtext.toString(), yp=ytext.toString(),zp=ztext.toString())
            val BASE_URL = "http://doge.saargrin.com:8080/generate_doge_get"
            val uri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendQueryParameter("x",xtext)
                .appendQueryParameter("y",ytext)
                .appendQueryParameter("z",ztext)
                .build()
            val furi = URLEncoder.encode(uri.toString(),"utf-8")
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("simple text",uri.toString())
            clipboard.setPrimaryClip(clip)

        }
    }
}
