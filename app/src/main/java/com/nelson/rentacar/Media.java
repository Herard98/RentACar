package com.nelson.rentacar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;


    public  class Media extends AppCompatActivity {
        SliderView sliderView;
        Button button;
        int[]images={R.drawable.car1,
                R.drawable.car3,
                R.drawable.car1,
                R.drawable.car5,
                R.drawable.car1,
                R.drawable.car3,
                R.drawable.car6,
                R.drawable.car7,
                R.drawable.car8,
                R.drawable.car9,
                R.drawable.car10,
                R.drawable.car11,
                R.drawable.car13,
                R.drawable.car12,
        };
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_media);
            button=findViewById(R.id.btnmedia);
            sliderView=findViewById(R.id.image_slider);
            SliderAdapter sliderAdapter=new SliderAdapter(images);
            sliderView.setSliderAdapter(sliderAdapter);
            sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
            sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
            sliderView.startAutoCycle();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Media.this, login.class));
                    finish();
                }
            });
        }

}