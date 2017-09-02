package cn.shgx.entity;

import java.util.Date;

/**
 * Created by Consule on 2017/9/2.
 */
public class Book {
    private int id;
    private String name;
    private double price;
    private String author;
    private Date pubDate;
    private int categoryId;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getAuthor() {
        return author;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Book(int id, String name, double price, String author, Date pubDate, int categoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.author = author;
        this.pubDate = pubDate;
        this.categoryId = categoryId;
    }

    public Book(String name, double price, String author, Date pubDate, int categoryId) {
        this.name = name;
        this.price = price;
        this.author = author;
        this.pubDate = pubDate;
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                ", pubDate=" + pubDate +
                ", categoryId=" + categoryId +
                '}';
    }
}
