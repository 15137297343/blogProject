$(function () {

    /*登录开始*/
    $("#login").on('click',function () {
        /*先获取值*/
        var name=$("#count").val().trim();
        var password=$("#password").val().trim();
        /*先对错误信息置空*/
        $("#count_span").html("");
        $("#password_span").html("");
        /*对值参数格式判断*/
        var ok=true;
        if (name == ""){
            $("#count_span").html("用户名不能为空！");
            ok=false;
        }
        if(password ==""){
            $("#password_span").html("密码不能为空！");
            ok=false;
        }
        if (ok==true){
            $.ajax({
                url:basePath+"user/login.do",
                data:{"name":name,"password":password},
                dataType:"json",
                type:"post",
                success:function (result) {
                    if (result.status==0){
                        alert("登陆成功！")
                        var user=result.data;
                        addCookie("uid",result.data.cn_user_id,2);
                        addCookie("uname",result.data.cn_user_name,2);
                        window.location.href="edit.html";
                    }if (result.status==1){
                        $("#count_span").html(result.msg);
                    }if (result.status==2){
                        $("#password_span").html(result.msg);
                    }
                },
                error:function () {
                    alert("什么都没有请求到.....请求可能被狗吃了..")
                },
            })
        }
    })
    /*登录结束*/

    /*注册开始*/
    $("#regist_button").on('click',function () {
       /*获取请求参数*/
        var name=$("#regist_username").val().trim();
        var nick=$("#nickname").val().trim();
        var password=$("#regist_password").val().trim();
        var f_password=$("#final_password").val().trim();
        $("#warning_1 span").html("");
        $("#warning_2 span").html("");
        $("#warning_3 span").html("");
        /*检查请求参数格式*/
        var ok=true;
        if (name==""){
            ok=false;
            $("#warning_1").show();
            $("#warning_1 span").html("请输入用户名！");
        }
        if (password==""){
            ok=false;
            $("#warning_2").show();
            $("#warning_2 span").html("请输入密码！");
        } else if(password<6){
            ok=false;
            $("#warning_2").show();
            $("#warning_2 span").html("密码不能小于六位！");
        }if (f_password==""){
            ok=false;
            $("#warning_3").show();
            $("#warning_3 span").html("确认密码不能为空！");
        }else if (f_password!=password){
            ok=false;
            $("#warning_3").show();
            $("#warning_3 span").html("两次密码不一致！");
        }
        if (ok){
            $.ajax({
                url:basePath+"user/register.do",
                type: "post",
                dataType: "json",
                data: {"name":name,"password":password,"nick":nick},
                success:function (result) {
                    if (result.status==0){
                        alert("注册成功！")
                        $("#back").click();
                    }
                    if (result.status==1){
                        $("#warning_1").show();
                        $("#warning_1 span").html(result.msg);
                    }
                },
                error:function () {
                    alert("什么都没有请求到.....请求可能被狗吃了..")
                }
            })
            /*注册结束*/
        }



    })





})