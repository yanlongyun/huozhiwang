/*
* 正则表达式验证邮箱
* */
var checkPhone=function(phone){
    var ckphReg = /^1[3456789]\d{9}$/;
    var backCheckPh = ckphReg.test(phone);
    return backCheckPh;
}
/*正则表达式验证密码*/
var checkPassword = function(password){
    var ckpaReg = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;
    var backCheckPa = ckpaReg.test(password);
    return backCheckPa;
}
/*注册功能*/
function  register(){
        var phone = $("#phoneNumber").val();
        var password = $("#registerPwd").val();
        var autoCode=$("#register-authCode").val();
        if(!checkPhone(phone)){
            swal({
            text:"手机号格式错误，请重新错误"
            })
            return false;
        }
        if(!checkPassword(password)){
            swal({
            text:"密码不符合要求"
            })
            return false;
        }
    if(autoCode.length<4){
        swal({
            text:"验证码不符合要求"
        })
        return false;
    }
        /*回调函数验证验证码和手机号*/
    checkRegisterAuthCode(phone,password,autoCode);
}

/*改变验证码*/
function changeImg(){
    var img = document.getElementById("verify_code_img");
    var login_img = document.getElementById("login_verify_code_img");
    img.src = "/codeImg?date=" + new Date();
}
/*验证验证码和手机号，最后完成注册*/
function checkRegisterAuthCode(phone,password,autoCode){
        var a={};
        a.authCode=autoCode;
        a.phone = phone;
        a.password = password;
        $.ajax({
            url:"/checkAuthCode",
            type:"POST",
            cache:false,
            contentType:"application/json;charset=utf-8",
            datatype:"json",
            data:JSON.stringify(a),
            success:function(back){
                var code = back.code;
                if(code === 1){
                    //alert("验证码正确");
                    $.ajax({
                        url:"/user/checkRegister",
                        type: "POST",
                        contentType: "application/json;charset=utf-8",
                        dataType: "json",
                        data: JSON.stringify(a),
                        success: function (data) {
                         //   alert("手机号正确");
                            result = data.data;
                            if(result === "true"){
                                $.ajax({
                                    url:"/user/register",
                                    type: "POST",
                                    contentType: "application/json;charset=utf-8",
                                    dataType: "json",
                                    data: JSON.stringify(a),
                                    success: function (data) {
                                        swal(data.data);
                                        $('#registerModal').modal('hide');
                                    },
                                    error: function (data) {
                                        swal("注册异常")
                                    }
                                })
                            }else {
                                swal(result)
                            }
                        },
                        error: function (data) {
                            swal("手机号异常")
                        }
                    });
                }
                    /*$("#div-authCode-register").addClass("has-success has-feedback");
                    $("#register-authCode").after("<span class='glyphicon glyphicon-ok form-control-feedback' aria-hidden='true'></span>");
                    $("#div-authCode-register").removeClass("has-error");
                    $("#div-authCode-register span").remove(".glyphicon-remove")}*/
                else{
                    if(code===0){
                        alert(back.data);
                    }
                       /* $("#div-authCode-register").addClass("has-error has-feedback");
                        $("#register-authCode").after("<span class='glyphicon glyphicon-remove form-control-feedback' aria-hidden='true'></span>");
                        $("#div-authCode-register").removeClass("has-success");
                        $("#div-authCode-register span").remove(".glyphicon-ok")}*/
                    else{
                        console.error("")}
                }
            }
        })
}
/*登录功能*/
/*注册功能*/
function  login(){
    var phone = $("#login_phoneNumber").val();
    var password = $("#loginPwd").val();
    var autoCode=$("#login-authCode").val();
    if(!checkPhone(phone)){
        swal({
            text:"手机号格式错误，请重新错误"
        })
        return false;
    }
    if(!checkPassword(password)){
        swal({
            text:"密码不符合要求"
        })
        return false;
    }
    if(autoCode.length<4){
        swal({
            text:"验证码不符合要求,请重新输入"
        })
        return false;
    }
    /*回调函数验证验证码*/
    checkLoginAuthCode(phone,password,autoCode);
}
function checkLoginAuthCode(phone,password,autoCode) {
    var a = {};
    a.authCode = autoCode;
    a.phone = phone;
    a.password = password;
    $.ajax({
        url: "/checkAuthCode",
        type: "POST",
        cache: false,
        contentType: "application/json;charset=utf-8",
        datatype: "json",
        data: JSON.stringify(a),
        success: function (back) {
            var code = back.code;
            if (code === 1) {
                result = data.data;
                if (result === "true") {
                    $.ajax({
                        url: "/user/login",
                        type: "POST",
                        contentType: "application/json;charset=utf-8",
                        dataType: "json",
                        data: JSON.stringify(a),
                        success: function (data) {
                            swal(data.data);
                            $('#loginModal').modal('hide');
                        },
                        error: function (data) {
                            swal("登录异常")
                        }
                    })
                } else {
                    swal(result)
                };
            }else (code === 0)
            {
                swal(back.data);
            };
        },
        error:function (back) {
            swal("验证码异常")
        }
    })
}


/*点击注册按钮后立即执行*/

$("#register").click(function ()
{
    register();
})




