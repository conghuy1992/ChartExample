package com.example.android.demochart;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleDataSet;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
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


//        Bitmap starBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
//        combinedChart.setRenderer(new ImageBarChartRenderer(combinedChart, combinedChart.getAnimator(), combinedChart.getViewPortHandler(), starBitmap));

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

//        mChart.setDrawOrder(new DrawOrder[]{
//                DrawOrder.BAR, DrawOrder.BUBBLE, DrawOrder.CANDLE, DrawOrder.LINE, DrawOrder.SCATTER
//        });

        // draw bars behind lines
        combinedChart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.LINE, CombinedChart.DrawOrder.BUBBLE
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
        data.setData(generateBubbleData());
//        data.setData(generateScatterData());
//        data.setData(generateCandleData());


        combinedChart.setData(data);
        combinedChart.invalidate();


    }

    private float getRandom(float range, float startsfrom) {
        return (float) (Math.random() * range) + startsfrom;
    }

    private String[] mMonths = new String[]{
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    };

    protected CandleData generateCandleData() {

//        ArrayList<CandleEntry> entries = new ArrayList<CandleEntry>();
//        for (int index = 0; index < mMonths.length; index++)
//            entries.add(new CandleEntry(index, 90, 70, 85, 75f));
//        CandleDataSet set = new CandleDataSet(entries, "Candle DataSet");
//        set.setDecreasingColor(Color.rgb(111, 111, 111));
//        set.setShadowColor(Color.DKGRAY);
//        set.setValueTextSize(10f);
//        set.setDrawValues(false);
//        CandleData d = new CandleData(getXAxisValues(), set);
//        d.addDataSet(set);
//
//        return d;

        ArrayList<CandleEntry> entries = new ArrayList<CandleEntry>();

        for (int index = 0; index < mMonths.length; index++)
            entries.add(new CandleEntry(index, 90, 70, 85, 75f));

        CandleDataSet set = new CandleDataSet(entries, "Candle DataSet");
        set.setDecreasingColor(Color.rgb(142, 150, 175));
        set.setShadowColor(Color.DKGRAY);
        set.setBodySpace(0.3f);
        set.setValueTextSize(10f);
        set.setDrawValues(false);

        CandleData d = new CandleData(getXAxisValues(), set);

        return d;
    }

    protected BubbleData generateBubbleData() {

//        BubbleData bd = new BubbleData();
//
//        ArrayList<BubbleEntry> entries = new ArrayList<BubbleEntry>();
//
//        for (int index = 0; index < mMonths.length; index++) {
//            float y = getRandom(10, 105);
//            float size = getRandom(100, 105);
//            entries.add(new BubbleEntry(index, y, size));
//        }
//
//        BubbleDataSet set = new BubbleDataSet(entries, "Bubble DataSet");
//        set.setColors(ColorTemplate.VORDIPLOM_COLORS);
//        set.setValueTextSize(10f);
//        set.setValueTextColor(Color.WHITE);
//        set.setHighlightCircleWidth(1.5f);
//        set.setDrawValues(true);
//        bd.addDataSet(set);
//
//        return bd;

        ArrayList<BubbleEntry> entries = new ArrayList<>();

        for (int index = 0; index < mMonths.length; index++) {
            float y = getRandom(50, 30);
            float size = getRandom(100, 105);
            entries.add(new BubbleEntry(index, y, size));
        }
        BubbleDataSet set = new BubbleDataSet(entries, "BubbleDataSet");
        set.setColor(Color.rgb(240, 238, 70));

//        set.setLineWidth(2.5f);
//        set.setCircleColor(Color.rgb(240, 238, 70));
//        set.setFillColor(Color.rgb(240, 238, 70));
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.rgb(240, 238, 70));
        BubbleData d = new BubbleData(getXAxisValues(), set);
        return d;
    }

    private LineData generateLineData() {
        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int index = 0; index < mMonths.length; index++)
            entries.add(new Entry(getRandom(15, 5), index));
        LineDataSet set = new LineDataSet(entries, "Line DataSet");
        set.setColor(Color.rgb(240, 238, 70));

        set.setLineWidth(2.5f);
        set.setCircleColor(Color.rgb(240, 238, 70));
        set.setFillColor(Color.rgb(240, 238, 70));
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.rgb(240, 238, 70));
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
