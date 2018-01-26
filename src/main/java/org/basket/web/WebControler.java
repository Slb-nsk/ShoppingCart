package org.basket.web;

import org.basket.dao.ProductsDao;
import org.basket.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WebControler {
    private final ProductsDao productsDao;
    private ArrayList<OutputProduct> productsList = new ArrayList<>();
    private Basket basket = new Basket();

    @Autowired
    public WebControler(ProductsDao productsDao) {
        this.productsDao = productsDao;
    }

    @GetMapping("/")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("list");
        List<Product> searchResult = productsDao.productsList();
        for (Product p : searchResult) {
            OutputProduct np = new OutputProduct(p.getProductId(), p.getProductName(), p.getProductAmount());
            np.setProductPrices(p.getProductPrice());
            productsList.add(np);
        }
        mav.addObject("productsList", productsList);
        return mav;
    }

    @GetMapping("/forward")
    public ModelAndView basket() {
        ModelAndView mav = new ModelAndView();
        if (basket.getCurrentOrder().isEmpty()) {
            mav.setViewName("emptybasket");
        } else {
            mav.setViewName("basket");
            mav.addObject("productsList", basket.getCurrentOrder());
            mav.addObject("total", basket.getTotal());
        }
        return mav;
    }

    @GetMapping("/return")
    public ModelAndView backToList() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("list");
        mav.addObject("productsList", productsList);
        return mav;
    }

    @GetMapping("/{productId}/{action}/{where}")
    public ModelAndView operation(@PathVariable int productId, @PathVariable String action, @PathVariable String where) {
        ModelAndView mav = new ModelAndView();

        ArrayList<OutputProduct> outputList;

        switch (action) {
            case "add":
                for (OutputProduct p : productsList) {
                    if (p.getProductId() == productId) {
                        if (p.getProductAmount() > 0) {
                            p.setProductAmount(p.getProductAmount() - 1);
                            boolean isNotInBasket = true;
                            for (OutputProduct pp : basket.getCurrentOrder()) {
                                if (pp.getProductId() == productId) {
                                    pp.setProductAmount(pp.getProductAmount() + 1);
                                    isNotInBasket = false;
                                }
                            }
                            if (isNotInBasket) {
                                OutputProduct np = new OutputProduct(productId, p.getProductName(), 1);
                                np.setProductPrices(p.getProductPrice());
                                outputList = basket.getCurrentOrder();
                                outputList.add(np);
                                basket.setCurrentOrder(outputList);
                            }
                            basket.getTotal().setProductAmount(basket.getTotal().getProductAmount()+1);
                            basket.getTotal().setProductPrices(basket.getTotal().getProductPrice()+p.getProductPrice());
                        }
                    }
                }
                break;

            case "remove":
                for (OutputProduct p : productsList) {
                    if (p.getProductId() == productId) {
                        p.setProductAmount(p.getProductAmount() + 1);
                    }
                }
                outputList = basket.getCurrentOrder();
                for (OutputProduct p : outputList) {
                    if (p.getProductId() == productId) {
                        p.setProductAmount(p.getProductAmount() - 1);
                        basket.getTotal().setProductAmount(basket.getTotal().getProductAmount()-1);
                        basket.getTotal().setProductPrices(basket.getTotal().getProductPrice()-p.getProductPrice());
                        if (p.getProductAmount() == 0) {
                            outputList.remove(outputList.lastIndexOf(p));
                        }
                    }
                }
                basket.setCurrentOrder(outputList);
                break;
        }

        mav.setViewName(where);
        switch (where) {
            case "list":
                mav.addObject("productsList", productsList);
                break;
            case "basket":
                mav.addObject("productsList", basket.getCurrentOrder());
                mav.addObject("total", basket.getTotal());
                break;
        }

        return mav;
    }

    @PostMapping("/complete")
    public ModelAndView bye(String firstName,  String lastName, String phoneNumber) {
        productsDao.createOrder(firstName, lastName, phoneNumber, basket);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("bye");
        mav.addObject("productsList", basket.getCurrentOrder());
        mav.addObject("total", basket.getTotal());
        mav.addObject("firstName", firstName);
        mav.addObject("lastName", lastName);
        mav.addObject("phoneNumber", phoneNumber);
        return mav;
    }
}
