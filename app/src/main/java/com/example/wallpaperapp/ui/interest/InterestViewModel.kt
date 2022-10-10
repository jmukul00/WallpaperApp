package com.example.wallpaperapp.ui.interest

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wallpaperapp.callbacks.IAllInterestCallbackListener
import com.example.wallpaperapp.models.InterestModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class InterestViewModel: ViewModel(), IAllInterestCallbackListener {

    var interestList: MutableLiveData<ArrayList<InterestModel>> = MutableLiveData()
    private val messageError: MutableLiveData<String>? = null
    private var iAllInterestCallbackListener: IAllInterestCallbackListener? = this


    fun getInterestMutableLiveData(): MutableLiveData<ArrayList<InterestModel>>{
        loadInterestList()
        return interestList
    }


    private fun loadInterestList(){
        val interestModelList: ArrayList<InterestModel> = ArrayList()
        val databaseRef = Firebase.database.getReference("Categories")

        val interestData = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(item in snapshot.children){

                    val interestModel: InterestModel? =item.getValue(InterestModel::class.java)
                    interestModelList.add(interestModel!!)
                }
                Log.d("InterestViewModel", "onDataChange: $interestModelList")
                iAllInterestCallbackListener?.onAllInterestLoadSuccess(interestModelList)

            }

            override fun onCancelled(error: DatabaseError) {
               iAllInterestCallbackListener?.onAllInterestLoadFailed(error.message)
            }

        }

        databaseRef.addListenerForSingleValueEvent(interestData)
    }

    override fun onAllInterestLoadSuccess(allInterestModels: ArrayList<InterestModel>) {
        Log.d("InterestViewModel", "onAllInterestLoadSuccess: $allInterestModels")
       interestList.value = allInterestModels
    }

    override fun onAllInterestLoadFailed(message: String?) {
        TODO("Not yet implemented")
    }
}