-- Tabla para gestionar los usuarios
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Tabla para gestionar las etiquetas
CREATE TABLE labels (
    label_id SERIAL PRIMARY KEY,
    label_name VARCHAR(255) NOT NULL,
    user_id INT NOT NULL REFERENCES users(user_id)
);

-- Tabla para gestionar las tareas
CREATE TABLE tasks (
    task_id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    due_date TIMESTAMP,
    completed_date TIMESTAMP,
    status VARCHAR(50) DEFAULT 'Pendiente',
    archived BOOLEAN DEFAULT FALSE,
    label_id INT REFERENCES labels(label_id),
    user_id INT NOT NULL REFERENCES users(user_id)
);

-- Datos de prueba para la tabla usuarios
INSERT INTO users (username, password) VALUES
    ('john_doe', 'password123'),
    ('jane_doe', 'password456'),
    ('mike_smith', 'password789');

-- Datos de prueba para la tabla etiquetas
INSERT INTO labels (label_name, user_id) VALUES
    ('Trabajo', 1),
    ('Casa', 1),
    ('Personal', 1),
    ('Trabajo', 2),
    ('Casa', 3),
    ('Personal', 3);

-- Datos de prueba para la tabla tareas
INSERT INTO tasks (title, description, due_date, label_id, user_id) VALUES
    ('Completar el informe', 'Completar el informe financiero del mes de Septiembre', '2023-10-05 00:00:00', 1, 1),
    ('Lavar los platos', 'Lavar los platos después de la cena', '2023-10-01 20:00:00', 2, 1),
    ('Reunión con el cliente', 'Reunión con el cliente para discutir los requerimientos del proyecto', '2023-10-10 10:00:00', 1, 2),
    ('Comprar comestibles', 'Comprar leche, pan, huevos y frutas', '2023-10-02 18:00:00', 5, 3),
    ('Pagar las facturas', 'Pagar la factura de la electricidad y el agua', '2023-10-06 00:00:00', 5, 3);

-- Marcar una tarea como completada
UPDATE tasks SET status = 'Completada', completed_date = '2023-10-01 19:00:00' WHERE task_id = 2;

-- Archivar una tarea completada
UPDATE tasks SET archived = TRUE WHERE task_id = 2;
