create Database testdb;

create table user (
	username Varchar(264) Not Null Primary Key,
    password Varchar(64) Not null,
    access integer(1)
);

create table WebsiteList(
	Name Varchar(256) not null,
    URL Varchar(256) not null primary key,
    totalS integer(2) not null,
    P1S integer(2) not null,
    P2S integer(2) not null,
    P3S integer(2) not null,
    P4S integer(2) not null,
    reviewed time not null default now()
);