package com.example.bartek.sudoku;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class Gra extends AppCompatActivity{

    private static final String ZNACZNIK = "Sudoku";

    public static final String TRUDNOSC_KLUCZ = "org.przyklad.sudoku.trudnosc";
    public static final int TRUDNOSC_LATWY = 0;
    public static final int TRUDNOSC_SREDNI = 1;
    public static final int TRUDNOSC_TRUDNY = 2;

    private int puzzle[] = new int [9*9];

    private WidokPuzzle widokPuzzle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(ZNACZNIK , "onCreate");
        int trud = getIntent().getIntExtra(TRUDNOSC_KLUCZ,TRUDNOSC_LATWY);
        //puzzlewezPuzzle(trud);
        //obliczUzytePola();

        widokPuzzle = new WidokPuzzle(this);
        setContentView(widokPuzzle);
        widokPuzzle.requestFocus();
    }
}
