
  CREATE TABLE "SCOTT"."MINFO" 
   (	"MNUMBER" NUMBER(20,0), 
	"MJOB" VARCHAR2(40 BYTE) NOT NULL ENABLE, 
	"MNAME" VARCHAR2(30 BYTE), 
	"MGENDER" VARCHAR2(30 BYTE), 
	"MLEVEL" VARCHAR2(30 BYTE), 
	"MDETAIL" VARCHAR2(700 BYTE), 
	 PRIMARY KEY ("MNUMBER")