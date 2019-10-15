package com.example.myapplication.features.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.R
import com.example.myapplication.base.BaseRecyclerViewAdapter
import com.example.myapplication.data.model.User
import kotlinx.android.synthetic.main.item_user.view.*

class HomeAdapter(private val itemClickListener: (Int) -> Unit) : RecyclerView.Adapter<HomeAdapter.Companion.ItemViewHolder>(),
    BaseRecyclerViewAdapter<User> {

    private val users by lazy { mutableListOf<User>() }

    override fun setData(list: List<User>) {
        val callback = UserDiffUtil(users, list)
        val result = DiffUtil.calculateDiff(callback)
        result.dispatchUpdatesTo(this)
        this.users.clear()
        this.users.addAll(list)
    }

    override fun setItem(item: User) {
    }

    override fun removeItem(pos: Int) {
        val temp = users
        temp.removeAt(pos)
        setData(temp)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ItemViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_user, p0, false)
        return ItemViewHolder(view, itemClickListener)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(p0: ItemViewHolder, p1: Int) {
        p0.onBind(users[p1], p1)
    }

    companion object {
        class ItemViewHolder(view: View, private val itemClickListener: (Int) -> Unit) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
            fun onBind(user: User, pos: Int) {
                with(itemView) {
                    Glide.with(context)
                        .load(user.avatarUrl)
                        .apply(RequestOptions.circleCropTransform())
                        .into(ivUserImage)
                    tvUserName.text = user.id.toString() + "-----"+ user.login
                    setOnClickListener { itemClickListener.invoke(pos) }
                }
            }
        }
    }
}

private class UserDiffUtil(@NonNull private var olds: List<User>, @NonNull private val news: List<User>) : DiffUtil.Callback() {

    override fun areItemsTheSame(p0: Int, p1: Int): Boolean {
        return olds[p0].id == news[p1].id
    }

    override fun getOldListSize(): Int = olds.size

    override fun getNewListSize(): Int = news.size

    override fun areContentsTheSame(p0: Int, p1: Int): Boolean {
        return olds[p0] == news[p1]
    }
}