var Zoom=function (obj,width, height){
    width=width||obj.parentNode.offsetWidth;
    height=width;
    var img=new Image(); 
    img.src=obj.src; 
    var scale=Math.max(width/img.width, height/img.height); 
    var newWidth=img.width*scale; 
    var newHeight=img.height*scale; 
    var div=obj.parentNode; 
    obj.width=newWidth; 
    obj.height=newHeight; 
    div.style.width=width+"px"; 
    div.style.height=height+"px"; 
    div.style.overflow="hidden"; 
    obj.style.marginLeft=(width-newWidth)/2+"px"; 
    obj.style.marginTop=(height-newHeight)/2+"px";
}