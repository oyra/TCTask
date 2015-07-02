package com.oyra.tt.oper;

import com.oyra.tt.IResultsReceiver;
import com.oyra.tt.oper.net.NetHelper;

import java.util.Random;

/**
 * Created by oyra on 07/06/15.
 */
public class Processor {

    public static final String[] URL = {"https://www.google.com/", "http://developer.android.com/index.html", "http://developer.android.com/develop/index.html"};


    public static final int TT_10TH_CHAR_REQUEST = 70000;
    public static final int TT_EVERY_10TH_CHAR_REQUEST = 70001;
    public static final int TT_WORD_COUNTER_REQUEST = 70002;

    private static final int CHAR_POS = 25;


    public void getData(final int reqType, final IResultsReceiver lstnr) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                NetHelper net = new NetHelper();
                String s = net.get(URL[new Random().nextInt(URL.length)]);
                TextProcessor processor = new TextProcessor();

                switch (reqType) {
                    case TT_10TH_CHAR_REQUEST:
                        if (lstnr != null) {
                            lstnr.onProcessingFinished(processor.processNthChar(s, CHAR_POS), CHAR_POS);
                        }
                        break;
                    case TT_EVERY_10TH_CHAR_REQUEST:
                        if (lstnr != null) {
                            lstnr.onProcessingFinished(processor.processEveryNthChar(s, CHAR_POS), CHAR_POS);
                        }
                        break;
                    case TT_WORD_COUNTER_REQUEST:
                        if (lstnr != null) {
                            lstnr.onProcessingFinished(processor.processToCountWords(s));
                        }
                        break;
                }

            }
        }).start();

    }


}
