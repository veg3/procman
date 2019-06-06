function myEncode (obj) {//加密
    var data = new Base64( ).encode(JSON.stringify(obj));
    return data;
}

function myUncode (str) {//解密
    var data =  JSON.parse(new Base64().decode(str ));
    return data;
}


