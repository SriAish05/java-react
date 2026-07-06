-- Sample data for testing
INSERT INTO departments (id, name) VALUES (1, 'Engineering');
INSERT INTO departments (id, name) VALUES (2, 'Marketing');
INSERT INTO departments (id, name) VALUES (3, 'Human Resources');
INSERT INTO departments (id, name) VALUES (4, 'Finance');

INSERT INTO employees (id, name, email, department_id, created_date, last_modified_date) VALUES (1, 'John Doe', 'john.doe@company.com', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO employees (id, name, email, department_id, created_date, last_modified_date) VALUES (2, 'Jane Smith', 'jane.smith@company.com', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO employees (id, name, email, department_id, created_date, last_modified_date) VALUES (3, 'Bob Johnson', 'bob.johnson@company.com', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO employees (id, name, email, department_id, created_date, last_modified_date) VALUES (4, 'Alice Williams', 'alice.williams@company.com', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO employees (id, name, email, department_id, created_date, last_modified_date) VALUES (5, 'Charlie Brown', 'charlie.brown@company.com', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO employees (id, name, email, department_id, created_date, last_modified_date) VALUES (6, 'Diana Prince', 'diana.prince@company.com', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO employees (id, name, email, department_id, created_date, last_modified_date) VALUES (7, 'Eve Davis', 'eve.davis@company.com', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO employees (id, name, email, department_id, created_date, last_modified_date) VALUES (8, 'Frank Miller', 'frank.miller@company.com', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO employees (id, name, email, department_id, created_date, last_modified_date) VALUES (9, 'Grace Lee', 'grace.lee@company.com', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO employees (id, name, email, department_id, created_date, last_modified_date) VALUES (10, 'Hank Wilson', 'hank.wilson@company.com', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
