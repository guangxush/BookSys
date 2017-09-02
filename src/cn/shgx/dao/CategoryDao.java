package cn.shgx.dao;

import cn.shgx.entity.Category;
import cn.shgx.util.DBHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.jsp.jstl.sql.Result;

/**
 * Created by Consule on 2017/9/2.
 */
public class CategoryDao extends DBHelper{
    public List<Category> getList(){
        String sql="select * from category";
        List<Category> list=new ArrayList<Category>();
        ResultSet rs= this.executeQuery(sql);
        try {
            while(rs.next()){
                list.add(new Category(rs.getInt(1),rs.getString(2)));
                System.out.println(list);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return list.size()>0?list:null;
    }

}
