package com.example.android.demochart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        CombinedChart combinedChart = (CombinedChart) findViewById(R.id.chart1);

        combinedChart.setBackgroundColor(Color.WHITE);
        combinedChart.setDrawGridBackground(false);
        combinedChart.setDrawBarShadow(false);
//        combinedChart.setDrawValueAboveBar(false);
        combinedChart.setDrawHighlightArrow(false);
        combinedChart.getXAxis().setDrawGridLines(false);

//        combinedChart.getXAxis().setEnabled(false);
//        combinedChart.getAxisLeft().setDrawAxisLine(false);
        combinedChart.getAxisRight().setDrawAxisLine(false);
        combinedChart.getAxisRight().setDrawLabels(false);

        // draw bars behind lines
        combinedChart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.LINE
        });

        YAxis rightAxis = combinedChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
//        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis leftAxis = combinedChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
//        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        XAxis xAxis = combinedChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        CombinedData data = new CombinedData(getXAxisValues());
        data.setData(generateBarData());
        data.setData(generateLineData());

        combinedChart.setData(data);
        combinedChart.invalidate();
    }

    private float getRandom(float range, float startsfrom) {
        return (float) (Math.random() * range) + startsfrom;
    }

    private String[] mMonths = new String[]{
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    };

    private LineData generateLineData() {


        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int index = 0; index < mMonths.length; index++)
            entries.add(new Entry(getRandom(15, 5), index));

        LineDataSet set = new LineDataSet(entries, "Line DataSet");
        set.setColor(Color.rgb(240, 238, 70));

        set.setLineWidth(2.5f);
        set.setCircleColor(Color.rgb(240, 238, 70));
//        set.setCircleRadius(5f);
        set.setFillColor(Color.rgb(240, 238, 70));
//        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.rgb(240, 238, 70));
//
//        set.setAxisDependency(YAxis.AxisDependency.LEFT);
//        d.addDataSet(set);

        LineData d = new LineData(getXAxisValues(), set);


        return d;
    }

    private BarData generateBarData() {

        ArrayList<BarEntry> entries1 = new ArrayList<BarEntry>();
        for (int index = 0; index < mMonths.length; index++)
            entries1.add(new BarEntry(getRandom(15, 5), index));

//        entries1.add(new BarEntry(4f, 0));
//        entries1.add(new BarEntry(8f, 1));
//        entries1.add(new BarEntry(6f, 2));
//        entries1.add(new BarEntry(12f, 3));
//        entries1.add(new BarEntry(18f, 4));
//        entries1.add(new BarEntry(9f, 5));
        BarDataSet set1 = new BarDataSet(entries1, "Bar 1");
//        set1.setColors(ColorTemplate.COLORFUL_COLORS);
        set1.setValueTextColor(Color.rgb(60, 220, 78));
//        set1.setValueTextSize(10f);
//        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
//
//
//        float barWidth = 0.45f; // x2 dataset
        // (0.45 + 0.02) * 2 + 0.06 = 1.00 -> interval per "group"

        BarData d = new BarData(getXAxisValues(), set1);
//        BarData d = new BarData(set1);
//        d.setBarWidth(barWidth);

        // make this BarData object grouped
//        d.groupBars(0, groupSpace, barSpace); // start at x = 0

        return d;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> labels = new ArrayList<>();
        for (int i = 0; i < mMonths.length; i++)
            labels.add(mMonths[i]);
        return labels;
    }

}
