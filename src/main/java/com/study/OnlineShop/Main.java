package com.study.OnlineShop;

import com.study.OnlineShop.dao.jdbc.JdbcProductDao;
import com.study.OnlineShop.service.impl.DefaultProductService;
import com.study.OnlineShop.web.servlet.AddProductServlet;
import com.study.OnlineShop.web.servlet.EditProductServlet;
import com.study.OnlineShop.web.servlet.GetAllProductsServlet;
import com.study.OnlineShop.web.servlet.RemoveProductServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) throws Exception {
        //configuration
        //dao
        JdbcProductDao jdbcProductDao = new JdbcProductDao();

        //services
        DefaultProductService defaultProductService = new DefaultProductService();
        defaultProductService.setProductDao(jdbcProductDao);

        //servlets
        GetAllProductsServlet allProductsServlet = new GetAllProductsServlet();
        allProductsServlet.setProductService(defaultProductService);

        AddProductServlet addProductServlet = new AddProductServlet();
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

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();

    }
}
