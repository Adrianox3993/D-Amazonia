package com.example.damaznia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Variaveis para implementação das telas iniciais
    private OnboardingAdapter onboardingAdapter;
    private LinearLayout layoutOnboardingIndigadores;
    private MaterialButton btnOnboardingAcao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutOnboardingIndigadores = findViewById(R.id.layoutOnboardingIndicators);
        btnOnboardingAcao = findViewById(R.id.btnOnboardingAcao);

        setOnboardingItems();

        ViewPager2 onboardingViewPager = findViewById(R.id.onboardingViewPager);
        onboardingViewPager.setAdapter(onboardingAdapter);

        setUpOnboardingIndicadores();
        setCurrentOnboardingIndicadores(0);
        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicadores(position);
            }
        });
        btnOnboardingAcao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onboardingViewPager.getCurrentItem() + 1 < onboardingAdapter.getItemCount()){
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem()+1);
                }else {
                    startActivity(new Intent(getApplicationContext(), EntrarActivity.class));
                    finish();
                }
            }
        });

    }

    private void setUpOnboardingIndicadores() {
        ImageView[] indicadores = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,8,8,8);
        for (int i=0; i<indicadores.length; i++){
            indicadores[i] = new ImageView(getApplicationContext());
            indicadores[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboarding_indicador_inativo
            ));
            indicadores[i].setLayoutParams(layoutParams);
            layoutOnboardingIndigadores.addView(indicadores[i]);
        }
    }

    private void setOnboardingItems(){
        List<OnboardingItem> onboardingItems = new ArrayList<>();

        OnboardingItem itemUm = new OnboardingItem();
        itemUm.setTitulo("Sua saúde merece atenção");
        itemUm.setDescricao("Por isso a D’Amazônia Produtos naturais fornece um catálogo " +
                "para você que é nosso cliente de pertinho ou de outro Local do Brazil");
        itemUm.setImage(R.drawable.pg_inicial_icon01);

        OnboardingItem itemDois = new OnboardingItem();
        itemDois.setTitulo("Agora você pode escolher seu produto sem sair de casa");
        itemDois.setDescricao("Aqui você pode escolher os produtos sem correr riscos saindo de casa" +
                " e podendo agendar horário de busca ou entrega do seu pedido");
        itemDois.setImage(R.drawable.pg_inicial_icon021);

        OnboardingItem itemTres = new OnboardingItem();
        itemTres.setTitulo("Cuidar de você agora ficou mais fácil");
        itemTres.setDescricao("Acesse agora o nosso catálogo, coloque seu produto no carrinho e " +
                "agende um horário para buscar o produto na loja física ");
        itemTres.setImage(R.drawable.pg_inicial_icon031);

        onboardingItems.add(itemUm);
        onboardingItems.add(itemDois);
        onboardingItems.add(itemTres);

        onboardingAdapter = new OnboardingAdapter(onboardingItems);
    }
    private void setCurrentOnboardingIndicadores(int index){
        int childCount = layoutOnboardingIndigadores.getChildCount();
        for (int i= 0; i<childCount; i++){
            ImageView imageView = (ImageView) layoutOnboardingIndigadores.getChildAt(i);
            if (i == index){
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicador_ativo)
                );
            }else{
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicador_inativo)
                );
            }
        }
        if (index == onboardingAdapter.getItemCount()-1){
            btnOnboardingAcao.setText("Começar");
        } else {
            btnOnboardingAcao.setText("Continuar");
        }
    }
}