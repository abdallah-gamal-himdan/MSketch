package com.example.msketch.ui.clients;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ClientsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    public int id;
    public String code;

    public String ClientName;
    public String mobile;

    public ClientsViewModel(int _id,String _code , String _ClientName , String _mobile) {


        mText = new MutableLiveData<>();
        mText.setValue("This is clients fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}