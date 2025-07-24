USE BookstoreDB

SELECT * FROM dbo.Customers

--Bai 1
DECLARE @email VARCHAR(99) = 'Quyet@gmail.com';
IF NOT EXISTS (SELECT * FROM dbo.Customers c WHERE @email = c.Email)
	INSERT INTO dbo.Customers
	(
	    CustomerID,
	    FullName,
	    Email,
	    PhoneNumber,
	    Address
	)
	VALUES
	(   5,    -- CustomerID - int
	    'Quyet', -- FullName - varchar(100)
	    @email, -- Email - varchar(100)
	    '045745554', -- PhoneNumber - varchar(20)
	    NULL  -- Address - varchar(100)
	    )
ELSE 
	PRINT('Email da ton tai');
	

--Bai 2


SELECT * FROM dbo.Books

UPDATE dbo.Books
SET Stock = 52
WHERE BookID = 4


DECLARE @QuantityOrder INT = 104
DECLARE @BookID INT = 3

IF EXISTS (SELECT b.Stock, b.BookID FROM dbo.Books b WHERE @QuantityOrder>b.Stock AND @BookID = b.BookID)
	PRINT('Khong cho dat hang');
ELSE
	PRINT('Dat Hang thanh cong');


--Bai 3

SELECT * FROM dbo.OrderDetails

DECLARE @BookTitle VARCHAR(100) = 'Elaina'

IF EXISTS (SELECT * FROM dbo.Books b WHERE @BookTitle = b.Title)
	PRINT('Tao don hang thanh cong');
ELSE
	PRINT('Sach khong ton tai');


--Bai 4

SELECT b.BookID, b.Title, b.Price
FROM BookS b
JOIN OrderDetail od ON b.BookID = od.BookID
WHERE o.CustomerID = 1
ORDER BY b.Price DESC

SELECT * FROM dbo.OrderDetails
SELECT * FROM dbo.Books


SELECT TOP 1 o.CustomerID, b.BookID, b.Title, b.Price, od.TotalPrice FROM dbo.Books b

JOIN dbo.OrderDetails od ON od.BookID = b.BookID
JOIN dbo.Orders o ON o.OrderID = od.OrderID
WHERE o.CustomerID = 1
ORDER BY
od.TotalPrice DESC


--Bai 5
SELECT * FROM dbo.Books

DECLARE @QuantityOrder INT = 105;
DECLARE @BookID INT = 3;
DECLARE @Stock INT;

SELECT @Stock = b.Stock
FROM dbo.Books b
WHERE b.BookID = @BookID;

IF @Stock IS NULL
    PRINT('Sach khong ton tai');
ELSE IF @Stock = 0
    PRINT('Het hang');
ELSE IF @QuantityOrder <= @Stock
    PRINT('Dat hang thanh cong');
ELSE IF @QuantityOrder > @Stock
    PRINT('Chi duoc dat so luong con lai');

	UPDATE dbo.Books
	SET Stock = 0
	WHERE BookID = 3





