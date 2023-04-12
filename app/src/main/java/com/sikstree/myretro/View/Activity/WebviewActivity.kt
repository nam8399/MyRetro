package com.sikstree.myretro.View.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.sikstree.myretro.R
import com.sikstree.myretro.databinding.ActivityWebviewBinding

class WebviewActivity : AppCompatActivity() {
    private lateinit var binding : ActivityWebviewBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityWebviewBinding.inflate(layoutInflater)

        binding.webview.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
        }

        setContentView(binding.root)

        val url = intent.getStringExtra("url")

        binding.webview.loadUrl(url.toString())

    }


    override fun onBackPressed() {
        if (binding.webview.canGoBack())
        {
            binding.webview.goBack()
        }
        else
        {
            finish()
        }
    }

}