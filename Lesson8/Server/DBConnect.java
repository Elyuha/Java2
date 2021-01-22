package Lesson8.Server;

import java.sql.*;

public class DBConnect {
    private static final String URL = "jdbc:mysql://localhost:3306/chat?"
            + "useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";
    private static final String USER = "root";
    private static final String PASSWORD = "18091999froLOVE";

    private static Connection connection;
    private static Driver driver;

    public static void init(){
        try {
            driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException throwables) {
            System.err.println("Filed to load driver!");
            throwables.printStackTrace();
        }
    }

    public static String getURL() {
        return URL;
    }

    public static String getUSER() {
        return USER;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static void add(String log, String pass, String nickname){
        try {
            connection = DriverManager.getConnection(DBConnect.getURL(),
                    DBConnect.getUSER(), DBConnect.getPASSWORD());
            if(connection.isClosed()) {
                throw new RuntimeException("Connection failed!");
            }

            String SQL = "insert into users(userLogin, userPassword, userNickname) values('" +
                    log + "', '" + pass + "', '" + nickname + "');";

            Statement st = connection.createStatement();
            st.execute(SQL);


            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void update(String log, String pass, String nickname){
        try {
            connection = DriverManager.getConnection(DBConnect.getURL(),
                    DBConnect.getUSER(), DBConnect.getPASSWORD());
            if(connection.isClosed()) {
                throw new RuntimeException("Connection failed!");
            }

            String SQLsearch = "select * from users;";

            PreparedStatement preparedStatement = connection.prepareStatement(SQLsearch);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                if(log.equals(rs.getString(2)) && pass == rs.getString(3)) {
                    String SQLupdate = "update into users set userNickname = '" + nickname +
                            "' where idUser = " + rs.getInt(1) + ";";
                    Statement st = connection.createStatement();
                    st.executeUpdate(SQLupdate);
                    break;
                }
            }

            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static String searchNickname(String log, String pass) {
        try {
            connection = DriverManager.getConnection(DBConnect.getURL(),
                    DBConnect.getUSER(), DBConnect.getPASSWORD());
            if(connection.isClosed()) {
                throw new RuntimeException("Connection failed!");
            }

            String SQL = "select * from users;";

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                if(log.equals(rs.getString(2)) && pass.equals(rs.getString(3))) {
                    return rs.getString(4);
                }
            }
            return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        } finally{
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
