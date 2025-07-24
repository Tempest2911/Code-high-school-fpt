function thongtin() {
    let display = document.getElementById("display");
    display.innerHTML = `<label for="">Họ và Tên:</label>
    <input type="text" id="inputname"><br>
    <label for="">Lớp:</label>
    <input type="text" id="inputclass"><br>
    <label for="">Môn:</label>
    <input type="text" id="inputsubject"><br>
    <label for="">GVHD:</label>
    <input type="text" id="inputteacher"><br>

    <input type="button" value="Hiển thị" onclick="display1()">

    <h1>Họ và Tên: <span id="disname"></span></h1>
    <h1>Lớp: <span id="disclass"></span></h1>
    <h1>Môn học: <span id="dissubject"></span></h1>
    <h1>GVHD: <span id="disteacher"></span></h1>`;
}

function display1() {
    let inputname = document.getElementById("inputname").value;
    let inputclass = document.getElementById("inputclass").value;
    let inputsubject = document.getElementById("inputsubject").value;
    let inputteacher = document.getElementById("inputteacher").value;

    document.getElementById("disname").innerHTML = inputname;
    document.getElementById("disclass").innerHTML = inputclass;
    document.getElementById("dissubject").innerHTML = inputsubject;
    document.getElementById("disteacher").innerHTML = inputteacher;
}


function diemm() {
    let display = document.getElementById("display");
    display.innerHTML = `
    <label for="">Điểm Quá Trình: </label>
    <input type="text" id="quaTrinh"><br>

    <label for="">Điểm Thi: </label>
    <input type="text" id="diemThi"><br>

    <label for="">Điểm Thái Độ: </label>
    <input type="text" id="thaiDo"><br>

    <input type="button" value="Hiển Thị" onclick="display2()">

    <h1>Điểm Quá Trình: <span id="diem1"></span></h1>
    <h1>Điểm Thi: <span id="diem2"></span></h1>
    <h1>Điểm Quá Trình: <span id="diem3"></span></h1>
    <h1>Điểm TB: <span id="TB"></span></h1>
    <h1>Kết Quả: <span id="ketqua"></span></h1>`
}

function display2() {
    let quaTrinh = document.getElementById("quaTrinh").value;
    let diemThi = document.getElementById("diemThi").value;
    let thaiDo = document.getElementById("thaiDo").value;
    quaTrinh = Number(quaTrinh);
    diemThi = Number(diemThi);
    thaiDo = Number(thaiDo);

    let TB = (quaTrinh + diemThi + thaiDo) / 3;

    document.getElementById("diem1").innerHTML = quaTrinh;
    document.getElementById("diem2").innerHTML = diemThi;
    document.getElementById("diem3").innerHTML = thaiDo;
    document.getElementById("TB").innerHTML = TB;

    if (TB >= 5) {
        ketqua.innerHTML = "Qua Môn";
    } else {
        ketqua.innerHTML = "Trượt Môn";
    }
    document.getElementById("ketqua") = ketqua;
}