package com.littleapp.news2.Model

import com.littleapp.news2.Unit.DATA
import java.io.Serializable

class NewsHeadlines : Serializable {
    var source: Source? = null
    var author = DATA.EMPTY
    var title = DATA.EMPTY
    var description = DATA.EMPTY
    var url = DATA.EMPTY
    var urlToImage = DATA.EMPTY
    var publishedAt = DATA.EMPTY
    var content = DATA.EMPTY
}