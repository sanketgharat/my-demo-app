package com.sg.mydemoapp.ui.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.sg.mydemoapp.R
import com.sg.mydemoapp.data.local.entity.User
import com.sg.mydemoapp.utils.CommonUtil
import com.sg.mydemoapp.utils.Logger

class UsersRecyclerAdapter(private var list: List<User>, private val onUserClickListener: OnUserClickListener) :
    RecyclerView.Adapter<UsersRecyclerAdapter.MyViewHolder>() {

    companion object {
        val TAG = "UsersRecyclerAdapter"
    }

    class MyViewHolder(view: View) : ViewHolder(view) {
        // Define click listener for the ViewHolder's View
        val tv_username: TextView = view.findViewById(R.id.tv_user_name)
        val tv_email: TextView = view.findViewById(R.id.tv_email)
        val iv_dp: ImageView = view.findViewById(R.id.iv_dp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.users_item, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = list.get(position)
        user?.let {
            val context = holder.iv_dp.context
            holder.tv_username.text = user.name
            holder.tv_email.text = user.email
            val photoURL = user.getPhotoUrl()
            Glide.with(context)
                .load(photoURL)
                .placeholder(R.drawable.baseline_person_24)
                .circleCrop()
                .into(holder.iv_dp)
            val color = CommonUtil.getColor()
            Logger.d(TAG, "Random color at $position : $color -$photoURL")
            //holder.iv_dp.setColorFilter(color)
        }

        holder.itemView.setOnClickListener {
            onUserClickListener?.let {
                onUserClickListener.onclick(list[holder.absoluteAdapterPosition])
            }
        }

    }

    fun updateList(listToUpdate: List<User>) {
        list = listToUpdate
        notifyDataSetChanged()
    }

    interface OnUserClickListener {
        fun onclick(user: User)
    }
}