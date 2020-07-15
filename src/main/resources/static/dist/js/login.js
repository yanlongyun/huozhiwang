function checkPhone(){
    var phone = document.getElementById('phone_number').value;
    if(!(/^1[3456789]\d{9}$/.test(phone))){
        swal({
            title:"手机号码有误，请重填",
            type:"error",
            confirmButtonText:"确定",
            closeOnConfirm:false
        });
        return false;
    }
    return true;
}
function checkPassword(){
    var password = document.getElementById('regis_password').value;
    if(!(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/.test(password))){
        swal({
            title:"密码不符合要求，请重填",
            type:"error",
            confirmButtonText:"确定",
            closeOnConfirm:false
        });
        return false;
    }
    return true;
}
$(document).ready(function(){
    $("#submit").click(function(){
        if (checkPhone()&&checkPassword()&&checkRegister()){
            var phone = $("#phone_number").val();
            var password = $("#regis_password").val();
            var dataObj = {};
            dataObj.phone = phone;
            dataObj.password = password;
            $.ajax({
                url:"/user/register",
                type: "POST",
                contentType: "application/json;charset=utf-8",
                dataType: "json",
                data: JSON.stringify(dataObj),
                success: function (data) {
                    swal(data.data)
                },
                error: function (data) {
                    swal("注册异常")
                }
            })
        }else {
            console.log("错误")
        }
    });
});

//电话号码已注册
function checkRegister() {
    if(checkPhone()){
        var dataObj ={};
        phone = $("#phone_number").val();
        dataObj.phone=phone;
        $.ajax({
            url:"/user/checkRegister",
            type: "POST",
            contentType: "application/json;charset=utf-8",
            dataType: "text",
            data: JSON.stringify(dataObj),
            success: function (data) {
                if(data=='true'){
                    return true;
                }else {
                    alert(data);
                    return false;
                }
            },
            error: function (data) {
                swal("手机号异常")
            }
        })
    }

}