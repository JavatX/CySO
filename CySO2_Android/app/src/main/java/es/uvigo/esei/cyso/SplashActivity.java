package es.uvigo.esei.cyso;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

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
import java.util.concurrent.ExecutionException;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;
    private static final int CONECTION_TIMEOUT = 2000;
    public static String[] dataR, dataC, dataPrim;
    ReportTask mRepTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        /*
        mRepTask = new ReportTask();
        try {
            mRepTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /*if(dataR[0].equals("error") || dataC.equals("error")) {
                    Snackbar.make(findViewById(R.id.activity_splash), "Error en la conexión, la aplicació se cerrará.", Snackbar.LENGTH_LONG).show();
                    Log.d("INFO", "Closing app.");
                    finish();
                }else{*/
                    Intent intent = new Intent(SplashActivity.this, LoginActivityV2.class);
                    startActivity(intent);
                    finish();
                    /*
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    intent.putExtra("constructions", dataC);
                    intent.putExtra("reports", dataR);
                    startActivity(intent);
                    finish();*/
                //}
            }
        }, SPLASH_TIME_OUT);


    }

    public class ReportTask extends AsyncTask<Void, Void, String> {

        //private final String construction;
        URL url = null;
        HttpURLConnection connection;

        public ReportTask() {
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
                Log.d("INFO", "SplashActivity");
                url = new URL("http://83.165.253.135/android/report_list.php");
                Log.d("INFO", url.toString());
                connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(CONECTION_TIMEOUT);
                connection.setReadTimeout(CONECTION_TIMEOUT);

                connection.setRequestMethod("POST");

                connection.setDoInput(true);
                connection.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder().appendQueryParameter("cons", "denuncias");
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

        }

        @Override
        protected void onCancelled() {
            mRepTask = null;
            //showProgress(false);
        }
    }

}
