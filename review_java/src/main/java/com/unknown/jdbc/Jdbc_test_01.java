package com.unknown.jdbc;

public class Jdbc_test_01 {

    public static void main(String[] args) throws Exception {

//        方式一
   /*     Driver driver = new Driver();
        String url = "jdbc:mysql://localhost:3306/changel";
        Properties info = new Properties();
        InputStream inputStream = Jdbc_test_01.class.getClassLoader().getResourceAsStream("jdbc/jdbc_02.properties");
        info.load(inputStream);
        Connection connect = driver.connect(url, info);
        Statement statement = connect.createStatement();
        String sql = "select * from user";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            System.out.println(resultSet.getString("username"));
        }*/
        /*//      方式二
         *//*   Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) aClass.newInstance();
        String url = "jdbc:mysql://localhost:3306/changel";
        Properties info = new Properties();
        InputStream inputStream = Study01.class.getClassLoader().getResourceAsStream("jdbc.properties");
        info.load(inputStream);
        Connection connect = driver.connect(url, info);
        Statement statement = connect.createStatement();
        String sql = "select * from user";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            System.out.println(resultSet.getString("username"));
        }*/

//        方式三
       /* Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) aClass.newInstance();
        String url = "jdbc:mysql://localhost:3306/changel";
        DriverManager.registerDriver(driver);
        Connection connect = DriverManager.getConnection(url, "root", "root");
        Statement statement = connect.createStatement();
        String sql = "select * from user";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            System.out.println(resultSet.getString("username"));
        }*/

//       方式四
      /*  Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/changel";
        Connection connect = DriverManager.getConnection(url, "root", "root");
        Statement statement = connect.createStatement();
        String sql = "select * from user";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            System.out.println(resultSet.getString("username"));
        }*/

        //方式五
        /*Properties  info = new Properties();
        InputStream inputStream = Jdbc_test01.class.getClassLoader().getResourceAsStream("jdbc.properties");
        info.load(inputStream);
        String userName = info.getProperty("jdbc.user");
        String password = info.getProperty("jdbc.password");
        String driver = info.getProperty("jdbc.driver");
        String url = info.getProperty("jdbc.url");
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, userName, password);*/
    }
}
