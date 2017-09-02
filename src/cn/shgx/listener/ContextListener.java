package cn.shgx.listener;

import cn.shgx.dao.CategoryDao;
import cn.shgx.entity.Category;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * Created by Consule on 2017/9/2.
 */
@WebListener
public class ContextListener implements ServletContextListener {
    private CategoryDao categoryDao = new CategoryDao();
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        List<Category> clist = categoryDao.getList();
        sce.getServletContext().setAttribute("clist", clist);
    }

}

