package app.com.studentcandy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import app.com.studentcandy.registerPOJO.registerBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;

    ProgressBar progress;

    private Button btn_login;
    private TextView tv_create_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login = (Button) findViewById(R.id.login_btn);
        tv_create_account = (TextView) findViewById(R.id.create_account_tv);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        progress = findViewById(R.id.progress);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();


                if (user.length() > 0) {

                    if (pass.length() > 0) {

                        progress.setVisibility(View.VISIBLE);

                        final bean b = (bean) getApplicationContext();


                        final Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(b.BASE_URL)
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        final AllAPIs cr = retrofit.create(AllAPIs.class);

                        Call<registerBean> call = cr.login(user, pass);

                        call.enqueue(new Callback<registerBean>() {
                            @Override
                            public void onResponse(Call<registerBean> call, Response<registerBean> response) {


                                if (response.body().getStatus().equals("1")) {

                                    b.userId = response.body().getData().getUserId();
                                    b.name = response.body().getData().getName();
                                    b.username = response.body().getData().getUsername();

                                    Intent intent = new Intent(LoginActivity.this, DasboardActivity.class);
                                    startActivity(intent);

                                    Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                progress.setVisibility(View.GONE);

                            }

                            @Override
                            public void onFailure(Call<registerBean> call, Throwable t) {

                                progress.setVisibility(View.GONE);

                            }
                        });

                    } else {
                        password.setError("Invalid Password");
                    }

                } else {
                    username.setError("Invalid Username");
                }


            }
        });
        tv_create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegistrationActivity
                        .class));
            }
        });
    }
}
