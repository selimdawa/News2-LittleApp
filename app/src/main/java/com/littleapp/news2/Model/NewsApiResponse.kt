package com.littleapp.news2.Model

import java.io.Serializable

class NewsApiResponse : Serializable {
    var status: String? = null
    var totalResults = 0
    var articles: List<NewsHeadlines>? = null
}