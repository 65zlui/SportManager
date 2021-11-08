package com.example.sportmanager;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class FIWStepView extends View {
    private int mOuterColor= Color.GREEN;
    private int mInnerColor= Color.BLUE;
    private int mBorderWidth;
    private int mStepText;
    private int mCurrentStep;
    private int mStepTextColor=Color.BLUE;
    private int mStepTextSize;
    private Paint mOuterPaint;
    private Paint mInnerPaint;
    private Paint mTextPaint;

    private int mMaxStep;

    public FIWStepView(Context context){
        this(context,null);
    }
    public FIWStepView(Context context, @Nullable AttributeSet attrs){
        this(context,attrs,0);
    }
    public FIWStepView(Context context,@Nullable AttributeSet attrs,int defStyleAttr){
        super(context,attrs,defStyleAttr);

        //
        TypedArray array=context.obtainStyledAttributes(attrs,R.styleable.FIWStepView);
        mOuterColor=array.getColor(R.styleable.FIWStepView_outer_color,mOuterColor);
        mInnerColor=array.getColor(R.styleable.FIWStepView_inner_color,mInnerColor);
        mBorderWidth=(int)array.getDimension(R.styleable.FIWStepView_borderWidth,0);
        mStepTextColor=array.getColor(R.styleable.FIWStepView_step_text_color,mStepTextColor);
        mStepTextSize=array.getDimensionPixelSize(R.styleable.FIWStepView_step_text_size,mStepTextSize);
        array.recycle();

        mOuterPaint =new Paint();
        mOuterPaint.setAntiAlias(true);
        mOuterPaint.setColor(mOuterColor);
        mOuterPaint.setStrokeWidth(mBorderWidth);
        mOuterPaint.setStyle(Paint.Style.STROKE);
        mOuterPaint.setStrokeCap(Paint.Cap.ROUND);

        mInnerPaint =new Paint();
        mInnerPaint.setAntiAlias(true);
        mInnerPaint.setColor(mInnerColor);
        mInnerPaint.setStrokeWidth(mBorderWidth);
        mInnerPaint.setStyle(Paint.Style.STROKE);
        mInnerPaint.setStrokeCap(Paint.Cap.ROUND);

        mTextPaint =new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(mStepTextColor);
        mTextPaint.setTextSize(mStepTextSize);
    }
    //
  @Override
    protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height=MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width>height?height:width,width>height?height:width);
    }
    @Override
    protected  void onDraw(Canvas canvas){
        super.onDraw(canvas);

        int center=getWidth()/2;
        int radius=(getWidth()-mBorderWidth)/2;
        RectF rectF=new RectF(mBorderWidth/2,mBorderWidth/2,center+radius,center+radius);
        canvas.drawArc(rectF,135,270,false,mOuterPaint);

        if(mMaxStep==0) return;
        float radio=(float)mCurrentStep/mMaxStep;
        canvas.drawArc(rectF,135,270*radio,false,mInnerPaint);

        String mText=mCurrentStep+"";
        Rect rect=new Rect();
        mTextPaint.getTextBounds(mText,0,mText.length(),rect);

        int dx = getWidth()/2-rect.width()/2;
        Paint.FontMetricsInt fontMetricsInt=mTextPaint.getFontMetricsInt();
        int dy = fontMetricsInt.bottom-fontMetricsInt.top;
        int baseLine = getHeight()/2+dy/2;
        canvas.drawText(mText,dx,baseLine,mTextPaint);

    }
    public void  setCurrentStep(int mCurrentStep){
        this.mCurrentStep=mCurrentStep;
        invalidate();

    }
    public void setMaxStep(int mMaxStep){
        this.mMaxStep=mMaxStep;
    }
}
