package instinctcoder.webapiclient;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.app.DatePickerDialog;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;



public class activityMovfinanceiro extends AppCompatActivity {

//public class activityMovfinanceiro extends ActionBarActivity implements android.view.View.OnClickListener{


    Button btn;
    int year_x, month_x, day_x;
    static final int DIALOG_ID = 0;

    String edtData;
    TextView textvSaldo;

    private Calendar data;

    RestService restService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_movfinanceiro);

        restService = new RestService();
        Calendar data;
        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);
        showDialogOnButtoClick();

    }


    public void showDialogOnButtoClick() {
        btn = (Button) findViewById(R.id.btnData);

        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DIALOG_ID);


                    }
                }

        );
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID)
            return new DatePickerDialog(this, dpickerListner, year_x, month_x, day_x);
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListner
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x = year;
            month_x = monthOfYear + 1;
            day_x = dayOfMonth;
            //Toast.makeText(activityMovfinanceiro.this,day_x +"/" + month_x + "/" + year_x,Toast.LENGTH_LONG).show();

            String dateTeste1 = year_x + "-" + month_x + "-" + day_x;

            Toast.makeText(activityMovfinanceiro.this, dateTeste1, Toast.LENGTH_LONG).show();


            //   Calendar calendar;
            // calendar= (Calendar) dateTeste;

            //  String  dateTeste //= "2016-12-23";
            //   Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            // String date = new SimpleDateFormat( dateTeste + " HH:mm:ss").format(timestamp.getTime());
            // System.out.println(date);


            //  _Student_Id =0;
           //  Intent intent = getIntent();


            // if (dateTeste)
           dateTeste = intent.getStringExtra(dateTeste);

            String dateTeste = year_x + "-" + month_x + "-" + day_x;

            restService.getService().getRecebimentoById(dateTeste , new Callback<Recebimento>() {

                @Override
                public void success(Recebimento recebimento, Response response) {

                    // editTextAge.setText(String.valueOf(student.age));
                    textvSaldo.setText(String.valueOf(recebimento.creceber_pag_valor));
                    //   textvSaldo.setText(student.email);


                }

                @Override
                public void failure(RetrofitError error) {


                    Toast.makeText(activityMovfinanceiro.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();

                }
            });
        }

        //esta me ouvindo?? não ainda?
    };
}






