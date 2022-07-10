package com.dwicandra.suitmediatest.activity.Third

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dwicandra.suitmediatest.R
import com.dwicandra.suitmediatest.activity.MainViewModelFactory
import com.dwicandra.suitmediatest.adapter.LoadingStateAdapter
import com.dwicandra.suitmediatest.adapter.UserPagingAdapter
import com.dwicandra.suitmediatest.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding

    private lateinit var recycler: RecyclerView

    private val thirdViewModel by viewModels<ThirdViewModel> {
        MainViewModelFactory.getInstance(
            this
        )
    }

    private lateinit var userPagingAdapter: UserPagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        userPagingAdapter = UserPagingAdapter()

    }

    override fun onResume() {
        super.onResume()
        getDataPaging()
    }

    private fun getDataPaging() {
        recycler = findViewById(R.id.rv_users)

        val layoutManager = LinearLayoutManager(this)
        recycler.layoutManager = layoutManager

        recycler.adapter = userPagingAdapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                userPagingAdapter.retry()
            }
        )
        userPagingAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                if (positionStart == 0) {
                    layoutManager.scrollToPosition(0)
                }
            }
        })
        thirdViewModel.users.observe(this) {
            userPagingAdapter.submitData(lifecycle, it)
        }
    }
}