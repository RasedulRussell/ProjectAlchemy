create table Article
(
	id varchar2(33) not null,
	title varchar2(1000) not null,
	rawDetails blob not null,
	details varchar2(1000) not null,
	url varchar2(1000) not null
)
/

create unique index Article_id_uindex
	on Article (id)
/

alter table Article
	add constraint Article_pk
		primary key (id)
/

