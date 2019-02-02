package com.example.noteapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    Intent intent;
    TextView editText;
    String newContent="";
    String oldContent="";
    MainActivity x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editText=(TextView) findViewById(R.id.editText1);

        intent=getIntent();
        x=new MainActivity();
        oldContent=intent.getStringExtra("note");

            editText.setText(oldContent);


    }

    @Override
    public void onBackPressed() {


        newContent = String.valueOf(editText.getText());
        //  x.saveback();
        intent=new Intent(getApplicationContext(),MainActivity.class);
        //intent.putExtra("newnote",newContent);


            if (!oldContent.equals(newContent)) {
           new AlertDialog.Builder(Main2Activity.this).setIcon(android.R.drawable.ic_menu_add).
           setTitle("Change Confirmation.").setMessage("Do U wanna Save these Changes ?").
                   setPositiveButton("yes", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                           try {

                               x.notes.set(x.position, newContent);
                               x.changecontent();
                               Toast.makeText(getApplicationContext(), "Changes Saved Succfully :) ", Toast.LENGTH_SHORT).show();
                           }catch(Exception e){
                               Toast.makeText(getApplicationContext(), "Error here ", Toast.LENGTH_SHORT).show();
                           }
                           finish();
                       }
                   }).setNegativeButton("No", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i) {
                   Toast.makeText(getApplicationContext(), "Changes Not Saved ^_^", Toast.LENGTH_SHORT).show();
                   finish();
               }
           }).show();


        }else {
                Toast.makeText(getApplicationContext(), "No Change :(", Toast.LENGTH_SHORT).show();
                super.onBackPressed();
            }
    }
}
