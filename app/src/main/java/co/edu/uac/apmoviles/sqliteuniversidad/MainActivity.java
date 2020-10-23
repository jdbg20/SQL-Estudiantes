package co.edu.uac.apmoviles.sqliteuniversidad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText codigo, nombre, programa;
    Button guardar, cancelar, listado;
  //  BaseDatos bd;
    Estudiante e;
    EstudianteController ec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        codigo = findViewById(R.id.edtcodigo);
        nombre = findViewById(R.id.edtnombre);
        programa = findViewById(R.id.edtprograma);
        guardar = findViewById(R.id.btnguardar);
        cancelar = findViewById(R.id.btncancelar);
        listado = findViewById(R.id.btnlistado);
        guardar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
        listado.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnguardar:
                ec = new EstudianteController(this);
                e = new Estudiante(codigo.getText().toString(), nombre.getText().toString(), programa.getText().toString());
                ec.agregarEstudiante(e);
                Toast.makeText(this, "Estudiante Agregado", Toast.LENGTH_LONG).show();
                break;
            case R.id.btnlistado:
                ec = new EstudianteController(this);
                Cursor c = ec.allEstudiantes();
                ArrayList<String> estudiantes= new ArrayList<String>();
                ArrayList<Estudiante> estudiantes2 =new ArrayList<Estudiante>();
                if (c != null) {
                    while (c.moveToNext()) {
                        estudiantes.add("Código: "+c.getString(0)+ ", Nombre: "+c.getString(1)+", Programa: "+c.getString(2));
                        estudiantes2.add(new Estudiante(c.getString(0), c.getString(1), c.getString(2)));
                    }
                    //Toast.makeText(this, estudiantes.get(0), Toast.LENGTH_LONG).show();

                    Intent i = new Intent(this, Listado.class);
                    i.putExtra("mylist", estudiantes);
                    i.putExtra("mylist2", estudiantes2);
                    startActivity(i);
                } else {
                    Toast.makeText(this, "No hay datos", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }
}
