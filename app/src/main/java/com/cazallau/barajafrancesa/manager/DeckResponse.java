package com.cazallau.barajafrancesa.manager;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gemabeltran on 17/2/17.
 */

public class DeckResponse {

    @SerializedName("deck_id") private String deckId;
    @SerializedName("remaining") private int remainig;

    public String getDeckId() {
        return deckId;
    }

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }

    public int getRemainig() {
        return remainig;
    }

    public void setRemainig(int remainig) {
        this.remainig = remainig;
    }
}
