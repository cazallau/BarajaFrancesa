package com.cazallau.barajafrancesa.manager;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cazallau.barajafrancesa.Deck;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.io.StringReader;

/**
 * Created by gemabeltran on 17/2/17.
 */

public class DeckApi {
    public static final  String URL="https://deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1";


    public  interface DeckDonloadDataFinish {
        public void newDeck(Deck apiDeck);
    }

    private DeckDonloadDataFinish listener;

    public void setOnDeckDowloadDataFinish(DeckDonloadDataFinish listener) {
        this.listener = listener;
    }

    public void getDeck(Context context){

        RequestQueue queue = Volley.newRequestQueue(context);

        Log.d("URL", URL);

        StringRequest request = new StringRequest(URL, new Response.Listener<String>() {
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
        DeckResponse deckResponse = gson.fromJson(reader, DeckResponse.class);
        Log.d("RESPONSE", deckResponse.toString());

        if (listener!=null){
            Deck deck = new Deck();
            deck.setId(deckResponse.getDeckId());
            deck.setRemainig(deckResponse.getRemainig());
            listener.newDeck(deck);
        }


    }
}
