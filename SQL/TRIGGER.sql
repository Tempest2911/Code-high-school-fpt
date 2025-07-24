--TRIGGER
-- AFTER VA INSTEAD OF
-- TU DONG IN RA THONG BAO THEM STUDENT THANH CONG
-- VA IN RA THONG TIN MINH VUA INSERT

USE ThanhToanHocPhi

INSERT INTO STUDENT(FIRSTNAME, LASTNAME, DATEOFBIRTH, GENDER)
VALUES('NGUYEN', 'PHONG', 2007-11-29, 'MALE')
GO
CREATE TRIGGER INSERTSTUDENT
ON dbo.Students
AFTER INSERT
AS
BEGIN
    PRINT'THEM STUDENT THANH CONG';
	SELECT * FROM INSERTED
END

INSERT INTO dbo.Students
(
    FirstName,
    LastName,
    DateOfBirth,
    Gender,
    Address,
    Email,
    Rank
)
VALUES
(   '233', -- FirstName - varchar(50)
    '123', -- LastName - varchar(50)
    '2007-11-29', -- DateOfBirth - date
    'M', -- Gender - varchar(1)
    'HAHHAA', -- Address - varchar(200)
    'SKJR@34.COM', -- Email - varchar(100)
    'II345J'  -- Rank - varchar(50)
    )

GO

CREATE TRIGGER CHECKSTUDENTNAME
ON dbo.Students
INSTEAD OF INSERT
AS
BEGIN
    IF EXISTS (SELECT 1 FROM Inserted WHERE Inserted.FirstName IS NULL)
		BEGIN
			RAISERROR('FIRST NAME KO DC RONG', 16,1);
			ROLLBACK
		END
	ELSE
		BEGIN
		    INSERT INTO dbo.Students
		    (
		        FirstName,
		        LastName,
		        DateOfBirth,
		        Gender,
		        Address,
		        Email,
		        Rank
		    )
		    SELECT  FirstName,
		        LastName,
		        DateOfBirth,
		        Gender,
		        Address,
		        Email,
		        Rank
				FROM Inserted
		END
END
--PRINT -> IN RA THONG BAO
--RAISERROR('THONG BAO', SEVERRITY, STATE)
go
    
--Bai1

CREATE TRIGGER NGANSOTIENQUAHOCPHI
ON dbo.HocPhi
INSTEAD OF INSERT
AS
BEGIN
	DECLARE @TienThanhToan DECIMAL(10,2);
	DECLARE @TienHocPhi DECIMAL(10,2);
	SELECT @TienThanhToan = hp.DaThanhToan , @TienHocPhi = hp.TongTien  FROM dbo.HocPhi hp
    IF(@TienThanhToan > @TienHocPhi)
	BEGIN
	    RAISERROR('So tien ko dc qua tien hoc phi', 16,1);
		ROLLBACK
	END
	ELSE
	BEGIN
	    INSERT INTO dbo.HocPhi
	    (
	        MaHocPhi,
	        MaSinhVien,
	        HocKy,
	        TongTien,
	        DaThanhToan
	    )
	   SELECT 
			MaHocPhi,
	        MaSinhVien,
	        HocKy,
	        TongTien,
	        DaThanhToan
			FROM Inserted
	END
END

SELECT * FROM dbo.ThanhToan

INSERT INTO dbo.ThanhToan
(
    MaThanhToan,
    MaHocPhi,
    NgayThanhToan,
    SoTien
)
VALUES
(   0,    -- MaThanhToan - int
    NULL, -- MaHocPhi - int
    NULL, -- NgayThanhToan - date
    NULL  -- SoTien - decimal(10, 2)
    )

--Bai2

GO

SELECT * FROM dbo.ThanhToan
go
CREATE TRIGGER UpdateDaThanhToan
ON dbo.ThanhToan
AFTER INSERT
AS
BEGIN
    DECLARE @MaHocPhi INT;
    DECLARE @TongTienThanhToan DECIMAL(10, 2);

    SELECT @MaHocPhi = tt.MaHocPhi
    FROM dbo.ThanhToan tt

    SELECT @TongTienThanhToan = SUM(tt.SoTien)
    FROM ThanhToan tt
    WHERE tt.MaHocPhi = @MaHocPhi;

    UPDATE HocPhi
    SET DaThanhToan = @TongTienThanhToan
    WHERE HocPhi.MaHocPhi = @MaHocPhi
END;

SELECT * FROM dbo.ThanhToan
SELECT * FROM dbo.HocPhi

INSERT INTO ThanhToan (MaThanhToan, MaHocPhi, NgayThanhToan, SoTien)
VALUES (7, 1, '2024-10-10', 990000);

--Bai 3

GO
CREATE TRIGGER XOATHONGTIN













