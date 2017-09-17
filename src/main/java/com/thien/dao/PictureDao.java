package com.thien.dao;

import java.sql.*;

public class PictureDao {
    private static PictureDao dao;
    private Connection connection;

    private static final String GET_RANDOM_PICTURE_SQL = "select * from pics order by rand() limit 1;";

    private PreparedStatement getRandomPictureSqlStatement;
    public static PictureDao getInstance(){
        if(dao == null){
            dao = new PictureDao();
        }
        return dao;
    }

    public PictureDao(){
        connection = getConnection();
        try {
            getRandomPictureSqlStatement = connection.prepareStatement(GET_RANDOM_PICTURE_SQL);
        } catch (SQLException e) {
            System.out.println("PreparedStatement failure");
        }
    }

    protected Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/art_or_photo_game?useSSL=false", "username","password");
        } catch (SQLException e) {
            System.out.println("Failed to get connection");
        }

        if(null == connection){
            System.out.println("Connection is null");
        }

        return connection;
    }

    public ResultSet getRandomPicturePath(){
        try{
            ResultSet rs = getRandomPictureSqlStatement.executeQuery();
            return rs;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
