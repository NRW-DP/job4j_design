INSERT INTO users (username, password, role_id) VALUES
    ('admin', 'adminpassword', 7),
    ('manager', 'managerpassword', 8),
    ('user', 'userpassword', 9);
	
INSERT INTO roles (role_name) VALUES
    ('Admin'),
    ('Manager'),
    ('User');
	
INSERT INTO rules (rule_name) VALUES
    ('Can Create Requests'),
    ('Can Manage Requests'),
    ('Can View Requests');

INSERT INTO states (state_name) VALUES
    ('New'),
    ('In Progress'),
    ('Resolved'),
    ('Closed');

INSERT INTO categories (category_name) VALUES
    ('Software'),
    ('Hardware'),
    ('Networking'),
    ('Security');
	
INSERT INTO items (title, description, user_id, category_id, state_id) VALUES
    ('Software Issue', 'Software keeps crashing randomly.', 16, 13, 13),
    ('Hardware Malfunction', 'Printer is not working properly.', 17, 14, 14),
    ('Network Outage', 'Unable to connect to the internet.', 18, 15, 14);

INSERT INTO attachs (item_id, file_name, file_path) VALUES
    (28, 'log.txt', '/path/to/log.txt'),
    (29, 'error.jpg', '/path/to/error.jpg'),
    (30, 'network-diagram.png', '/path/to/network-diagram.png');
	
INSERT INTO comments (item_id, comment_text, created_at) VALUES
    (28, 'I will investigate the software issue.', CURRENT_TIMESTAMP),
    (29, 'I will check the printer for issues.', CURRENT_TIMESTAMP),
    (30, 'I will troubleshoot the network problem.', CURRENT_TIMESTAMP);

SELECT * FROM roles;
SELECT * FROM users;
SELECT * FROM rules;
SELECT * FROM role_rules;
SELECT * FROM states;
SELECT * FROM categories;
SELECT * FROM items;
SELECT * FROM attachs;
SELECT * FROM comments;