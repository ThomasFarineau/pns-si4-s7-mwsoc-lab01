package utils;

import java.io.Serializable;
import java.math.BigInteger;

public class Bill implements Serializable {
    String movieName;
    BigInteger outrageousPrice;

    public Bill(String movieName, BigInteger outrageousPrice) {
        this.movieName = movieName;
        this.outrageousPrice = outrageousPrice;
    }

    public String getMovieName() {
        return movieName;
    }

    public BigInteger getOutrageousPrice() {
        return outrageousPrice;
    }
}
