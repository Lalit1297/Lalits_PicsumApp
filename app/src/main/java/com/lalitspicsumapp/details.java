package com.lalitspicsumapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class details extends AppCompatActivity {
    TextView caption;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        caption =   findViewById(R.id.author);
        imageView   =   findViewById(R.id.image);

        Intent intent  =   getIntent();
        String author  =   intent.getStringExtra("author");
        String image   =   intent.getStringExtra("image");

        caption.setText(author);
        Glide.with(this)
                .load(image)
                .into(imageView);

    }
}
