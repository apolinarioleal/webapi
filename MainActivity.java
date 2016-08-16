package conecty.com.webapi;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
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

    private static final String BASE_URL = "http://conecty.com/webapi/api/meuprojeto/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson g = new GsonBuilder().registerTypeAdapter(Student.class, new StudentDec()).create();


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);




                RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layout);




        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();

        StudentService service = retrofit.create(StudentService.class);
        Call<List<Student>> students = service.GetStudents();

        students.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                if (response.isSuccessful()) {

                    List<Student> users = response.body();




                    for (Student s : users) {
                        Log.i("USER", s.getId() + "--" + s.getName());
                        Log.i("USER", "----------------------");


                    }

                } else {

                    Toast.makeText(getApplicationContext(), "Erro:" + response.code(), Toast.LENGTH_LONG).show();
                }
            }


            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro:" + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }



//                    List<Student> students =;  // recupera do banco de dados ou webservice


        private class NossoAdapter extends RecyclerView.Adapter {


       //    private List<Student> students;

            public NossoAdapter(List<Student> students) {
                this.students = students;
            }


       //     public class NossoAdapter extends RecyclerView.Adapter {

                private List<Student> students;
                private Context context;

                public NossoAdapter(List<Student> students, Context context) {
                    this.students = students;
                    this.context = context;


                    // restante do código


                }


                @Override
                public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(context).inflate(R.layout.activity_main, parent, false);

                    NossoViewHolder holder = new NossoViewHolder(view);

                    return holder;
                }


              private  class NossoViewHolder extends RecyclerView.ViewHolder {


//                  }

                    final TextView id;
                    final TextView name;
                    final TextView age;
                    final TextView email;

                    public NossoViewHolder(View view) {
                        super(view);
                        id = (TextView) view.findViewById(R.id.recycler);
                        name = (TextView) view.findViewById(R.id.recycler);
                        age = (TextView) view.findViewById(R.id.recycler);
                        email = (TextView) view.findViewById(R.id.recycler);


                        // restante das buscas
                    }

                }


                @Override
                public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {


                    NossoViewHolder holder = (NossoViewHolder)  viewHolder;

                    Student student = students.get(position);

                    holder.id.setText(student.getId());
                    holder.name.setText(student.getName());
                    holder.age.setText(student.getAge());
                    holder.email.setText(student.getEmail());


                    //demais campos

                }




            @Override
            public int getItemCount() {
                return students.size();
            }
            //    });


        }


    }

