package com.example.android.demochart;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.BarDataProvider;
import com.github.mikephil.charting.renderer.BarChartRenderer;
import com.github.mikephil.charting.utils.ViewPortHandler;

/**
 * Created by Android on 3/11/2018.
 */

public class ImageBarChartRenderer extends BarChartRenderer {

    private final Bitmap barImage;

    public ImageBarChartRenderer(BarDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler, Bitmap barImage) {
        super(chart, animator, viewPortHandler);
        this.barImage = barImage;
    }

    @Override
    public void drawData(Canvas c) {
        super.drawData(c);
    }

    @Override
    protected void drawDataSet(Canvas c, BarDataSet dataSet, int index) {
        super.drawDataSet(c, dataSet, index);
        drawBarImages(c, dataSet, index);
    }

    protected void drawBarImages(Canvas c, BarDataSet dataSet, int index) {
        BarBuffer buffer = mBarBuffers[index];

        float left; //avoid allocation inside loop
        float right;
        float top;
        float bottom;

        final Bitmap scaledBarImage = scaleBarImage(buffer);

        int starWidth = scaledBarImage.getWidth();
        int starOffset = starWidth / 2;

        for (int j = 0; j < buffer.buffer.length * mAnimator.getPhaseX(); j += 4) {
            left = buffer.buffer[j];
            right = buffer.buffer[j + 2];
            top = buffer.buffer[j + 1];
            bottom = buffer.buffer[j + 3];

            float x = (left + right) / 2f;

            if (!mViewPortHandler.isInBoundsRight(x))
                break;

            if (!mViewPortHandler.isInBoundsY(top)
                    || !mViewPortHandler.isInBoundsLeft(x))
                continue;

            BarEntry entry = dataSet.getEntryForXIndex(j / 4);
            float val = entry.getVal();

            if (val > 10) {
                drawImage(c, scaledBarImage, x - starOffset, top);
            }
        }
    }

    private Bitmap scaleBarImage(BarBuffer buffer) {
        float firstLeft = buffer.buffer[0];
        float firstRight = buffer.buffer[2];
        int firstWidth = (int) Math.ceil(firstRight - firstLeft);
        return Bitmap.createScaledBitmap(barImage, firstWidth, firstWidth, false);
    }

    protected void drawImage(Canvas c, Bitmap image, float x, float y) {
        if (image != null) {
            c.drawBitmap(image, x, y, null);
        }
    }
}