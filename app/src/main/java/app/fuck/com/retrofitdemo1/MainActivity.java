package app.fuck.com.retrofitdemo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Bind(R.id.phone_view)
     EditText phoneView;
    @Bind(R.id.tv_result)
     TextView resultView;

    private PhoneApi phoneApi;
    private  PhoneService phoneService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        phoneApi = PhoneApi.getApi();
        phoneService = phoneApi.getService();
    }

    @OnClick({R.id.btn_query})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_query:
                query();
                break;
            default:
                break;
        }
    }



    private void query(){

        resultView.setText("");
        String number = phoneView.getText().toString();
        if(number.isEmpty()){
            Toast.makeText(this,"请输出电话号码!",Toast.LENGTH_SHORT).show();
            return;
        }


        Call<PhoneResult> call = phoneService.getResult(PhoneApi.API_KEY,number);

        //3.发送请求
        call.enqueue(new Callback<PhoneResult>() {
            @Override
            public void onResponse(Call<PhoneResult> call, Response<PhoneResult> response) {
                //4.处理结果
                if (response.isSuccessful()){
                    PhoneResult result = response.body();
                    if(result!=null&&result.getErrNum()==0){
                        PhoneResult.RetDataEntity entity = result.getRetData();
                        resultView.setText("地址: "+entity.getCity());
                    }
                }
            }

            @Override
            public void onFailure(Call<PhoneResult> call, Throwable t) {

            }
        });
    }

}
