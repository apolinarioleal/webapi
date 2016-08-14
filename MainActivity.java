package conecty.com.webapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://conecty.com/webapi/api/meuprojeto/institute/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson g = new GsonBuilder().registerTypeAdapter(Student.class,new StudentDec()).create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();

        StudentService service = retrofit.create(StudentService.class);
        Call<List<Student>> students = service.GetStudents();

        students.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                if(response.isSuccessful()){
                    List<Student> users = response.body();

                    for(Student s: users){
                        Log.i("USER",s.getId()+"--"+s.getName());
                        Log.i("USER","----------------------");
                    }

                }else{

                    Toast.makeText(getApplicationContext(),"Erro:"+ response.code(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Erro:"+ t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }
}
