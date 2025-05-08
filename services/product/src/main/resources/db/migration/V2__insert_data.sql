INSERT INTO category (name, description) VALUES
('Electronics', 'Electronic gadgets and accessories'),
('Clothing', 'Apparel for men, women, and children'),
('Books', 'A wide selection of reading material'),
('Home Goods', 'Items for your home and living spaces'),
('Food & Beverage', 'Groceries and drinks');


INSERT INTO product (name, description, available_quantity, price, category_id) VALUES
('Smartphone X', 'Latest generation smartphone', 150, 999.99, (SELECT id FROM category WHERE name = 'Electronics')),
('T-Shirt (Blue)', 'Comfortable cotton t-shirt', 500, 25.50, (SELECT id FROM category WHERE name = 'Clothing')),
('Science Fiction Novel', 'A thrilling sci-fi adventure', 200, 15.00, (SELECT id FROM category WHERE name = 'Books')),
('Coffee Maker', 'Automatic drip coffee maker', 75, 49.95, (SELECT id FROM category WHERE name = 'Home Goods')),
('Organic Coffee Beans', 'Freshly roasted arabica beans', 300, 12.75, (SELECT id FROM category WHERE name = 'Food & Beverage')),
('Wireless Headphones', 'Noise-cancelling over-ear headphones', 120, 199.00, (SELECT id FROM category WHERE name = 'Electronics')),
('Denim Jeans', 'Classic blue denim jeans', 400, 59.99, (SELECT id FROM category WHERE name = 'Clothing')),
('Mystery Thriller', 'A suspenseful mystery novel', 180, 16.25, (SELECT id FROM category WHERE name = 'Books')),
('Table Lamp', 'Modern design table lamp', 90, 35.00, (SELECT id FROM category WHERE name = 'Home Goods')),
('Sparkling Water', 'Naturally flavored sparkling water (pack of 12)', 600, 8.50, (SELECT id FROM category WHERE name = 'Food & Beverage'));