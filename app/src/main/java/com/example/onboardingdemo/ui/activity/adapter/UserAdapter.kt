package com.example.onboardingdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout.view.*

class UserAdapter(private var dataList: List<DataModel>, private  val context: Context) :
        RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var email : TextView
        var img : ImageView
        var firstname : TextView

        init {
            email = itemView.emailId
            img = itemView.item_Image
            firstname = itemView.first_name
        }
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val detail = dataList[position]
        holder.email.text = detail.email
        Glide.with(context).load(dataList[position].img).into(holder.img)
        holder.firstname.text = detail.fname

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}