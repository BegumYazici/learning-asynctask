package asynctask.begi.com.asynctasksample;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private Button btnStart;
    private TextView tvResult;
    private String url="https://assets.pcmag.com/media/images/535129-things-you-can-do-to-make-your-android-device-less-annoying-right-now.png?thumb=y&width=810&height=456";
    private Button btnDownload;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart= (Button)findViewById(R.id.btn_Start);
        btnDownload= (Button)findViewById(R.id.btn_Download);
        tvResult=(TextView)findViewById(R.id.tvResult);
        img = (ImageView)findViewById(R.id.img);


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackgroundTask backgroundTask = new BackgroundTask(MainActivity.this,tvResult);
                backgroundTask.execute();
            }
        });

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DownloadImage downloadImage = new DownloadImage();
                downloadImage.execute(url);

            }
        });

    }

    class DownloadImage extends AsyncTask<String,Void,Bitmap>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Resim İndiriliyor...");
            progressDialog.setMessage("Lütfen Bekleyiniz!");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... URL) {

            String imageURL =URL[0];
            Bitmap bitmap = null;
            try {
                // Resim indiriyoruz
                InputStream input = new java.net.URL(imageURL).openStream();
                // Decode Bitmap
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            img.setImageBitmap(result);
            progressDialog.dismiss();
        }
    }
}
