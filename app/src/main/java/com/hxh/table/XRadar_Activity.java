package com.hxh.table;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.orzangleli.radar.XRadarView;

public class XRadar_Activity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener
{
    private XRadarView xrv;
    private TextView tv_shape, tv_layer;

    // 图标
    private int array_icon[] = new int[]{R.drawable.ic_launcher, R.drawable.ic_launcher,
            R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
            R.drawable.ic_launcher, R.drawable.ic_launcher};
    // 标题
    private CharSequence array_title[] = new CharSequence[]{"击杀", "金钱", "生存", "防御", "魔法", "物理", "助攻", "智慧"};
    // 各个标题下面的数值文本
    private CharSequence array_describe[] = new CharSequence[]{"80", "80%", "0.9", "100%", "3/5", "0.5", "0.77个", "1~12"};
    // 每种属性的值（0~1.0）
    private double array_value[] = new double[]{0.8, 0.8, 0.9, 1, 0.6, 0.5, 0.77, 0.9};
    // 用不同颜色区别相邻区域时的颜色数组（可选）
    private int array_color[] = new int[]{Color.parseColor("#A0ffcc00"), Color.parseColor("#A000ff00"),
            Color.parseColor("#A00000ff"), Color.parseColor("#A0FF00FF"), Color.parseColor("#A000FFFF"),
            Color.parseColor("#A0FFFF00"), Color.parseColor("#A000FF00"), Color.parseColor("#A0FF00FF")};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_xradar);

        initXRadar();
    }

    private void initXRadar()
    {
        xrv = findViewById(R.id.xrv);

        tv_shape = findViewById(R.id.tv_shape);
        tv_layer = findViewById(R.id.tv_layer);

        SeekBar sb_shape = findViewById(R.id.sb_shape);
        SeekBar sb_layer = findViewById(R.id.sb_layer);
        SeekBar sb_iconSize = findViewById(R.id.sb_icon_size);
        SeekBar sb_titleSize = findViewById(R.id.sb_title_size);

        ToggleButton tb_border = findViewById(R.id.tb_border);
        ToggleButton tb_point = findViewById(R.id.tb_point);
        ToggleButton tb_polygon = findViewById(R.id.tb_polygon);
        ToggleButton tb_shade = findViewById(R.id.tb_shade);
        ToggleButton tb_multicolor = findViewById(R.id.tb_multicolor);
        ToggleButton tb_gradient = findViewById(R.id.tb_gradient);
        ToggleButton tb_circle = findViewById(R.id.tb_circle);
        ToggleButton tb_radius = findViewById(R.id.tb_radius);
        ToggleButton tb_text = findViewById(R.id.tb_text);
        ToggleButton tb_icon = findViewById(R.id.tb_icon);
        ToggleButton tb_richText = findViewById(R.id.tb_richtext);

        // 雷达图各项值比例
        xrv.setPercents(array_value);
        // 设置各区域颜色的数组
        // 如果设置了colors则会每个区域显示不同的颜色，否则所有区域显示同一个颜色   操作1
        xrv.setColors(null);
        // 如果想设置所有区域同一种颜色，可以设置dataColor                    操作2    （操作1和操作2 互斥）
//        xrv.setDataColor(Color.parseColor("#999900"));
        // 设置各项标题
        xrv.setTitles(array_title);
        // 设置各项显示的值的文本
        xrv.setValues(array_describe);
        // 设置各项的图标
        xrv.setDrawables(array_icon);
        // 设置允许各个点之间连线
        xrv.setEnabledBorder(true);
        // 显示圆点
        xrv.setEnabledShowPoint(true);
        // 绘制正n变形
        xrv.setEnabledPolygon(true);
        // 绘制渐变环
        xrv.setEnabledShade(true);
        // 绘制半径
        xrv.setEnabledRadius(true);
        // 绘制标题，数值和图标等
        xrv.setEnabledText(true);
        // 开启动画
        xrv.setEnabledAnimation(true);
        // 设置层数
        xrv.setLayerCount(5);
        // 无渐变色的单一色
        xrv.setSingleColor(Color.parseColor("#800000ff"));
        // 配置区域渐变, 第二个参数需要微调才能获得好的结果
        xrv.setRegionShaderConfig(new int[]{Color.YELLOW, Color.RED}, new float[]{0.2f, 0.6f});

        // 雷达图标题和图标的点击事件
        xrv.setOnTitleClickListener(new XRadarView.OnTitleClickListener()
        {
            @Override
            public void onTitleClick(XRadarView view, int position, int x, int y, Rect rect)
            {
                Toast.makeText(XRadar_Activity.this, "position = " + position, Toast.LENGTH_SHORT).show();

                Log.d("lxc", "position ----> " + position);
                Log.d("lxc", "x ----> " + x);
                Log.d("lxc", "y ----> " + y);
            }
        });

        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                xrv.loadAnimation(true);
            }
        });

        sb_shape.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                String radios = "";

                if (progress == 0)
                {
                    radios = "正三边形";
                }
                else if (progress == 1)
                {
                    radios = "正四边形";
                }
                else if (progress == 2)
                {
                    radios = "正五边形";
                }
                else if (progress == 3)
                {
                    radios = "正六边形";
                }
                else if (progress == 4)
                {
                    radios = "正七边形";
                }

                tv_shape.setText(radios);

                xrv.setCount(progress + 3);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });

        sb_layer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                String layoutText = "层数";

                tv_layer.setText(layoutText + progress);

                xrv.setLayerCount(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
            }
        });

        sb_iconSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                xrv.setDrawableSize(progress + 20);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
            }
        });

        sb_titleSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                xrv.setTitleSize(progress + 30);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
            }
        });

        tb_border.setOnCheckedChangeListener(this);
        tb_point.setOnCheckedChangeListener(this);
        tb_polygon.setOnCheckedChangeListener(this);
        tb_shade.setOnCheckedChangeListener(this);
        tb_multicolor.setOnCheckedChangeListener(this);
        tb_gradient.setOnCheckedChangeListener(this);
        tb_circle.setOnCheckedChangeListener(this);
        tb_radius.setOnCheckedChangeListener(this);
        tb_text.setOnCheckedChangeListener(this);
        tb_icon.setOnCheckedChangeListener(this);
        tb_richText.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
    {
        switch (buttonView.getId())
        {
            case R.id.tb_border:
            {
                xrv.setEnabledBorder(isChecked);

                break;
            }
            case R.id.tb_point:
            {
                xrv.setEnabledShowPoint(isChecked);

                break;
            }
            case R.id.tb_polygon:
            {
                xrv.setEnabledPolygon(isChecked);

                break;
            }
            case R.id.tb_shade:
            {
                xrv.setEnabledShade(isChecked);

                break;
            }
            case R.id.tb_multicolor:
            {
                if (isChecked)
                {
                    xrv.setColors(array_color);
                }
                else
                {
                    xrv.setColors(null);
                }

                break;
            }
            case R.id.tb_gradient:
            {
                if (isChecked && xrv.getColors() != null)
                {
                    Toast.makeText(this, "开启区域颜色渐变，需要先关闭多色区分区域", Toast.LENGTH_LONG).show();

                    return;
                }

                xrv.setEnabledRegionShader(isChecked);

                break;
            }
            case R.id.tb_circle:
            {
                xrv.setCircle(isChecked);

                break;
            }
            case R.id.tb_radius:
            {
                xrv.setEnabledRadius(isChecked);

                break;
            }
            case R.id.tb_text:
            {
                xrv.setEnabledText(isChecked);

                break;
            }
            case R.id.tb_icon:
            {
                if (isChecked)
                {
                    xrv.setEnabledRegionShader(false);
                    xrv.setDrawables(array_icon);
                }
                else
                {
                    xrv.setEnabledRegionShader(true);
                    xrv.setDrawables(null);
                }

                break;
            }
            case R.id.tb_richtext:
            {
                if (isChecked)
                {
                    // 注意，如果使用富文本，则只显示titles字段了，所以可以手动将values拼接到titles后面
                    SpannableString ss = new SpannableString("富文本\n值为0.1");
                    ss.setSpan(new AbsoluteSizeSpan(30), 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ss.setSpan(new ForegroundColorSpan(Color.RED), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ss.setSpan(new ForegroundColorSpan(Color.GREEN), 1, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ss.setSpan(new BackgroundColorSpan(Color.YELLOW), 1, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ss.setSpan(new ForegroundColorSpan(Color.BLUE), 2, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ss.setSpan(new AbsoluteSizeSpan(10), 4, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                    array_title[0] = ss;

                    xrv.setTitles(array_title);
                }
                else
                {
                    array_title[0] = "击杀";
                    xrv.setTitles(array_title);
                }

                break;
            }
        }
    }
}