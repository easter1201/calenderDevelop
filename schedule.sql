CREATE TABLE 'user'(
    'user_id' BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    'user_name' VARCHAR(255) NOT NULL,
    'email' VARCHAR(255) NOT NULL,
    'password' VARCHAR(25) NOT NULL,
    'created_at' DATETIME NOT NULL,
    'updated_at' DATETIME NOT NULL
    );

CREATE TABLE 'schedule' (
    'schedule_id' BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    'user_id' BIGINT NOT NULL,
    'schedule_title' VARCHAR(255) NOT NULL,
    'schedule_content' VARCHAR(1000) NOT NULL,
    'created_at' DATETIME NOT NULL,
    'updated_at' DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
    );