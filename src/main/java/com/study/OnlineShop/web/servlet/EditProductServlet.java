package com.study.OnlineShop.web.servlet;

import com.study.OnlineShop.entity.Product;
import com.study.OnlineShop.service.ProductService;
import com.study.OnlineShop.web.template.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EditProductServlet  extends HttpServlet {
        private ProductService productService;

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            int id = Integer.parseInt(req.getParameter("id"));
            Product product = productService.getById(id);
            Map<String, Object> pageContent = new HashMap<>();
            pageContent.put("product",product);

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println(PageGenerator.instance().getPage("edit.html", pageContent));

        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          Product product = new Product();
          product.setId(Integer.valueOf(req.getParameter("id")));
          product.setName(req.getParameter("name"));
          product.setPrice(Double.valueOf(req.getParameter("price")));
          productService.updateById(product);

            resp.sendRedirect("/products");
        }

        public void setProductService(ProductService productService) {
            this.productService = productService;
        }
    }

