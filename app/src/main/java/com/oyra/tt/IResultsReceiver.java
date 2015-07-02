package com.oyra.tt;

import java.util.HashMap;

/**
 * Created by oyra on 07/06/15.
 */
public interface IResultsReceiver {

    void onProcessingFinished(char c, int numOfChar);

    void onProcessingFinished(char[] chars, int posPeriodOfChars);

    void onProcessingFinished(HashMap<String, Integer> words);
}
