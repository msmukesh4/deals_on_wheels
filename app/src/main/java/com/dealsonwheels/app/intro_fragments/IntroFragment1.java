package com.dealsonwheels.app.intro_fragments;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dealsonwheels.app.R;

/**
 * Created by mukesh on 12/11/17.
 */

public class IntroFragment1 extends Fragment {

    private String title;
    private String desc;
    public static IntroFragment1 newInstance(int frag_number, String title, String description) {

        Bundle args = new Bundle();
        args.putInt("fragment-number", frag_number);
        args.putString("title",title);
        args.putString("desc",description);
        IntroFragment1 fragment = new IntroFragment1();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.my_fragment_intro, container, false);

        ImageView imageView = (ImageView) rootView.findViewById(R.id.image_view);
        if (getArguments().getInt("fragment-number",0) == 1)
            imageView.setImageDrawable(getResources().getDrawable(R.mipmap.slide1));
        else if (getArguments().getInt("fragment-number",0) == 2)
            imageView.setImageDrawable(getResources().getDrawable(R.mipmap.slide2));
        else if (getArguments().getInt("fragment-number",0) == 3)
            imageView.setImageDrawable(getResources().getDrawable(R.mipmap.slide3));
        else if (getArguments().getInt("fragment-number",0) == 4)
            imageView.setImageDrawable(getResources().getDrawable(R.mipmap.slide4));
        else
            imageView.setImageResource(R.mipmap.ic_launcher);


        if (null != getArguments().getString("title") && !getArguments().getString("title").trim().isEmpty()) {
            TextView title = (TextView) rootView.findViewById(R.id.title);
            title.setText(getArguments().getString("title"));
            title.setVisibility(View.VISIBLE);
        }

        if (null != getArguments().getString("desc") && !getArguments().getString("desc").trim().isEmpty()) {
            TextView desc = (TextView) rootView.findViewById(R.id.desc);
            desc.setText(getArguments().getString("desc"));
            desc.setVisibility(View.VISIBLE);
        }




        return rootView;
    }
}
