package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Category {
    private int categoryID;
    private String categoryName;
    private int ageLimit;

    public Category(int categoryID, String categoryName, int ageLimit) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.ageLimit = ageLimit;
    }

    public Category(ResultSet result) throws SQLException {
        this.categoryID = result.getInt("categoryId");
        this.categoryName = result.getString("categoryName");
        this.ageLimit = result.getInt("ageLimit");

    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public int getCategoryID() {
        return categoryID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category categoryy = (Category) o;
        return categoryID == categoryy.categoryID && ageLimit == categoryy.ageLimit && Objects.equals(categoryName, categoryy.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryID, categoryName, ageLimit);
    }

    @Override
    public String toString() {
        return "Categoryy{" +
                "categoryID=" + categoryID +
                ", categoryName='" + categoryName + '\'' +
                ", ageLimit=" + ageLimit +
                '}';
    }
}
