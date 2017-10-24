package es.uvigo.esei.cyso;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivityV2 extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;
    private static final int CONECTION_TIMEOUT = 2000;
    public static String[] dataR, dataC, dataPrim;
    LoginActivityV2.ReportTask mRepTask;
    Intent intent;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_v2);
        //getActionBar().setTitle("CySO");
        //setupActionBar();

        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        //populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        intent = new Intent(LoginActivityV2.this, MainActivity.class);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        Button guestButton = (Button) findViewById(R.id.no_sign_in_button);
        guestButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mRepTask = new LoginActivityV2.ReportTask();
                try {
                    showProgress(true);
                    mRepTask.execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                /*
                Intent intent = new Intent(LoginActivityV2.this, MainActivity.class);
                intent.putExtra("constructions", dataC);
                intent.putExtra("reports", dataR);
                startActivity(intent);*/
                finish();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isDNIValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            finish();
            overridePendingTransition( 0, 0);
            startActivity(getIntent());
            overridePendingTransition( 0, 0);
            Snackbar.make(mLoginFormView, "Campos erroneos", Snackbar.LENGTH_SHORT).show();
            //focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            //if()
            showProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isDNIValid(String dni) {
        //TODO: Replace this with your own logic
        return dni.length() == 9;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 0;
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
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivityV2.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, String> {

        private final String mEmail;
        private final String mPassword;
        URL url = null;
        HttpURLConnection connection;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected String doInBackground(Void... args) {
            // TODO: attempt authentication against a network service.

            try {
                url = new URL("http://83.165.253.135/android/login.php");
                Log.d("LOGIN", url.toString());
                connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(10000);
                connection.setConnectTimeout(15000);
                connection.setRequestMethod("POST");

                connection.setDoInput(true);
                connection.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder().appendQueryParameter("user", mEmail).appendQueryParameter("pass", mPassword);
                String query = builder.build().getEncodedQuery();
                Log.d("LOGIN", query.toString());
                OutputStream os = connection.getOutputStream();
                Log.d("LOGIN", "os");
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8")
                );
                Log.d("LOGIN", "writer start");
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                connection.connect();
                // Simulate network access.
                //Thread.sleep(2000);

            }catch (IOException ie){
                Log.d("LOGIN", ie.toString());
                return "exception";
            }

            try{
                int response_code = connection.getResponseCode();
                Log.d("LOGIN", "Connection response");
                if (response_code == HttpURLConnection.HTTP_OK){
                    Log.d("LOGIN", "Connection if");
                    InputStream input = connection.getInputStream();
                    Log.d("LOGIN", "Connection input");
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(input)
                    );
                    Log.d("LOGIN", "Connection buffer");
                    StringBuilder result = new StringBuilder();
                    String line;
                    Log.d("LOGIN", "Connection string");
                    while((line = reader.readLine()) != null){
                        result.append(line);
                    }
                    Log.d("LOGIN", result.toString());
                    return result.toString();

                }else{
                    return "unsuccessful";
                }
            }catch (IOException io){
                return "exception";
            }finally {
                connection.disconnect();
            }
            /*for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }*/

            // TODO: register the new account here.
            //return true;
        }

        @Override
        protected void onPostExecute(final String result) {
            mAuthTask = null;
            showProgress(false);

            if (result.contains("true")) {
                Log.d("LOGIN", "Autentificación correcta");
                Toast.makeText(LoginActivityV2.this, "Login successfully", Toast.LENGTH_LONG);
                mRepTask = new LoginActivityV2.ReportTask(mEmail);
                try {

                    mRepTask.execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                //intent = new Intent(LoginActivityV2.this, MainActivity.class);
                //intent.putExtra("dni", mEmail);
                //startActivity(intent);
                //finish();
            } else {
                if(result.contains("false_pass")){
                    mPasswordView.setError(getString(R.string.error_incorrect_password));
                    mPasswordView.requestFocus();
                }else{
                    if(result.contains("false_DNI")){
                        mEmailView.setError(getString(R.string.error_invalid_email));
                        mEmailView.requestFocus();
                    }else {
                        if (result.contains("unsuccessful") || result.contains("exception")) {
                            Toast.makeText(LoginActivityV2.this, "Some error occurred", Toast.LENGTH_LONG);
                        }
                    }
                }

            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }

    public class ReportTask extends AsyncTask<Void, Void, String> {

        //private final String construction;
        URL url = null;
        HttpURLConnection connection;
        private String dni;

        public ReportTask() {
            dni = "NC";
        }

        public ReportTask( String dni){
            this.dni = dni;
        }
        /*
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            ProgressDialog progDailog = new ProgressDialog(MainActivity.this);
            progDailog.setMessage("Loading...");
            progDailog.setIndeterminate(false);
            progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDailog.setCancelable(true);
            progDailog.show();
        }*/
        @Override
        protected String doInBackground(Void... args) {
            // TODO: attempt authentication against a network service.

            try {
                Log.d("INFO", "Report list");
                Log.d("DNI", dni);
                url = new URL("http://83.165.253.135/android/report_list.php");
                Log.d("INFO", url.toString());
                connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(CONECTION_TIMEOUT);
                connection.setReadTimeout(CONECTION_TIMEOUT);

                connection.setRequestMethod("POST");

                connection.setDoInput(true);
                connection.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder().appendQueryParameter("cons", "denuncias");
                if(!dni.equals("NC")){
                    Log.d("INFO", "DNI setted");
                    builder.appendQueryParameter("dni", this.dni);
                }
                String query = builder.build().getEncodedQuery();
                Log.d("INFO", query);
                OutputStream os = connection.getOutputStream();
                Log.d("INFO", "os");
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8")
                );
                Log.d("INFO", "writer start");
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                connection.connect();

                // Simulate network access.
                //Thread.sleep(2000);

            } catch (SocketTimeoutException se) {
                Log.d("INFO", se.toString());
                return "exception";
            } catch (IOException ie) {
                Log.d("INFO", ie.toString());
                return "exception";
            }

            try {
                int response_code = connection.getResponseCode();
                Log.d("INFO", "Connection response");
                if (response_code == HttpURLConnection.HTTP_OK) {
                    Log.d("INFO", "Connection if");
                    InputStream input = connection.getInputStream();
                    Log.d("INFO", "Connection input");
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(input)
                    );
                    Log.d("INFO", "Connection buffer");
                    StringBuilder result = new StringBuilder();
                    String line;
                    Log.d("INFO", "Connection string");
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    Log.d("INFO", result.toString());
                    return result.toString();

                } else {
                    Log.d("INFO", "Unsuccess");
                    return "unsuccessful";
                }
            } catch (IOException io) {
                return "exception";
            } finally {
                connection.disconnect();
            }
            /*for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }*/

            // TODO: register the new account here.
            //return true;
        }

        @Override
        protected void onPostExecute(final String result) {
            Log.d("INFO-PostExecute", "Post first if");
            if (result.length() > 0) {
                if (result.equals("exception") || result.equals("unsuccessful")) {
                    Log.d("INFO", "An error occured. Closing the app.");
                    dataC = new String[1];
                    dataR = new String[1];
                    dataC[0] = "error";
                    dataR[0] = "error";
                }else {
                    //mRepTask = null;
                    //showProgress(false);
                    Log.d("INFO-PostExecute", "Split data");
                    dataPrim = result.split(":");
                    Log.d("SPLIT", dataPrim[0]);
                    Log.d("SPLIT", dataPrim[1]);
                    dataR = dataPrim[0].split("&");
                    dataC = dataPrim[1].split("&");
                /*
                for (int i = 0; i < data.length; i++) {
                    data[i] = info[i];
                }
    */
                    for (int i = 0; i < dataR.length; ++i)
                        Log.d("INFO-PostExecute", dataR[i]);
                    for (int i = 0; i < dataC.length; ++i)
                        Log.d("INFO-PostExecute", dataC[i]);
                    /*Log.d("INFO-PostExecute", dataR[1]);
                    Log.d("INFO-PostExecute", dataR[2]);
                    Log.d("INFO-PostExecute", dataR[3]);
                    Log.d("INFO-PostExecute", dataR[4]);*/
                }

            } else {
                Log.d("INFO-PostExecute", "ERROR");
                dataR = new String[1];
                dataR[0] = result;
                dataC = new String[1];
                dataC[0] = result;
                //Snackbar.make(mainV, "Error en la conexión", Snackbar.LENGTH_LONG).show();
            }

            //intent = new Intent(LoginActivityV2.this, MainActivity.class);
            SavedData.getInstance(dataC, dataR, dni);
            //intent.putExtra("constructions", dataC);
            //intent.putExtra("reports", dataR);
            //intent.putExtra("dni", dni);
            //intent.putExtra("data", savedData);
            finish();
            startActivity(intent);


        }

        @Override
        protected void onCancelled() {
            mRepTask = null;
            //showProgress(false);
        }
    }
}

