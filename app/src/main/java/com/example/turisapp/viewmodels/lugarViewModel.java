package com.example.turisapp.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.turisapp.modelos.lugares;

public class lugarViewModel extends ViewModel {

    private MutableLiveData <lugares> lugares = new MutableLiveData<>();

    public MutableLiveData<lugares> getLugares() {return lugares;}

    public void setLugares (lugares lugares) {this.lugares.setValue(lugares);}

}
