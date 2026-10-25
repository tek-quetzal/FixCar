TRUNCATE orders, cars, mechanics, clients;

INSERT INTO clients (name, phone) VALUES
    ('Иван Петров',    '+7 900 111-22-33'),
    ('Anna Smith',     '+49 170 5551234'),
    ('Мария Сидорова', '+7 911 000-00-00');

INSERT INTO mechanics (name, specialization) VALUES
    ('Сергей Козлов', 'двигатель'),
    ('Олег Новиков',  'ходовая часть');

INSERT INTO cars (brand, model, year, license_plate, owner_id) VALUES
    ('Saab',   '9-3',     2005, '1234 AB-7', 1),
    ('Audi',   'A4',      2010, '5678 CD-7', 1),
    ('Toyota', 'Corolla', 2018, 'WX 1234',   2);

INSERT INTO orders (description, cost, status, car_id, mechanic_id) VALUES
    ('Замена масла',             80.00,  'IN_PROGRESS', 1, 1),
    ('Замена тормозных колодок', 150.50, 'IN_PROGRESS', 1, 3),
    ('Диагностика электрики',    60.00,  'IN_PROGRESS', 2, 2),
    ('Плановое ТО',              200.00, 'COMPLETED',   3, 1);





