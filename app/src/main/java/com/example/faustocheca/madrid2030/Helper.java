package com.example.faustocheca.madrid2030;

import android.content.Context;

/**
 * Created by faustocheca on 12/5/16.
 */
public class Helper {

    public static int dpToPx(Context context, int dp) {

        float density = context.getResources().getDisplayMetrics().density;
        return Math.round(dp*density);
    }
}
