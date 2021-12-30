package com.medhat.weatherapp.cameraScreen;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.google.common.util.concurrent.ListenableFuture;
import com.medhat.weatherapp.R;
import com.medhat.weatherapp.databinding.ActivityCameraBinding;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;

public class CameraActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_PERMISSIONS = 10;
    private static final String[] REQUIRED_PERMISSIONS = { Manifest.permission.CAMERA};
    private static final String TAG = "sss";

    private ListenableFuture<ProcessCameraProvider> mCameraProviderFuture;
    private Camera camera;

    private ActivityCameraBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_camera);

        if(allPermissionsGranted()){
            startCamera();
        } else{
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
        }

        binding.cameraCaptureButton.setOnClickListener( v -> {
            Bitmap image = binding.viewFinder.getBitmap();

            saveBitmapImage(image);

            String filePath= tempFileImage(this,image,"name");
            Intent intent = new Intent(CameraActivity.this, ImageActivity.class);
            intent.putExtra("BitmapImage", filePath);
            startActivity(intent);
        });
    }


    private void startCamera() {
        binding.viewFinder.post(() -> {
            mCameraProviderFuture = ProcessCameraProvider.getInstance(this);
            mCameraProviderFuture.addListener(() -> {
                try {
                    ProcessCameraProvider cameraProvider = mCameraProviderFuture.get();
                    bindPreview(cameraProvider);
                } catch (ExecutionException | InterruptedException e) {
                    // No errors need to be handled for this Future.
                    // This should never be reached.
                }
            }, ContextCompat.getMainExecutor(this));
        });

    }

    private void bindPreview(@NonNull ProcessCameraProvider cameraProvider) {

        cameraProvider.unbindAll();

        Preview preview = new Preview.Builder().build();
        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        camera = cameraProvider.bindToLifecycle((LifecycleOwner)this, cameraSelector, preview);

        camera.getCameraControl().enableTorch(true);
        preview.setSurfaceProvider(binding.viewFinder.getSurfaceProvider());

    }


    public static String tempFileImage(Context context, Bitmap bitmap, String name) {

        File outputDir = context.getCacheDir();
        File imageFile = new File(outputDir, name + ".jpg");

        OutputStream os;
        try {
            os = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
        } catch (Exception e) {
            Log.e(context.getClass().getSimpleName(), "Error writing file", e);
        }

        return imageFile.getAbsolutePath();
    }

    private boolean allPermissionsGranted(){
        for(String permission : REQUIRED_PERMISSIONS){
            if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera();
            } else {
                Toast.makeText(this, "Permissions not granted by the user.", Toast.LENGTH_SHORT).show();
                //this.finish();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(camera != null)
            camera.getCameraControl().enableTorch(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(camera != null)
            camera.getCameraControl().enableTorch(true);
    }

    public void updateProfilePicture(Bitmap bitmap) {
        File sdDir = Environment.getExternalStorageDirectory();
        String packageName = getPackageName();
        File profile = new File(sdDir, "/Android/data/"+packageName+"/cache/ImageFile");
        profile.mkdirs();

        if (bitmap == null)
            return;
        File destination = new File((profile.getPath()));
        if (destination.exists())
            destination.delete();
        try {
            FileOutputStream out = new FileOutputStream(destination);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (IOException e) {
            //Timber.e(e);
            e.printStackTrace();
        }
        //getProfilePicture().setValue(getProfilePictureUri());
    }

    public void saveBitmapImage(Bitmap bitmap) {
        OutputStream output;
        File filepath = getCacheDir();

        File dir = new File(filepath.getAbsolutePath());
        dir.mkdirs();

        File file = new File(dir, "Wallpaper.jpg");
        try {
            output = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, output);
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void writeFileOnInternalStorage(Bitmap bitmap){
        File dir = new File(this.getFilesDir(), "mydir");
        if(!dir.exists()){
            dir.mkdir();
        }
        OutputStream output;

        try {
            File gpxfile = new File(dir, "IDImage.jpg");
            output = new FileOutputStream(gpxfile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, output);
            output.flush();
            output.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

//    public void saveBitmapImage(Bitmap bitmap) {
//        OutputStream output;
//        // Find the SD Card path
//        File filepath = Environment.getExternalStorageDirectory();
//
//        // Create a new folder in SD Card
//        File dir = new File(filepath.getAbsolutePath());
//        dir.mkdirs();
//
//        // Create a name for the saved image
//        File file = new File(dir, "Wallpaper.jpg" );
//
//        try {
//
//            output = new FileOutputStream(file);
//
//            // Compress into png format image from 0% - 100%
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, output);
//            output.flush();
//            output.close();
//
//        }
//
//        catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }


    public void calculateCardDimentions(){
        CardDimentions cardDimentions = new CardDimentions();
//        cardDimentions.setHeightFrame(frameImage.getHeight());
//        cardDimentions.setTopFrame(frameImage.getTop());
//
//        cardDimentions.setLeftFrame(frameImage.getLeft());
//        cardDimentions.setWidthFrame(frameImage.getWidth());

        cardDimentions.setMainHeight(binding.viewFinder.getHeight());
        cardDimentions.setMainWidth(binding.viewFinder.getWidth());
    }
    public static Bitmap cropImageOnFrame(Bitmap bitmap, CardDimentions cardDimentions) {

        int heightReal = bitmap.getHeight();
        int widthReal = bitmap.getWidth();

        int newSourceHeight = (int) ((cardDimentions.getHeightFrame() * heightReal) / cardDimentions.getMainHeight());
        int newTopFrame = (int) ((cardDimentions.getTopFrame() * heightReal) / cardDimentions.getMainHeight());
        int newLeftFrame = (int) ((cardDimentions.getLeftFrame() * widthReal) / cardDimentions.getMainWidth());
        int newWidth = (int) ((cardDimentions.getWidthFrame() * widthReal) / cardDimentions.getMainWidth());
        bitmap = Bitmap.createBitmap(bitmap, newLeftFrame, newTopFrame + 20, newWidth , newSourceHeight - 50);
        bitmap = Bitmap.createScaledBitmap(bitmap, 900, (int) ((int) 900 / 1.70), true);
        return bitmap;
    }
}