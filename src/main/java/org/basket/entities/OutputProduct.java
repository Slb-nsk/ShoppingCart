package org.basket.entities;

import static java.lang.String.valueOf;

public class OutputProduct {
    private Integer productId;
    private String productName;
    private Integer productAmount;
    private Long productPrice;
    private String outputProductPrice;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Integer productAmount) {
        this.productAmount = productAmount;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public String getOutputProductPrice() {
        return outputProductPrice;
    }

    public void setOutputProductPrice(String productPrice) {
        this.outputProductPrice = productPrice;
    }

    public void setProductPrices(Long productPrice) {
        this.productPrice = productPrice;
        String output = productPrice.toString();
        int length = output.length();
        if (length == 1) {output = "0.0"+output;} else
            if (length == 2) {output = "0." +output;} else
            {char outputStart[] = new char[length-2];
            char outputEnd[] = new char[2];
            for (int i=0; i < length-2; i++){outputStart[i] = output.charAt(i);};
            outputEnd[0] = output.charAt(length-2);
            outputEnd[1] = output.charAt(length-1);
            output = valueOf(outputStart) + '.' + valueOf(outputEnd);
            }
        this.outputProductPrice = output;
    }

    public OutputProduct() {
    }

    public OutputProduct(Integer productId, String productName, Integer productAmount) {
        this.productId = productId;
        this.productName = productName;
        this.productAmount = productAmount;
    }
}
