// This function all clear
function clearScreen() {
    document.getElementById("result").value = "";
}
 
// This function all displays 
function display(value) {
    document.getElementById("result").value += value;
}
 
// This function returns the result
function calculate() {
    var p = document.getElementById("result").value;
    var q = eval(p);
    document.getElementById("result").value = q;
}
