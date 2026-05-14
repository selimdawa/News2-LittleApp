package com.littleapp.news2

import com.littleapp.news2.Model.NewsHeadlines

interface SelectListener {
    fun onNewsClicked(headlines: NewsHeadlines?)
}