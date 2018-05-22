package com.example.bartek.sudoku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View buttonInformation = findViewById(R.id.button_about);
        buttonInformation.setOnClickListener(this);

        View buttonNew = findViewById(R.id.button_new);
        buttonNew.setOnClickListener(this);

        View buttonExit = findViewById(R.id.button_exit);
        buttonExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button_about:
                Log.i("wszedlem" , "jestem");
                Intent i = new Intent(this,Information.class);
                startActivity(i);
                break;
            case R.id.button_new:
                Log.i("wszedlem" , "jestem");
                otworzDialogNowaGra();
                break;
            case R.id.button_exit:
                finish();
                break;
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater wypelniacz = getMenuInflater();
        wypelniacz.inflate(R.menu.menu , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.ustawienia:
                startActivity(new Intent(this , Preferencje.class));
                return true;


        }
        return false;
    }

    private void otworzDialogNowaGra(){
         new AlertDialog.Builder(this).setTitle(R.string.tytul_nowa_gra).setItems(R.array.trudnosc, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {
                uruchomGre(i);
            }
        }).show();

    }
    private static final String ZNACZNIK = "Sudoku";

    public void uruchomGre(int i){
        Log.d(ZNACZNIK , "kliknieta" + i);
        Intent intencja = new Intent(MainActivity.this , Gra.class);
        intencja.putExtra(Gra.TRUDNOSC_KLUCZ,i);
        startActivity(intencja);
    }
}
