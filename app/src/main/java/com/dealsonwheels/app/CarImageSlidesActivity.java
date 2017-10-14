package com.dealsonwheels.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.truba.touchgallery.GalleryWidget.GalleryViewPager;
import ru.truba.touchgallery.GalleryWidget.UrlPagerAdapter;

/**
 * Created by mukesh on 14/10/17.
 */

public class CarImageSlidesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_slides);

//        String[] urls = {
//                "http://cs407831.userapi.com/v407831207/18f6/jBaVZFDhXRA.jpg",
//                "http://cs407831.userapi.com/v4078f31207/18fe/4Tz8av5Hlvo.jpg", //special url with error
//                "http://cs407831.userapi.com/v407831207/1906/oxoP6URjFtA.jpg",
//                "http://cs407831.userapi.com/v407831207/190e/2Sz9A774hUc.jpg",
//                "http://cs407831.userapi.com/v407831207/1916/Ua52RjnKqjk.jpg",
//                "http://cs407831.userapi.com/v407831207/191e/QEQE83Ok0lQ.jpg"
//        };

        String[] urls = {
                "http://kitcarempire.com/wp-content/gallery/bugatti-veyron-toy-car/bugatti-veyron-replica-small-mini-08.jpg",
                "http://www.clker.com/cliparts/1/2/c/1/13975770108622529252013-lamborghini-gallardo-lp570-4-performante-editione-tecnica-photos_1-hi.png", //special url with error
                "https://cars.usnews.com/static/images/article/201510/125272/MINIP90139264_highRes.jpg"
        };
        List<String> items = new ArrayList<String>();
        Collections.addAll(items, urls);
        UrlPagerAdapter pagerAdapter = new UrlPagerAdapter(this, items);
        GalleryViewPager mViewPager = (GalleryViewPager)findViewById(R.id.viewer);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(pagerAdapter);
    }
}
