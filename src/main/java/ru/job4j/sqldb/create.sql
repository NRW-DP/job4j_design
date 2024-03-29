/*
The relationships between the tables are as follows:
1. users - roles = many-to-one 
2. roles - rules = many-to-many
3. items - users = many-to-one
4. items - comments = one-to-many
5. items - attachs = one-to-many
6. items - categories = many-to-one 
7. items - states = many-to-one 
*/


-- Table of role
CREATE TABLE roles (
    role_id SERIAL PRIMARY KEY,
    role_name VARCHAR(50) NOT NULL
);

-- Table of users
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    role_id INT REFERENCES roles(role_id) -- users/role (1. many-to-one)
);

-- Table of rules
CREATE TABLE rules (
    rule_id SERIAL PRIMARY KEY,
    rule_name VARCHAR(50) NOT NULL
);

-- Table of role and rule relationships (2. many-to-many)
CREATE TABLE role_rules (
    role_rule_id SERIAL PRIMARY KEY,
    role_id INT REFERENCES roles(role_id),
    rule_id INT REFERENCES rules(rule_id),
    UNIQUE (role_id, rule_id) -- The unique combination of role_id and rule_id
);

-- Table of status of requests
CREATE TABLE states (
    state_id SERIAL PRIMARY KEY,
    state_name VARCHAR(50) NOT NULL
);

-- Table of request categories
CREATE TABLE categories (
    category_id SERIAL PRIMARY KEY,
    category_name VARCHAR(50) NOT NULL
);

-- Table of requests
CREATE TABLE items (
    item_id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT, -- description of the content of the items apps 
    user_id INT REFERENCES users(user_id), -- items/users (3. many-to-one)
    category_id INT REFERENCES categories(category_id), -- items/category (6. many-to-one)
    state_id INT REFERENCES states(state_id) -- items/states (7. many-to-one)
);

-- Table of attached files to requests
CREATE TABLE attachs (
    attach_id SERIAL PRIMARY KEY,
    item_id INT REFERENCES items(item_id), -- items/attachs (5. one-to-many)
    file_name VARCHAR(255) NOT NULL,
    file_path VARCHAR(255) NOT NULL
);

-- Table of comments on requests
CREATE TABLE comments (
    comment_id SERIAL PRIMARY KEY,
    item_id INT REFERENCES items(item_id), -- items/comments (4. one-to-many)
    comment_text TEXT NOT NULL,
    created_at TIMESTAMP -- Comment creation time column
);

