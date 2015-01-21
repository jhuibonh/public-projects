package com.altitudelabs.jm.loginapplication;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;


public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    public MainActivityTest() {
        super(MainActivity.class);
    }

    /**
     * test cases for helper function checkLoginDetails
     */
    public void testCheckLoginDetails(){
        MainActivity activity = getActivity();

        final EditText userField = (EditText) activity.findViewById(R.id.userField);
        final EditText passField = (EditText) activity.findViewById(R.id.passField);
        final EditText userField2 = (EditText) activity.findViewById(R.id.userField);
        final EditText passField2 = (EditText) activity.findViewById(R.id.passField);
        final EditText userField3 = (EditText) activity.findViewById(R.id.userField);
        final EditText passField3 = (EditText) activity.findViewById(R.id.passField);

        //no username or password
        assertFalse(activity.checkLoginDetails());

        //correct username and password
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                userField.setText("abcdefgh");
                passField.setText("12345678");
            }
        });
        assertFalse(activity.checkLoginDetails());

        //incorrect username and correct password
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                userField2.setText("abc");
                passField2.setText("12345678");
            }
        });
        assertFalse(activity.checkLoginDetails());

        //correct username but incorrect password
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                userField3.setText("abcdefgh");
                passField3.setText("123");
            }
        });
        assertFalse(activity.checkLoginDetails());

    }

    /**
     * test cases for helper function isAlphabetic
     */
    public void testIsAlphabetic(){
        MainActivity activity = getActivity();
        String theMamba = "kobe";
        String theMamba2 = "kobebryantthegreatest";
        String correct = "abcdefgh";
        String correct2 = "aaaaaaaa";
        String hasSymbols = "*&%*@$$%^^)@";

        assertFalse(activity.isAlphabetic(theMamba));
        assertTrue(activity.isAlphabetic(correct));
        assertFalse(activity.isAlphabetic(hasSymbols));
        assertTrue(activity.isAlphabetic(correct2));
        assertFalse(activity.isAlphabetic(theMamba2));
    }
}