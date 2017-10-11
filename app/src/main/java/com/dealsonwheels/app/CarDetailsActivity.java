package com.dealsonwheels.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.dealsonwheels.app.car_details_fragment.CarDetailsPagerAdapter;
import com.dealsonwheels.app.homepage_fragments.HomePagerAdapter;

import java.util.HashMap;

/**
 * Created by mukesh on 10/10/17.
 */

public class CarDetailsActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    private SliderLayout carSliderLayout;
    private static final String TAG = "Home";
    private CarDetailsPagerAdapter mCarDetailsPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);
        carSliderLayout = (SliderLayout) findViewById(R.id.carSlides);

//        HashMap<String,Integer> url_maps = new HashMap<String, Integer>();
//        file_maps.put("one title",R.drawable.one);
//        file_maps.put("two title",R.drawable.two);
//        file_maps.put("three title",R.drawable.three);

        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("1", "http://kitcarempire.com/wp-content/gallery/bugatti-veyron-toy-car/bugatti-veyron-replica-small-mini-08.jpg");
        url_maps.put("2", "http://www.clker.com/cliparts/1/2/c/1/13975770108622529252013-lamborghini-gallardo-lp570-4-performante-editione-tecnica-photos_1-hi.png");
        url_maps.put("3", "https://cars.usnews.com/static/images/article/201510/125272/MINIP90139264_highRes.jpg");


        for(String name : url_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            carSliderLayout.addSlider(textSliderView);
        }
        carSliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        carSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        carSliderLayout.stopAutoCycle();
        carSliderLayout.setmShowText(false);
//        imageSlider.setCustomAnimation(new DescriptionAnimation());
//        imageSlider.setDuration(0);
        carSliderLayout.addOnPageChangeListener(this);


        // set the home pager
        mCarDetailsPagerAdapter = new CarDetailsPagerAdapter(getSupportFragmentManager(),getApplicationContext());
        mViewPager = (ViewPager) findViewById(R.id.car_details_view_pager);
        mTabLayout = (TabLayout) findViewById(R.id.car_details_tabs);
        mViewPager.setAdapter(mCarDetailsPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);


    }

    @Override
    protected void onStop() {
        carSliderLayout.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.e("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}
}
