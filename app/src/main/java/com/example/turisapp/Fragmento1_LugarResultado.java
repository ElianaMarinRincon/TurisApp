package com.example.turisapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TwoLineListItem;

import com.example.turisapp.adapters.Adapter_ListView_LugarResultado;
import com.example.turisapp.clases.lugaresADO;
import com.example.turisapp.modelos.lugares;
import com.example.turisapp.viewmodels.lugarViewModel;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragmento1_LugarResultado#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragmento1_LugarResultado extends Fragment {


    public Fragmento1_LugarResultado() {
        // Required empty public constructor
    }

    public static Fragmento1_LugarResultado newInstance(String param1, String param2) {
        Fragmento1_LugarResultado fragment = new Fragmento1_LugarResultado();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_fragmento1__lugar_resultado, container, false);

        ListView lstLista = (ListView) vista.findViewById(R.id.fragmento1_lugar_listViewResultado);
        lugaresADO dbLugares = new lugaresADO(vista.getContext());
        ArrayList<lugares> lugares =dbLugares.listar();

        Adapter_ListView_LugarResultado adaptador = new Adapter_ListView_LugarResultado(lugares);

        lstLista.setAdapter(adaptador);

        lstLista.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                lugares lug =new lugares();

                TwoLineListItem vista =(TwoLineListItem)view;
                int id = Integer.parseInt(vista.getContentDescription().toString());

                lugaresADO lugaresdb = new lugaresADO(view.getContext());
                lug = lugaresdb.obtenerLugar(id);

                lugarViewModel lugarvm = ViewModelProviders.of(getActivity()).get(lugarViewModel.class);
                lugarvm.setLugares(lug);

            }
        });

        return vista;

    }

}
