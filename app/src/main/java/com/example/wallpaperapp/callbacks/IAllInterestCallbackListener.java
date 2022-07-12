package com.example.wallpaperapp.callbacks;

import com.example.wallpaperapp.models.InterestModel;

import java.util.ArrayList;
import java.util.List;

public interface IAllInterestCallbackListener {

    void onAllInterestLoadSuccess(ArrayList<InterestModel> allInterestModels);

    void onAllInterestLoadFailed(String message);
}
