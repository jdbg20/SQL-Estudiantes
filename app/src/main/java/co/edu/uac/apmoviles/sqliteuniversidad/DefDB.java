package co.edu.uac.apmoviles.sqliteuniversidad;

public class DefDB {

    public static final String nameDB = "Universidad";

    public static final String tabla_est = "Estudiante";
    public static final String col_codigo = "codigo";
    public static final String col_nombre = "nombre";
    public static final String col_programa = "programa";
    public static final String create_tabla_est = "CREATE TABLE IF NOT EXISTS " + DefDB.tabla_est + " ( " +
            DefDB.col_codigo + " text primary key," +
            DefDB.col_nombre + " text," +
            DefDB.col_programa + " text" +
            ");";
}

