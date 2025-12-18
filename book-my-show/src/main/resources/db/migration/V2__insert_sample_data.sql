INSERT INTO users VALUES
('u1', 'Alice Johnson', 'alice@example.com', 'hash1', '9999999999', CURRENT_TIMESTAMP()),
('u2', 'Bob Smith', 'bob@example.com', 'hash2', '8888888888', CURRENT_TIMESTAMP());


INSERT INTO movies VALUES
('m1', 'Inception', 'Sci-fi thriller', 148, 'Sci-Fi', DATE '2010-07-16', 'PG-13', 'English'),
('m2', 'Interstellar', 'Space exploration epic', 169, 'Sci-Fi', DATE '2014-11-07', 'PG-13', 'English');


INSERT INTO theaters VALUES
('t1', 'PVR Cinemas', 'MG Road', 'Bangalore', 'Karnataka', '560001', '0801234567');


INSERT INTO screens VALUES
('s1', 't1', 'Screen 1', 100),
('s2', 't1', 'Screen 2', 80);


INSERT INTO seats VALUES
('seat1', 's1', 'A1', 'REGULAR', FALSE),
('seat2', 's1', 'A2', 'REGULAR', FALSE),
('seat3', 's1', 'B1', 'VIP', TRUE);


INSERT INTO showtimes VALUES
('sh1', 'm1', 's1', DATEADD('DAY', 1, CURRENT_TIMESTAMP()), DATEADD('HOUR', 4, CURRENT_TIMESTAMP()), 'English', '2D'),
('sh2', 'm2', 's2', DATEADD('DAY', 2, CURRENT_TIMESTAMP()), DATEADD('HOUR', 5, CURRENT_TIMESTAMP()), 'English', 'IMAX');


INSERT INTO promotions VALUES
('p1', 'NEWUSER10', '10% off for new users', 10, CURRENT_DATE, DATEADD('DAY', 30, CURRENT_DATE), 100);


INSERT INTO bookings VALUES
('b1', 'u1', 'sh1', 'p1', CURRENT_TIMESTAMP(), 450.00, 'CONFIRMED');


INSERT INTO payments VALUES
('pay1', 'b1', CURRENT_TIMESTAMP(), 450.00, 'CARD', 'SUCCESS', 'TXN123456');


INSERT INTO reviews VALUES
('r1', 'u1', 'm1', 5, 'Amazing movie!', CURRENT_TIMESTAMP());


INSERT INTO booking_seats VALUES
('bs1', 'b1', 'seat1', 150.00),
('bs2', 'b1', 'seat2', 150.00),
('bs3', 'b1', 'seat3', 150.00);