package com.example.bartek.sudoku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;

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


        Paint pierwszy_plan = new Paint (Paint.ANTI_ALIAS_FLAG);
        pierwszy_plan.setColor(getResources().getColor(R.color.puzzle_pierwszy_plan));
        pierwszy_plan.setStyle(Style.FILL);
        pierwszy_plan.setTextSize(wysokosc*0.75f);
        pierwszy_plan.setTextScaleX(szerokosc/wysokosc);
        pierwszy_plan.setTextAlign(Paint.Align.CENTER);

        //Rysuje figure
        FontMetrics fm = pierwszy_plan.getFontMetrics();
        float x = szerokosc/2;
        float y = wysokosc/2-(fm.ascent + fm.descent)/2;
        for(int i = 0;i < 9;i++){
            for(int j = 0;j < 9;j++){
                plotno.drawText(this.gra.wezZnakPola(i,j) , i * szerokosc + x , j * wysokosc + y,pierwszy_plan);
            }
        }

        //Rysuje zaznaczenie
        Log.d(ZNACZNIK , "wybProst" + wybProst);
        Paint zaznaczony = new Paint();
        zaznaczony.setColor(getResources().getColor(R.color.puzzle_zaznaczony));
        plotno.drawRect(wybProst,zaznaczony);

        //RysujePodpowiedzi
        Paint podpowiedz = new Paint();
        int c[] = {
                getResources().getColor(R.color.puzzle_podpowiedz_0),
                getResources().getColor(R.color.puzzle_podpowiedz_1),
                getResources().getColor(R.color.puzzle_podpowiedz_2)
        };
        Rect r = new Rect();
        for(int i = 0;i < 9;i++){
            for (int j = 0;j < 9;j++){
                int pozostaleRuchy = 9 - gra.wezUzytePola(i,j).length;
                if(pozostaleRuchy < c.length){
                    wezProst(i,j,r);
                    podpowiedz.setColor(c[pozostaleRuchy]);
                    plotno.drawRect(r,podpowiedz);
                }
            }
        }
    }

    @Override
    public boolean onKeyDown(int kodKlaw, KeyEvent zdarzenie) {
        Log.d(ZNACZNIK , "onKeyDown: kod klawisza=" + kodKlaw + " zdarzenie " + zdarzenie);
        switch (kodKlaw){
            case KeyEvent.KEYCODE_0:
            case KeyEvent.KEYCODE_SPACE:
                ustawWybranePole(0);
                break;
            case KeyEvent.KEYCODE_1:
                ustawWybranePole(1);
                break;
            case KeyEvent.KEYCODE_2:
                ustawWybranePole(2);
                break;
            case KeyEvent.KEYCODE_3:
                ustawWybranePole(3);
                break;
            case KeyEvent.KEYCODE_4:
                ustawWybranePole(4);
                break;
            case KeyEvent.KEYCODE_5:
                ustawWybranePole(5);
                break;
            case KeyEvent.KEYCODE_6:
                ustawWybranePole(6);
                break;
            case KeyEvent.KEYCODE_7:
                ustawWybranePole(7);
                break;
            case KeyEvent.KEYCODE_8:
                ustawWybranePole(8);
                break;
            case KeyEvent.KEYCODE_9:
                ustawWybranePole(9);
                break;
            case KeyEvent.KEYCODE_ENTER:
            case KeyEvent.KEYCODE_DPAD_CENTER:
                gra.pokazKlawiatureLubBlad(wybX , wybY);
                break;

            case KeyEvent.KEYCODE_DPAD_UP:
                wybierz(wybX , wybY -1);
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                wybierz(wybX , wybY +1);
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                wybierz(wybX -1, wybY);
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                wybierz(wybX +1, wybY );
                break;
                default:return super.onKeyDown(kodKlaw,zdarzenie);

        }
        return true;
    }



    private void wybierz(int x, int y) {
        invalidate(wybProst);
        wybX = Math.min(Math.max(0,x) , 8);
        wybY = Math.min(Math.max(0,y) , 8);
        wezProst(wybX , wybY , wybProst);
        invalidate(wybProst);
    }

    @Override
    public boolean onTouchEvent(MotionEvent zdarzenie) {
        if(zdarzenie.getAction() != MotionEvent.ACTION_DOWN)
            return super.onTouchEvent(zdarzenie);
        wybierz((int)(zdarzenie.getX()/szerokosc) , (int)(zdarzenie.getY()/wysokosc));
        gra.pokazKlawiatureLubBlad(wybX , wybY);
        Log.d(ZNACZNIK , "onTouchEvent: x " + wybX + ", y " + wybY);
        return true;
    }
    public void ustawWybranePole(int pole) {
        if (gra.ustawPoleJesliPoprawne(wybX, wybY, pole)) {
            invalidate();// podpowiedzi mogą ulec zmianie
        } else {
            // Niepoprawna cyfra dla tego pola
            Log.d(ZNACZNIK, "ustawWybranePole: nieprawidłowe: " + pole);

            startAnimation(AnimationUtils.loadAnimation(gra,
                    R.anim.wstrzasy));

        }
    }


}
