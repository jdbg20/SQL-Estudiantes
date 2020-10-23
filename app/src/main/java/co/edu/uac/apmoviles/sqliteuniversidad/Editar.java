package co.edu.uac.apmoviles.sqliteuniversidad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Editar extends AppCompatActivity implements View.OnClickListener{

    EditText codigo, nombre, programa;
    Button guardar, cancelar;
    Estudiante estudiante;
    EstudianteController ec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        codigo = findViewById(R.id.edtcodigo);
        nombre = findViewById(R.id.edtnombre);
        programa = findViewById(R.id.edtprograma);
        programa = findViewById(R.id.edtprograma);
        programa = findViewById(R.id.edtprograma);
        codigo.setFocusable(false);
        codigo.setEnabled(false);
        codigo.setCursorVisible(false);
        estudiante = (Estudiante) getIntent().getSerializableExtra("estudiante");
        codigo.setText(estudiante.getCodigo());
        nombre.setText(estudiante.getNombre());
        programa.setText(estudiante.getPrograma());
        guardar = findViewById(R.id.btnguardar);
        cancelar = findViewById(R.id.btncancelar);
        guardar.setOnClickListener(this);
        cancelar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnguardar:
                ec = new EstudianteController(this);
                estudiante.setNombre(nombre.getText().toString());
                estudiante.setPrograma(programa.getText().toString());
                boolean isEdit=ec.editEstudiante(estudiante);
                //if (isEdit){
                    Intent intent = new Intent();
                    intent.putExtra("value", estudiante);
                    setResult(RESULT_OK, intent);
                    finish();
                //}
                break;

        }
    }

        public void OnClickCancelar(View v){
            codigo.setText("");
            nombre.setText("");
            programa.setText("");
        }
}