package com.hqinterview.dhara.asynctasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.hqinterview.dhara.interfaces.IResponseListener;
import com.hqinterview.dhara.models.BaseResponseHolder;
import com.hqinterview.dhara.utils.network.NetworkUtilities;

/**
 * Created by USER on 23-07-2015.
 */
public class GetBaseDataAsyncTask extends AsyncTask<Void,Void,BaseResponseHolder>{
    private IResponseListener mListener;

    public GetBaseDataAsyncTask(IResponseListener listener) {
        mListener = listener;
    }

    @Override
    protected BaseResponseHolder doInBackground(Void... params) {
        String response = NetworkUtilities.makeWebserviceCall(NetworkUtilities.METHOD.GET,
                NetworkUtilities.NETWORK_FUNCTIONS.GetDataFromBase,
                null,
                null);
        try {
            if(response != null) {
                Gson gson = new Gson();
                BaseResponseHolder holder = gson.fromJson(response,
                        BaseResponseHolder.class);
                return holder;
            }
        }catch (JsonSyntaxException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(BaseResponseHolder baseResponseHolder) {
        super.onPostExecute(baseResponseHolder);
        if(mListener != null) {
            mListener.onResponseReceived(baseResponseHolder);
        }
    }
}
