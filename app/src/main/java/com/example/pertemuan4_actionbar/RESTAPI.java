package com.example.pertemuan4_actionbar;

import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import com.example.pertemuan4_actionbar.databinding.ActivityRestapiBinding;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
public class RESTAPI extends AppCompatActivity implements
        View.OnClickListener{
    //declaration variable
    private ActivityRestapiBinding binding;
    String index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setup view binding
        binding = ActivityRestapiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.fetchButton.setOnClickListener(this);
    }
    //onclik button fetch
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.fetch_button){
            index = binding.inputId.getText().toString();
            try {
                getData();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }
    //get data using api link
    public void getData() throws MalformedURLException {
        Uri uri = Uri.parse("https://run.mocky.io/v3/d494cdc6-b064-4631-b850-06f56f169264")
                .buildUpon().build();
        URL url = new URL(uri.toString());
        new DOTask().execute(url);
    }
    class DOTask extends AsyncTask<URL, Void, String>{
        //connection request
        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls [0];
            String data = null;
            try {
                data = NetworkUtils.makeHTTPRequest(url);
            }catch (IOException e){
                e.printStackTrace();
            }
            return data;
        }
        @Override
        protected void onPostExecute(String s){
            try {
                parseJson(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //get data json
        public void parseJson(String data) throws JSONException{
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(data);
            }catch (JSONException e){
                e.printStackTrace();
            }
            JSONObject innerObj =
                    jsonObject.getJSONObject("data");
            JSONArray cityArray = innerObj.getJSONArray("data");
            for (int i =0; i <cityArray.length(); i++){
                JSONObject obj = cityArray.getJSONObject(i);
                String Sobj = obj.get("id").toString();
                if (Sobj.equals(index)){
                    String id = obj.get("id").toString();
                    binding.resultId.setText(id);
                    String created_at = obj.get("created_at").toString();
                    binding.resultCreated.setText(created_at);
                    String updated_at = obj.get("updated_at").toString();
                    binding.resultUpdated.setText(updated_at);
                    String name = obj.get("name").toString();
                    binding.resultName.setText(name);
                    String descrption = obj.get("descrption").toString();
                    binding.resultDescription.setText(descrption);
                    String qty = obj.get("qty").toString();
                    binding.resultQty.setText(qty);
                    String price = obj.get("price").toString();
                    binding.resultPrice.setText(price);
                    String image = obj.get("image").toString();
                    binding.resultImage.setText(image);
                    String rating = obj.get("rating").toString();
                    binding.resultRating.setText(rating);
                    break;
                }
                else{
                    binding.resultName.setText("Not Found");
                }
            }
        }
    }
}
