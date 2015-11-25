package com.oyra.tt.test;

import android.test.AndroidTestCase;

import com.oyra.tt.oper.TextProcessor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by oyra on 07/06/15.
 */
@RunWith(Suite.class)
public class TextProcessorTest extends AndroidTestCase {

    private static final int CHAR_POS = 10;
    private TextProcessor textProcessor;

    protected void setUp() throws Exception {
        textProcessor = new TextProcessor();
        super.setUp();
    }


    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testProcess10thChar_validate() {
        assertThat(textProcessor.processNthChar("12345", CHAR_POS), is('\0'));

        assertThat(textProcessor.processNthChar("1234567890zxc", CHAR_POS), is('0'));
    }

    @Test
    public void testProcessEvery10thChar_validate() {
        char[] validRes = {'0', 'p'};
        assertArrayEquals(textProcessor.processEveryNthChar("1234567890qwertyuiopl", CHAR_POS), validRes);

        char[] validRes2 = {};
        assertArrayEquals(textProcessor.processEveryNthChar("1", CHAR_POS), validRes2);
    }

    @Test
    public void testProcessToCountWords_validate() {
        HashMap<String, Integer> validOne = new HashMap<>();
        validOne.put("12345", 1);
        assertTrue(textProcessor.processToCountWords("12345").equals(validOne));

        HashMap<String, Integer> valid2 = new HashMap<>();
        valid2.put("123onetwogoogle", 1);
        valid2.put("google", 2);
        valid2.put("45", 1);
        assertTrue(textProcessor.processToCountWords("  123Google GooGle 45 google   ").equals(valid2));

    }

}
