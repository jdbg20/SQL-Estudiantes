package co.edu.uac.apmoviles.sqliteuniversidad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class EstudianteController {
    private BaseDatos bd;
    private Context c;
    public EstudianteController(Context c) {
        this.bd = new BaseDatos(c,1);
        this.c = c;
    }
    public long agregarEstudiante(Estudiante e){
        try{
            SQLiteDatabase sql = bd.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(DefDB.col_codigo, e.getCodigo());
            cv.put(DefDB.col_nombre, e.getNombre());
            cv.put(DefDB.col_programa, e.getPrograma());
            long id = sql.insert(DefDB.tabla_est,null,cv);
            return id;
        }
        catch (Exception ex){
            return 0;
        }
    }

    public Cursor allEstudiantes(){
        try{
            SQLiteDatabase data = bd.getReadableDatabase();
            Cursor cur = data.query(DefDB.tabla_est,null,null,null,null,null,null);
            return cur;
        }
        catch(Exception ex){
            Toast.makeText(c,"Error Consulta",Toast.LENGTH_LONG).show();
           return null;
        }
    }

    public boolean deleteEstudiante(String cod){
        try{
            SQLiteDatabase data = bd.getReadableDatabase();
            return data.delete(DefDB.tabla_est, DefDB.col_codigo+"="+cod,null)>0;
        }
        catch(Exception ex){
            Toast.makeText(c,"Error Consulta",Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public boolean editEstudiante(Estudiante e){
        try{
            SQLiteDatabase data = bd.getReadableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(DefDB.col_nombre,e.getNombre());
            cv.put(DefDB.col_programa,e.getPrograma());
            return data.update(DefDB.tabla_est, cv, DefDB.col_codigo+"="+e.getCodigo(), null)>1;
        }
        catch (Exception ex){
            Toast.makeText(c,"Error Consulta",Toast.LENGTH_LONG).show();
            return false;
        }
    }

}
