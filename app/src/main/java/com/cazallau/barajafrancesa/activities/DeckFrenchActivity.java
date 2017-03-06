package com.cazallau.barajafrancesa.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cazallau.barajafrancesa.Deck;
import com.cazallau.barajafrancesa.R;
import com.cazallau.barajafrancesa.manager.CardAPI;
import com.cazallau.barajafrancesa.manager.CardResponse;
import com.cazallau.barajafrancesa.manager.DeckApi;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeckFrenchActivity extends AppCompatActivity {
    private Deck deck = new Deck();
    private boolean load;
    @BindView(R.id.activity_deck_french_text) TextView textView;
    @BindView(R.id.activity_deck_french_button) Button startButton;
    @BindView(R.id.activity_deck_french_image) ImageView deckImage;
    @BindView(R.id.alert) LinearLayout alert;
    @BindView(R.id.activity_deck_french_button_cancel) Button cancelButton;
    @BindView(R.id.activity_deck_french_button_ok) Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_french);
        ButterKnife.bind(this);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewDeck();
                deckImage.setImageResource(R.drawable.card1);
            }
        });


        deckImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (load) {
                    load = false;
                    deckImage.setImageResource(R.drawable.card1);
                    getNewCard();
                }
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewDeck();
                alert.setVisibility(View.GONE);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    public void createNewDeck() {

        DeckApi deckApi = new DeckApi();
        deckApi.getDeck(this);
        deckApi.setOnDeckDowloadDataFinish(new DeckApi.DeckDonloadDataFinish() {
            @Override
            public void newDeck(Deck apiDeck) {
                textView.setText(""+ apiDeck.getRemainig());
                deck = apiDeck;
                load = true;

            }
        });
    }

    public void getNewCard(){
        CardAPI cardApi = new CardAPI();
        cardApi.getCard(this, deck.getId());
        cardApi.setOnCardDowloadDataFinish(new CardAPI.CardDonloadDataFinish() {
            @Override
            public void newCard(CardResponse.Card card, int remainig) {
                if (remainig != 0){
                    Picasso.with(getBaseContext()).load(card.getImage()).into(deckImage);
                    textView.setText(""+ remainig);
                    load = true;
                }else {
                    alert.setVisibility(View.VISIBLE);
                    load = false;


                }

            }
        });

    }




}
