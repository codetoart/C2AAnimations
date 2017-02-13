package com.android.codetoart.c2aanimations.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.Switch;

import com.android.codetoart.c2aanimations.R;

public class SwitchButtonView extends Switch {

    private Context mContext;
    private int color1, color2;
    public SwitchButtonView(Context context) {
        super(context);
        mContext=context;
        initView(null);
    }

    public SwitchButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        initView(attrs);
    }

    public SwitchButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        if (attrs==null) {
            return;
        }
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.custom_color);
        color1=a.getColor(R.styleable.custom_color_color1, -1);
        color2=a.getColor(R.styleable.custom_color_color2, -1);

        setThumbDrawable(getCustomThumbDrawable());
        setTrackDrawable(getCustomTrackDrawable());
    }

    private StateListDrawable getCustomTrackDrawable() {
        StateListDrawable res = new StateListDrawable();
        res.setEnterFadeDuration(400);
        res.setExitFadeDuration(400);
        res.addState(new int[]{-android.R.attr.state_checked}, getTrackNotSelectedDrawable());
        res.addState(new int[]{android.R.attr.state_checked}, getTrackSelectedDrawable());
        return res;
    }

    private StateListDrawable getCustomThumbDrawable() {
        StateListDrawable res = new StateListDrawable();
        res.addState(new int[]{-android.R.attr.state_checked}, getThumbNotSelectedDrawable());
        res.addState(new int[]{android.R.attr.state_checked}, getThumbSelectedDrawable());
        return res;
    }

    private Drawable getThumbNotSelectedDrawable() {
        int colors[] = {color2, color2};
        GradientDrawable shape =  new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors);
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadius(8f);
        shape.setSize(convertDpToPixel(40),convertDpToPixel(30));
        shape.setStroke(convertDpToPixel(3), ContextCompat.getColor(mContext,android.R.color.transparent));
        return shape;
    }

    private Drawable getThumbSelectedDrawable() {
        int colors[] = {color1, color1};
        GradientDrawable shape =  new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors);
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadius(8f);
        shape.setSize(convertDpToPixel(40),convertDpToPixel(30));
        shape.setStroke(convertDpToPixel(3), ContextCompat.getColor(mContext,android.R.color.transparent));
        return shape;
    }

    private Drawable getTrackNotSelectedDrawable() {
        int colors[] = {color1, color1};
        GradientDrawable shape =  new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors);
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadius(8f);
        shape.setSize(convertDpToPixel(200),convertDpToPixel(40));
        //shape.setStroke(convertDpToPixel(4), ContextCompat.getColor(mContext,android.R.color.transparent));
        return shape;
    }

    private Drawable getTrackSelectedDrawable() {
        int colors[] = {color2, color2};
        GradientDrawable shape =  new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors);
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadius(8f);
        shape.setSize(convertDpToPixel(200),convertDpToPixel(40));
        //shape.setStroke(convertDpToPixel(4), ContextCompat.getColor(mContext,android.R.color.transparent));
        return shape;
    }

    public int convertDpToPixel(float dp) {
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return (int) px;
    }
}
