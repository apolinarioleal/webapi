package conecty.com.webapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Leal on 14/08/2016.
 */
public interface StudentService {

    @GET("/Students")
    Call<List<Student>>GetStudents();
}
