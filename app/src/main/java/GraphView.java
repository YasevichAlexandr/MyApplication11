package com.example.myapplication11;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class GraphView extends View {

    private double xRange = 10.0;
    private double yRange = 10.0;

    private int numPts = 100;

    private double[] x = new double[numPts];
    private double[] y = new double[numPts];
    private static final float arrowSize = 10.0f;
    private static final int padding = 20;

    private Path arrowPath;
    private Paint paint;
    private Paint paintos;

    public GraphView(Context context) {
        super(context);
        init();
    }

    public GraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GraphView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStrokeWidth(2);
        paint.setAntiAlias(true);
        paint.setColor(Color.rgb(0,100,0));
        paintos = new Paint();
        paintos.setStrokeWidth(2);
        paintos.setAntiAlias(true);
        paintos.setColor(Color.rgb(0,0,0));
        arrowPath = new Path();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int startY = height / 2;

        float arrowStartX = padding;
        float arrowStartY = height / 2;
        float arrowEndX = padding + arrowSize;
        float arrowEndY = arrowStartY - arrowSize;
        //стрелка по ОХ
        canvas.drawLine(width, startY, width - arrowSize, startY - arrowSize, paint);
        canvas.drawLine(width, startY, width - arrowSize, startY + arrowSize, paint);
        //стрелка по оси OY
        canvas.drawLine(centerX, 0, centerX - arrowSize, 0 + arrowSize, paint);
        canvas.drawLine(centerX, 0, centerX + arrowSize, 0 + arrowSize, paint);


        canvas.drawLine(centerX, 0, centerX, height, paint);
        canvas.drawLine(0, startY, width, startY, paint);

        double scaleFactorX = width / (xRange * 2);
        double scaleFactorY = height / (yRange * 2);

        float previousX = (float) (centerX + x[0] * scaleFactorX);
        float previousY = (float) (startY - y[0] * scaleFactorY);

        for (int i = 1; i < numPts; i++) {
            float currentX = (float) (centerX + x[i] * scaleFactorX);
            float currentY = (float) (startY - y[i] * scaleFactorY);

            canvas.drawLine(previousX, previousY, currentX, currentY, paintos);

            previousX = currentX;
            previousY = currentY;
        }
    }
    public void updateGraph(double xValue, double aValue, double bValue) {
        double step = (2 * xRange) / numPts;

        for (int i = 0; i < numPts; i++) {
            x[i] = -xRange + i * step;
            y[i] = calculateFunction(x[i], aValue, bValue);
        }

        invalidate();
    }
    private double calculateFunction(double x, double a, double b) {
        return (3 * Math.pow(x, 2) - 7 * a) * b;
    }

}