package utils;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Bill class
 * @param movieName the name of the movie
 * @param outrageousPrice the outrageous price of the movie
 */
public record Bill(String movieName, BigInteger outrageousPrice) implements Serializable {
}
