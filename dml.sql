INSERT INTO role (id, path, description) VALUES ('1', 'masterdata', 'البيانات الساسية');


INSERT INTO modules_operations (id, name, description) VALUES (1, 'All', 'كل العمليات');

INSERT INTO modules_operations (id, name, description) VALUES (2, 'Add', 'أضافة');

INSERT INTO modules_operations (id, name, description) VALUES (3, 'Edit', 'تعديل');

INSERT INTO modules_operations (id, name, description) VALUES (4, 'Delete', 'حذف');

INSERT INTO modules_operations (id, name, description) VALUES (5, 'Confirm', 'اعتماد');

INSERT INTO modules_operations (id, name, description) VALUES (6, 'Post', 'ترحيل');

INSERT INTO modules_operations (id, name, description) VALUES (7, 'Cancel', 'الغاء');



INSERT INTO components (id, name, description) VALUES ('1', 'masterdata', 'التعريفات');

INSERT INTO components (id, name, description) VALUES ('3', 'transactions', 'العمليات');

INSERT INTO components (id, name, description) VALUES ('4', 'reports', 'التقارير');

INSERT INTO components (id, name, description, parent_id) VALUES ('5', 'accounts', 'الحسابات', '3');

INSERT INTO components (id, name, description, parent_id) VALUES ('6', 'purchasing', 'المشتريات', '3');

INSERT INTO components (id, name, description, parent_id) VALUES ('7', 'sales', 'المبيعات', '3');

INSERT INTO components (id, name, description, parent_id) VALUES ('8', 'stores', 'المخازن', '3');

INSERT INTO components (id, name, description, parent_id) VALUES ('9', 'treasure', 'الخزينة', '3');

INSERT INTO components (id, name, description, parent_id) VALUES ('10', 'bank', 'البنوك', '3');





INSERT INTO modules (id, path, description, comp_id,form_name) VALUES ('1', '/masterdata/security/Users.xhtml', 'المستخدمين', '1','Users');


INSERT INTO modules (id, path, description, form_name, comp_id,non_menu) VALUES 
('2', '/masterdata/security/UserModules.xhtml', 'صلاحيات المستخدمين', 'UserModules', '1',0);

INSERT INTO modules (id, path, description, form_name, comp_id) VALUES ('3', '/masterdata/banks/BankList.xhtml', 'البنوك', 'BankList', '1');

INSERT INTO modules (id, path, description, form_name, comp_id) VALUES ('4', '/masterdata/manfez/ManfezList.xhtml', 'المنافذ', 'ManfezList', '1');

INSERT INTO modules (id, path, description, form_name, comp_id) VALUES ('5', '/masterdata/costCenters/CostCentersTree.xhtml', 'مراكز التكلفة', 'CostCentersTree', '1');

INSERT INTO modules (id, path, description, form_name, comp_id,non_menu) VALUES ('6', '/masterdata/costCenters/AddEditCostCenter.xhtml', 'إضافة وتعديل مراكز التكلفة', 'AddEditCostCenter', '1',1);

INSERT INTO modules (id, path, description, form_name, comp_id,non_menu) VALUES 
('7', '/masterdata/checks/ChecksList.xhtml', 'الشيكات', 'ChecksList', '1',0);

INSERT INTO modules (id, path, description, form_name, comp_id,non_menu) VALUES 
('8', '/masterdata/contract/ContractList.xhtml', 'العقود', 'ContractList', '1',0);

INSERT INTO modules (id, path, description, form_name, comp_id,non_menu) VALUES 
('9', '/masterdata/hotel/HotelList.xhtml', 'الفنادق', 'HotelList', '1',0);


INSERT INTO modules (id, path, description, form_name, comp_id,non_menu) VALUES 
('10', '/masterdata/items/ItemsList.xhtml', 'الأصناف', 'ItemsList', '1',0);


INSERT INTO modules (id, path, description, form_name, comp_id,non_menu) VALUES 
('11', '/masterdata/packages/PackageList.xhtml', 'الحزم', 'PackageList', '1',0);



INSERT INTO modules (id, path, description, comp_id,form_name) VALUES ('12', '/masterdata/company/CompanyList.xhtml', 'الشركات', '1','CompanyList');

INSERT INTO modules (id, path, description, comp_id,form_name) VALUES ('13', '/masterdata/names/NamesList.xhtml', 'الأسماء', '1','NamesList');

INSERT INTO modules (id, path, description, comp_id,form_name) VALUES ('14', '/masterdata/exchangeRate/ExchangeRateList.xhtml', 'اسعار الصرف', '1','ExchangeRateList');

INSERT INTO modules (id, path, description, comp_id,form_name) VALUES ('15', '/masterdata/tax/TaxList.xhtml', 'االضرائب', '1','TaxList');

INSERT INTO modules (id, path, description, comp_id,form_name) VALUES ('16', '/masterdata/store/StoreList.xhtml', 'المخازن', '1','StoreList');

INSERT INTO modules (id, path, description, comp_id,form_name) VALUES ('17', '/masterdata/accounts/MainChartOfAccountingTree.xhtml', 'الحسابات', '1','MainChartOfAccountingTree');

INSERT INTO modules (id, path, description, comp_id,form_name,non_menu) VALUES ('18', '/masterdata/accounts/AddEditMainChartOfAccounting.xhtml', 'إضافة وتعديل الحسابات', '1','AddEditMainChartOfAccounting',1);


-----------------------------------------Refrence Date -------------------------------------



INSERT Into JVType (id, type) VALUES (1, N'مشتريات')
;INSERT Into JVType (id, type) VALUES (2, N'مبيعات')
;INSERT Into JVType (id, type) VALUES (3, N'مرتجعات مشتريات')
;INSERT Into JVType (id, type) VALUES (4, N'مرتجعات مبيعات')
;INSERT Into JVType (id, type) VALUES (5, N'صرف نقدية')
;INSERT Into JVType (id, type) VALUES (6, N'توريد نقدية')
;INSERT Into JVType (id, type) VALUES (7, N'قيد تسوية')
;INSERT Into JVType (id, type) VALUES (8, N'رصيد اول اصناف')
;INSERT Into JVType (id, type) VALUES (9, N'استلام اوراق قبض')
;INSERT Into JVType (id, type) VALUES (10, N'تسليم اوراق الدفع')
;INSERT Into JVType (id, type) VALUES (11, N'تسديد اوراق قبض')
;INSERT Into JVType (id, type) VALUES (12, N'تسديد اوراق الدفع')
;INSERT Into JVType (id, type) VALUES (13, N'رصيد افتتاحي')
;INSERT Into JVType (id, type) VALUES (14, N'تسديد اوراق قبض تحت التحصيل')
;INSERT Into JVType (id, type) VALUES (15, N'تسديد اوراق دفع تحت التحصيل')

----------------------------------------------
INSERT into  CustomerType (Customer_Type_ID, type) VALUES (1, N'اساسى');
INSERT into  CustomerType (Customer_Type_ID, type) VALUES (2, N'اضافى');
INSERT into  CustomerType (Customer_Type_ID, type) VALUES (3, N'هدية');

-----------------------------------------------
INSERT into Currency (ID, Currency, Ishome, Symbol) VALUES (33, N'جنيه مصري', 1, N'EG');