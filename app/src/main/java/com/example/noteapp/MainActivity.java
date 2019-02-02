package com.example.noteapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // to linke the Menue To this Activity
    /////////////////////////////////////////////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menuer,menu);
        return super.onCreateOptionsMenu(menu);
    }
// To add listener to the Menue Trigger What Button is Clicked
    ///////////////////////////////////////////////////////////
    String newName="";
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.newNote:
            addNewNote();
                Toast.makeText(this, "Note Add To List", Toast.LENGTH_SHORT).show();
                return true;
            default:
                Toast.makeText(this, "Can't Select This Option", Toast.LENGTH_SHORT).show();

                return true;
        }

    }






static SharedPreferences sharedPreferences;
     ListView listView;
   static ArrayList<String> notes;
  static   ArrayAdapter adapter;
   static Intent intent;
    static int position=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


     //Setting the Listview Content
        listView=(ListView) findViewById(R.id.listview1);
        notes=new ArrayList<String>();
        sharedPreferences=this.getSharedPreferences("com.example.noteapp",MODE_PRIVATE);
        getData();




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                intent.putExtra("note", notes.get(i));
                position=i;
                startActivity(intent);
                changecontent();
                adapter.notifyDataSetChanged();
               // saveback();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,final int pos, long id) {
                // TODO Auto-generated method stub
                new AlertDialog.Builder(MainActivity.this).
                        setIcon(android.R.drawable.ic_delete).
                        setTitle("Delete The Note").
                        setMessage(" You Really Wanna Delete it ?").
                        setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                               notes.remove(pos);

                               changecontent();
                                Toast.makeText(MainActivity.this, "Note Deleted Succfully", Toast.LENGTH_SHORT).show();
                            }
                        }).
                        setNegativeButton("No",null).show();

                return true;
            }
        });







    }















    void addNewNote(){
    notes.add("New Note");

        try {
            //putString i the method used to put the data u wanna debend on it's type

            //putString To Save String
            sharedPreferences.edit().putString("saved",ObjectSerializer.serialize(notes)).apply();
            adapter.notifyDataSetChanged();
            //to put integer Value
            //  sharedPreferences.edit().putInt("number",15).apply();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
   static void changecontent(){

        try {
            //putString i the method used to put the data u wanna debend on it's type

            //putString To Save String
            sharedPreferences.edit().putString("saved",ObjectSerializer.serialize(notes)).apply();
            adapter.notifyDataSetChanged();
            //to put integer Value
            //  sharedPreferences.edit().putInt("number",15).apply();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }





    void getData(){

        try {
            //getString("friends","") could make the app crach if it's empty so we return an desrialized object of ArrayList
            notes= (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("saved", ObjectSerializer.serialize(new ArrayList<String>())));
            adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, notes);
            listView.setAdapter(adapter);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "failed getting data ", Toast.LENGTH_SHORT).show();
        }
        adapter.notifyDataSetChanged();
    }



    //method to save the new content of the note
    /*
    void saveback(){
        try {
            if (intent.hasExtra("newnote")) {
          notes.set(intent.getIntExtra("number",0),intent.getStringExtra("newnote"));
                Toast.makeText(this, "Change Saved Succfully", Toast.LENGTH_SHORT).show();
            } else {
                //Toast.makeText(this, "Good Night ", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Problem Setting The Change", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){

            Toast.makeText(this, "Error Saving The Process", Toast.LENGTH_SHORT).show();
        }
    }
*/

}
