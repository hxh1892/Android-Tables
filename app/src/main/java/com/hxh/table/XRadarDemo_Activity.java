package com.hxh.table;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.orzangleli.radar.XRadarView;

public class XRadarDemo_Activity extends AppCompatActivity
{
    XRadarView xrv;

    String[] array_title = new String[]{"击杀", "金钱", "防御", "魔法", "物理", "助攻", "生存"};
    double[] array_value = new double[]{1.0, 0.46, 0.63, 0.75, 0.5, 0.9, 0.26};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_xradardemo);

        xrv = findViewById(R.id.xrv);
        xrv.setTitles(array_title);
        xrv.setPercents(array_value);
    }
}
