package com.example.bartek.sudoku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

public class WidokPuzzle extends View {

    private static final String ZNACZNIK = "Sudoku";
    private final Gra gra;
    public WidokPuzzle(Context context) {
        super(context);
        this.gra = (Gra) context;
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    private float szerokosc;
    private float wysokosc;
    private int wybX;
    private int wybY;
    private final Rect wybProst = new Rect();

    @Override
    protected void onSizeChanged(int s, int w, int staras, int staraw) {
        super.onSizeChanged(s, w, staras, staraw);
        szerokosc = s/9f;
        wysokosc = w/9f;
        wezProst(wybX , wybY , wybProst);
        Log.d(ZNACZNIK , "onSizedChanged: szerokość" + szerokosc + ", wysokosc " + wysokosc);
        super.onSizeChanged(s,w,staras , staraw);

    }

    private void wezProst(int x, int y, Rect prost){
        prost.set((int)(x*szerokosc),(int)(y*wysokosc) , (int)(x*szerokosc + szerokosc) , (int)(y*wysokosc+wysokosc));
    }

    @Override
    protected void onDraw(Canvas plotno) {
        Paint tlo = new Paint();
        tlo.setColor(getResources().getColor(R.color.puzzle_tlo));
        plotno.drawRect(0,0,getWidth(), getHeight(),tlo);

        Paint ciemny = new Paint();
        ciemny.setColor(getResources().getColor(R.color.puzzle_ciemny));

        Paint podswietlanie = new Paint();
        podswietlanie.setColor(getResources().getColor(R.color.puzzle_podswietlanie));

        Paint jasny = new Paint();
        jasny.setColor(getResources().getColor(R.color.puzzle_jasny));

        for(int i = 0;i < 9;i++){
            plotno.drawLine(0,i*wysokosc,getWidth(),i*wysokosc,jasny);
            plotno.drawLine(0,i*wysokosc + 1,getWidth(),i*wysokosc+1,podswietlanie);
            plotno.drawLine(i*szerokosc+1,0,i*szerokosc+1 , getHeight(),podswietlanie);
        }

        for(int i = 0;i < 9;i++){
            if(i%3 != 0)
                continue;
            plotno.drawLine(0,i*wysokosc,getWidth(),i*wysokosc,ciemny);
            plotno.drawLine(0,i*wysokosc + 1,getWidth(),i*wysokosc + 1,ciemny);
            plotno.drawLine(i*szerokosc,0,i*szerokosc,getHeight(),ciemny);
            plotno.drawLine(i*szerokosc + 1,0,i*szerokosc + 1,getHeight(),ciemny);
        }
    }
}
