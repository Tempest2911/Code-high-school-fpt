function openTab() {
    
    let cf = window.confirm("Nigga?");
    console.log(cf);

    window.open("https://www.youtube.com/")
}

function checkSize() {
    // console.log(window.innerHeight);
    // console.log(window.innerWidth);
    // console.log(window.outerHeight);
    // console.log(window.outerWidth);
    // resizeTo, resizeBy
    // window.resizeTo(1900,1000);
    console.log(navigator.language);
}

function openRoblox() {
    console.log(location.href);
    location.href = "https://www.roblox.com/home?nl=true";
}

function goBackBtn() {
    history.back();
}

function goFwBtn() {
    history.forward();   
}