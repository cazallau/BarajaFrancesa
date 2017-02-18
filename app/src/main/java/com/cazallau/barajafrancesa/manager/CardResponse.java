package com.cazallau.barajafrancesa.manager;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gemabeltran on 17/2/17.
 */

public class CardResponse {

    @SerializedName("cards") private List<Card> card;
    @SerializedName("remaining") private int remainig;


    public class Card {
        @SerializedName("image") String image;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }

    public List<Card> getCard() {
        return card;
    }

    public void setCard(List<Card> card) {
        this.card = card;
    }

    public int getRemainig() {
        return remainig;
    }

    public void setRemainig(int remainig) {
        this.remainig = remainig;
    }
}
