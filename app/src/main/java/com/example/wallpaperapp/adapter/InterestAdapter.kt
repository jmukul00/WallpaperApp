package com.example.wallpaperapp.adapter

import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaperapp.R
import com.example.wallpaperapp.models.InterestModel

class InterestAdapter(val context:Context, val interestList: List<InterestModel>): RecyclerView.Adapter<InterestAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_interest, parent, false)
        return InterestAdapter.MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val interestModel = interestList[position]
        Log.d("InterestAdapter", "onDataChange: " + interestModel.interestName)
        holder.txtInterestName.text =  interestModel.interestName
        holder.itemView.setOnClickListener {
            interestModel.isSelected = !interestModel.isSelected

            if (interestModel.isSelected){
                holder.txtInterestName.setBackgroundResource(R.drawable.tab_color)
                holder.txtInterestName.setTextColor(ContextCompat.getColor(context, R.color.white))
            }
            else{
                with(holder) {
                    txtInterestName.setBackgroundResource(R.drawable.tab_bg)
                    txtInterestName.setTextColor(ContextCompat.getColor(context, R.color.primary))
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return interestList.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val txtInterestName = itemView.findViewById<TextView>(R.id.txt_interest_name)
    }

    public fun getSelected(): ArrayList<String>{
        val selected:ArrayList<String> = ArrayList<String>()
        for(i in interestList.indices){
            Log.d("TAG", "getSelected: " + (interestList[i].isSelected))
            if (interestList[i].isSelected) {
                selected.add(interestList[i].interestName!!)
            }
        }
        return selected
    }
}