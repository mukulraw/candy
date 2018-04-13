package app.com.studentcandy;

import app.com.studentcandy.profilePOJO.profileBean;
import app.com.studentcandy.registerPOJO.registerBean;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface AllAPIs {

    @Multipart
    @POST("student_candy/api/register.php")
    Call<registerBean> register(
            @Part("name") String name,
            @Part("username") String username,
            @Part("phone") String phone,
            @Part("email") String email,
            @Part("password") String password,
            @Part("gender") String gender
    );

    @Multipart
    @POST("student_candy/api/login.php")
    Call<registerBean> login(
            @Part("username") String username,
            @Part("password") String password
    );


    @Multipart
    @POST("student_candy/api/get_profile.php")
    Call<profileBean> getProfile(
            @Part("userId") String userId
    );

}
