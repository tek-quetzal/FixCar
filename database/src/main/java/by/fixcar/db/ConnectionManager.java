package by.fixcar.db;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    private static final String configFile  = "access.properties";
    private static final String URL_KEY = "db.url";
    private static final String LOGIN_KEY  = "db.login";
    private static final String PASSWORD_KEY = "db.password";
    private static final Properties properties = new Properties();


    static {
        try(InputStream inputStream = ConnectionManager.class.getClassLoader().getResourceAsStream(configFile)){
            properties.load(inputStream);
            Class.forName("org.postgresql.Driver");
        }
        catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Ошибка инициализации драйвера или файла " + configFile, e);
        }
    }

    public static Connection getConnection(){
        try{
            return DriverManager.getConnection(
                    properties.getProperty(URL_KEY),
                    properties.getProperty(LOGIN_KEY),
                    properties.getProperty(PASSWORD_KEY));
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка получения доступа к базе данных!", e);
        }
    }

    private ConnectionManager(){}
}

