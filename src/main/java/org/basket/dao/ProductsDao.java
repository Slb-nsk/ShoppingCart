package org.basket.dao;

import org.basket.entities.Basket;
import org.basket.entities.OutputProduct;
import org.basket.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProductsDao {

    @PersistenceContext
    private EntityManager em;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductsDao(EntityManager em, JdbcTemplate jdbcTemplate) {
        this.em = em;
        this.jdbcTemplate = jdbcTemplate;
    }

    //List of all products
    public List<Product> productsList() {
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE p.productAmount > 0", Product.class);
        return query.getResultList();
    }

    //Creating an order
    @Transactional
    public void createOrder(String firstName, String lastName, String phoneNumber, Basket basket) {
        Query q = em.createNativeQuery("INSERT INTO orders (firstName, lastName, phoneNumber)"
                + "VALUES (?,?,?)")
                .setParameter(1, firstName)
                .setParameter(2, lastName)
                .setParameter(3, phoneNumber);
        q.executeUpdate();
        q = em.createNativeQuery("SELECT orderId FROM orders WHERE firstName=? AND lastName=? AND phoneNumber=?")
                .setParameter(1, firstName)
                .setParameter(2, lastName)
                .setParameter(3, phoneNumber);
        int orderId = (Integer) q.getSingleResult();
        for (OutputProduct p : basket.getCurrentOrder()) {
            String sql = "INSERT INTO orderitems (orderId, productId, orderItemAmount)" +
                    "VALUES (?,?,?)";
            jdbcTemplate.update(sql, orderId, p.getProductId(), p.getProductAmount());
        }
    }

}
