package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Membership {
    private int userID;
    private int libraryID;

    public Membership(ResultSet result) throws SQLException {
        this.libraryID = result.getInt("userID");
        this.userID = result.getInt("libraryID");
    }

    public int getUserID() {
        return userID;
    }

    public int getLibraryID() {
        return libraryID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setLibraryID(int libraryID) {
        this.libraryID = libraryID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Membership that = (Membership) o;
        return userID == that.userID && libraryID == that.libraryID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, libraryID);
    }

}



