package com.littleapp.news2

import com.littleapp.news2.Model.NewsHeadlines

interface OnFetchDataListener<NewsApiResponse> {
    fun onFetchData(list: List<NewsHeadlines?>?, message: String?)
    fun onError(message: String?)
}