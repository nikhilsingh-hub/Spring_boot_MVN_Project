function myFunction() {
    var toastBar = document.getElementById("toastBar");
    toastBar.className = "show";
    setTimeout(function(){ toastBar.className = toastBar.className.replace("show", ""); }, 3000);
  }