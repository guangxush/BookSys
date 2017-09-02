package cn.shgx.dao;

import cn.shgx.entity.Book;
import cn.shgx.util.DBHelper;
import cn.shgx.util.PageUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Consule on 2017/9/2.
 */
public class BookDao extends DBHelper{
    //查询所有的书籍
    public List<Book> getAll(PageUtil pu){
        List<Book> list = new ArrayList<Book>();
        //查询操作
        String sql ="select * from book limit ?,?";
        try {
            ResultSet rs = this.executeQuery(sql,(pu.getCurrentPage()-1)*pu.getPageSize(),pu.getPageSize());
            while(rs.next()){
               list.add(new Book(rs.getInt(1),rs.getString(2),rs
                       .getDouble(3), rs.getString(4), rs.getDate(5),rs.getInt(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close();
        }
        return list;
    }
    //获取总页数
    public int Count(){
        String sql="select count(1) from book";
        ResultSet rs = this.executeQuery(sql);
        try {
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return 0;
    }
    public Book getById(int id){
        String sql= "select * from book where id = ?";
        try {
            ResultSet rs = this.executeQuery(sql,id);
            if(rs.next()){
                return new Book(rs.getInt(1), rs.getString(2), rs
                        .getDouble(3), rs.getString(4), rs.getDate(5),rs.getInt(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return null;
    }
    //添加书籍
    public int add(Book b){
        String sql = "insert into book(name,price,author,pubDate,categoryId) values (?,?,?,?,?)";
        return this.executeUpdate(sql, b.getName(),b.getPrice(),b.getAuthor(),
                new SimpleDateFormat("yyyy-MM-dd").format(b.getPubDate()),b.getCategoryId());
    }
    //修改书籍
    public int update(Book b){
        String sql="update book set name=?,price=?,author=?,pubDate=?,categoryId=? where id=?";
        return this.executeUpdate(sql, b.getName(),b.getPrice(),b.getAuthor(),
                new SimpleDateFormat("yyyy-MM-dd").format(b.getPubDate()),b.getCategoryId(),b.getId());
    }
    //删除书籍
    public int delete(int id){
        String sql="delete from book where id =?";
        return this.executeUpdate(sql, id);
    }
}
