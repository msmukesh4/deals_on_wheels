package com.dealsonwheels.app;

import com.dealsonwheels.app.api.APIClient;
import com.dealsonwheels.app.models.User;

/**
 * Created by mukesh on 13/9/17.
 */

public class Constants {
    public static final int SPLASH_ACTIVITY_TIMER = 4000;
    public static final String INITIAL_DATA = "InitData";
    public static final String SAVE_CONTACT_NUMBER = "SavePhone";
    public static final int LOG_LEVEL = APIClient.LOG_REQ_RES;
    public static final String PRODUCT_LIST = "GetProducts";
    public static final String PRODUCT_DETAILS = "GetProductsDetails";

    public static User currentUser = new User();

    public static String[] LOCATION = new String[] {
            "Kolkata", "Karnataka", "Kolapur", "Kali Ghat"
    };

    public static String[] CARS = new String[] {
            "BMW 200M", "AUDI 900Q", "BETTLE 3S", "BUCATI VERON", "LEMBORGINI GALLADO", "LEMBORGINI ADO", "LEMBORGINI LADO"
    };
    public static final String BASE_URL = "http://pstsolution.com/sellcar/";
    public static final String ERROR_IMAGE = "https://cars.usnews.com/static/images/article/201510/125272/MINIP90139264_highRes.jpg";

    public static int PRICE_SLIDER_MIN_RANGE = 0;
    public static int PRICE_SLIDER_MAX_RANGE = 31;
}
