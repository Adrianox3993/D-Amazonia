package com.example.damaznia;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.damaznia.databinding.FragmentHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeFragment extends Fragment {
    FragmentHomeBinding homeBinding;
    private FirebaseUser usuario;
    private DatabaseReference referencia;
    private String idUsuario;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        usuario = FirebaseAuth.getInstance().getCurrentUser();
        referencia = FirebaseDatabase.getInstance().getReference("Usuarios");
        idUsuario = usuario.getUid();
        referencia.child(idUsuario).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Usuario perfilUsuario = snapshot.getValue(Usuario.class);

                if (perfilUsuario!=null){
                    String nome = perfilUsuario.nome;
                    homeBinding.txtNome.setText(nome);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        // Inflate the layout for this fragment
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = homeBinding.getRoot();
        return view;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        homeBinding=null;
    }
}