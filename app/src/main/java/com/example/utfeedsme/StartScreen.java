package com.example.utfeedsme;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.utfeedsme.addeditevent.AddEditEventActivity;
import com.firebase.client.Firebase;
import com.firebase.ui.auth.core.AuthProviderType;
import com.firebase.ui.auth.core.FirebaseLoginBaseActivity;
import com.firebase.ui.auth.core.FirebaseLoginError;
import com.parse.Parse;
import com.parse.ParseObject;

public class StartScreen extends FirebaseLoginBaseActivity {

    private Firebase mRef;
	
	private final static String TAG = "StartScreen";
	
	//protected RecordsDataSource dataSource;
		
	ImageButton happening_now_btn, near_you_btn, all_events_btn, add_event_btn, add_event_btn2;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        mRef = new Firebase("https://austin-feeds-me.firebaseio.com/");
        
       // final StartScreen thisActivity = this;
        Parse.initialize(this, "vdhZN2rmjBYhLJFlFK8NRFW0wKZHQ3CDNMEkwAWy", BuildConfig.PARSE_KEY);
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
        //dataSource = new RecordsDataSource(this);
        //dataSource.open();
        
        happening_now_btn = (ImageButton) findViewById(R.id.happening_now);
        near_you_btn = (ImageButton) findViewById(R.id.near_you);
        all_events_btn = (ImageButton) findViewById(R.id.all_events);
        add_event_btn = (ImageButton) findViewById(R.id.add_event);
        add_event_btn2 = (ImageButton) findViewById(R.id.add_event2);

        
        happening_now_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent openHappeningNow = new Intent("com.example.utfeedsme.HAPPENINGNOW");
				startActivity(openHappeningNow);
			}
		});
        
        near_you_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent openNearYou = new Intent("com.example.utfeedsme.NEARYOU");
				startActivity(openNearYou);
			}
		});
        
        all_events_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent openAllEvents = new Intent("com.example.utfeedsme.ALLEVENTS");
				startActivity(openAllEvents);
			}
		});
        
        add_event_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent openAddEvent = new Intent("com.example.utfeedsme.ADDEVENT");
				Log.v(TAG, "yoooo we pressed the add event button");
				startActivity(openAddEvent);
			}
		});

        add_event_btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent openAddEvent = new Intent(StartScreen.this, AddEditEventActivity.class);
                Log.v(TAG, "yoooo we pressed the add event 2 button");
                startActivity(openAddEvent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        setEnabledAuthProvider(AuthProviderType.TWITTER);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start_screen, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.login_menu_item).setVisible(getAuth() == null);
        menu.findItem(R.id.logout_menu_item).setVisible(getAuth() != null);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.login_menu_item:
                this.showFirebaseLoginPrompt();
                return true;
            case R.id.logout_menu_item:
                this.logout();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
      //dataSource.open();
      super.onResume();
    }

    @Override
    protected void onPause() {
      //dataSource.close();
      super.onPause();
    }

    @Override
    public Firebase getFirebaseRef() {
        return mRef;
    }

    @Override
    public void onFirebaseLoginProviderError(FirebaseLoginError firebaseError) {
        Log.e(TAG, "Login provider error: " + firebaseError.toString());
        resetFirebaseLoginPrompt();
    }

    @Override
    public void onFirebaseLoginUserError(FirebaseLoginError firebaseError) {
        Log.e(TAG, "Login user error: "+firebaseError.toString());
        resetFirebaseLoginPrompt();
    }

}
