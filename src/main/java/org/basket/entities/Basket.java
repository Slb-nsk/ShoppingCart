package org.basket.entities;

import java.util.ArrayList;

public class Basket {
    ArrayList<OutputProduct> currentOrder;
    OutputProduct total;

    public ArrayList<OutputProduct> getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(ArrayList<OutputProduct> currentOrder) {
        this.currentOrder = currentOrder;
    }


    public OutputProduct getTotal() {
        return total;
    }

    public void setTotal(OutputProduct total) {
        this.total = total;
    }

    public Basket() {
        this.currentOrder = new ArrayList<OutputProduct>();
        this.total = new OutputProduct();
        this.total.setProductName("Total");
        this.total.setProductAmount(0);
        this.total.setProductPrices(0L);
    }
}
