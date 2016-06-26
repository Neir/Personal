package com.prodeveloper.jerome.bataillenavale;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

/**
 * Created by Jerome on 02/11/2015.
 */
public class LoadFruit {

    public static Drawable getDrawable(Context ctx, int size) {
        Drawable drawable = null;
        switch (size){
            case 0 :
                drawable = ContextCompat.getDrawable(ctx, R.drawable.vide);
                break;
            case 1 :
                drawable = ContextCompat.getDrawable(ctx, R.drawable.strawberry);
                break;
            case 2 :
                drawable = ContextCompat.getDrawable(ctx, R.drawable.lemon);
                break;
            case 3 :
                drawable = ContextCompat.getDrawable(ctx, R.drawable.apple);
                break;
            case 4 :
                drawable = ContextCompat.getDrawable(ctx, R.drawable.carrot);
                break;
            default:
                break;
        }
        return drawable;
    }

    public static GameCells getCell(int size, boolean mystery) {
        switch (size){
            case 0 :
                return (mystery)? GameCells.MYSTERY : GameCells.EMPTY;
            case 1 :
                return (mystery)? GameCells.MYST_STRAW : GameCells.STRAW;
            case 2 :
                return (mystery)? GameCells.MYST_LEMON : GameCells.LEMON;
            case 3 :
                return (mystery)? GameCells.MYST_APPLE : GameCells.APPLE;
            case 4 :
                return (mystery)? GameCells.MYST_CARROT : GameCells.CARROT;
            default:
                return null;
        }
    }
}
