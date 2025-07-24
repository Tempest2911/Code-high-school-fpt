package Lab5;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class XFile {

    Student s = new Student();
    public List<Student> listHS = new ArrayList<>();
//
//    // Bài 1: Đọc và ghi file nhị phân

    public static byte[] read(String path) {
        try {
            FileInputStream fis = new FileInputStream(path);
            int n = fis.available();
            byte[] data = new byte[n];
            fis.read(data);
            fis.close();
            return data;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//

    public static void write(String path, byte[] data) {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            fos.write(data);
            fos.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//
//    // Bài 2: Đọc và ghi đối tượng

    // 📝 Ghi danh sách Student vào file TXT
    public void ghiFile() {
        File file = new File("students.txt");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Student s : listHS) {
                bw.write(s.getName() + "," + s.getMarks() + "," + s.getMajor()); // Lưu dưới dạng CSV
                bw.newLine(); // Xuống dòng
            }
            System.out.println("Ghi file thành công!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 📖 Đọc danh sách Student từ file TXT
    public void docFile() {
        File file = new File("students.txt");

        if (!file.exists()) {
            System.out.println("Không tìm thấy file");
            return;
        }

        listHS.clear(); // Xóa danh sách cũ để cập nhật danh sách mới

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(","); // Tách dữ liệu theo dấu phẩy
                if (parts.length == 3) {
                    String name = parts[0].trim();
                    double marks = Double.parseDouble(parts[1].trim());
                    String major = parts[2].trim();
                    listHS.add(new Student(name, marks, major));
                }
            }
            System.out.println("Đọc file thành công!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object readObject(String path) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
            Object object = ois.readObject();
            ois.close();
            return object;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void writeObject(String path, Object object) {
        try {
            ObjectOutputStream oos
                    = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(object);
            oos.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
