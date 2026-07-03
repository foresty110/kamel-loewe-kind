CREATE TABLE IF NOT EXISTS category (
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS board (
    seq         BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_id BIGINT NOT NULL,
    author      VARCHAR(20) NOT NULL,
    password    VARCHAR(255) NOT NULL,
    title       VARCHAR(200) NOT NULL,
    content     TEXT NOT NULL,
    view_count  INT NOT NULL DEFAULT 0,
    created_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME NULL,
    CONSTRAINT fk_board_category FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE IF NOT EXISTS comment (
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    board_seq  BIGINT NOT NULL,
    author     VARCHAR(20) NOT NULL,
    content    VARCHAR(500) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_comment_board FOREIGN KEY (board_seq) REFERENCES board(seq) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS attachment (
    id                BIGINT AUTO_INCREMENT PRIMARY KEY,
    board_seq         BIGINT NOT NULL,
    original_filename VARCHAR(255) NOT NULL,
    stored_filename   VARCHAR(255) NOT NULL,
    created_at        DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_attachment_board FOREIGN KEY (board_seq) REFERENCES board(seq) ON DELETE CASCADE
);
