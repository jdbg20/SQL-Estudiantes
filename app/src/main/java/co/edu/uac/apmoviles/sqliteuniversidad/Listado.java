package co.edu.uac.apmoviles.sqliteuniversidad;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Listado extends AppCompatActivity {

    ListView lista;
    public ArrayList<String> v1;
    public ArrayList<Estudiante> v2;
    EstudianteController ec;
    int posicion;
    Estudiante est;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        lista = findViewById(R.id.lstestudiantes);
        v1 = (ArrayList<String>) getIntent().getSerializableExtra("mylist");
        v2 = (ArrayList<Estudiante>) getIntent().getSerializableExtra("mylist2");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, v1);
        lista.setAdapter(adapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        registerForContextMenu(lista);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        switch (v.getId()){

            case R.id.lstestudiantes:

                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                String estudiante = ((TextView) info.targetView).getText().toString();
                menu.setHeaderTitle(estudiante);

                String [] actions = getResources().getStringArray(R.array.context_menu);
                for (int i=0;i<actions.length;i++){
                    menu.add(Menu.NONE, i, i, actions[i]);
                }
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //Key code
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        String [] menuItems = getResources().getStringArray(R.array.context_menu);
        String menuItemName = menuItems[menuItemIndex];
        switch (menuItemName) {
            case "Editar":
                Estudiante estudent= v2.get(menuInfo.position);
                Intent i = new Intent(this, Editar.class);
                i.putExtra("estudiante", estudent);
                startActivityForResult(i, 1);
                posicion=menuInfo.position;
                break;

            case "Borrar":
                String cod = v2.get(menuInfo.position).getCodigo();
                ec = new EstudianteController(this);
                boolean isDelete=ec.deleteEstudiante(cod);
                if (isDelete){
                    v1.remove(menuInfo.position);
                    v2.remove(menuInfo.position);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, v1);
                lista.setAdapter(adapter);
                Toast.makeText(getApplicationContext(), "Estudiante eliminado",Toast.LENGTH_LONG).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) {
                est = (Estudiante) data.getSerializableExtra("value");
                v2.set(posicion, est);
                v1.set(posicion, "Código: "+est.getCodigo()+ ", Nombre: "+est.getNombre()+", Programa: "+est.getPrograma());
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, v1);
                lista.setAdapter(adapter);
                Toast.makeText(getApplicationContext(), "Estudiante editado",Toast.LENGTH_LONG).show();
            }
        }
    }
}