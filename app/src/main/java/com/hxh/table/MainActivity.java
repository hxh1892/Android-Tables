package com.hxh.table;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity
{
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
    }

    public void hc(View v)
    {
        startActivity(new Intent(mContext, HelloChart_Activity.class));
    }

    public void xr(View v)
    {
        startActivity(new Intent(mContext, XRadar_Activity.class));
    }

    public void xrd(View v)
    {
        startActivity(new Intent(mContext, XRadarDemo_Activity.class));
    }
}
