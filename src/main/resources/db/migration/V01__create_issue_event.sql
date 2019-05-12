
CREATE SEQUENCE octo.sq_issue START WITH 1 INCREMENT by 1 NO CYCLE;

CREATE TABLE octo.issue(
    id               NUMERIC(8)   NOT NULL,
    number           NUMERIC(8)   NOT NULL,
    created_at       TIMESTAMP    NOT NULL,

    CONSTRAINT pk_issu PRIMARY KEY (id)
);

CREATE SEQUENCE octo.sq_issue_event START WITH 1 INCREMENT by 1 NO CYCLE;

CREATE TABLE octo.issue_event(
    id               NUMERIC(8)   NOT NULL,
    action           VARCHAR(15)  NOT NULL,
    created_at       TIMESTAMP    NOT NULL,
    issue_id          NUMERIC(8)   NOT NULL,

    CONSTRAINT pk_isev PRIMARY KEY (id),
    CONSTRAINT fk_issu FOREIGN KEY (issue_id) REFERENCES octo.issue(id)
);
