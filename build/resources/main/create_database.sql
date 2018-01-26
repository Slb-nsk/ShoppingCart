CREATE TABLE products(
  productId INT NOT NULL PRIMARY KEY,
  productName TEXT NOT NULL,
  productAmount INT,
  productPrice INT
);

INSERT INTO products(productId, productName, productAmount, productPrice) VALUES ('1', 'Product1', '10', '199');
INSERT INTO products(productId, productName, productAmount, productPrice) VALUES ('2', 'Product2', '10', '2000');
INSERT INTO products(productId, productName, productAmount, productPrice) VALUES ('3', 'Product3', '10', '450');
INSERT INTO products(productId, productName, productAmount, productPrice) VALUES ('4', 'Product4', '10', '10000');
INSERT INTO products(productId, productName, productAmount, productPrice) VALUES ('5', 'Product5', '10', '5780');
INSERT INTO products(productId, productName, productAmount, productPrice) VALUES ('6', 'Product6', '10', '200');
INSERT INTO products(productId, productName, productAmount, productPrice) VALUES ('7', 'Product7', '10', '500');
INSERT INTO products(productId, productName, productAmount, productPrice) VALUES ('8', 'Product8', '10', '10000');
INSERT INTO products(productId, productName, productAmount, productPrice) VALUES ('9', 'Product9', '10', '999');
INSERT INTO products(productId, productName, productAmount, productPrice) VALUES ('10', 'Product10', '10', '8600');

CREATE TABLE orders(
  orderId SERIAL PRIMARY KEY,
  firstName TEXT NOT NULL,
  lastName TEXT NOT NULL,
  phoneNumber TEXT
);

CREATE TABLE orderitems(
  orderId INT NOT NULL REFERENCES orders(orderId),
  productId INT NOT NULL REFERENCES products(productId),
  orderItemAmount INT NOT NULL CHECK (orderItemAmount > 0),
  PRIMARY KEY (orderId, productId)
);