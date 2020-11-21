$(function () {
    /*加载页面后自动查询笔记本列表*/
    /*获取请求参数*/
    var userID=getCookie("uid");
    if (userID==null){
        window.location.href="log_in.html";
    }
    $.ajax({
        url:basePath+"book/books.do",
        type:"post",
        dataType:"json",
        data:{"userId":userID},
        success:function (result) {
            /*获取状态*/
            var status=result.status;
            if (status==1){
                window.location.href="log_in.html";
            }
            if (status===0){
                /*获取结果集*/
                for (var i=0;i<result.data.length;i++){
                    var bookName=result.data[i].cn_notebook_name;
                    var bookId=result.data[i].cn_notebook_id;
                        getBooks(bookName,bookId);
                }
            }
        },
        error:function () {
            alert("什么都没有请求到.....请求可能被狗吃了..")
        }
    })
    function getBooks(bookName,bookId) {
 /*       <li class="online">
            <a  class="checked">
                <i class="fa fa-book" title="online" rel="tooltip-bottom">默认笔记本</i>
            </a>
        </li>
  */
        var lis='<li class="online">\n' +
            '            <a>\n' +
            '                <i class="fa fa-book" title="online" rel="tooltip-bottom">'+bookName+'</i>          \n' +
            '            </a>\n' +
            '        </li>';
        /*把bookId绑定到li上*/
        var li=$(lis);
        li.data("bookId",bookId);
        $("#book_ul").append(li);
    }


    /*添加笔记本列表*/
    //1.当点击加号时弹出添加对话框
    $("#add_notebook").on("click",alertAddBookWindow);
    //2.点击创建发送请求添加笔记本
    $("#can").on("click","#sure_addbook",function () {
        /*获取请求参数*/
        var userId=getCookie("uid");
        var bookName=$("#input_notebook").val().trim();
        /*2.对请求参数格式校验*/
        $("#notebook_span").html("");
        if (bookName==""){
            $("#notebook_span").html("<b style='color: red'>笔记本名称不能为空</b>");
        }
        else{
            $.ajax({
                url:basePath+"book/addBook.do",
                type: "post",
                dataType: "json",
                data: {"cn_user_id":userId,"cn_notebook_name":bookName},
                success:function (result) {
                   if (result.status==0){
                       closeAlertWindow();
                      /*获取到bookId*/
                       var bookId=result.data.cn_notebook_id;
                       getBooks(bookName,bookId);
                       alert(result.msg)
                   }else {
                       alert(result.msg);
                   }
                },
                error:function () {
                    alert("什么都没有请求到....请求可能被狗吃了");
                }
            })
        }

    })


})