package com.oyra.tt.oper;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * Created by oyra on 07/06/15.
 */
public class TextProcessor {



    /**
     * Finds the 10th character in the string
     *
     * @param s input string
     * @return 10th character in the string
     */
    public char processNthChar(String s, int numOfChar) {
        char res = '\0';// = 0;
        if (s != null && s.length() > numOfChar - 1) {
            res = s.charAt(numOfChar - 1);
        }
        return res;

    }

    /**
     * Find every Nth character (i.e: 10th, 20th,  30th, etc.) in the string
     *
     * @param s Nth chars' array
     */
    public char[] processEveryNthChar(String s, int posPeriodOfChars) {
        char[] res = new char[0];
        if (s != null && s.length() > posPeriodOfChars - 1) {

            int size = s.length() / posPeriodOfChars;
            res = new char[size];
            int k = 0;
            for (int i = posPeriodOfChars - 1; i < size * posPeriodOfChars; i = i + posPeriodOfChars) {

                res[k] = s.charAt(i);
                k++;

            }

        }

        return res;
    }


    /**
     * Splits the text into words by using whitespace characters (i.e: space, linefeed, etc.).
     * Counts the occurrence of every word (case insensitively).
     *
     * @param s input string
     * @return map with words and their numbers
     */
    public HashMap<String, Integer> processToCountWords(String s) {
        HashMap<String, Integer> words = new HashMap<>();
        if (s != null && s.length() > 0) {

            Pattern p = Pattern.compile("\\s+");
            Matcher m = p.matcher(s);

            //end pos of the previous whitespace interval + 1
            int prevEnd = 0;

            //first word
            if (!m.find()) {
                putWord(words, s);
            } else {
                if (prevEnd != m.start()) {
                    putWord(words, s.substring(prevEnd, m.start()));
                }
                prevEnd = m.end();

                while (m.find()) {
                    putWord(words, s.substring(prevEnd, m.start()));
                    prevEnd = m.end();
                }

                if (prevEnd < s.length() - 1) {
                    //last word
                    putWord(words, s.substring(prevEnd, s.length()));
                }
            }
        }
        return words;
    }

    private void putWord(HashMap<String, Integer> words, String word) {
        word = word.toLowerCase();
        if (words.containsKey(word)) {
            words.put(word, words.get(word) + 1);
        } else {
            words.put(word, 1);
        }
    }
}
