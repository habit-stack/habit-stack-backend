CREATE TABLE member(
                       member_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       name VARCHAR(50) NOT NULL UNIQUE,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       email_verified BOOLEAN NOT NULL DEFAULT FALSE,
                       status VARCHAR(20) NOT NULL DEFAULT 'ACTIVATED',
                       role VARCHAR(20) NOT NULL DEFAULT 'MEMBER',
                       last_login_at TIMESTAMP,
                       password_updated_at TIMESTAMP,
                       failed_login_count INT NOT NULL DEFAULT 0,
                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       deleted_at TIMESTAMP
);

CREATE TABLE auth(
                     auth_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                     member_id BIGINT NOT NULL,
                     refresh_token VARCHAR(512) NOT NULL,
                     refresh_token_expires_at TIMESTAMP NOT NULL,
                     created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                     updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                     CONSTRAINT fk_auth_member
                         FOREIGN KEY (member_id)
                             REFERENCES member(member_id)
);

CREATE TABLE tag(
                    tag_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                    member_id BIGINT NOT NULL,
                    tag_name VARCHAR(50) NOT NULL,
                    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                    deleted_at TIMESTAMP,

                    CONSTRAINT fk_tag_member
                        FOREIGN KEY (member_id)
                            REFERENCES member(member_id)
);

CREATE TABLE category(
                         category_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                         cat_name VARCHAR(50) NOT NULL UNIQUE,
                         icon VARCHAR(50) NOT NULL
);

CREATE TABLE record(
                       record_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       member_id BIGINT NOT NULL,
                       category_id BIGINT NOT NULL,
                       record_date DATE NOT NULL,
                       content VARCHAR(255),
                       is_representative BOOLEAN NOT NULL DEFAULT FALSE,
                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       deleted_at TIMESTAMP,

                       CONSTRAINT fk_record_member
                           FOREIGN KEY (member_id)
                               REFERENCES member(member_id),

                       CONSTRAINT fk_record_category
                           FOREIGN KEY (category_id)
                               REFERENCES category(category_id)
);

CREATE TABLE record_tag(
                           tag_id BIGINT NOT NULL,
                           record_id BIGINT NOT NULL,

                           PRIMARY KEY (record_id, tag_id),

                           CONSTRAINT fk_record_tag_tag
                               FOREIGN KEY (tag_id)
                                   REFERENCES tag(tag_id)
                                   ON DELETE CASCADE,

                           CONSTRAINT fk_record_tag_record
                               FOREIGN KEY (record_id)
                                   REFERENCES record(record_id)
                                   ON DELETE CASCADE
);

CREATE INDEX idx_auth_member_id ON auth(member_id);
CREATE INDEX idx_tag_member_id ON tag(member_id);
CREATE INDEX idx_record_member_id ON record(member_id);
CREATE INDEX idx_record_category_id ON record(category_id);
CREATE INDEX idx_record_tag_tag_id ON record_tag(tag_id);