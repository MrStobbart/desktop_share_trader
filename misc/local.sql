DROP TABLE IF EXISTS TMPCLP69751;
DROP TABLE IF EXISTS TMPCLP514701;
DROP TABLE IF EXISTS StoreProduct;
DROP TABLE IF EXISTS StoreQuantity;
DROP TABLE IF EXISTS Transaction;
DROP TABLE IF EXISTS TransactionLine;
DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS Sale;
DROP TABLE IF EXISTS Delivery;
DROP TABLE IF EXISTS Products;
DROP TABLE IF EXISTS zoneProd;
-- ----------------------------------------------------------
-- MDB Tools - A library for reading MS Access database files
-- Copyright (C) 2000-2011 Brian Bruns and others.
-- Files in libmdb are licensed under LGPL and the utilities under
-- the GPL, see COPYING.LIB and COPYING files respectively.
-- Check out http://mdbtools.sourceforge.net
-- ----------------------------------------------------------

-- That file uses encoding UTF-8

CREATE TABLE `TMPCLP69751`
 (
	`PID`			int, 
	`Weight`			int, 
	`UnitPerPack`			int, 
	`BaseCost`			float
);

CREATE TABLE `TMPCLP514701`
 (
	`CompanyStockID`			int, 
	`PID`			int, 
	`Quantity`			int, 
	`DesiredQuantity`			int
);

CREATE TABLE `StoreProduct`
 (
	`PID`			int, 
	`CostAdjustment`			float
);

CREATE TABLE `StoreQuantity`
 (
	`PID`			int, 
	`Quantity`			int, 
	`DesiredQuantity`			int
);

CREATE TABLE `Transaction`
 (
	`TID`			int, 
	`CustomerID`			int
);

CREATE TABLE `TransactionLine`
 (
	`OID`			int, 
	`TID`			int, 
	`PID`			int
);

CREATE TABLE `Customer`
 (
	`CustomerID`			int, 
	`Name`			varchar (100), 
	`Address`			varchar (100), 
	`FinanceOK`			varchar (100)
);

CREATE TABLE `Sale`
 (
	`SaleID`			int, 
	`PID`			int, 
	`sale`			int
);

CREATE TABLE `Delivery`
 (
	`ZoneID`			int, 
	`Cost`			int
);

CREATE TABLE `Products`
 (
	`PID`			int, 
	`Weight`			int, 
	`UnitPerPack`			int, 
	`BaseCost`			float, 
	`Name`			varchar (100)
);

CREATE TABLE `zoneProd`
 (
	`PID`			int, 
	`ZoneID`			int
);


INSERT INTO `TMPCLP69751` (`PID`, `Weight`, `UnitPerPack`, `BaseCost`) VALUES (1,100,1,1.0098999999999999e+02);
INSERT INTO `TMPCLP69751` (`PID`, `Weight`, `UnitPerPack`, `BaseCost`) VALUES (2,110,1,1.0900000000000000e+02);
INSERT INTO `TMPCLP69751` (`PID`, `Weight`, `UnitPerPack`, `BaseCost`) VALUES (3,101,2,5.9000000000000000e+01);
INSERT INTO `TMPCLP69751` (`PID`, `Weight`, `UnitPerPack`, `BaseCost`) VALUES (4,77,3,5.7000000000000000e+01);
INSERT INTO `TMPCLP69751` (`PID`, `Weight`, `UnitPerPack`, `BaseCost`) VALUES (5,55,2,4.9000000000000000e+01);
INSERT INTO `TMPCLP69751` (`PID`, `Weight`, `UnitPerPack`, `BaseCost`) VALUES (6,39,1,9.9989999999999995e+01);
INSERT INTO `TMPCLP514701` (`CompanyStockID`, `PID`, `Quantity`, `DesiredQuantity`) VALUES (1,1,368,1000);
INSERT INTO `TMPCLP514701` (`CompanyStockID`, `PID`, `Quantity`, `DesiredQuantity`) VALUES (2,2,678,1000);
INSERT INTO `TMPCLP514701` (`CompanyStockID`, `PID`, `Quantity`, `DesiredQuantity`) VALUES (5,5,897,1000);
INSERT INTO `TMPCLP514701` (`CompanyStockID`, `PID`, `Quantity`, `DesiredQuantity`) VALUES (6,6,345,1000);
INSERT INTO `TMPCLP514701` (`CompanyStockID`, `PID`, `Quantity`, `DesiredQuantity`) VALUES (3,3,456,1000);
INSERT INTO `TMPCLP514701` (`CompanyStockID`, `PID`, `Quantity`, `DesiredQuantity`) VALUES (4,4,345,1000);
INSERT INTO `StoreProduct` (`PID`, `CostAdjustment`) VALUES (1,1.0000000000000000e+02);
INSERT INTO `StoreProduct` (`PID`, `CostAdjustment`) VALUES (2,2.0000000000000000e+00);
INSERT INTO `StoreProduct` (`PID`, `CostAdjustment`) VALUES (3,2.5000000000000000e+00);
INSERT INTO `StoreProduct` (`PID`, `CostAdjustment`) VALUES (4,1.0000000000000000e+02);
INSERT INTO `StoreProduct` (`PID`, `CostAdjustment`) VALUES (5,4.5000000000000000e+00);
INSERT INTO `StoreProduct` (`PID`, `CostAdjustment`) VALUES (6,-9.8999999999999999e-01);
INSERT INTO `StoreQuantity` (`PID`, `Quantity`, `DesiredQuantity`) VALUES (1,100,100);
INSERT INTO `StoreQuantity` (`PID`, `Quantity`, `DesiredQuantity`) VALUES (2,100,100);
INSERT INTO `StoreQuantity` (`PID`, `Quantity`, `DesiredQuantity`) VALUES (3,100,100);
INSERT INTO `StoreQuantity` (`PID`, `Quantity`, `DesiredQuantity`) VALUES (4,100,100);
INSERT INTO `StoreQuantity` (`PID`, `Quantity`, `DesiredQuantity`) VALUES (5,100,100);
INSERT INTO `StoreQuantity` (`PID`, `Quantity`, `DesiredQuantity`) VALUES (6,6,100);
INSERT INTO `Transaction` (`TID`, `CustomerID`) VALUES (6,6);
INSERT INTO `Transaction` (`TID`, `CustomerID`) VALUES (5,5);
INSERT INTO `Transaction` (`TID`, `CustomerID`) VALUES (4,4);
INSERT INTO `Transaction` (`TID`, `CustomerID`) VALUES (3,3);
INSERT INTO `Transaction` (`TID`, `CustomerID`) VALUES (2,2);
INSERT INTO `Transaction` (`TID`, `CustomerID`) VALUES (1,1);
INSERT INTO `TransactionLine` (`OID`, `TID`, `PID`) VALUES (1,1,1);
INSERT INTO `TransactionLine` (`OID`, `TID`, `PID`) VALUES (2,1,2);
INSERT INTO `TransactionLine` (`OID`, `TID`, `PID`) VALUES (3,1,3);
INSERT INTO `TransactionLine` (`OID`, `TID`, `PID`) VALUES (4,2,1);
INSERT INTO `TransactionLine` (`OID`, `TID`, `PID`) VALUES (5,2,2);
INSERT INTO `TransactionLine` (`OID`, `TID`, `PID`) VALUES (6,2,4);
INSERT INTO `TransactionLine` (`OID`, `TID`, `PID`) VALUES (7,3,5);
INSERT INTO `TransactionLine` (`OID`, `TID`, `PID`) VALUES (8,3,6);
INSERT INTO `TransactionLine` (`OID`, `TID`, `PID`) VALUES (9,3,2);
INSERT INTO `TransactionLine` (`OID`, `TID`, `PID`) VALUES (10,4,3);
INSERT INTO `TransactionLine` (`OID`, `TID`, `PID`) VALUES (11,5,4);
INSERT INTO `TransactionLine` (`OID`, `TID`, `PID`) VALUES (12,6,1);
INSERT INTO `Customer` (`CustomerID`, `Name`, `Address`, `FinanceOK`) VALUES (4,"Mr Murdoch","1 MadDog Avenue","False");
INSERT INTO `Customer` (`CustomerID`, `Name`, `Address`, `FinanceOK`) VALUES (5,"Mr Knight","1 Kit Road","True");
INSERT INTO `Customer` (`CustomerID`, `Name`, `Address`, `FinanceOK`) VALUES (2,"Mr T","1 Tee Road","False");
INSERT INTO `Customer` (`CustomerID`, `Name`, `Address`, `FinanceOK`) VALUES (6,"Mr Banner","1 GreenGiant Park","False");
INSERT INTO `Customer` (`CustomerID`, `Name`, `Address`, `FinanceOK`) VALUES (1,"Mr Smith","1 Smith Street","True");
INSERT INTO `Customer` (`CustomerID`, `Name`, `Address`, `FinanceOK`) VALUES (3,"Mr Peck","1 Templeton Cresent","False");
INSERT INTO `Sale` (`SaleID`, `PID`, `sale`) VALUES (20,3,50);
INSERT INTO `Sale` (`SaleID`, `PID`, `sale`) VALUES (21,4,5);
INSERT INTO `Sale` (`SaleID`, `PID`, `sale`) VALUES (22,5,80);
INSERT INTO `Sale` (`SaleID`, `PID`, `sale`) VALUES (23,1,12);
INSERT INTO `Sale` (`SaleID`, `PID`, `sale`) VALUES (24,2,2);
INSERT INTO `Delivery` (`ZoneID`, `Cost`) VALUES (1,10);
INSERT INTO `Delivery` (`ZoneID`, `Cost`) VALUES (2,20);
INSERT INTO `Delivery` (`ZoneID`, `Cost`) VALUES (3,13);
INSERT INTO `Delivery` (`ZoneID`, `Cost`) VALUES (4,9);
INSERT INTO `Delivery` (`ZoneID`, `Cost`) VALUES (5,3);
INSERT INTO `Delivery` (`ZoneID`, `Cost`) VALUES (6,7);
INSERT INTO `Products` (`PID`, `Weight`, `UnitPerPack`, `BaseCost`, `Name`) VALUES (1,100,1,1.0000000000000000e+02,"Item A");
INSERT INTO `Products` (`PID`, `Weight`, `UnitPerPack`, `BaseCost`, `Name`) VALUES (2,110,1,1.0900000000000000e+02,"Item B");
INSERT INTO `Products` (`PID`, `Weight`, `UnitPerPack`, `BaseCost`, `Name`) VALUES (3,101,2,6.0000000000000000e+01,"Item C");
INSERT INTO `Products` (`PID`, `Weight`, `UnitPerPack`, `BaseCost`, `Name`) VALUES (4,77,3,5.5990000000000002e+01,"Item D");
INSERT INTO `Products` (`PID`, `Weight`, `UnitPerPack`, `BaseCost`, `Name`) VALUES (5,55,2,4.9000000000000000e+01,"Item E");
INSERT INTO `Products` (`PID`, `Weight`, `UnitPerPack`, `BaseCost`, `Name`) VALUES (6,39,1,1.0000000000000000e+02,"Item F");
INSERT INTO `zoneProd` (`PID`, `ZoneID`) VALUES (1,1);
INSERT INTO `zoneProd` (`PID`, `ZoneID`) VALUES (2,2);
INSERT INTO `zoneProd` (`PID`, `ZoneID`) VALUES (3,3);
INSERT INTO `zoneProd` (`PID`, `ZoneID`) VALUES (4,4);
INSERT INTO `zoneProd` (`PID`, `ZoneID`) VALUES (5,5);
INSERT INTO `zoneProd` (`PID`, `ZoneID`) VALUES (6,6);
