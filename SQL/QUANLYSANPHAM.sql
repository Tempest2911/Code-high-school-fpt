--Built in function trg sql
--GetDate () Lấy ra ngày tháng hiện tại current day
--year() : Lấy ra năm dựa theo tham số truyền vào
--ex : year (2007-25-5) : 2007
--month(): lấy ra tháng hiện tại
--ex :05
--Day () lấy ra ngày hiện tại 
--ex:25
--concat(chuoigoc, 'noi') 
--huongtmmhe130721 :account
--concat(account,'fpt.edu.vn')
--format ngày tháng :YYYY/MM/DD , MM//DD/YYYY
--dung voi ham format(NgayThang,'YYYY/MM/DD')
--Substring () : lấy một chuỗi con trong 1 chuỗi lớn
--Charindex(): tìm vị trí của chuỗi con trong chuỗi lớn
--SUBSTRING(Chuoi lon, start, end)
--Input phongnd -> output : substring ('phongnd', 1,5) ---> phong

--Charindex(): tìm vị trí của chuỗi con trong chuỗi lớn
--cu phap CHARINDEX(chuỗi cần tìm, chuỗi bố)
-- phongnd@fpt.ed.vn -> trả về vị trí của @
-- charindex ('@', 'phongnd@fpt.edu.vn') -> vị trí: 8
-- Kết hợp Substring và charindex
-- truyền vào email -> in ra account của giang vien
-- độ dài của tên ko = nhau -> acc độ dài không bằng nhau
--substring(email, 1, charindex('@', email)-1)"


CREATE DATABASE QUANLYSANPHAM
GO
USE QUANLYSANPHAM
GO
CREATE TABLE SanPham (
    MaSanPham INT PRIMARY KEY,
    MaNCC INT,
    NgayNhap DATE,
    SoLuong INT,
    GiaNhap DECIMAL(10, 2)
);
CREATE TABLE KhachHang (
    MaKhachHang INT PRIMARY KEY,
    TenKhachHang NVARCHAR(100),
    DiaChi NVARCHAR(255),
    SoDienThoai NVARCHAR(15),
    NgaySinh DATE
);
CREATE TABLE ChiTietHoaDon (
    MaHoaDon INT PRIMARY KEY,
    MaSanPham INT,
    MaKhachHang INT,
    NgayDatHang DATE,
    SoLuongMua INT,
    DonGia DECIMAL(10, 2),
    FOREIGN KEY (MaSanPham) REFERENCES SanPham(MaSanPham),
    FOREIGN KEY (MaKhachHang) REFERENCES KhachHang(MaKhachHang)
);
INSERT INTO SanPham (MaSanPham, MaNCC, NgayNhap, SoLuong, GiaNhap) VALUES
(4, 104, '2023-10-01', 46, 35353),
(2, 102, '2024-10-05', 30, 15000.00),
(3, 103, '2024-10-10', 20, 20000.00);

INSERT INTO KhachHang (MaKhachHang, TenKhachHang, DiaChi, SoDienThoai, NgaySinh) VALUES
(4, N'Nguyễn Văn A', N'123 Đường 1, Quận 1', '0901234567', '2023-01-01'),
(2, N'Trần Thị B', N'456 Đường 2, Quận 2', '0912345678', '1995-02-02'),
(3, N'Lê Văn C', N'789 Đường 3, Quận 3', '0923456789', '1988-03-03');

INSERT INTO ChiTietHoaDon (MaHoaDon, MaSanPham, MaKhachHang, NgayDatHang, SoLuongMua, DonGia) VALUES
(1, 1, 1, '2024-10-12', 2, 10000.00),
(2, 2, 2, '2024-10-13', 1, 15000.00),
(3, 3, 3, '2024-10-14', 3, 20000.00);

GO
--Bai 4
CREATE VIEW V_SanPham
AS
SELECT sp.MaSanPham, sp.MaNCC, sp.NgayNhap, sp.SoLuong, sp.GiaNhap, (sp.SoLuong* sp.GiaNhap) AS 'Thanh Tien' 
FROM dbo.SanPham sp
WHERE YEAR(sp.NgayNhap) = 2023
GO
SELECT * FROM dbo.V_SanPham
--Bai 5
GO
CREATE VIEW V_ChiTietHoaDon
AS
SELECT sp.MaSanPham, ct.MaHoaDon, FORMAT(ct.NgayDatHang, 'dd/MM/yyyy') AS 'NgayDatHang', ct.SoLuongMua, ct.DonGia, (sp.SoLuong* sp.GiaNhap) AS 'Thanh Tien' FROM dbo.SanPham sp
JOIN dbo.ChiTietHoaDon ct ON ct.MaSanPham = sp.MaSanPham
GO
SELECT * FROM dbo.V_ChiTietHoaDon
--Bai 6
GO
CREATE VIEW V_KhachHang
AS
SELECT kh.MaKhachHang, kh.TenKhachHang, kh.DiaChi, kh.SoDienThoai, (YEAR(GETDATE()) - YEAR(kh.NgaySinh)) AS Tuoi FROM dbo.KhachHang kh
GO
SELECT * FROM dbo.V_KhachHang
--Bai 7
GO
CREATE FUNCTION TinhTongGiaTriHangNhap(
	@MaNCC int
)
RETURNS DECIMAL(10,2)
AS
BEGIN
	DECLARE @Tong DECIMAL(10,2);
    SELECT @Tong = SUM(sp.SoLuong * sp.GiaNhap) FROM dbo.SanPham sp
	WHERE @MaNCC = sp.MaNCC AND YEAR(sp.NgayNhap) = 2023

RETURN @Tong
END
GO

SELECT dbo.TinhTongGiaTriHangNhap(104) AS TongTien

--Bai 8
GO
CREATE VIEW V_KhachHangTuoiTren30
AS
SELECT kh.MaKhachHang, kh.TenKhachHang, kh.DiaChi, kh.SoDienThoai, (YEAR(GETDATE()) - YEAR(kh.NgaySinh)) AS Tuoi FROM dbo.KhachHang kh
WHERE (YEAR(GETDATE()) - YEAR(kh.NgaySinh)) > 30
GO
SELECT * FROM dbo.V_KhachHangTuoiTren30

--Bai 9
GO
CREATE VIEW V_HoaDonTheoThang
AS
SELECT MONTH(ct.NgayDatHang) AS 'Thang', COUNT(ct.MaHoaDon) AS 'SoLuongDonHang', SUM(ct.SoLuongMua*ct.DonGia) AS 'TongTien' FROM dbo.ChiTietHoaDon ct
GROUP BY ct.NgayDatHang
ORDER BY MONTH(ct.NgayDatHang) DESC

GO
SELECT * FROM dbo.V_HoaDonTheoThang hd
ORDER BY hd.Thang DESC

--Bai 10
GO
ALTER VIEW V_SanPhamTrongThang
AS
SELECT sp.MaSanPham, sp.MaNCC, sp.SoLuong, sp.GiaNhap FROM dbo.SanPham sp
WHERE MONTH(sp.NgayNhap) = 10
GO
DROP 
SELECT * FROM V_SanPhamTrongThang

--Bai 11
GO
CREATE VIEW V_Email
AS
SELECT CONCAT(kh.SoDienThoai, '@gmail.com') AS Gmail FROM dbo.KhachHang kh
GO
SELECT * FROM dbo.V_Email

--Bai 12
GO
CREATE FUNCTION InRaNhaMang(
	@Sodienthoai VARCHAR(15)
)
RETURNS VARCHAR(15)
AS
BEGIN
    DECLARE @NhaMang VARCHAR(10)
	IF LEFT(@Sodienthoai,2) = '01'
		BEGIN
		    SET @NhaMang = 'Viettel'
		END
	IF LEFT(@Sodienthoai,2) = '02'
		BEGIN
		    SET @NhaMang = 'VinaPhone'
		END
	IF LEFT(@Sodienthoai,2) = '03'
		BEGIN
		    SET @NhaMang = 'Mobiphone'
		END
	RETURN @NhaMang
END

GO
SELECT dbo.InRaNhaMang('032334424') AS NhaMang 

--Bai 13
GO

CREATE VIEW Inrasdt
AS
SELECT SUBSTRING(sp.MaNCC_Varchar, 1,CHARINDEX('-',sp.MaNCC_Varchar)-1) AS SO FROM dbo.SanPham sp

GO
SELECT * FROM dbo.Inrasdt




















