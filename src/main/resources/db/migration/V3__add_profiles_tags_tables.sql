CREATE TABLE profiles
(
    id BIGINT PRIMARY KEY ,
    bio TEXT,
    phone_number VARCHAR(15),
    date_of_birth DATE,
    loyalty_points INT UNSIGNED DEFAULT 0,
    FOREIGN KEY (id) REFERENCES users(id)
);

CREATE TABLE tags
(
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY ,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE user_tags
(
    user_id BIGINT NOT NULL,
    tag_id INT NOT NULL,
    PRIMARY KEY (user_id, tag_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE ,
    FOREIGN KEY (tag_id) REFERENCES tags(id) ON DELETE CASCADE
)