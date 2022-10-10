package com.example.wallpaperapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaperapp.R

class InterestItemAdapter(
    val context: Context,
    private val interestItemList: ArrayList<String>,
    private val interestItemSelectedListener: InterestItemSelectedListener
) : RecyclerView.Adapter<InterestItemAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_interest_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val interestItem = interestItemList[position]
        var currentItem = interestItemList[0]
        Log.d("TAG", "Printed: $interestItem")
        holder.txtSelectedInterestName.text = interestItem
        holder.itemView.setOnClickListener {
            Log.d("TAG", "InterestItem: $interestItem")
            Log.d("TAG", "CurrentItemBefore: $currentItem")
            if (currentItem != interestItem){
                currentItem = interestItem
                interestItemSelectedListener.onInterestItemSelected(interestItem)
                Log.d("TAG", "CurrentItemAfter: $currentItem")
            }
            //interestItemSelectedListener.onInterestItemSelected(interestItem)
        }
    }

    override fun getItemCount(): Int {
        return interestItemList.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtSelectedInterestName: TextView = itemView.findViewById(R.id.txtSelectedInterestName)
    }


}


interface InterestItemSelectedListener {
    fun onInterestItemSelected(name:String)
}