package com.study.OnlineShop.web.servlet;

import com.study.OnlineShop.entity.Product;
import com.study.OnlineShop.service.ProductService;
import com.study.OnlineShop.web.template.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllProductsServlet extends HttpServlet {

    private ProductService productService;

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> allProducts = productService.getAll();
//        List <Product> allProducts = Arrays.asList(
//                new Product(1,"Apple",10.0),
//                new Product(2,"Bread",100.99),
//                new Product(3,"Cola",50.0)
//       );
        PageGenerator pageGenerator = PageGenerator.instance();

        Map <String,Object> parameters = new HashMap<>();
        parameters.put("products", allProducts);

        String page = pageGenerator.getPage("index.html", parameters);
        resp.getWriter().write(page);
    }
}
