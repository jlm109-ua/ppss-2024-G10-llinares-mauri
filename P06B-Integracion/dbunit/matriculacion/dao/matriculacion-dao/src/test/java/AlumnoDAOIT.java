import org.dbunit.Assertion;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ppss.matriculacion.to.AlumnoTO;
import java.time.LocalDate;
import java.sql.SQLException;

public class AlumnoDAOIT {
    private JdbcDatabaseTester databaseTester;
    private ppss.matriculacion.to.AlumnoTO alumnoTO; // SUT
    private IDatabaseConnection connection;

    @BeforeEach
    public void setup() throws Exception {
        String cadena_conexionDB = "cadena de conexion";
        databaseTester = new JdbcDatabaseTester("clase del driver jdbc para poder acceder a la BD",
                "cadena de conexion", "root", "ppss");
        connection = databaseTester.getConnection();

        alumnoTO = new AlumnoTO();
    }

    /**
     * Esquema a seguir con los tests:
     * public void testN() {
     *     // Crear alumno (Arrange)
     *
     *     // Inicializar la BBDD
     *        - Crear el IDataSet cargando la base de datos (fichero.xml)
     *        - Setear el dataset
     *        - onSetup()
     *
     *     // Invocar a la SUT (Act)
     *
     *     // Recuperar los datos de la BBDD
     *        - Crear el IDataSet con la variable connection
     *        - Crear la ITable currentTable (en el código es actualTable pero bueno, se entiende el typo)
     *
     *     // Crear el dataset con el resultado esperado
     *        - Crear el IDataSet cargando la base de datos (fichero.xml con el resultado esperado)
     *        - Crear la ITable expectedTable
     *
     *     // Assert
     * }
     */

    @Test
    public void testA1() throws Exception {
        // Arrange
        AlumnoTO alumno = new AlumnoTO();
        alumno.setNif("33333333C");
        alumno.setNombre("Elena Aguirre Juarez");
        LocalDate fechaNac = LocalDate.of(1985,2,22);
        alumno.setFechaNacimiento(fechaNac);

        // Inicializar la BBDD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        // Invocar a la SUT (Act)
        SQLException excepcion = Assertions.assertThrows(SQLException.class, ()->new ppss.matriculacion.dao.FactoriaDAO().getAlumnoDAO().addAlumno(alumnoTO));

        // Recuperar los datos de la BBDD
        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("alumnos");

        // Crear el dataset con el resultado esperado
        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla3.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        // Assert
        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void testA2() throws Exception {
        // Arrange
        AlumnoTO alumno = new AlumnoTO();
        alumno.setNif("11111111A");
        alumno.setNombre("Alfonso Ramirez Ruiz");
        LocalDate fechaNac = LocalDate.of(1982,2,22);
        alumno.setFechaNacimiento(fechaNac);
        String resultadoEsperado = "Error al conectar con BD";

        // Inicializar la BBDD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml"); // Error al conectar con la base de datos
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        // Invocar a la SUT (Act)
        ppss.matriculacion.dao.DAOException excepcion = Assertions.assertThrows(ppss.matriculacion.dao.DAOException.class, ()->new ppss.matriculacion.dao.FactoriaDAO().getAlumnoDAO().addAlumno(alumnoTO));

        // Assert
        Assertions.assertEquals(resultadoEsperado, excepcion.getMessage());
    }

    @Test
    public void testA3() throws Exception {
        // Arrange
        AlumnoTO alumno = new AlumnoTO();
        alumno.setNif("44444444D");
        alumno.setNombre(null);
        LocalDate fechaNac = LocalDate.of(1982,2,22);
        alumno.setFechaNacimiento(fechaNac);
        String resultadoEsperado = "Error al conectar con BD";

        // Inicializar la BBDD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml"); // Error al conectar con la base de datos
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        // Invocar a la SUT (Act)
        ppss.matriculacion.dao.DAOException excepcion = Assertions.assertThrows(ppss.matriculacion.dao.DAOException.class, ()->new ppss.matriculacion.dao.FactoriaDAO().getAlumnoDAO().addAlumno(alumnoTO));

        // Assert
        Assertions.assertEquals(resultadoEsperado, excepcion.getMessage());
    }

    @Test
    public void testA4() throws Exception {
        // Arrange
        AlumnoTO alumno = new AlumnoTO();
        String resultadoEsperado = "Alumno nulo";

        // Inicializar la BBDD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml"); // Error al conectar con la base de datos
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        // Invocar a la SUT (Act)
        ppss.matriculacion.dao.DAOException excepcion = Assertions.assertThrows(ppss.matriculacion.dao.DAOException.class, ()->new ppss.matriculacion.dao.FactoriaDAO().getAlumnoDAO().addAlumno(alumnoTO));

        // Assert
        Assertions.assertEquals(resultadoEsperado, excepcion.getMessage());
    }

    @Test
    public void testA5() throws Exception {
        // Arrange
        AlumnoTO alumno = new AlumnoTO();
        alumno.setNif(null);
        alumno.setNombre("Pedro Garcia Lopez");
        LocalDate fechaNac = LocalDate.of(1982,2,22);
        alumno.setFechaNacimiento(fechaNac);
        String resultadoEsperado = "Error al conectar con BD";

        // Inicializar la BBDD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml"); // Error al conectar con la base de datos
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        // Invocar a la SUT (Act)
        ppss.matriculacion.dao.DAOException excepcion = Assertions.assertThrows(ppss.matriculacion.dao.DAOException.class, ()->new ppss.matriculacion.dao.FactoriaDAO().getAlumnoDAO().addAlumno(alumnoTO));

        // Assert
        Assertions.assertEquals(resultadoEsperado, excepcion.getMessage());
    }

    @Test
    public void testB1() throws Exception {
        // Arrange
        String nif = "44444444D";

        // Inicializar la BBDD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        // Invocar a la SUT (Act)
        SQLException excepcion = Assertions.assertThrows(SQLException.class, ()->new ppss.matriculacion.dao.FactoriaDAO().getAlumnoDAO().delAlumno(nif));

        // Recuperar los datos de la BBDD
        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("alumnos");

        // Crear el dataset con el resultado esperado
        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla4.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        // Assert
        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void testB2() throws Exception {
        // Arrange
        String nif = "33333333C";
        String resultadoEsperado = "Mo se ha borrado ningun alumno";

        // Inicializar la BBDD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        // Invocar a la SUT (Act)
        ppss.matriculacion.dao.DAOException excepcion = Assertions.assertThrows(ppss.matriculacion.dao.DAOException.class, ()->new ppss.matriculacion.dao.FactoriaDAO().getAlumnoDAO().delAlumno(nif));

        // Assert
        Assertions.assertEquals(resultadoEsperado, excepcion.getMessage());
    }
}
