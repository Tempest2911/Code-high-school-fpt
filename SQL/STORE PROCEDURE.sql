--Stored Procedure


--CREATE PROCEDURE tenSP

--@bien kieu du lieu
--@bien1 kieu du lieu
--AS
--BEGIN
	--T-SQL(select, insert,...)
--END

--CREATE PROCEDURE tinhtong
--@i INT
--AS
--BEGIN
--DECLARE @tong INT = 0; 
--	WHILE @i <100
--		BEGIN	
--		IF(@i%2=0)
--			BEGIN
--				SET @tong = @tong + @i
--			END
--		SET @i = @i+1
--		END
--	PRINT'Ket qua la: ' + CAST(@tong AS VARCHAR(100))
--END

--DROP PROCEDURE dbo.tinhtong

--EXECUTE dbo.tinhtong @i = 0 -- int

--GO

--CREATE PROCEDURE TimKhachHang
--@ID int
--AS
--BEGIN
--	IF EXISTS(SELECT 1 FROM dbo.Students s WHERE @ID = s.StudentID)
--		BEGIN
--			SELECT * FROM dbo.Students s WHERE @ID = s.StudentID
--		END
--	ELSE
--		PRINT'Khong ton tai sv'
--END

--EXECUTE dbo.TimKhachHang @ID = 4 -- int


--Bai1

--CREATE PROCEDURE Bai1
--AS 
--BEGIN
--    SELECT c.CourseID, c.CourseName, c.Credits FROM dbo.Courses c
--END

--EXECUTE dbo.Bai1

--Bai 2

--CREATE PROCEDURE TimTheoLastName
--@LastName VARCHAR(100)
--AS
--BEGIN
--    SELECT * FROM dbo.Students 
--	WHERE Students.LastName LIKE '%' + @LastName + '%'
--END

--SELECT * FROM dbo.Students

--EXECUTE dbo.TimTheoLastName @LastName = 'Smith' -- varchar(100)


--Bai 3

--CREATE PROCEDURE ThongTinSV
--AS
--BEGIN
--    SELECT e.EnrollmentID, e.StudentID, e.CourseID, e.Grade FROM dbo.Enrollments e
--END

--EXECUTE dbo.ThongTinSV

--Bai4

--CREATE PROCEDURE TimTheoGioiTinh
--@gender VARCHAR(10)
--AS 
--BEGIN
--    SELECT s.StudentID, s.FirstName, s.LastName, s.Gender, s.Email FROM dbo.Students s WHERE @gender = s.Gender
--END


--EXECUTE dbo.TimTheoGioiTinh @gender = 'M' -- varchar(10)

--Bai 5

CREATE PROCEDURE TatCaSVCourseID
@CourseID INT
AS
BEGIN
    SELECT s.StudentID, s.FirstName, s.LastName, e.Grade FROM dbo.Students s 
	JOIN dbo.Enrollments e ON e.StudentID = s.StudentID
	WHERE @CourseID = e.CourseID
END

EXECUTE dbo.TatCaSVCourseID @CourseID = 2 -- int

SELECT * FROM dbo.Enrollments


--









