package es.uvigo.esei.cyso;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.common.api.GoogleApiClient;
import com.google.zxing.Result;

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
import java.util.concurrent.TimeoutException;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.widget.AbsListView.*;


public class MainActivity extends AppCompatActivity {


    static final int INTENT_CAMERA_CODE = 0;
    static final int INTENT_LOGIN_CODE = 01;
    static final int CONECTION_TIMEOUT = 2000;
    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 1;
    public static String[] dataR, dataC, dataPrim;
    protected static AccessTask infoTask = null;
    View mainV;
    String cameraData, reportData, loginData;
    /**
     * The {@link PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private AccessTask mAcceTask = null;
    private ReportTask mRepTask = null;
    private String dni;

    private View mProgressView, mMainView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MAIN", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mProgressView = findViewById(R.id.login_progress);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        mainV = findViewById(R.id.container);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                        startActivityForResult(intent, INTENT_CAMERA_CODE);
                    } else {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
                    }
                } else {
                    Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                    startActivityForResult(intent, INTENT_CAMERA_CODE);
                }
                /*
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                startActivityForResult(intent, INTENT_CAMERA_CODE);*/
            }
        });
        infoTask = new AccessTask();
        /**
         *
         *
         * CAMBIOS INTRODUCIDOS AQUI
         *
         *
         */
        if(savedInstanceState == null) {
            //Bundle extras = getIntent().getExtras();

            Log.d("DATAAAAAAAAAAA", SavedData.data.getDni());
            dataC = SavedData.data.getCons();
            dataR = SavedData.data.getRepo();
            /*dni = extras.getString("dni");
            if (dni.equals("NC")) {
                Log.d("MAIN", "Setting data");
                //Bundle extras = getIntent().getExtras();
                dataC = extras.getStringArray("constructions");
                Log.d("MAIN", "datC: " + dataC.toString());
                dataR = extras.getStringArray("reports");
            } else {
                Log.d("MAIN", "New data");
                dataC = extras.getStringArray("constructions");
                Log.d("MAIN", "datC: " + dataC.toString());
                dataR = extras.getStringArray("reports");
            /*
            mRepTask = new ReportTask();
            try {
                mRepTask.execute((Void) null).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            }*/
        }else{
            dataC = savedInstanceState.getStringArray("Cdata");
            dataR = savedInstanceState.getStringArray("Rdata");
        }
        /*
        mRepTask = new ReportTask();
        try {
            mRepTask.execute((Void) null).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        */
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArray("Cdata", dataC);
        outState.putStringArray("Rdata", dataR);
        outState.putString("nif", dni);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if(infoTask.getCalled()){
            infoTask = new AccessTask();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_CAMERA) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                startActivityForResult(intent, INTENT_CAMERA_CODE);
            } else {
                Snackbar snackbar = Snackbar.make(mainV, "El permiso de acceso a la cámara es necesario", Snackbar.LENGTH_LONG)
                        .setAction("ACTIVAR", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent in = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                in.addCategory(Intent.CATEGORY_DEFAULT);
                                in.setData(Uri.parse("package:" + getApplicationContext().getPackageName()));
                                in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                in.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                in.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

                                startActivity(in);
                            }
                        });
                snackbar.show();
                // Toast.makeText(this, "Permiso no garantizado", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == INTENT_CAMERA_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                cameraData = data.getStringExtra("QR_CODE");
                if(cameraData.contains("CONSTRUC")) {
                    mAcceTask = new AccessTask(cameraData);
                    mAcceTask.execute((Void) null);
                }else{
                    Snackbar.make(mViewPager, "QR no válido.", Snackbar.LENGTH_SHORT).show();
                }
                //Toast.makeText(getApplicationContext(), cameraData, Toast.LENGTH_SHORT).show();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
        if (requestCode == INTENT_LOGIN_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                dni = data.getStringExtra("dni");
                ReportTask mReTask = new ReportTask();
                mReTask.execute((Void) null);
                //Toast.makeText(getApplicationContext(), cameraData, Toast.LENGTH_SHORT).show();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    */
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
        if (id == R.id.action_login) {
            Intent intent = new Intent(this, LoginActivity.class);
            this.startActivityForResult(intent, INTENT_LOGIN_CODE);
            //Toast.makeText(getApplicationContext(), "Login option clicked", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mViewPager.setVisibility(show ? View.GONE : View.VISIBLE);
            mViewPager.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mViewPager.setVisibility(show ? View.GONE : View.VISIBLE);
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
            mViewPager.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment{
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        ListView listD;
        ListView listC;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public void llamarAsync(String item) {
            //showProgress(true);
            infoTask.setConstruccion(item);
            infoTask.execute();

        }

        public void llamarAsyncR(String item) {
            //showProgress(true);
            infoTask.setReport(item);
            infoTask.execute();

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView;
            if (getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
                rootView = inflater.inflate(R.layout.fragment_main, container, false);
                listD = (ListView) rootView.findViewById(R.id.denuncias);
                TextView tv = (TextView) rootView.findViewById(R.id.empty_text);
                listD.setEmptyView(tv);
                Log.d("ADAPTER", "Data setting");
                if (!dataR[0].equals("vacio")) {
                    String[] s = dataR;
                    Log.d("ADAPTER", "String adapter");
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_expandable_list_item_1, android.R.id.text1, s);
                    Log.d("ADAPTER", "Setting adapter");
                    listD.setAdapter(adapter);
                    Log.d("ADAPTER", "Adapter setted");
                    listD.setOnScrollListener(new OnScrollListener() {
                        @Override
                        public void onScrollStateChanged(AbsListView view, int scrollState) {

                        }

                        @Override
                        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                        }
                    });
                    listD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            //ReportInfoTask at = new ReportInfoTask((String) listC.getItemAtPosition(position));
                            //at.execute((Void) null);
                            llamarAsyncR((String) listD.getItemAtPosition(position));
                            //Toast.makeText(getContext(), "Position: " + position + " Item: " + (String) listD.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                //String[] s = new String[]{"1", "2"};
                //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_expandable_list_item_1, android.R.id.text1, s);
                //listD.setAdapter(adapter);
            } else {
                rootView = inflater.inflate(R.layout.fragment_second, container, false);
                listC = (ListView) rootView.findViewById(R.id.obras);
                TextView tv = (TextView) rootView.findViewById(R.id.empty_text2
                );
                listC.setEmptyView(tv);
                Log.d("ADAPTER", "Data setting");
                if (!dataC[0].equals("vacio")) {
                    String[] s = dataC;
                    Log.d("ADAPTER", "String adapter");
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_expandable_list_item_1, android.R.id.text1, s);
                    Log.d("ADAPTER", "Setting adapter");
                    listC.setAdapter(adapter);
                    Log.d("ADAPTER", "Adapter setted");
                    listC.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            llamarAsync((String) listC.getItemAtPosition(position));
                            //ReportInfoTask at = new ReportInfoTask((String) listC.getItemAtPosition(position));
                            //at.execute((Void) null);
                            //Toast.makeText(getContext(), "Position: " + position + " Item: " + (String) listC.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                /*
                textView = (TextView) rootView.findViewById(R.id.section_label);
                textView.setText("Fragment 2");
                TextView textView2 = (TextView) rootView.findViewById(R.id.section_label2);
                textView2.setText("Fragment click");
                textView2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                    }
                });*/
            }
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "DENUNCIAS ENVIADAS";
                case 1:
                    return "CONSTRUCCIONES";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }

    public class AccessTask extends AsyncTask<Void, Void, String> {

        URL url = null;
        HttpURLConnection connection;
        private String construction, report;
        private boolean called = false;
        private boolean typeCall; //true - Construction, false - Report

        AccessTask() {
        }

        AccessTask(String construction) {
            this.construction = construction;
            typeCall = true;
        }

        public void setConstruccion(String value) {
            this.construction = value;
            typeCall = true;
        }

        public void setReport(String value) {
            this.report = value;
            typeCall = false;
        }

        public boolean getCalled(){return called;}

        @Override
        protected String doInBackground(Void... args) {
            // TODO: attempt authentication against a network service.

            try {
                Log.d("INFO", "MainActivity");
                if(typeCall)
                    url = new URL("http://83.165.253.135/android/cons_info.php");
                else
                    url = new URL("http://83.165.253.135/android/report_info.php");
                Log.d("INFO", url.toString());
                connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(CONECTION_TIMEOUT);
                connection.setConnectTimeout(CONECTION_TIMEOUT);
                connection.setRequestMethod("POST");

                connection.setDoInput(true);
                connection.setDoOutput(true);

                Uri.Builder builder;
                if(typeCall)
                    builder = new Uri.Builder().appendQueryParameter("cons", construction);
                else
                    builder = new Uri.Builder().appendQueryParameter("cons", report);
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
                Log.d("INFO", "connected");
                connection.connect();
                //ss Simulate network access.
                //Thread.sleep(2000);

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
                    return "unsuccessful";
                }
            } catch (IOException io) {
                return "exception";
            } finally {
                Log.d("INFO", "Disconnected");
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
            Log.d("INFO-PostExecute", "Result contains: " + result);

            if(typeCall) {
                if (!result.equals("exception")) {
                    mAcceTask = null;
                    //showProgress(false);
                    Log.d("INFO-PostExecute", "Split data");
                    String data[] = result.split("&");
                    Log.d("INFO-PostExecute", "Data lenght: " + data.length);
                    for (int i = 0; i < data.length; i++)
                        Log.d("INFO-PostExecute", "Dato " + i + " es: " + data[i]);
            /*
            for (int i = 0; i < data.length; i++) {
                data[i] = info[i];
            }
*/
                    Log.d("INFO-PostExecute", data[0]);
                    Log.d("INFO-PostExecute", data[1]);
                    Log.d("INFO-PostExecute", data[2]);
                    Log.d("INFO-PostExecute", data[3]);
                    Log.d("INFO-PostExecute", data[4]);
                    Log.d("INFO-PostExecute", data[5]);
                    Intent intent = new Intent(MainActivity.this, InformationActivity.class);
                    intent.putExtra("id", data[0]);
                    intent.putExtra("direccion", data[1]);
                    intent.putExtra("superficie", data[2]);
                    intent.putExtra("descripcion", data[3]);
                    intent.putExtra("constructor", data[4] + " " + data[5]);
                    called = true;
                    startActivity(intent);
                } else {
                    Snackbar.make(mainV, "Error en la conexión", Snackbar.LENGTH_LONG).show();
                }
            }else{
                if (!result.equals("exception")) {
                    mAcceTask = null;
                    //showProgress(false);
                    Log.d("INFO-PostExecute", "Split data");
                    String data[] = result.split("&");
                    Log.d("INFO-PostExecute", "Data lenght: " + data.length);
                    for (int i = 0; i < data.length; i++)
                        Log.d("INFO-PostExecute", "Dato " + i + " es: " + data[i]);
            /*
            for (int i = 0; i < data.length; i++) {
                data[i] = info[i];
            }
*/
                    Log.d("INFO-PostExecute", data[0]);
                    Log.d("INFO-PostExecute", data[1]);
                    Log.d("INFO-PostExecute", data[2]);
                    Log.d("INFO-PostExecute", data[3]);
                    Log.d("INFO-PostExecute", data[4]);
                    Log.d("INFO-PostExecute", data[5]);
                    Intent intent = new Intent(MainActivity.this, ReportInfoActivity.class);
                    intent.putExtra("numDenuncia", data[0]);
                    intent.putExtra("fecha", data[1]);
                    intent.putExtra("foto", data[2]);
                    intent.putExtra("descripcion", data[3]);
                    intent.putExtra("construccion", data[5]);
                    called = true;
                    startActivity(intent);
                } else {
                    Snackbar.make(mainV, "Error en la conexión", Snackbar.LENGTH_LONG).show();
                }
            }
        }

        @Override
        protected void onCancelled() {
            mAcceTask = null;
            //showProgress(false);
        }
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
                Log.d("INFO", "MainActivity");
                url = new URL("http://83.165.253.135/android/report_list.php");
                Log.d("INFO", url.toString());
                connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(CONECTION_TIMEOUT);
                connection.setReadTimeout(CONECTION_TIMEOUT);

                connection.setRequestMethod("POST");

                connection.setDoInput(true);
                connection.setDoOutput(true);
                if(dni != null) {
                    Uri.Builder builder = new Uri.Builder().appendQueryParameter("dni", dni);
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
                }
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
                mRepTask = null;
                //showProgress(false);
                Log.d("INFO-PostExecute", "Split data");
                dataPrim = result.split(":");
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

            } else {
                Log.d("INFO-PostExecute", "ERROR");
                dataR = new String[1];
                dataR[0] = result;
                dataC = new String[1];
                dataC[0] = result;
                Snackbar.make(mainV, "Error en la conexión", Snackbar.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onCancelled() {
            mRepTask = null;
            //showProgress(false);
        }
    }

    public class ReportInfoTask extends AsyncTask<Void, Void, String> {

        private final String idDenuncia;
        //private final Context context;
        URL url = null;
        HttpURLConnection connection;

        ReportInfoTask(String den) {
            this.idDenuncia = den;
        }

        @Override
        protected String doInBackground(Void... args) {
            // TODO: attempt authentication against a network service.

            try {
                Log.d("INFO", "InformacionActivity");
                url = new URL("http://83.165.253.135/android/report_info.php");
                Log.d("INFO", url.toString());
                connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(10000);
                connection.setConnectTimeout(15000);
                connection.setRequestMethod("POST");

                connection.setDoInput(true);
                connection.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder().appendQueryParameter("cons", idDenuncia);
                String query = builder.build().getEncodedQuery();
                Log.d("INFO", query.toString());
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
                //mUpTask = null;
                //showProgress(false);
                Log.d("INFO-PostExecute", "Split data");
                String data[] = result.split("&");
            /*
            for (int i = 0; i < data.length; i++) {
                data[i] = info[i];
            }
            */
                Log.d("INFO-PostExecute", data[0]);
                Log.d("INFO-PostExecute", data[1]);
                Log.d("INFO-PostExecute", data[2]);
                Log.d("INFO-PostExecute", data[3]);
                Log.d("INFO-PostExecute", data[5]);
                Intent intent = new Intent(MainActivity.this, ReportInfoActivity.class);
                intent.putExtra("denuncia", data[0]);
                intent.putExtra("fecha", data[1]);
                intent.putExtra("foto", data[2]);
                intent.putExtra("descripcion", data[3]);
                intent.putExtra("constructor", data[5]);
                startActivity(intent);
            } else {
                //Snackbar.make(iView, "Error en la conexión", Snackbar.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onCancelled() {
            //mUpTask = null;
            //showProgress(false);
        }
    }
}
