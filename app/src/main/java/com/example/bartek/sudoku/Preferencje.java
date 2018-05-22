package com.example.bartek.sudoku;

import android.preference.PreferenceActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class Preferencje extends PreferenceActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.ustawienia);
    }
}
