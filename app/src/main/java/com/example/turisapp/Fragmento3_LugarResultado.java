package com.example.turisapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.turisapp.modelos.lugares;
import com.example.turisapp.viewmodels.lugarViewModel;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragmento3_LugarResultado#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragmento3_LugarResultado extends Fragment {


    public Fragmento3_LugarResultado() {
        // Required empty public constructor
    }


    public static Fragmento3_LugarResultado newInstance(String param1, String param2) {
        Fragmento3_LugarResultado fragment = new Fragmento3_LugarResultado();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_fragmento3__lugar_resultado, container, false);

        TextView txtDepartamento = (TextView) vista.findViewById(R.id.fragmento3_lugar_txtDepartamento);
        TextView txtMunicipio = (TextView) vista.findViewById(R.id.fragmento3_lugar_txtMunicipio);
        TextView txtTipo = (TextView) vista.findViewById(R.id.fragmento3_lugar_txtTipo);
        TextView txtPresupuesto = (TextView) vista.findViewById(R.id.fragmento3_lugar_txtPresupuesto);
        TextView txtComentarios = (TextView) vista.findViewById(R.id.fragmento3_lugar_txtComentarios);

        lugarViewModel lugarvm = ViewModelProviders.of(getActivity()).get(lugarViewModel.class);
        lugarvm.getLugares().observe(getViewLifecycleOwner(), new Observer<lugares>() {
            @Override
            public void onChanged(lugares lugares) {
                txtDepartamento.setText(lugares.getDepartamento());
                txtMunicipio.setText(lugares.getMunicipio());
                txtTipo.setText(lugares.getTipo());
                txtPresupuesto.setText(lugares.getPresupuesto());
                txtComentarios.setText(lugares.getComentarios());
            }
        });

        return vista;
    }
}