package asynctask.begi.com.asynctasksample;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by asus1 on 8.10.2017.
 */
public class BackgroundTask extends AsyncTask<Void,Integer,Void> {

    public BackgroundTask(Context context) {
        this.context = context;
    }

    Context context;
    ProgressDialog progressDialog;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMax(100);
        progressDialog.setProgress(0);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(Void... voids) {

        for(int i=0 ; i<101 ; i=i+10){
            try {
                publishProgress(i);
                Thread.sleep(1000);  //10sn de bir onProgressUpdate değer gönder(i).
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        int currentTimes =values[0];
        progressDialog.setProgress(currentTimes);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
    }

    @Override
    protected void onCancelled(Void result) {
        super.onCancelled(result);
        progressDialog.dismiss();
    }
}
