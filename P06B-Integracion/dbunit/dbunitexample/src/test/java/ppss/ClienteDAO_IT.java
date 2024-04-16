package ppss;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.jupiter.api.*;
import java.sql.SQLException;

/* IMPORTANTE:
    Dado que prácticamente todos los métodos de dBUnit lanzan una excepción,
    vamos a usar "throws Esception" en los métodos, para que el código quede más
    legible sin necesidad de usar un try..catch o envolver cada sentencia dbUnit
    con un assertDoesNotThrow()
    Es decir, que vamos a primar la legibilidad de los tests.
    Si la SUT puede lanza una excepción, SIEMPRE usaremos assertDoesNotThrow para
    invocar a la sut cuando no esperemos que se lance dicha excepción (independientemente de que hayamos propagado las excepciones provocadas por dbunit).
*/
public class ClienteDAO_IT {

    private ClienteDAO clienteDAO; //SUT
    private IDatabaseTester databaseTester;
    private IDatabaseConnection connection;

    @BeforeEach
    public void setUp() throws Exception {

        String cadena_conexionDB = "cadena de conexion";
        databaseTester = new JdbcDatabaseTester("clase del driver jdbc para poder acceder a la BD",
                "cadena de conexion", "root", "ppss");
        connection = databaseTester.getConnection();

        clienteDAO = new ClienteDAO();
    }

    @Test
    public void D1_insert_should_add_John_to_cliente_when_John_does_not_exist() throws Exception {
        Cliente cliente = new Cliente(1,"John", "Smith");
        cliente.setDireccion("1 Main Street");
        cliente.setCiudad("Anycity");

        //inicializamos la BD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/cliente-init.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        //invocamos a la sut
        Assertions.assertDoesNotThrow(()->clienteDAO.insert(cliente));

        //recuperamos los datos de la BD después de invocar al SUT
        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("cliente");

        //creamos el dataset con el resultado esperado
        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/cliente-esperado.xml");
        ITable expectedTable = expectedDataSet.getTable("cliente");

        Assertion.assertEquals(expectedTable, actualTable);

    }

    @Test
    public void D2_delete_should_remove_John_from_cliente_when_John_is_in_table() throws Exception {
        Cliente cliente =  new Cliente(1,"John", "Smith");
        cliente.setDireccion("1 Main Street");
        cliente.setCiudad("Anycity");

        //inicializamos la BD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/cliente-init.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        //invocamos a la SUT
        Assertions.assertDoesNotThrow(()->clienteDAO.delete(cliente));

        //recuperamos los datos de la BD después de invocar al SUT
        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("cliente");

        //creamos el dataset con el resultado esperado
        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/cliente-esperado.xml");
        ITable expectedTable = expectedDataSet.getTable("cliente");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void D3_insert_should_not_add_John_to_cliente_when_John_exists() throws Exception {
        Cliente cliente = new Cliente(1,"John", "Smith");
        cliente.setDireccion("1 Main Street");
        cliente.setCiudad("Anycity");
        String resultadoEsperado = "Duplicate entry";

        //inicializamos la BD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/cliente-init.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        //invocamos a la sut
        SQLException excepcion = Assertions.assertThrows(SQLException.class, ()->clienteDAO.insert(cliente));

        //recuperamos los datos de la BD después de invocar al SUT
        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("cliente");

        //creamos el dataset con el resultado esperado
        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/cliente-esperado.xml");
        ITable expectedTable = expectedDataSet.getTable("cliente");

        Assertions.assertEquals(resultadoEsperado,excepcion.getMessage());
        Assertion.assertEquals(expectedTable, actualTable);

    }

    @Test
    public void D4_delete_should_not_remove_4_from_cliente_when_4_is_not_in_table() throws Exception {
        Cliente cliente =  new Cliente(4,"John", "Smith");
        cliente.setDireccion("1 Main Street");
        cliente.setCiudad("Anycity");

        //inicializamos la BD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/cliente-init2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        //invocamos a la SUT
        Assertions.assertDoesNotThrow(()->clienteDAO.delete(cliente));

        //recuperamos los datos de la BD después de invocar al SUT
        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("cliente");

        //creamos el dataset con el resultado esperado
        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/cliente-esperado.xml");
        ITable expectedTable = expectedDataSet.getTable("cliente");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void D5_update_should_update_1_from_cliente() throws Exception {
        Cliente cliente =  new Cliente(1,"John", "Smith");
        cliente.setDireccion("1 Main Street");
        cliente.setCiudad("Anycity");

        Cliente updatedCliente = new Cliente(1, "John", "Smith");
        cliente.setDireccion("Other Street");
        cliente.setCiudad("NewCity");

        //inicializamos la BD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/cliente-init3.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        //invocamos a la SUT
        Assertions.assertDoesNotThrow(()->clienteDAO.update(updatedCliente));

        //recuperamos los datos de la BD después de invocar al SUT
        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("cliente");

        //creamos el dataset con el resultado esperado
        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/cliente-esperado3.xml");
        ITable expectedTable = expectedDataSet.getTable("cliente");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void D6_retrieve_should_give_1() throws Exception {
        Cliente cliente =  new Cliente(1,"John", "Smith");
        cliente.setDireccion("1 Main Street");
        cliente.setCiudad("Anycity");

        //inicializamos la BD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/cliente-init3.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        //invocamos a la SUT
        Assertions.assertDoesNotThrow(()->clienteDAO.retrieve(1));

        //recuperamos los datos de la BD después de invocar al SUT
        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("cliente");

        //creamos el dataset con el resultado esperado
        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/cliente-esperado.xml");
        ITable expectedTable = expectedDataSet.getTable("cliente");

        Assertion.assertEquals(expectedTable, actualTable);
    }
}
