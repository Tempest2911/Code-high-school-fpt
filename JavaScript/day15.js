function validateGender() {
        let gender = document.querySelector('input[name="gender"]:checked');
        let errorGender = document.getElementById("errorGender");
        if (!gender) {
                errorGender.innerHTML = 'Chon gioi tinh';
                return false;
        } else {
                errorGender.innerHTML = '';
                return true;
        }
}

function validateMajor() {
        let major = document.querySelectorAll('input[name="major"]:checked');
        let errormajor = document.getElementById("errormajor");

        if (major.length === 0) {
                errormajor.innerHTML = "Vui lòng chọn chuyên ngành"
                return false;
        } else {
                errormajor.innerHTML = '';
                return true;
        }
}

function displayInfo() {
        let gender = document.querySelector('input[name="gender"]:checked').value;
        let major = document.querySelectorAll('input[name="major"]:checked');
        let majorlist = '';
        for (let i = 0; i < major.length; i++) {
                majorlist += major[i].value;

        }
        let info = `Giới tính là: ${gender}\nChuyen nganh: ${majorlist}`;
        alert(info);

}