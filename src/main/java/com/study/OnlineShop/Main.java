package com.study.OnlineShop;

import com.study.OnlineShop.dao.jdbc.JdbcProductDao;
import com.study.OnlineShop.service.impl.DefaultProductService;
import com.study.OnlineShop.web.servlet.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        //configuration
        //dao
        JdbcProductDao jdbcProductDao = new JdbcProductDao();
        List<String> tokens = new ArrayList<>();

        //services
        DefaultProductService defaultProductService = new DefaultProductService();
        defaultProductService.setProductDao(jdbcProductDao);

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
