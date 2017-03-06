package com.cazallau.barajafrancesa.manager;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.io.StringReader;

/**
 * Created by gemabeltran on 17/2/17.
 */

public class CardAPI {
    public static final  String BASE_URL="https://deckofcardsapi.com/api/deck/";


    public  interface CardDonloadDataFinish {
        public void newCard(CardResponse.Card card, int remainig);
    }

    private CardDonloadDataFinish listener;

    public void setOnCardDowloadDataFinish(CardDonloadDataFinish listener) {
        this.listener = listener;
    }

    public void getCard(Context context, String deckId){

        RequestQueue queue = Volley.newRequestQueue(context);

        String url = BASE_URL + deckId + "/draw/?count=1";
        Log.d("URL", url);

        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //all ok

                Log.d("RESPONSE", response);
                parseJSON(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //tenemos un problema
                Log.e("HORROR", "Connection went to shit to the tracks");
            }
        });

        queue.add(request);
    }

    private void parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        Reader reader = new StringReader(response);
        CardResponse cardResponse = gson.fromJson(reader, CardResponse.class);
        Log.d("RESPONSE", cardResponse.toString());

        if (listener!=null){
            CardResponse.Card card = cardResponse.getCard().get(0);
            listener.newCard(card, cardResponse.getRemainig());
        }


    }
}
