package com.littleapp.news2.Activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.littleapp.news2.Model.NewsHeadlines
import com.littleapp.news2.R
import com.littleapp.news2.Unit.DATA
import com.littleapp.news2.Unit.THEME
import com.littleapp.news2.Unit.VOID
import com.littleapp.news2.databinding.ActivityNewsAppDetailsBinding

class NewsAppDetailsActivity : AppCompatActivity() {

    private var binding: ActivityNewsAppDetailsBinding? = null
    private var headlines: NewsHeadlines? = null
    var context: Context = this@NewsAppDetailsActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        THEME.setThemeOfApp(context)
        super.onCreate(savedInstanceState)
        binding = ActivityNewsAppDetailsBinding.inflate(layoutInflater)
        val view = binding!!.root
        setContentView(view)

        headlines = intent.getSerializableExtra(DATA.DATA) as NewsHeadlines?
        binding!!.toolbar.nameSpace.setText(R.string.post_details)
        binding!!.toolbar.back.setOnClickListener { onBackPressed() }

        binding!!.title.text = headlines!!.title
        binding!!.author.text = headlines!!.author
        binding!!.time.text = headlines!!.publishedAt
        binding!!.detail.text = headlines!!.description
        binding!!.content.text = headlines!!.content

        VOID.Glide(context, headlines!!.urlToImage, binding!!.image)
    }
}