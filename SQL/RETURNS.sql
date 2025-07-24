--Hàm Function



CREATE FUNCTION tenHam(
	@STUDENTID INT,
	@name VARCHAR(20),
	@gioitinh CHAR(1)
)

--return 
--RETURNS TABLE : Tra ve bang
--Tra ve gia tri don
RETURNS DECIMAL(10,2)--KIEU DU LIEU GIA TRI TRA VE
AS
BEGIN
    -- T-SQL (CRUD,..)
	DECLARE @diemTB DECIMAL(10,2)
	SELECT @diemTB = AVG(e.Grade) FROM dbo.Enrollments e 
	JOIN dbo.Students s ON s.StudentID = e.StudentID 
	WHERE @STUDENTID = e.StudentID AND s.FirstName = @name AND s.Gender = @gioitinh
	RETURN @diemTB;
END

DROP FUNCTION dbo.tenHam
SELECT * FROM dbo.Enrollments
SELECT * FROM dbo.Students

SELECT dbo.tenHam(2, 'Jane', 'F') AS DiemTB

go
--Bai1
CREATE FUNCTION LayHoTenSV(
	@StudentId int,
	@Name varchar(50)
	)

	RETURNS NVARCHAR(100)
AS
BEGIN
    DECLARE @FirstName VARCHAR(20)
	DECLARE @LastName VARCHAR(20)

	SELECT @FirstName = s.FirstName, @LastName = s.LastName 
	FROM dbo.Students s
	WHERE @StudentId = s.StudentID AND @Name = s.FirstName
	RETURN @FirstName+@LastName
END

GO
SELECT dbo.LayHoTenSV(1, 'John')

--Bai 4
GO

CREATE FUNCTION TraVeSoTien(
	@MaThanhToan INT,
	@MaHocPhi INT,
	@NgayThanhToan DATE
)
	RETURNS DECIMAL(10,2)
AS
BEGIN
    DECLARE @SoDu FLOAT

	SELECT @SoDu = hp.TongTien - tt.SoTien FROM dbo.HocPhi hp 
	JOIN dbo.ThanhToan tt ON tt.MaHocPhi = hp.MaHocPhi
	WHERE @MaThanhToan = tt.MaThanhToan AND @MaHocPhi = tt.MaHocPhi AND @NgayThanhToan = tt.NgayThanhToan
	RETURN @SoDu
END

GO

DROP FUNCTION dbo.TraVeSoTien

SELECT * FROM dbo.ThanhToan

SELECT * FROM dbo.HocPhi

SELECT dbo.TraVeSoTien(1,1,'1-15-2024') AS SoDu

--Bai 2
GO

CREATE FUNCTION LaySoDT(
	@MaSV int
)

RETURNS NVARCHAR(100)
AS
BEGIN
    DECLARE @SoDT NVARCHAR(100)

	SELECT @SoDT = sv.SoDienThoai FROM dbo.SinhVien sv WHERE @MaSV = sv.MaSinhVien
	RETURN @SoDT
END

GO

SELECT dbo.LaySoDT(1) AS SoDT

--Bai 3

CREATE FUNCTION TraVeSoTien3(
	@MaHocPhi INT
)
	RETURNS DECIMAL(10,2)
AS
BEGIN
    DECLARE @SoDu FLOAT

	SELECT @SoDu = hp.TongTien - tt.SoTien FROM dbo.HocPhi hp 
	JOIN dbo.ThanhToan tt ON tt.MaHocPhi = hp.MaHocPhi
	WHERE  @MaHocPhi = tt.MaHocPhi
	RETURN @SoDu
END

GO

SELECT dbo.TraVeSoTien3(1) AS SoDu

GO


USE StudentManagement

--HAM TRA VE BANG TABLE

CREATE FUNCTION listSVNam()
RETURNS TABLE
AS 
	RETURN(
		SELECT * FROM dbo.Students s WHERE s.Gender = 'M'
	);

GO

SELECT * FROM listSVNam()

GO

--Bai1

CREATE FUNCTION dbo.GetStudentTuitionFees (@MaSinhVien INT)
RETURNS TABLE
AS
RETURN
(
    SELECT 
        MaHocPhi,
        HocKy,
        TongTien,
        DaThanhToan
    FROM 
        dbo.HocPhi
    WHERE 
        MaSinhVien = @MaSinhVien
);

GO
SELECT * from dbo.GetStudentTuitionFees(1)


--Bai 2
GO

CREATE FUNCTION LichSuThanhToan(@MaHocPhi INT)
RETURNS TABLE
AS
RETURN(
	SELECT tt.MaThanhToan, tt.NgayThanhToan, tt.SoTien FROM dbo.ThanhToan tt WHERE @MaHocPhi = tt.MaHocPhi
);
GO
SELECT * FROM dbo.LichSuThanhToan(1)

--Bai 3
GO

CREATE FUNCTION ThongTinChitietHocPhi(@MaSV int)
RETURNS TABLE
AS 
RETURN(
    SELECT tt.MaHocPhi, hp.HocKy, hp.TongTien, tt.SoTien, tt.NgayThanhToan 
    FROM dbo.HocPhi hp
    JOIN dbo.ThanhToan tt ON tt.MaHocPhi = hp.MaHocPhi 
    WHERE hp.MaSinhVien = @MaSV
    AND tt.NgayThanhToan = (SELECT MAX(NgayThanhToan) 
                            FROM dbo.ThanhToan 
                            WHERE MaHocPhi = hp.MaHocPhi)
);


GO
DROP FUNCTION dbo.ThongTinChitietHocPhi
GO
SELECT * FROM dbo.ThongTinChitietHocPhi(1)

