package com.dwicandra.suitmediatest.activity.Second

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.dwicandra.suitmediatest.activity.AuthViewModelFactory
import com.dwicandra.suitmediatest.activity.Third.ThirdActivity
import com.dwicandra.suitmediatest.database.UserEntity
import com.dwicandra.suitmediatest.databinding.ActivitySecondBinding
import com.dwicandra.suitmediatest.utils.USER_ID


class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private var userEntity: UserEntity? = null
    private val secondViewModel by viewModels<SecondViewModel> {
        AuthViewModelFactory.getInstance(
            this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupViewModel()
        getSelectedUser()
        binding.btnChooseUser.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getSelectedUser() {
        userEntity = intent.getParcelableExtra(USER_ID)
        userEntity?.let {
            binding.tvSelectedUser.text = it.firstName
        }
        binding.tvSelectedUser.visibility = VISIBLE
    }

    private fun setupViewModel() {
        secondViewModel.getUser().observe(this) { user ->
            binding.tvName.text = user.name
        }
    }
}