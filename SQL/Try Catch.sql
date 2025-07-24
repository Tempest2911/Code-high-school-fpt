--Select, insert, if, while

--try, catch

--Giao dich

--in ra so luong khoa hoc cua

--mot sinh vien cu the ( xu li loi bang try catch)

CREATE PROCEDURE apDungTryCatch
@id INT
AS
BEGIN
    DECLARE @soluong INT
    BEGIN TRY
		IF EXISTS(SELECT 1 FROM dbo.Students s WHERE s.StudentID = @id)
		BEGIN 
			SELECT @soluong = COUNT(e.CourseID) FROM dbo.Enrollments e WHERE e.StudentID = @id
		END
        ELSE
			PRINT 'Sinh Vien ko ton tai'
	END TRY
    BEGIN CATCH
		DECLARE @error VARCHAR(2000)
		SET @error = ERROR_MESSAGE();
		PRINT 'Loi cua ban la: ' + @error
    END catch
END

EXEC dbo.apDungTryCatch @id = 1 -- int

-------

CREATE PROCEDURE xoaDan
@id INT
AS
BEGIN
    BEGIN try
        BEGIN TRANSACTION
		IF EXISTS(SELECT 1 FROM dbo.Enrollments e WHERE e.StudentID = @id)
			BEGIN
				ROLLBACK TRANSACTION
				PRINT'Ban Dan dang di hoc ko dc xoa'
            END
		ELSE
			BEGIN
			    DELETE FROM dbo.Enrollments WHERE Enrollments.StudentID = @id
				COMMIT TRANSACTION
				PRINT'Xoa Dan thanh cong'
			END
			
    END TRY
	BEGIN CATCH
	    ROLLBACK TRANSACTION
		DECLARE @error VARCHAR(2000)
		SET @error = ERROR_MESSAGE();
		PRINT 'Loi cua ban la: ' + @error
	END CATCH
END

EXECUTE xoaDan @id = 8
