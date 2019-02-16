package com.study.OnlineShop;

import com.study.OnlineShop.dao.jdbc.JdbcProductDao;
import com.study.OnlineShop.dao.jdbc.JdbcUserDao;
import com.study.OnlineShop.dao.jdbc.connection.JDBCConnection;
import com.study.OnlineShop.service.impl.DefaultProductService;
import com.study.OnlineShop.service.impl.DefaultUserService;
import com.study.OnlineShop.web.servlet.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {
        //configuration
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("src\\main\\resources\\db.properties");){
            properties.load(fileInputStream);
        }
        JDBCConnection jdbcConnection = new JDBCConnection();
        jdbcConnection.setProperties(properties);

        //dao
        JdbcProductDao jdbcProductDao = new JdbcProductDao(jdbcConnection);
        JdbcUserDao jdbcUserDao = new JdbcUserDao(jdbcConnection);
        List<String> tokens = new ArrayList<>();

        //services
        DefaultProductService defaultProductService = new DefaultProductService();
        defaultProductService.setProductDao(jdbcProductDao);
        DefaultUserService defaultUserService = new DefaultUserService();
        defaultUserService.setUserDao(jdbcUserDao);

        //servlets
        GetAllProductsServlet allProductsServlet = new GetAllProductsServlet();
        allProductsServlet.setProductService(defaultProductService);

        AddProductServlet addProductServlet = new AddProductServlet(tokens);
        addProductServlet.setProductService(defaultProductService);

        RemoveProductServlet removeProductServlet = new RemoveProductServlet();
        removeProductServlet.setProductService(defaultProductService);

        EditProductServlet editProductServlet = new EditProductServlet();
        editProductServlet.setProductService(defaultProductService);


        //servlet mapping
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(allProductsServlet), "/products");
        context.addServlet(new ServletHolder(removeProductServlet), "/products/remove");
        context.addServlet(new ServletHolder(addProductServlet), "/products/add");
        context.addServlet(new ServletHolder(editProductServlet), "/products/edit/*");
        context.addServlet(new ServletHolder(new LoginServlet(tokens)), "/login");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();

    }
}
