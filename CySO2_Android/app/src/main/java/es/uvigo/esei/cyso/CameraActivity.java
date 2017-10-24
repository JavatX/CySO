package es.uvigo.esei.cyso;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class CameraActivity extends AppCompatActivity  implements ZXingScannerView.ResultHandler{

    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 1;

    private ZXingScannerView mScannerView;
    View mainV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mainV  = findViewById(R.id.camera);
        QrScanner(mainV);
    }

    @Override
    public void onPause(){
        super.onPause();
        mScannerView.stopCameraPreview();
    }

    @Override
    public void onResume(){
        super.onResume();
        mScannerView.resumeCameraPreview(this);
    }
    public void QrScanner(View view){

        if(Build.VERSION.SDK_INT == Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
                setContentView(mScannerView);
                mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
                mScannerView.startCamera();         // Start camera
            }else{
                if(shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)){
                    Toast.makeText(this, "Los permisos de cámara son necesarios para el total funcionamiento de la aplicación",
                            Toast.LENGTH_SHORT).show();
                }
                requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
            }
        }else{
            mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
            setContentView(mScannerView);
            mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
            mScannerView.startCamera();         // Start camera
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if(requestCode == MY_PERMISSIONS_REQUEST_CAMERA){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                QrScanner(mainV);
            }else{
                Toast.makeText(this, "Permiso no garantizado", Toast.LENGTH_SHORT).show();
            }
        }else{
            super.onRequestPermissionsResult(requestCode, permissions,grantResults);
        }
    }

    @Override
    public void handleResult(Result result) {
        // Do something with the result here

        Intent returnIntent = new Intent();
        returnIntent.putExtra("QR_CODE",result.getText());
        setResult(Activity.RESULT_OK,returnIntent);


        Log.e("handler", result.getText()); // Prints scan results
        Log.e("handler", result.getBarcodeFormat().toString()); // Prints the scan format (qrcode)
        mScannerView.stopCamera();
        finish();
        // show the scanner result into dialog box.
        //AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setTitle("Scan Result");
        //builder.setMessage(result.getText());
        //AlertDialog alert1 = builder.create();
        //alert1.show();

        // If you would like to resume scanning, call this method below:
        // mScannerView.resumeCameraPreview(this);
    }
}
