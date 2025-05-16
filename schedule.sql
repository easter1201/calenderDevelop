CREATE TABLE 'schedule' (
    'schedule_id' BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    'user_name' VARCHAR(255) NOT NULL,
    'schedule_title' VARCHAR(255) NOT NULL,
    'schedule_content' VARCHAR(1000) NOT NULL,
    'created_at' DATETIME NOT NULL,
    'updated_at' DATETIME NOT NULL
    )