package model;

import java.util.Objects;

public class CategoryFitment {
    private int categoryID;
    private String ISBN;

    public CategoryFitment(int categoryID, String ISBN) {
        this.categoryID = categoryID;
        this.ISBN = ISBN;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public String getISBN() {
        return ISBN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryFitment that = (CategoryFitment) o;
        return categoryID == that.categoryID && Objects.equals(ISBN, that.ISBN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryID, ISBN);
    }
}

