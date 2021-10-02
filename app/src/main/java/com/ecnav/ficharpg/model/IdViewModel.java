package com.ecnav.ficharpg.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class IdViewModel extends ViewModel
{
    private int selectedId;

    public void setId(int id)
    {
        selectedId = id;
    }

    public int getSelectedItem()
    {
        return selectedId;
    }
}
