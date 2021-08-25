package com.example.strateegapockettoolsv00;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Definindo variaveis
    EditText title;
    EditText location;
    EditText description;
    Button Agendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.etTitle);
        location = findViewById(R.id.etTitle);
        description = findViewById(R.id.etDescription);
        Agendar = findViewById(R.id.btnAdd);

        Agendar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                //It makes a validation of fields, if one of the Edit Texts are null, we receive a toast.
                if (!title.getText().toString().isEmpty() && !location.getText().toString().isEmpty() &&
                        !description.getText().toString().isEmpty()){

                    //Configuring the use of the btnAdd
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, title.getText().toString());
                    intent.putExtra(CalendarContract.Events.EVENT_LOCATION, location.getText().toString());
                    intent.putExtra(CalendarContract.Events.DESCRIPTION, description.getText().toString());
                    intent.putExtra(CalendarContract.Events.CAN_INVITE_OTHERS, true);
                    

                    //This is what changes the default agenda need of starting and ending hour making it a all day event
                    intent.putExtra(CalendarContract.Events.ALL_DAY, true);
                    //This configure the function of sending e-mails (It's done in hardcode style, needs improvement)
                    intent.putExtra(Intent.EXTRA_EMAIL, "ReinaldoPianinho@gmail.com, SeuZéDaCocaDeVidro@yahoo.com");

                    if (intent.resolveActivity(getPackageManager()) !=null){
                        startActivity(intent);
                    }else {
                        Toast.makeText(MainActivity.this, "O app não suporta isso",Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "É necessario preencher todos os campos",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}