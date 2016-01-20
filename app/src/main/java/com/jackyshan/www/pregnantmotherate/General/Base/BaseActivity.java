package com.jackyshan.www.pregnantmotherate.General.Base;

import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

import com.jackyshan.www.pregnantmotherate.R;

/**
 * Created by jackyshan on 1/20/16.
 */
public abstract class BaseActivity extends ActionBarActivity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
