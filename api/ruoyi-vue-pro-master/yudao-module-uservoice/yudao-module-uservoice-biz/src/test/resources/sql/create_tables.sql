CREATE TABLE IF NOT EXISTS "uservoice_app_user" (
    "id" bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    "create_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "update_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    "creator" varchar DEFAULT '',
    "updater" varchar DEFAULT '',
    "deleted" bit NOT NULL DEFAULT FALSE,
    "user_type" varchar NOT NULL,
    "username" varchar,
    "password" varchar,
    "user_open_id" varchar,
    "avatar" varchar,
    "nickname" varchar,
    "status" varchar,
    PRIMARY KEY ("id")
) COMMENT 'App用户表';

CREATE TABLE IF NOT EXISTS "uservoice_feedback" (
    "id" bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    "create_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "update_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    "creator" varchar DEFAULT '',
    "updater" varchar DEFAULT '',
    "deleted" bit NOT NULL DEFAULT FALSE,
    "uid" bigint,
    "content" varchar,
    "likes" bigint,
    "feedback_type" int,
    PRIMARY KEY ("id")
) COMMENT '反馈表';