package cn.shgx.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.shgx.dao.BookDao;
import cn.shgx.dao.CategoryDao;
import cn.shgx.entity.Book;
import cn.shgx.entity.Category;
import cn.shgx.util.PageUtil;
@WebServlet({"/book","/bk"})//通过注解进行配置
public class BookServlet extends HttpServlet{
    private BookDao bookDao= new BookDao();
    private CategoryDao categoryDao = new CategoryDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("type/html;charset=utf-8");
        //获取操作的参数，该参数指明即将进行的动作
        String action = req.getParameter("op");
        /***
         * op=list查询操作
         * op=add添加操作
         * op=delete删除操作
         * op=update更新操作
         * op=getById查询单个
         * op=toAdd到添加页面去
         */
        System.out.println(action);
        if(action==null||"list".equals(action)){
            list(req,resp);
        }else if("toAdd".equals(action)){
            toAdd(req,resp);
        }else if("add".equals(action)){
            add(req,resp);
        }else if("delete".equals(action)){
            delete(req,resp);
        }else if("getById".equals(action)){
            getById(req,resp);
        }else if("update".equals(action)){
            update(req,resp);
        }


    }
    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id  =0;
        if(req.getParameter("id")!=null){
            id=Integer.parseInt(req.getParameter("id"));
        }
        String bookName  = req.getParameter("name");
        double price =Double.parseDouble(req.getParameter("price"));
        String author  = req.getParameter("author");
        Date pubDate =null;
        try {
            pubDate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("pubDate"));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        Book  book =new Book(id,bookName,price,author,pubDate,categoryId);
        System.out.println("update"+book);
        if(bookDao.update(book)>0){
            System.out.print("Succeed");
            resp.sendRedirect("book?op=list");
        }else {
            System.out.print("Failed");
            resp.getWriter().print("修改失败 ！");
        }


    }
    //
    private void getById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取id
        int id  =0;
        if(req.getParameter("id")!=null){
            id=Integer.parseInt(req.getParameter("id"));
        }
        //根据id查询对应的记录
        Book  book = bookDao.getById(id);
        req.setAttribute("book", book);

        req.getRequestDispatcher("update.jsp").forward(req, resp);

    }
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id=0;
        if(req.getParameter("id")!=null){
            id = Integer.parseInt(req.getParameter("id"));
        }
        if(bookDao.delete(id)>0){
            resp.sendRedirect("book?op=list");
        }else {
            resp.getWriter().print("删除失败！");
        }

    }
    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String bookName  = req.getParameter("name");
        double price =Double.parseDouble(req.getParameter("price"));
        String author  = req.getParameter("author");
        Date pubDate =null;
        try {
            pubDate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("pubDate"));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        Book  book =new Book(bookName,price,author,pubDate,categoryId);
        if(bookDao.add(book)>0){
            resp.sendRedirect("book?op=list");
        }else {
            resp.getWriter().print("添加失败！");
        }

    }
    private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //List<Category> clist = categoryDao.getList();
        //req.setAttribute("clist", clist);
        req.getRequestDispatcher("add.jsp").forward(req, resp);
    }
    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageUtil pageUtil=new PageUtil();
        //设置每页显示记录数
        pageUtil.setPageSize(3);
        //设置总共记录数
        pageUtil.setTotalCount(bookDao.Count());
        //获取当前页--默认为第一页
        int currentPage=1;
        if(req.getParameter("currentPage")!=null){
            currentPage=Integer.parseInt(req.getParameter("currentPage"));
        }
        pageUtil.setCurrentPage(currentPage);
        //调用Dao直接查询
        List<Book> list= bookDao.getAll(pageUtil);
        //List<Category> clist = categoryDao.getList();
        //System.out.print(clist);
        req.setAttribute("list", list);
        //req.setAttribute("clist", clist);
        req.setAttribute("page", pageUtil);
        //System.out.print(clist);
        req.getRequestDispatcher("list.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
