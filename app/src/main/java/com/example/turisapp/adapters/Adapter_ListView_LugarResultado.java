package com.example.turisapp.adapters;

import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.TwoLineListItem;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.turisapp.R;
import com.example.turisapp.clases.Mensajes;
import com.example.turisapp.modelos.lugares;

import java.util.ArrayList;

public class Adapter_ListView_LugarResultado implements ListAdapter {

    private final ArrayList<lugares> lugares;

    public Adapter_ListView_LugarResultado(ArrayList<lugares> lugares) {
        this.lugares = lugares;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int i) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return lugares.size();
    }

    @Override
    public Object getItem(int i) {
        return lugares.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TwoLineListItem tv = (TwoLineListItem) LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.simple_list_item_2, viewGroup, false);
        tv.getText1().setText(lugares.get(i).getNombre());
        tv.setContentDescription(String.valueOf(lugares.get(i).getId()));
        return tv;
    }

    @Override
    public int getItemViewType(int i) {
        return i;
    }

    @Override
    public int getViewTypeCount() {
        return (lugares.size()>0)? lugares.size() : 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

}
