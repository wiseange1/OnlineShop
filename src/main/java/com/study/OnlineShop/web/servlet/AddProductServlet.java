package com.study.OnlineShop.web.servlet;

import com.study.OnlineShop.entity.Product;
import com.study.OnlineShop.service.ProductService;
import com.study.OnlineShop.web.auth.AuthUtils;
import com.study.OnlineShop.web.template.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddProductServlet extends HttpServlet {
    private ProductService productService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

   //     if (AuthUtils.isTokenValid(req, tokens)) {
            String page = PageGenerator.instance().getPage("add.html", null);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println(page);
 //       } else {
 //           resp.sendRedirect("/login");
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product();
        product.setName(req.getParameter("name"));
        product.setPrice(Double.valueOf(req.getParameter("price")));
        productService.add(product);

        resp.sendRedirect("/products");
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

}