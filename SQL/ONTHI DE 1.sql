CREATE DATABASE QUANLYEVENT
GO
USE QUANLYEVENT
GO
CREATE TABLE EVENT(
	event_id INT PRIMARY KEY IDENTITY(1,1),
	event_name VARCHAR(100),
	event_date DATE,
	location VARCHAR(100)
);

CREATE TABLE Artists (
	artist_id INT PRIMARY KEY IDENTITY(1,1),
	artist_name VARCHAR(100)
);

CREATE TABLE Tickets(
	ticket_id INT PRIMARY KEY IDENTITY(1,1),
	event_id INT,
	price FLOAT NOT NULL CHECK (price>0),
	quantity INT CHECK(quantity>0),
	FOREIGN KEY(event_id) REFERENCES dbo.EVENT(event_id)
);
GO
CREATE PROCEDURE Insertevent(
	@event_name VARCHAR(100),
	@event_date DATE,
	@location VARCHAR(100)
)
AS
BEGIN
    INSERT INTO dbo.EVENT
    (
        event_name,
        event_date,
        location
    )
    VALUES
    (   @event_name, -- event_name - varchar(100)
        @event_date, -- event_date - date
        @location  -- location - varchar(100)
        )
END
GO
EXEC dbo.Insertevent @event_name = 'White Party',           -- varchar(100)
                     @event_date = '2024-10-19', -- date
                     @location = 'OHIO'              -- varchar(100)

					 EXEC dbo.Insertevent @event_name = 'SIGMA SEX',           -- varchar(100)
                     @event_date = '2024-5-2', -- date
                     @location = 'Phong' 

					 EXEC dbo.Insertevent @event_name = 'SKIBIDI',           -- varchar(100)
                     @event_date = '2024-4-6', -- date
                     @location = 'MMM' 
GO

CREATE PROCEDURE insertArtist(
	@artist_name VARCHAR(100)
)
AS
BEGIN
    INSERT INTO dbo.Artists
    (
        artist_name
    )
    VALUES
    (@artist_name -- artist_name - varchar(100)
        )
END
GO
EXEC dbo.insertArtist @artist_name = 'Diddy' -- varchar(100)
EXEC dbo.insertArtist @artist_name = 'Eminem' 
EXEC dbo.insertArtist @artist_name = 'Pig' 
GO
CREATE PROCEDURE insertTickets (
	@event_id INT,
	@price FLOAT,
	@quantity INT
)
AS
BEGIN
    INSERT INTO dbo.Tickets
    (
        event_id,
        price,
        quantity
    )
    VALUES
    (   @event_id, -- event_id - int
        @price,  -- price - float
        @quantity  -- quantity - int
        )
END
GO
EXEC dbo.insertTickets @event_id = 1, -- int
                       @price = 213,  -- float
                       @quantity = 123  -- int

EXEC dbo.insertTickets @event_id = 2, -- int
                       @price = 324,  -- float
                       @quantity = 436  -- int

EXEC dbo.insertTickets @event_id = 3, -- int
                       @price = 234,  -- float
                       @quantity = 53  -- int



GO
--1. Yêu c?u v? Stored Procedures (CRUD)
--Bai1
EXEC dbo.Insertevent @event_name = '!!!!!',           -- varchar(100)
                     @event_date = '9999-9-9', -- date
                     @location = 'VN' 
--Bai2
GO

CREATE PROCEDURE updateName(
	@artist_id INT,
	@artist_name VARCHAR(100)
)
AS
BEGIN
    UPDATE dbo.Artists
	SET Artists.artist_name = @artist_name
	WHERE @artist_id = Artists.artist_id
END

--Bai 3
go
CREATE PROCEDURE XoaVe(
	@ticket_id INT
)
AS
BEGIN
    DELETE dbo.Tickets
	WHERE @ticket_id = Tickets.ticket_id
END

--Bai 4
GO
CREATE PROCEDURE TruyXuat(
	@event_id INT
)
AS
BEGIN
    SELECT e.event_name, e.event_date, e.location FROM dbo.EVENT e 
	WHERE @event_id = e.event_id
END

--Bai5
GO
CREATE PROCEDURE TangVe(
	@ticket_id INT,
	@quanlity int
)
AS
BEGIN
    UPDATE dbo.Tickets
	SET Tickets.quantity = @quanlity
	WHERE @ticket_id = Tickets.ticket_id
END

--2. Yêu c?u v? Functions (Tính toán)
GO
--Bai1
CREATE FUNCTION TinhTongDoanhthu(
	@event_id int
)
RETURNS float
as
BEGIN
DECLARE @Tong float
    SELECT @Tong = SUM(t.price*t.quantity)FROM dbo.Tickets t WHERE @event_id = t.event_id
	RETURN @Tong
END

--Bai2
go
CREATE FUNCTION TinhSoNgayDenEvent(
	@event_id int
)
RETURNS int
as
BEGIN
	DECLARE @songay INT;
    SELECT @songay = DATEDIFF(DAY,GETDATE(),e.event_date) FROM dbo.EVENT e WHERE @event_id = e.event_id
	RETURN @songay
END
GO
DROP FUNCTION TinhSoNgayDenEvent
GO
SELECT dbo.TinhSoNgayDenEvent(2) AS Tong

SELECT * FROM dbo.EVENT

GO
--Bai3
CREATE FUNCTION KiemTraGiaVeHopLe(@giaVe DECIMAL(18, 2))
RETURNS VARCHAR(100)
AS
BEGIN
    DECLARE @result VARCHAR(100);

    IF @giaVe > 0
        SET @result = 'Gia ve hop le';
    ELSE
        SET @result = 'dit me may';

    RETURN @result;
END
GO

SELECT dbo.KiemTraGiaVeHopLe(1) AS Result
GO
--Bai4
CREATE FUNCTION TinhGiaTB(
	@event_id int
)
RETURNS FLOAT
AS
BEGIN
	DECLARE @TB float
    SELECT @TB =  AVG(t.price) FROM dbo.Tickets t WHERE @event_id = t.event_id
	RETURN @TB
END

GO
SELECT dbo.TinhGiaTB(1) AS GiaTBcua1Ve

--3. Yêu c?u v? Views (SELECT và tính toán)
--Bai1
GO
CREATE VIEW DanhSachSuKien30Ngay
AS
SELECT e.event_id,
       e.event_name,
       e.event_date,
       e.location FROM dbo.EVENT e
WHERE e.event_date > GETDATE() AND e.event_date <= DATEADD(DAY,30,GETDATE())

GO
SELECT * FROM dbo.DanhSachSuKien30Ngay

--Bai 2
GO
CREATE VIEW Top5SKconhieuve
AS
SELECT TOP 5 e.event_name, t.quantity FROM dbo.Tickets t
			 JOIN dbo.EVENT e ON e.event_id = t.event_id
ORDER BY t.quantity DESC
go
--Bai 3
CREATE VIEW DoanhThuSK
AS
SELECT e.event_name, SUM(t.price*t.quantity) AS Tong FROM dbo.Tickets t
			 JOIN dbo.EVENT e ON e.event_id = t.event_id
			 GROUP BY e.event_name
--Bai 4
go
CREATE VIEW NoiTenNgheSiVaSuKien
AS
SELECT e.event_id, e.event_name, a.artist_name
FROM dbo.EVENT e
INNER JOIN Artists a ON e.artist_id = a.artist_id;
GO
--Bai 5
CREATE VIEW TinhNgayKetThuc
AS
SELECT DATEADD(DAY,3,e.event_date) AS TGKetThuc
FROM dbo.EVENT e
GO
--Bai6
CREATE VIEW SoNgayConLai
AS
SELECT DATEDIFF(DAY, GETDATE(), e.event_date) AS NgayConLai FROM dbo.EVENT e

--4. Yêu c?u v? Transactions
--Bai1
GO
CREATE PROCEDURE BanVe(
	@QuantityVe INT,
	@Ticket_id int
)
AS
BEGIN
    BEGIN TRY
	BEGIN TRANSACTION
        IF @QuantityVe >0
        BEGIN
            UPDATE dbo.Tickets
			SET Tickets.quantity = Tickets.quantity - @QuantityVe
			WHERE @Ticket_id = Tickets.ticket_id

			SELECT SUM(t.quantity*t.price) AS DoanhThu FROM dbo.Tickets t		
        END
		COMMIT TRANSACTION
    END TRY

    BEGIN CATCH
        ROLLBACK TRANSACTION
		DECLARE @error VARCHAR(2000)
		SET @error = ERROR_MESSAGE();
		PRINT 'Loi cua ban la: ' + @error
    END CATCH
END

GO
EXEC dbo.BanVe @QuantityVe = 1, -- int
               @Ticket_id = 1   -- int

			   SELECT * FROM dbo.Tickets

--Bai 2
GO
CREATE PROCEDURE TaoEventvaThemVe(
	@event_name VARCHAR(100),
	@event_date DATE,
	@location VARCHAR(100),
	@price FLOAT,
	@quantity INT
)
AS
BEGIN
    BEGIN try
        BEGIN TRANSACTION
		DECLARE @event_id INT;
			INSERT INTO dbo.EVENT
			(
			    event_name,
			    event_date,
			    location
			)
			VALUES
			(   @event_name, -- event_name - varchar(100)
			    @event_date, -- event_date - date
			    @location  -- location - varchar(100)
			    )

			SET @event_id = SCOPE_IDENTITY();

			INSERT INTO dbo.Tickets
			(
			    event_id,
			    price,
			    quantity
			)
			VALUES
			(   @event_id, -- event_id - int
			    @price,  -- price - float
			    @quantity  -- quantity - int
			    )
				COMMIT TRANSACTION
    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION
        DECLARE @error NVARCHAR(4000)
        SET @error = ERROR_MESSAGE()
        PRINT 'Error occurred: ' + @error
    END CATCH
END

GO
EXEC dbo.TaoEventvaThemVe @event_name = 'Thac loan',           -- varchar(100)
                          @event_date = '6666-6-6', -- date
                          @location = 'asdasd',             -- varchar(100)
                          @price = 234,               -- float
                          @quantity = 123               -- int


						  SELECT * FROM dbo.EVENT
						  SELECT * FROM dbo.Tickets

--Bai 3
GO
CREATE PROCEDURE XoaSuKien(
	@event_id INT
)
AS
BEGIN
    BEGIN try
        BEGIN TRANSACTION

			DELETE FROM dbo.Tickets
			WHERE @event_id = Tickets.event_id

			DELETE FROM dbo.EVENT 
			WHERE @event_id = EVENT.event_id

			
				COMMIT TRANSACTION
    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION
        DECLARE @error NVARCHAR(4000)
        SET @error = ERROR_MESSAGE()
        PRINT 'Error occurred: ' + @error
    END CATCH
END

DROP PROCEDURE dbo.XoaSuKien

EXEC dbo.XoaSuKien @event_id = 8 -- int





	







