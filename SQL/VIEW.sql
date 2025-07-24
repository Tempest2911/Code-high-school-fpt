--VIEW
--CHI DUNG DUOC VOI SELECT
-- KO DUNG DC VOI IF ELSE
-- KHONG DUNG DUOC BIEN
-- CHI DUNG DUOC VOI ORDER BY TRONG TRUONG HOP"
		--SELECT TOP



CREATE VIEW DANHSACHHOCSINH
AS
SELECT * FROM dbo.Students WHERE Students.Gender = 'F'

GO
SELECT * FROM dbo.DANHSACHHOCSINH

--VD

go
CREATE VIEW Top3
AS
SELECT TOP 3 s.LastName , e.StudentID, AVG(e.Grade) AS DIEMTB FROM dbo.Enrollments e
JOIN dbo.Students s ON s.StudentID = e.StudentID
GROUP BY s.LastName , e.StudentID
ORDER BY AVG(e.Grade) DESC

GO

SELECT * FROM dbo.Top3
GO

--Bai1

CREATE VIEW Bai1
AS
SELECT * FROM dbo.SinhVien
GO
SELECT * FROM dbo.Bai1

--Bai2
GO
CREATE VIEW Bai2
AS
SELECT * FROM dbo.HocPhi
GO
SELECT * FROM dbo.Bai2

--Bai3
GO
CREATE VIEW Bai3
AS
SELECT sv.MaSinhVien, sv.HoTen, SUM(hp.DaThanhToan) AS'TongTien' FROM dbo.SinhVien sv 
JOIN dbo.HocPhi hp ON hp.MaSinhVien = sv.MaSinhVien
GROUP BY sv.MaSinhVien, sv.HoTen
GO
SELECT * FROM dbo.Bai3

--Bai4
GO
CREATE VIEW Bai4
AS
SELECT sv.MaSinhVien, sv.HoTen, hp.TongTien, hp.DaThanhToan FROM dbo.SinhVien sv
JOIN dbo.HocPhi hp ON hp.MaSinhVien = sv.MaSinhVien
JOIN dbo.ThanhToan tt ON tt.MaHocPhi = hp.MaHocPhi
WHERE hp.DaThanhToan<hp.TongTien
GO
SELECT * FROM dbo.Bai4

--Bai5
GO
CREATE VIEW Bai5
AS
SELECT sv.MaSinhVien, hp.MaHocPhi, tt.NgayThanhToan, tt.SoTien FROM dbo.ThanhToan tt
JOIN dbo.HocPhi hp ON hp.MaHocPhi = tt.MaHocPhi
JOIN dbo.SinhVien sv ON sv.MaSinhVien = hp.MaSinhVien
GO
SELECT * FROM dbo.Bai5

--Bai6
GO
CREATE VIEW Bai6
AS
SELECT sv.MaSinhVien, sv.HoTen, hp.HocKy FROM dbo.HocPhi hp
JOIN dbo.SinhVien sv ON sv.MaSinhVien = hp.MaSinhVien
JOIN dbo.ThanhToan tt ON tt.MaHocPhi = hp.MaHocPhi
WHERE hp.DaThanhToan = hp.TongTien
GO
SELECT * FROM dbo.Bai6

--Bai 7
GO
CREATE VIEW Bai7
AS
SELECT hp.HocKy, SUM(hp.TongTien) AS 'Tong Tien'  FROM dbo.HocPhi hp
GROUP BY hp.HocKy
GO
SELECT * FROM dbo.Bai7

--Bai 13
GO
CREATE VIEW Bai13
AS
SELECT TOP 3 sv.MaSinhVien, sv.HoTen, tt.NgayThanhToan FROM dbo.ThanhToan tt
JOIN dbo.HocPhi hp ON hp.MaHocPhi = tt.MaHocPhi
JOIN dbo.SinhVien sv ON sv.MaSinhVien = hp.MaSinhVien
ORDER BY tt.NgayThanhToan DESC
GO
SELECT * FROM dbo.Bai13

--Bai14
GO
CREATE VIEW Bai14
AS
SELECT TOP 5 hp.MaSinhVien, sv.HoTen, hp.TongTien FROM dbo.SinhVien sv
JOIN dbo.HocPhi hp ON hp.MaSinhVien = sv.MaSinhVien
ORDER BY hp.TongTien ASC
GO
SELECT * FROM bai14

--Bai15
GO
CREATE VIEW Bai15
AS
SELECT TOP 3 sv.MaSinhVien, sv.HoTen, SUM(tt.SoTien) AS 'SoTien' FROM dbo.ThanhToan tt 
JOIN dbo.HocPhi gp ON gp.MaHocPhi = tt.MaHocPhi
JOIN dbo.SinhVien sv ON sv.MaSinhVien = gp.MaSinhVien
GROUP BY
sv.MaSinhVien, sv.HoTen
GO
SELECT * FROM bai15



















