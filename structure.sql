create database app_work;


use app_work;
create table user(
id numeric primary key,
password varchar(20) not null,
username varchar(50) not null,
lastName VARCHAR(100),
secondName varchar(100),
firstName varchar(100) not null,
isAdmin TINYINT(1) not null
);

create table modules (
id numeric primary key,
path varchar(100),
description varchar(100),
form_name varchar(45),
non_menu TINYINT(1)
);

create table role (
id numeric primary key,
path varchar(100),
description varchar(100)
);


create table user_roles(
id numeric primary key,
user_id numeric not null,
role_id numeric not null
);

alter table user_roles add (
 foreign key U_FK (user_id) references  user(id)

);

alter table user_roles add (
 foreign key R_FK (role_id) references  role(id)

);


ALTER TABLE user 

ADD UNIQUE INDEX username_UNIQUE (username ASC) ;






CREATE TABLE seq_table (
  seq_name varchar(100) NOT NULL,
  seq_value decimal(20,0) NOT NULL,
  PRIMARY KEY (seq_name)
);





CREATE  TABLE components (

  id DECIMAL(10,0)  NOT NULL ,

  name VARCHAR(100) NOT NULL ,

  description VARCHAR(100) NOT NULL ,

  parent_id DECIMAL(10,0)  NULL ,

  PRIMARY KEY (id) 

  );
  
  alter table components add (
 foreign key parent_FK (parent_id) references  components(id)

);

ALTER TABLE modules ADD COLUMN comp_id DECIMAL(10,0) not null , 

  ADD CONSTRAINT com_id

  FOREIGN KEY (comp_id )

  REFERENCES components (id );
  
  
  
  CREATE  TABLE modules_operations (

  id INT NOT NULL ,

  name VARCHAR(45) NOT NULL ,
  description VARCHAR(45) NOT NULL ,

  PRIMARY KEY (id) );
  
  
  
  
  CREATE  TABLE user_components (

  id DECIMAL(10,0)  NOT NULL ,

  user_id DECIMAL(10,0)  NOT NULL ,

  component_id DECIMAL(10,0)  NOT NULL ,

  PRIMARY KEY (id) ,

  

  CONSTRAINT u_id

    FOREIGN KEY (user_id )

    REFERENCES user (id ),

    

  CONSTRAINT c_id

    FOREIGN KEY (component_id )

    REFERENCES components (id )
    );



CREATE  TABLE user_modules (

  id DECIMAL(10,0)  NOT NULL ,

  user_id DECIMAL(10,0)  NOT NULL ,

  module_id DECIMAL(10,0)  NOT NULL ,

  PRIMARY KEY (id) ,

  

  CONSTRAINT um_id

    FOREIGN KEY (user_id )

    REFERENCES user (id ),

    

  CONSTRAINT m_id

    FOREIGN KEY (module_id )

    REFERENCES modules (id )
    );
	
	
	
	ALTER TABLE user_modules ADD COLUMN operation_id INT NULL  AFTER module_id ;
	
	ALTER TABLE user_modules CHANGE COLUMN operation_id operation_id INT NULL DEFAULT NULL  , 

  ADD CONSTRAINT op_id

  FOREIGN KEY (operation_id )

  REFERENCES app_work.modules_operations (id );

alter table user_modules add constraint user_moulde_uk unique (user_id,module_id);
  
  
  