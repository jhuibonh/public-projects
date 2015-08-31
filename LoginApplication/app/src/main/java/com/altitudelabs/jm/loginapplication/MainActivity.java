package com.altitudelabs.jm.loginapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkLoginDetails()){
                    Toast.makeText(MainActivity.this, "Incorrect Login Details", Toast.LENGTH_LONG).show();
                }
                else {
                    startNewActivity();
                }
            }
        });


    }

    /**
     * kill current activity and start the second activity
     */
    public void startNewActivity(){
        Intent i = new Intent(this,SecondActivity.class);
        startActivity(i);
        finish();
    }

    /**
     * Returns true iff username is less than 8 characters, if
     * password is less than 8 characters and if the username is
     * in alphabetical order
     * @return
     */
    public boolean checkLoginDetails(){
        EditText userField = (EditText) findViewById(R.id.userField);
        EditText passField = (EditText) findViewById(R.id.passField);
        String user = userField.getText().toString();
        String pass = passField.getText().toString();

        if (user.length() < 8 || pass.length() < 8){
            return false;
        }
        else if (!isAlphabetic(user)){
            return false;
        }
        return true;

    }

    /**
     * Returns True iff username contains only alphabets and if
     * username is in the correct alphabetical order
     * @param user
     * @return
     */
    public boolean isAlphabetic(String user){
        char[] list = user.toCharArray();

        for (char c:list){
            if (!Character.isLetter(c)){
                Toast.makeText(MainActivity.this,"fail2",Toast.LENGTH_LONG).show();
                return false;
            }
        }

        for (int i=0; i<user.length()-1; i++){
            String letter = Character.toString(user.charAt(i));
            String letter2 = Character.toString(user.charAt(i+1));
            if (letter.compareTo(letter2) > 0){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

