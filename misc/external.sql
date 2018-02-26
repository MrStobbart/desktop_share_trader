DROP TABLE IF EXISTS CompanyQuantity;
DROP TABLE IF EXISTS CompanyStock;
-- ----------------------------------------------------------
-- MDB Tools - A library for reading MS Access database files
-- Copyright (C) 2000-2011 Brian Bruns and others.
-- Files in libmdb are licensed under LGPL and the utilities under
-- the GPL, see COPYING.LIB and COPYING files respectively.
-- Check out http://mdbtools.sourceforge.net
-- ----------------------------------------------------------

-- That file uses encoding UTF-8

CREATE TABLE `CompanyQuantity`
 (
	`CompanyStockID`			int, 
	`PID`			int, 
	`Quantity`			int, 
	`DesiredQuantity`			int
);

CREATE TABLE `CompanyStock`
 (
	`PID`			int, 
	`Weight`			int, 
	`UnitPerPack`			int, 
	`BaseCost`			float, 
	`Name`			varchar (100)
);


INSERT INTO `CompanyQuantity` (`CompanyStockID`, `PID`, `Quantity`, `DesiredQuantity`) VALUES (1,1,0,10000);
INSERT INTO `CompanyQuantity` (`CompanyStockID`, `PID`, `Quantity`, `DesiredQuantity`) VALUES (2,2,6567,10000);
INSERT INTO `CompanyQuantity` (`CompanyStockID`, `PID`, `Quantity`, `DesiredQuantity`) VALUES (5,5,7471,10000);
INSERT INTO `CompanyQuantity` (`CompanyStockID`, `PID`, `Quantity`, `DesiredQuantity`) VALUES (6,6,0,10000);
INSERT INTO `CompanyQuantity` (`CompanyStockID`, `PID`, `Quantity`, `DesiredQuantity`) VALUES (3,3,5238,10000);
INSERT INTO `CompanyQuantity` (`CompanyStockID`, `PID`, `Quantity`, `DesiredQuantity`) VALUES (4,4,4471,10000);
INSERT INTO `CompanyStock` (`PID`, `Weight`, `UnitPerPack`, `BaseCost`, `Name`) VALUES (1,100,1,1.0000000000000000e+02,"Item A");
INSERT INTO `CompanyStock` (`PID`, `Weight`, `UnitPerPack`, `BaseCost`, `Name`) VALUES (2,110,1,1.0900000000000000e+02,"Item B");
INSERT INTO `CompanyStock` (`PID`, `Weight`, `UnitPerPack`, `BaseCost`, `Name`) VALUES (3,101,2,6.0000000000000000e+01,"Item C");
INSERT INTO `CompanyStock` (`PID`, `Weight`, `UnitPerPack`, `BaseCost`, `Name`) VALUES (4,77,3,5.5990000000000002e+01,"Item D");
INSERT INTO `CompanyStock` (`PID`, `Weight`, `UnitPerPack`, `BaseCost`, `Name`) VALUES (5,55,2,4.9000000000000000e+01,"Item E");
INSERT INTO `CompanyStock` (`PID`, `Weight`, `UnitPerPack`, `BaseCost`, `Name`) VALUES (6,39,1,1.0000000000000000e+02,"Item F");
