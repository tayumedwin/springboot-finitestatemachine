CREATE TABLE jobstbl
(
    job_id              int(6)         AUTO_INCREMENT,
    job_type            VARCHAR(50)    DEFAULT '',
    state               VARCHAR(50)    DEFAULT '',
    status              VARCHAR(50)    DEFAULT '',
    created_by          VARCHAR(50)    DEFAULT '',
    created_date        DATETIME       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by          VARCHAR(50)    DEFAULT '',
    updated_date        DATETIME       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT jobs_pk PRIMARY KEY (job_id)
);