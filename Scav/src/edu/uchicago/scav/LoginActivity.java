package edu.uchicago.scav;

import java.util.concurrent.ExecutionException;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class LoginActivity extends Activity {
	
	/**
	 * A dummy authentication store containing known user names and passwords.
	 * TODO: remove after connecting to a real authentication system.
	 */

	/**
	 * The default email to populate the email field with.
	 */
	public static final String EXTRA_EMAIL = "com.example.android.authenticatordemo.extra.EMAIL";

	/**
	 * Keep track of the login task to ensure we can cancel it if requested.
	 */
	private UserLoginTask mAuthTask = null;

	// Values for email and password at the time of the login attempt.
	private String mCnet;
	private String mPassword;

	// UI references.
	private EditText mCnetView;
	private EditText mPasswordView;
	private View mLoginFormView;
	private View mLoginStatusView;
	private TextView mLoginStatusMessageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);
		setupActionBar();

		// Set up the login form.
		mCnet = getIntent().getStringExtra(EXTRA_EMAIL);
		mCnetView = (EditText) findViewById(R.id.cnet);
		mCnetView.setText(mCnet);
		
/*		mCnetView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((EditText) findViewById(R.id.email)).setText(R.string.uchicago_email);
			}	
		});*/
		
		mCnetView.setOnFocusChangeListener(new View.OnFocusChangeListener() {	
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus)
				{
					Editable email = ((EditText) findViewById(R.id.cnet)).getText();
					String enteredText = email.toString();
					if (enteredText.contains("@uchicago.edu"))
					{
						String cnet = enteredText.split("@")[0];
						((EditText) findViewById(R.id.cnet)).setText(cnet);
					} else if (enteredText.contains("@"))
					{
						Toast cnetAlert = Toast.makeText(getApplicationContext(), R.string.cnet_alert, (Toast.LENGTH_LONG + 10));
						cnetAlert.setGravity(Gravity.TOP, 0, 200);
						cnetAlert.show();
					}
				}
			}
			
		});

		mPasswordView = (EditText) findViewById(R.id.password);
		mPasswordView
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView textView, int id,
							KeyEvent keyEvent) {
						if (id == R.id.login || id == EditorInfo.IME_NULL) {
							try {
								attemptLogin();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ExecutionException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							return true;
						}
						return false;
					}
				});

		mLoginFormView = findViewById(R.id.login_form);
		mLoginStatusView = findViewById(R.id.login_status);
		mLoginStatusMessageView = (TextView) findViewById(R.id.login_status_message);

		findViewById(R.id.sign_in_button).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						try {
							attemptLogin();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ExecutionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			// Show the Up button in the action bar.
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			// TODO: If Settings has multiple levels, Up should navigate up
			// that hierarchy.
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.pretend:
			Intent tabs = new Intent(LoginActivity.this, Tabs.class);
			startActivity(tabs);
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

	/**
	 * Attempts to sign in or register the account specified by the login form.
	 * If there are form errors (invalid email, missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public void attemptLogin() throws InterruptedException, ExecutionException {
		// get rid of this to make it respond to the sign in button every time
//		if (mAuthTask != null) {
//			return;
//		}

		// Reset errors.
		mCnetView.setError(null);
		mPasswordView.setError(null);

		// Store values at the time of the login attempt.
		mCnet = mCnetView.getText().toString();
		mPassword = mPasswordView.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid password.
		if (TextUtils.isEmpty(mPassword)) {
			mPasswordView.setError(getString(R.string.error_field_required));
			focusView = mPasswordView;
			cancel = true;
		} else if (mPassword.length() < 4) {
			mPasswordView.setError(getString(R.string.error_invalid_password));
			focusView = mPasswordView;
			cancel = true;
		}

		// Check for a valid email address.
		if (TextUtils.isEmpty(mCnet)) {
			mCnetView.setError(getString(R.string.error_field_required));
			focusView = mCnetView;
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
			mLoginStatusMessageView.setText(R.string.login_progress_signing_in);
			showProgress(false);
			mAuthTask = new UserLoginTask();
			
				// check if the user exists and return a dialog box if not
				User myUser = mAuthTask.execute((Void) null).get();
				
				if (myUser == null)
				{
					AlertDialog.Builder builder = new AlertDialog.Builder(this);
					builder.setMessage(R.string.no_user_found_text);
					builder.setTitle(R.string.no_user_found_title);
					builder.setPositiveButton(R.string.new_user_button, new DialogInterface.OnClickListener() {
				           public void onClick(DialogInterface dialog, int id)
				           {
				        	   // go to register them
				               registerNewUser();
				               Toast verificationSent = Toast.makeText(Scav.app, R.string.verification_sent, Toast.LENGTH_LONG);
				               verificationSent.setGravity(Gravity.CENTER, 0, 0);
				               verificationSent.show();
				           }
				       });
					builder.setNegativeButton(R.string.made_mistake_login,  new DialogInterface.OnClickListener() {
				           public void onClick(DialogInterface dialog, int id) 
				           {}
				       });
					AlertDialog dialog = builder.create();
					dialog.show();
				} else if (myUser.cnetVerified == false)
				{
					Log.d("message", "I get here");
					AlertDialog.Builder builder = new AlertDialog.Builder(this);
					builder.setMessage(R.string.not_verified_text);
					builder.setTitle(R.string.not_verified_title);
					builder.setPositiveButton(R.string.ok_dialog, new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {}
					});
					builder.setNegativeButton(R.string.no_verification_email, new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							registerNewUser();
							Toast emailResent = Toast.makeText(Scav.app, R.string.verification_resent, Toast.LENGTH_LONG);
							emailResent.show();
						}
					});
					AlertDialog dialog = builder.create();
					dialog.show();
				} else if (myUser.cnetVerified == true)
				{
					Log.d("team", myUser.aTeamName);
					if (myUser.aTeamName == "null")
					{
						completeRegistration();
					} else
					{
						launchTabs();
					}
				}
				
		}
	}
	
	protected void registerNewUser()
	{
		new RegisterNewUser().execute((Void) null);
	}
	
	protected void completeRegistration()
	{
		Intent pickTeam = new Intent(LoginActivity.this, PickTeam.class);
		pickTeam.putExtra("cnet", mCnet);
		pickTeam.putExtra("password", mPassword);
		startActivity(pickTeam);
	}
	
	protected void launchTabs()
	{
		SharedPreferences scavPrefs = getSharedPreferences(Scav.PREFS_NAME, 0);
		scavPrefs.edit().putString("cnetid", mCnet).commit();
		scavPrefs.edit().putString("password", mPassword).commit();
		scavPrefs.edit().putBoolean("first_launch", false).commit();
		Intent tabs = new Intent(LoginActivity.this, Tabs.class);
		startActivity(tabs);
		finish();
	}

	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(
					android.R.integer.config_shortAnimTime);

			mLoginStatusView.setVisibility(View.VISIBLE);
			mLoginStatusView.animate().setDuration(shortAnimTime)
					.alpha(show ? 1 : 0)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginStatusView.setVisibility(show ? View.VISIBLE
									: View.GONE);
						}
					});

			mLoginFormView.setVisibility(View.VISIBLE);
			mLoginFormView.animate().setDuration(shortAnimTime)
					.alpha(show ? 0 : 1)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginFormView.setVisibility(show ? View.GONE
									: View.VISIBLE);
						}
					});
		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}

	/**
	 * Represents an asynchronous login/registration task used to authenticate
	 * the user.
	 */
	private class UserLoginTask extends AsyncTask<Void, Void, User> {
		@Override
		protected User doInBackground(Void... params) 
		{
			
			User myUser = new ScavRest(Scav.serverURL, Scav.accessKey).getUser(mCnet, mPassword);
			return myUser;
		}
		
	}
	
	private class RegisterNewUser extends AsyncTask<Void, Void, Void>
	{
		@Override
		protected Void doInBackground(Void...params)
		{
			new ScavRest(Scav.serverURL, Scav.accessKey).createUser(mCnet, mPassword);
			return null;
		}
	}
	
}
