package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class GenericReadDAO {
    private static  GenericReadDAO genericRead;

    private GenericReadDAO() {}

    public static GenericReadDAO getInstance(){
        if (genericRead == null){
            genericRead = new GenericReadDAO();
        }
        return genericRead;
    }

    public void readAll(Connection connection, String query) {
        try {
            PreparedStatement prepstmt = connection.prepareStatement(query);

            // executam instructiunea
            ResultSet rs = prepstmt.executeQuery(query);
            // extragem numele coloanelor
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int nrColumns = rsMetaData.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= nrColumns; i++) {
                    System.out.println(rsMetaData.getColumnName(i) + ": " + rs.getObject(i));
                }
                System.out.println();
            }
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }


    public void readByID(Connection connection, String query, int id) {
        try {
            PreparedStatement prepstmt = connection.prepareStatement(query);
            prepstmt.setInt(1, id);

            // executam instructiunea
            ResultSet rs = prepstmt.executeQuery(query);
            // extragem numele coloanelor
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int nrColumns = rsMetaData.getColumnCount();

            if (rs.next()) {
                for (int i = 1; i <= nrColumns; i++) {
                    System.out.println(rsMetaData.getColumnName(i) + ": " + rs.getObject(i));
                }
                System.out.println();
            }
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
