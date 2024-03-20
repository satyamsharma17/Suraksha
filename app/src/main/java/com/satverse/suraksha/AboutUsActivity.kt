package com.satverse.suraksha

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AboutUsActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)

        val back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            @Suppress("DEPRECATION")
            onBackPressed()
        }

        fun openURL(url: String) {
            val uri = Uri.parse(url)

            val intent = Intent(Intent.ACTION_VIEW, uri)

            // Special handling for specific apps
            when {
                url.contains("linkedin.com") -> {
                    intent.setPackage("com.linkedin.android")
                }
                url.contains("instagram.com") -> {
                    intent.setPackage("com.instagram.android")
                }
                url.contains("twitter.com") -> {
                    intent.setPackage("com.twitter.android")
                }
            }

            @Suppress("DEPRECATION") val resolveInfo = packageManager.queryIntentActivities(intent, 0)
            if (resolveInfo.isNotEmpty()) {
                // At least one app is available, let the user choose
                val chooser = Intent.createChooser(intent, "Open with")
                startActivity(chooser)
            } else {
                // No app available, open in browser
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }

        findViewById<ImageView>(R.id.logo_image).setOnClickListener {
            openURL("https://www.github.com/Satverse")
        }

        findViewById<TextView>(R.id.textSuraksha).setOnClickListener {
            openURL("https://www.instagram.com/satverse.tech")
        }

        findViewById<Button>(R.id.twitterButton).setOnClickListener {
            openURL("https://www.twitter.com/satverse_tech")
        }

        findViewById<Button>(R.id.websiteButton).setOnClickListener {
            openURL("https://www.satverse.co/suraksha")
        }

        findViewById<Button>(R.id.rateusButton).setOnClickListener {
            openURL("https://play.google.com/store/apps/details?id=com.satverse.suraksha")
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        mediaPlayer?.stop()
        @Suppress("DEPRECATION")
        super.onBackPressed()
    }
}