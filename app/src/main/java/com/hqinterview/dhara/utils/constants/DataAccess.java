package com.hqinterview.dhara.utils.constants;

import java.util.HashMap;

/**
 * Created by USER on 27-07-2015.
 */
public class DataAccess {
    private static HashMap<String,String> ACCESS_VALUES;
    public static final String COUNTRY_CODE_KEY = "countryCode";
    public static final String USERID_KEY = "userId";
    public static final String APP_SECRET_KEY="appSecretKey";
    public static final String OFFER_ID_KEY="offerId";
    public static final String CURRENCY_CODE_KEY =  "currencyCode";
    public static final String SELECTED_VOUCHERS_KEY = "selectedVouchers";
    public static final String CITY_ID_KEY="cityId";

    public static void init() {
        ACCESS_VALUES = new HashMap<>();
        ACCESS_VALUES.put(CITY_ID_KEY,"");
        ACCESS_VALUES.put(COUNTRY_CODE_KEY,"");
        ACCESS_VALUES.put(CURRENCY_CODE_KEY,"USD");
        ACCESS_VALUES.put(APP_SECRET_KEY,"gvx32RFZLNGhmzYrfDCkb9jypTPa8Q");
        ACCESS_VALUES.put(USERID_KEY,"276");
        ACCESS_VALUES.put(OFFER_ID_KEY,"10736598");
        ACCESS_VALUES.put(SELECTED_VOUCHERS_KEY,"[]");
    }

    public static String getValue(String key) {
        return ACCESS_VALUES.get(key);
    }
}
