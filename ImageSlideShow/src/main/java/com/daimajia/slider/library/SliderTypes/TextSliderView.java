package com.daimajia.slider.library.SliderTypes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.slider.library.R;
import com.daimajia.slider.library.SliderLayout;

/**
 * This is a slider with a description TextView.
 */
public class TextSliderView extends BaseSliderView{
    public TextSliderView(Context context) {
        super(context);
    }

    @Override
    public View getView() {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.render_type_text,null);
        ImageView target = (ImageView)v.findViewById(R.id.daimajia_slider_image);
        LinearLayout descLayout = (LinearLayout) v.findViewById(R.id.description_layout);
        if (SliderLayout.mShowText) {
            descLayout.setVisibility(View.VISIBLE);
            TextView description = (TextView) v.findViewById(R.id.description);
            description.setText(getDescription());
        }else
            descLayout.setVisibility(View.INVISIBLE);
        bindEventAndShow(v, target);
        return v;
    }
}
