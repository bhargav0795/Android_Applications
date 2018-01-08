package com.example.akhilajana.inclass10_group22;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;

public class SelectAvatarActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton avatarf1,avatarf2,avatarf3;
    ImageButton avatarm1,avatarm2,avatarm3;
    String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_avatar);

        avatarf1 = (ImageButton) findViewById(R.id.avatarf1);
        avatarf2 = (ImageButton) findViewById(R.id.avatarf2);
        avatarf3 = (ImageButton) findViewById(R.id.avatarf3);
        avatarm1 = (ImageButton) findViewById(R.id.avatarm1);
        avatarm2 = (ImageButton) findViewById(R.id.avatarm2);
        avatarm3 = (ImageButton) findViewById(R.id.avatarm3);

        avatarf1.setOnClickListener(this);
        avatarf2.setOnClickListener(this);
        avatarf3.setOnClickListener(this);
        avatarm1.setOnClickListener(this);
        avatarm2.setOnClickListener(this);
        avatarm3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
         switch(view.getId()){
            case R.id.avatarf1:{
                imagePath = "R.drawable.avatar_f_1";
                break;
        }
             case R.id.avatarf2:{
                 imagePath = "R.drawable.avatar_f_2";
                 break;
             }
             case R.id.avatarf3:{
                 imagePath = "R.drawable.avatar_f_3";
                 break;
             }
             case R.id.avatarm1:{
                 imagePath = "R.drawable.avatar_m_1";
                 break;
             }
             case R.id.avatarm2:{
                 imagePath = "R.drawable.avatar_m_2";
                 break;
             }
             case R.id.avatarm3:{
                 imagePath = "R.drawable.avatar_m_3";
                 break;
             }
        }

        Intent i = new Intent(SelectAvatarActivity.this,CreateContactActivity.class);
        i.putExtra("imagePath",imagePath);
        startActivity(i);
    }
}
