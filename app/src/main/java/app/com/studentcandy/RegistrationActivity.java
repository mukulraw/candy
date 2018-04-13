package app.com.studentcandy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import app.com.studentcandy.registerPOJO.registerBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RegistrationActivity extends AppCompatActivity {

    TextView tv_already_login;

    EditText name, username, phone, email, password, retpassword;

    RadioGroup group;

    Button register;

    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        tv_already_login = (TextView) findViewById(R.id.already_login_tv);


        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        retpassword = findViewById(R.id.repassword);

        group = findViewById(R.id.group);

        progress = findViewById(R.id.progress);

        register = findViewById(R.id.register);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nam = name.getText().toString();
                String user = username.getText().toString();
                String pho = phone.getText().toString();
                String ema = email.getText().toString();
                String pass = password.getText().toString();
                String retp = retpassword.getText().toString();


                if (nam.length() > 0) {

                    if (user.length() > 0) {

                        if (pho.length() > 0) {

                            if (ema.length() > 0) {

                                if (pass.length() > 0) {

                                    if (retp.equals(pass)) {

                                        int id = group.getCheckedRadioButtonId();


                                        if (id != -1) {

                                            RadioButton btn = findViewById(id);

                                            progress.setVisibility(View.VISIBLE);


                                            final bean b = (bean) getApplicationContext();


                                            final Retrofit retrofit = new Retrofit.Builder()
                                                    .baseUrl(b.BASE_URL)
                                                    .addConverterFactory(ScalarsConverterFactory.create())
                                                    .addConverterFactory(GsonConverterFactory.create())
                                                    .build();

                                            final AllAPIs cr = retrofit.create(AllAPIs.class);

                                            Call<registerBean> call = cr.register(nam, user, pho, ema, pass, btn.getText().toString());


                                            call.enqueue(new Callback<registerBean>() {
                                                @Override
                                                public void onResponse(Call<registerBean> call, Response<registerBean> response) {

                                                    if (response.body().getStatus().equals("1")) {
                                                        Toast.makeText(RegistrationActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                                        finish();
                                                    } else {
                                                        Toast.makeText(RegistrationActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                                    }


                                                    progress.setVisibility(View.GONE);

                                                }

                                                @Override
                                                public void onFailure(Call<registerBean> call, Throwable t) {

                                                    progress.setVisibility(View.GONE);

                                                }
                                            });
                                        } else {
                                            Toast.makeText(RegistrationActivity.this, "Please Select a Gender", Toast.LENGTH_SHORT).show();
                                        }


                                    } else {
                                        retpassword.setError("Password did not match");
                                    }

                                } else {
                                    password.setError("Invalid Password");
                                }

                            } else {
                                email.setError("Invalid Email");
                            }


                        } else {
                            phone.setError("Invalid Phone Number");
                        }

                    } else {
                        username.setError("Invalid Username");
                    }

                } else {
                    name.setError("Invalid Name");
                }


            }
        });


        tv_already_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent al_intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(al_intent);
            }
        });
    }
}
