import java.sql.*;

public class DatabaseConn {
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:db.sqlite";

    private Connection conn;
    private Statement stat;

    public DatabaseConn() {
        try {
            Class.forName(DatabaseConn.DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Brak sterownika JDBC");
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();
        } catch (SQLException e) {
            System.out.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }

        createTables();
    }

    public boolean createTables() {
        String createAdresy = "CREATE TABLE IF NOT EXISTS adresy(id_adresu INTEGER PRIMARY KEY  AUTOINCREMENT , lat double, lon double, country varchar(20), city varchar(20), road varchar(20), house_number varchar(20))";

        try {
            stat.execute(createAdresy);
        } catch (SQLException e) {
            System.out.println("Blad przy tworzeniu tabeli");
            e.printStackTrace();
        }
        return true;
    }

    public boolean insertAdres (double lat, double lon, String country, String city,  String road, String house_number){
        try {
            PreparedStatement prepStmt = conn.prepareStatement("INSERT  INTO adresy VALUES (NULL, ?, ?, ?, ?, ?, ?);");
            prepStmt.setDouble(1, lat);
            prepStmt.setDouble(2, lon);
            prepStmt.setString(3, country);
            prepStmt.setString(4, city);
            prepStmt.setString(5, road);
            prepStmt.setString(6, house_number);
            prepStmt.execute();
        } catch (SQLException e) {
            System.out.println("Blad przy wstawianiu adresu");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void closeConnection(){
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Problem z zamknieciem polaczenia");
            e.printStackTrace();
        }

    }

}
