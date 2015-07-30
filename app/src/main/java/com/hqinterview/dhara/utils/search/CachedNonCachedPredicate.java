package com.hqinterview.dhara.utils.search;

import com.google.common.base.Predicate;
import com.hqinterview.dhara.models.BaseResponseHolder;
import com.hqinterview.dhara.models.Module;

import java.lang.reflect.Field;

/**
 * Returns a list of fields with cache value true  or false </br>
 * The base field will have a cache value of false, so it is taken as part of the navigation drawer.
 * Created by Dhara on 27-07-2015.
 */
public class CachedNonCachedPredicate implements Predicate<Field> {
    private BaseResponseHolder mHolder;
    private String mTrueFalse;
    public CachedNonCachedPredicate(BaseResponseHolder baseResponseHolder,
                                    String strTrueFalse) {
        mHolder = baseResponseHolder;
        mTrueFalse = strTrueFalse;
    }

    @Override
    public boolean apply(Field field) {
        try {
            BaseResponseHolder responseHolder = mHolder;
            for (Field fieldObj : responseHolder.getClass().getDeclaredFields()) {
                if(fieldObj.getName().equals(field.getName())) {
                    fieldObj.setAccessible(true);
                    Object moduleValue = fieldObj.get(responseHolder);
                    if(mTrueFalse.equals("true")) {
                        if(((Module)moduleValue).getCache().equals(mTrueFalse) ||
                                (((Module)moduleValue).getCache().equals("false") &&
                                        fieldObj.getName().equals("base"))) {
                            return true;
                        }
                    }else {
                        if(((Module)moduleValue).getCache().equals(mTrueFalse) &&
                                !fieldObj.getName().equals("base")){
                            return true;
                        }
                    }
                }
            }
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }
}
