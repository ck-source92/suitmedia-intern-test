package com.dwicandra.suitmediatest.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dwicandra.suitmediatest.R
import com.dwicandra.suitmediatest.activity.Second.SecondActivity
import com.dwicandra.suitmediatest.database.UserEntity
import com.dwicandra.suitmediatest.databinding.ItemUserBinding
import com.dwicandra.suitmediatest.utils.USER_ID

class UserPagingAdapter :
    PagingDataAdapter<UserEntity, UserPagingAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserEntity) {
            Glide.with(itemView.context)
                .load(user.avatar)
                .error(R.mipmap.ic_launcher)
                .into(binding.ivUser)
            binding.tvFirstName.text = user.firstName
            binding.tvLastName.text = user.lastName
            binding.tvEmail.text = user.email
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, SecondActivity::class.java)
                intent.putExtra(USER_ID, user)
                println("owakawo berapa ${intent}")
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(view)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserEntity>() {
            override fun areItemsTheSame(
                oldItem: UserEntity,
                newItem: UserEntity
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: UserEntity,
                newItem: UserEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}