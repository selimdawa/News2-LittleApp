package com.littleapp.news2.Activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.littleapp.news2.Model.NewsHeadlines
import com.littleapp.news2.R
import com.littleapp.news2.Unit.DATA
import com.littleapp.news2.Unit.THEME
import com.littleapp.news2.Unit.VOID
import com.littleapp.news2.databinding.ActivityNewsAppDetailsBinding
import java.io.Serializable

class NewsAppDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsAppDetailsBinding
    private var headlines: NewsHeadlines? = null
    private val context: Context = this@NewsAppDetailsActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        THEME.setThemeOfApp(context)
        super.onCreate(savedInstanceState)
        binding = ActivityNewsAppDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })

        headlines = intent.serializable(DATA.DATA)
        binding.nameSpace.setText(R.string.post_details)
        binding.back.setOnClickListener { onBackPressedDispatcher.onBackPressed() }

        headlines?.let { data ->
            binding.title.text = data.title
            binding.author.text = data.author
            binding.time.text = data.publishedAt
            binding.detail.text = data.description
            binding.content.text = data.content

            data.urlToImage.let { url ->
                VOID.Glide(context, url, binding.image)
            }
        }
    }
}

inline fun <reified T : Serializable> Intent.serializable(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getSerializableExtra(key, T::class.java)
    } else {
        @Suppress("DEPRECATION")
        getSerializableExtra(key) as? T
    }
}