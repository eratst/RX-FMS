function deleteCell(){
  var mailbody = document.getElementById("mainbody");
  var cell = document.getElementById("delCell");
  if(cell!=undefined){
     mainbody.removeChild(cell);
  } 
 }
