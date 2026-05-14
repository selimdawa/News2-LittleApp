package com.littleapp.news2.Activity

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.littleapp.news2.Adapter.NewsAppAdapter
import com.littleapp.news2.Model.NewsApiResponse
import com.littleapp.news2.Model.NewsHeadlines
import com.littleapp.news2.OnFetchDataListener
import com.littleapp.news2.RequestManger
import com.littleapp.news2.SelectListener
import com.littleapp.news2.R
import com.littleapp.news2.Unit.CLASS
import com.littleapp.news2.Unit.DATA
import com.littleapp.news2.Unit.THEME
import com.littleapp.news2.Unit.VOID
import com.littleapp.news2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), SelectListener, View.OnClickListener {

    private var binding: ActivityMainBinding? = null
    var context: Context = this@MainActivity
    private var adapter: NewsAppAdapter? = null
    private var dialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        THEME.setThemeOfApp(context)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding!!.root
        setContentView(view)

        binding!!.toolbar.back.visibility = View.VISIBLE
        binding!!.toolbar.back.setOnClickListener { onBackPressed() }
        binding!!.toolbar.nameSpace.setText(R.string.news_app)

        dialog = ProgressDialog(context)
        dialog!!.setTitle("Fetching news Articles...")
        dialog!!.show()

        binding!!.search.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                dialog!!.setTitle("Fetching news Articles of $query")
                dialog!!.show()
                val manger = RequestManger(context)
                manger.getNewsHeadlines(listener, "general", query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        binding!!.linearSwitchUser.business.setOnClickListener(this)
        binding!!.linearSwitchUser.entertainment.setOnClickListener(this)
        binding!!.linearSwitchUser.general.setOnClickListener(this)
        binding!!.linearSwitchUser.health.setOnClickListener(this)
        binding!!.linearSwitchUser.science.setOnClickListener(this)
        binding!!.linearSwitchUser.sports.setOnClickListener(this)
        binding!!.linearSwitchUser.technology.setOnClickListener(this)

        val manger = RequestManger(context)
        manger.getNewsHeadlines(listener, "general", null)
    }

    private val listener: OnFetchDataListener<NewsApiResponse?> =
        object : OnFetchDataListener<NewsApiResponse?> {
            override fun onFetchData(list: List<NewsHeadlines?>?, message: String?) {
                if (list!!.isEmpty()) {
                    Toast.makeText(context, "No data found! ", Toast.LENGTH_SHORT).show()
                } else {
                    showNews(list)
                    dialog!!.dismiss()
                }
            }

            override fun onError(message: String?) {
                Toast.makeText(context, "Error! ", Toast.LENGTH_SHORT).show()
            }
        }

    private fun showNews(list: List<NewsHeadlines?>?) {
        //binding.recyclerView.setHasFixedSize(true);
        adapter = NewsAppAdapter(context, list, this)
        binding!!.recyclerView.adapter = adapter
    }

    override fun onNewsClicked(headlines: NewsHeadlines?) {
        VOID.IntentSerializable(context, CLASS.NEWS_APP_DETAILS, DATA.DATA, headlines)
    }

    override fun onClick(view: View) {
        val button = view as TextView
        val category = button.text.toString()
        dialog!!.setTitle("Fetching news Articles of $category")
        dialog!!.show()
        val manger = RequestManger(context)
        manger.getNewsHeadlines(listener, category, null)
    }
}