package com.dwicandra.suitmediatest.activity.First

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.dwicandra.suitmediatest.R
import com.dwicandra.suitmediatest.activity.AuthViewModelFactory
import com.dwicandra.suitmediatest.activity.Second.SecondActivity
import com.dwicandra.suitmediatest.data.auth.User
import com.dwicandra.suitmediatest.databinding.ActivityFirstBinding
import com.google.android.material.snackbar.Snackbar

class FirstActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityFirstBinding
    private val firstViewModel by viewModels<FirstViewModel> { AuthViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        binding.btnCheck.setOnClickListener(this)
        binding.btnNext.setOnClickListener(this)
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    override fun onClick(v: View?) {
        val name = binding.nameEditText.text.toString()
        val palindrome = binding.palindromeEditText.text.toString()

        when (v?.id) {
            R.id.btn_check -> {
                if (isPalindrome(palindrome)) {
                    showSnackBar(binding.root, "True")
                } else {
                    showSnackBar(binding.root, "False")
                }
            }
            R.id.btn_next -> {
                when {
                    TextUtils.isEmpty(name) -> {
                        binding.nameEditText.error = "Field ini tidak boleh kosong"
                    }
                    TextUtils.isEmpty(palindrome) -> {
                        binding.palindromeEditText.error = "Field ini tidak boleh kosong"
                    }
                    !isPalindrome(palindrome) -> {
                        showSnackBar(binding.root, "Should Palindrome")
                    }
                    else -> {
                        if (isPalindrome(palindrome)) {
                            firstViewModel.saveUser(User(name))
                            val intent = Intent(this, SecondActivity::class.java)
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }

    private fun isPalindrome(text: String): Boolean {
        val reverseString = text.reversed().toString()
        return text.equals(reverseString, ignoreCase = true)
    }

    private fun showSnackBar(view: View, message: String) {
        val snack = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE)
        val snackBarView = snack.view
        snackBarView.setBackgroundColor(ContextCompat.getColor(this, R.color.teal_200))
        snack.show()
    }

}