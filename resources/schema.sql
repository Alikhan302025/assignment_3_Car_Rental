CREATE TABLE customers (
                           customer_id SERIAL PRIMARY KEY,
                           first_name VARCHAR(50) NOT NULL,
                           last_name VARCHAR(50) NOT NULL,
                           email VARCHAR(100) UNIQUE NOT NULL,
                           phone VARCHAR(20) UNIQUE NOT NULL,
                           created_at DATE DEFAULT CURRENT_DATE
);




CREATE TABLE branches(
                         branches_id SERIAL PRIMARY KEY,
                         city VARCHAR(100) NOT NULL,
                         address VARCHAR(100) NOT NULL
);

CREATE TABLE cars(
                     car_id SERIAL PRIMARY KEY,
                     brand VARCHAR(50) NOT NULL,
                     model VARCHAR(50) NOT NULL,
                     year INT NOT NULL,
                     daily_price INT CHECK (daily_price > 0),
                     branch_id INT NOT NULL,
                     FOREIGN KEY (branch_id) REFERENCES branches(branches_id)
);

CREATE TABLE rentals (
                         rental_id SERIAL PRIMARY KEY,
                         customer_id INT NOT NULL,
                         car_id INT NOT NULL,
                         start_date DATE NOT NULL,
                         end_date DATE,
                         status VARCHAR(20) DEFAULT 'active',
                         FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
                         FOREIGN KEY (car_id) REFERENCES cars(car_id)
);

CREATE TABLE damage_reports (
                                report_id SERIAL PRIMARY KEY,
                                rental_id INT NOT NULL,
                                description TEXT NOT NULL,
                                repair_cost INT CHECK (repair_cost >= 0),
                                report_date DATE DEFAULT CURRENT_DATE,
                                FOREIGN KEY (rental_id) REFERENCES rentals(rental_id)
);

ALTER TABLE customers ADD COLUMN date_of_birth DATE NOT NULL;


INSERT INTO branches(city,address) VALUES
                                       ('Astana', 'Abay 45'),
                                       ('Almaty', 'Turan 45'),
                                       ('Shymkent', 'Tauke 8'),
                                       ('Karaganda', 'Bukhar Zhyrau 22'),
                                       ('Aktobe', 'Abilkayyr Khan 10'),
                                       ('Atyrau', 'Satpayev 15'),
                                       ('Pavlodar', 'Lomonosov 5'),
                                       ('Taraz', 'Tole Bi 30');

INSERT INTO customers (first_name, last_name, email, phone, date_of_birth) VALUES
                                                                               ('Alikhan', 'Zhambayev', 'alikhan@mail.kz', '+77011111111', '2007-03-30'),
                                                                               ('Aruzhan', 'Nurgali', 'aruzhan@mail.kz', '+77022222222', '2002-09-20'),
                                                                               ('Dias', 'Suleimenov', 'dias@mail.kz', '+77033333333', '1999-01-10'),
                                                                               ('Madina', 'Iskakova', 'madina@mail.kz', '+77044444444', '2001-03-08'),
                                                                               ('Timur', 'Bekov', 'timur@mail.kz', '+77055555555', '1998-07-25'),
                                                                               ('Dana', 'Serik', 'dana@mail.kz', '+77066666666', '2000-11-30'),
                                                                               ('Nursultan', 'Kairat', 'nurs@mail.kz', '+77077777777', '1997-02-17'),
                                                                               ('Amina', 'Zhanibek', 'amina@mail.kz', '+77088888888', '2004-06-05');



INSERT INTO cars (brand, model, year, daily_price, branch_id) VALUES
                                                                  ('Toyota', 'Camry', 2020, 18000, 1),
                                                                  ('Hyundai', 'Elantra', 2019, 15000, 1),
                                                                  ('Kia', 'Rio', 2018, 12000, 2),
                                                                  ('BMW', 'X5', 2021, 35000, 2),
                                                                  ('Mercedes', 'C200', 2020, 32000, 3),
                                                                  ('Toyota', 'Corolla', 2017, 11000, 4),
                                                                  ('Chevrolet', 'Cobalt', 2019, 10000, 5),
                                                                  ('Lexus', 'RX350', 2022, 40000, 6);



INSERT INTO rentals (customer_id, car_id, start_date, end_date, status) VALUES
                                                                            (1, 1, '2025-01-01', '2025-01-05', 'completed'),
                                                                            (2, 2, '2025-01-03', '2025-01-07', 'completed'),
                                                                            (3, 3, '2025-01-10', '2025-01-15', 'completed'),
                                                                            (4, 4, '2025-01-12', NULL, 'active'),
                                                                            (5, 5, '2025-01-14', '2025-01-18', 'completed'),
                                                                            (6, 6, '2025-01-16', '2025-01-20', 'completed'),
                                                                            (7, 7, '2025-01-18', NULL, 'active'),
                                                                            (8, 8, '2025-01-20', '2025-01-25', 'completed'),
                                                                            (1, 2, '2025-02-01', '2025-02-03', 'completed'),
                                                                            (2, 3, '2025-02-05', '2025-02-10', 'completed'),
                                                                            (3, 4, '2025-02-07', NULL, 'active'),
                                                                            (4, 5, '2025-02-09', '2025-02-12', 'completed'),
                                                                            (5, 6, '2025-02-11', '2025-02-15', 'completed'),
                                                                            (6, 7, '2025-02-14', NULL, 'active'),
                                                                            (7, 8, '2025-02-16', '2025-02-20', 'completed');