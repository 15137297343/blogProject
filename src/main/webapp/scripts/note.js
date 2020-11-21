$(function () {
    /*获取笔记列表*/
    $("#book_ul").on("click", "li", function () {
        $("#pc_part_6").hide();//搜索分享笔记列表
        $("#pc_part_2").show();//笔记列表
        $("#pc_part_4").hide();//回收站列表
        $("#pc_part_7").hide();
        $("#pc_part_8").hide();
        //1.获取请求参数
        var bookId = $(this).data("bookId");
        //2.对请求参数进行格式校验
        $("#book_ul a").removeClass("checked");
        //添加笔记本列表的选中样式
        $(this).find("a").addClass("checked");
        //3.发送Ajax请求
        $("#note_ul li").remove();
        $.ajax({
            url: basePath + "note/notes.do",
            type: "post",
            dataType: "json",
            data: {"bookId": bookId},
            success: function (result) {
                if (result.status == 0) {
                    var notes = result.data;
                    for (var i = 0; i < notes.length; i++) {
                        var noteTitle = notes[i].cn_note_title;
                        var noteId = notes[i].cn_note_id;
                        getNotes(noteTitle, noteId);

                        if(notes[i].cn_note_type_id == '2'){
                            var icon = '<i class="fa fa-sitemap"></i>';
                            //获取新添加的li元素
                            var note = $("#note_ul li:last");
                            note.find(".btn_slide_down").before(icon);
                        }

                    }
                }
            },
            error: function () {
                alert("什么都没有请求到.....请求可能被狗吃了..")

            }
        })

    });

    function getNotes(noteTitle, noteId) {
        /*   <li class="online">
               <a  class="checked">
                   <i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> 使用Java操作符<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>
               </a>
               <div class="note_menu" tabindex='-1'>
                   <dl>
                       <dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>
                       <dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>
                       <dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>
                   </dl>
               </div>
           </li>*/
        var li = '<li class="online">\n' +
            '                <a>\n' +
            '                    <i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>' + noteTitle + '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>\n' +
            '                </a>\n' +
            '                <div class="note_menu" tabindex="-1">\n' +
            '                    <dl>\n' +
            '                        <dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>\n' +
            '                        <dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>\n' +
            '                        <dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>\n' +
            '                    </dl>\n' +
            '                </div>\n' +
            '            </li>';
        var lis = $(li);
        lis.data("noteId", noteId);
        $("#note_ul").append(lis);
    }

    /*获取笔记信息*/
    $("#note_ul").on("click", "li", function () {
        //切换显示
        $("#pc_part_3").show();
        $("#pc_part_5").hide();
        //1.获取请求参数
        var noteId = $(this).data("noteId");
        //2.对请求参数进行格式校验
        $("#note_ul a").removeClass("checked");
        $(this).find("a").addClass("checked");
        //3.发送Ajax请求
        $.ajax({
            url: basePath + "note/noteInfo.do",
            type: "post",
            dataType: "json",
            data: {"noteId": noteId},
            success: function (result) {
                if (result.status == 0) {
                    var title = result.data.cn_note_title;
                    var body = result.data.cn_note_body;
                    $("#input_note_title").val(title);
                    um.setContent(body);
                }
            },
            error: function () {
                alert("什么都没有请求到.....请求可能被狗吃了..")
            }
        })
    });

    /*修改笔记信息*/
    $("#save_note").on("click", function () {
        //1.获取请求参数
        //获取要修改笔记的noteId，根据选中状态来获取
        var note = $("#note_ul a.checked").parent();
        var noteId = note.data("noteId");
        //获取note标题
        var title = $("#input_note_title").val();
        //获取富文本框内容
        var body = um.getContent();
        $("#note_title_span").html("");
        //2.请求参数进行格式校验
        //在没有选择笔记的情况下点击保存笔记必须给予提示
        if (note.length == 0) {
            alert("请选择要保存的笔记");

        } else if (title == "") {
            $("#note_title_span").html("<b style='color: red'>标题不能为空</b>");
        } else {
            //3.发送Ajax请求
            $.ajax({
                url: basePath + "note/updateNote.do",
                type: "post",
                dataType: "json",
                data: {"noteId": noteId, "noteTitle": title, "noteBody": body},
                success: function (result) {
                    if (result.status == 0) {
                        note.find("a").html(" <i class=\"fa fa-file-text-o\" title=\"online\" rel=\"tooltip-bottom\"></i>" + title + "<button type=\"button\" class=\"btn btn-default btn-xs btn_position btn_slide_down\"><i class=\"fa fa-chevron-down\"></i></button>");
                        alert(result.msg);
                    } else {
                        alert(result.msg);
                    }
                },
                error: function () {
                    alert("什么都没请求到....请求可能被狗吃了")
                }


            })
        }
    });

    /*添加笔记*/
    //点击加号弹框
    $("#add_note").on("click", alertAddNoteWindow);
    //点击创建
    $("#can").on("click", "#sure_addnote", function () {
        //1.获取请求参数
        //获取userId
        var userId = getCookie("uid");
        //获取bookId
        //选中状态的父元素
        var bookId = $("#book_ul a.checked").parent().data("bookId");
        //获取noteTitle
        var noteTitle = $("#input_note").val().trim();
        $("#note_span").html("");
        //2.对请求参数进行格式校验
        var ok = true;
        if (userId == null) {
            ok = false;
            window.location.href = "log_in.html";
        } else if (noteTitle == "") {
            $("#note_span").html("<font style='color: red'>笔记名称不能为空</font>");
        } else if (ok) {
            //发送ajax请求
            $.ajax({
                url: basePath + "note/addNote.do",
                type: "post",
                dataType: "json",
                data: {"userId": userId, "bookId": bookId, "noteTitle": noteTitle},
                success: function (result) {
                    if (result.status == 0) {
                        //如果成功，先关闭弹窗
                        closeAlertWindow();
                        //获取noteId
                        var noteId = result.data;
                        //创建一个note li添加到note列表
                        getNotes(noteTitle, noteId);
                        alert(result.msg);
                    }

                    if (result != 0) {
                        alert(result.msg);
                    }
                },
                error: function () {
                    alert("什么都没有请求到....请求可能被狗吃了")
                }
            })
        }

    });

    /*删除笔记操作（实际上进行的是修改操作）*/
    //1.点击得到下拉菜单
    $("#note_ul").on("click", ".btn_slide_down", function (e) {
        //先隐藏所有菜单
        $("#note_ul div").hide();
        //点击显示菜单
        $(this).parent().next().slideDown(500);
        $("#note_ul a").removeClass("checked");
        $(this).parent().addClass("checked");
        e.stopPropagation();
    });
    //点击页面隐藏当前打开的菜单
    $("body").click(function () {
        $("#note_ul div").hide();
    });
    //点击删除按钮弹出选择窗口
    $("#note_ul").on("click", ".btn_delete", function () {
        alertDeleteNoteWindow();
    });

    //点击对话框的删除按钮时进行的操作
    $("#can").on("click", "#sure_deletenote", function () {
        //1.获取请求参数
        var note = $("#note_ul a.checked").parent();
        var noteId = note.data("noteId");
        //2.对请求参数进行格式校验
        //3.发送ajax请求
        $.ajax({
            url: basePath + "note/deleteNote.do",
            type: "post",
            dataType: "json",
            data: {"noteId": noteId},
            success: function (result) {
                if (result.status == 0) {
                    note.remove();
                    $("#input_note_title").val("");
                    um.setContent("");
                    alert(result.msg);
                } else {
                    alert(result.msg);
                }
            },
            error: function () {
                alert("什么都没有请求到.....请求可能被狗吃了")
            }

        })


    });


    /*移动笔记操作*/
    //1.点击弹出移动对话框
    $("#note_ul").on("click", ".btn_move", function (){
        alertMoveNoteWindow();
    })
    //2.点击确认按钮
    $("#can").on("click","#sure_movenote",function () {
        //获取请求参数
           //获取当前笔记的noteId
     var note=$("#note_ul a.checked").parent();
     var noteId=note.data("noteId");
           //再获取要转移到的笔记本id
        var bookId=$("#moveSelect").val();
        //请求参数格式校验
        //发送ajax请求
       $.ajax({
           url:basePath+"note/moveNote.do",
           type:"post",
           dataType:"json",
           data:{"noteId":noteId,"bookId":bookId},
           success:function (result) {
               note.remove();
               alert(result.msg);
           },
            error:function () {
                alert("什么都没有请求到.....请求可能藏起来了")
            }
       })

    })

    /*分享笔记操作*/
    //分享操作就是将要分享的笔记添加到分享列表里，然后把笔记的type_id改为2
    //点击分享图标时，需要在笔记标题后面添加分享图标，然后弹窗提示分享成功
    $("#note_ul").on("click", ".btn_share", function (){
        //1.获取请求参数
        //获取当前选中的noteId
        var note=$("#note_ul a.checked").parent();
        var noteId=note.data("noteId");
        //2.请求参数格式校验
        //3.发送ajax请求
       $.ajax({
           url:basePath+"note/shareNote.do",
           type:"post",
           dataType:"json",
           data:{"noteId":noteId},
           success:function (result) {
                alert(result.msg);
                var icon='<i class="fa fa-sitemap"></i>';
                note.find(".btn_slide_down").before(icon);
           },
           error:function () {
               alert("什么都没有请求到......")
           }
       })

    })


    var keyword="";
    var page=1;
    /* 搜索分享笔记操作*/
    //点击回车按钮发送请求
    $("#search_note").keydown(function (e) {
      var code=e.keyCode;
        if (code==13){
        //发送请求之前先清空列表
        $("#pc_part_6 ul").empty();
        $("#pc_part_6").show();
        $("#pc_part_2").hide();
        $("#pc_part_4").hide();
        $("#pc_part_7").hide();
        $("#pc_part_8").hide();
            //获取请求参数
             keyword=$("#search_note").val().trim();
             page=1;
            //参数格式校验
            //发送ajax请求
            $.ajax({
                url:basePath+"search/searchShareNote.do",
                type:"post",
                data:{"keyword":keyword,"page":page},
                dataType:"json",
                success:function (result) {
                    if (result.status==0){
                        var list=result.data;
                        for (var i=0;i<list.length;i++){
                            var title=list[i].cn_share_title;
                            var shareId=list[i].cn_share_id;
                            var li = '<li class="online">\n' +
                                '                <a>\n' +
                                '                    <i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>' + title +
                                '                </a>\n' +
                                '     </li>';
                            var lis = $(li);
                            lis.data("shareId", shareId);
                            $("#pc_part_6 ul").append(lis);
                        }


                    }
                },
                error:function () {
                    alert("什么都没有请求到....")
                }
            })
        }



    });

    /*点击更多的时候加载更多*/
    $("#more_note").on("click",function () {
            page++;
            $.ajax({
                url:basePath+"search/searchShareNote.do",
                type:"post",
                data:{"keyword":keyword,"page":page},
                dataType:"json",
                success:function (result) {
                    if (result.status==0){
                        var list=result.data;
                        for (var i=0;i<list.length;i++){
                            var title=list[i].cn_share_title;
                            var shareId=list[i].cn_share_id;
                            var li = '<li class="online">\n' +
                                '                <a>\n' +
                                '                    <i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>' + title +
                                '                </a>\n' +
                                '     </li>';
                            var lis = $(li);
                            lis.data("shareId", shareId);
                            $("#pc_part_6 ul").append(lis);
                        }


                    }
                },
                error:function () {
                    alert("什么都没有请求到....")
                }
            })
        })

    /*预览搜索分享笔记*/
    $("#pc_part_6 ul").on("click","li",function () {
        //切换显示
        $("#pc_part_3").hide();
        $("#pc_part_5").show();
        $("#pc_part_6 ul a").removeClass("checked");
        $(this).find("a").addClass("checked");
        //获取请求参数
        var shareId = $(this).data("shareId");
        //发送ajax请求
        $.ajax({
            url:basePath+"note/loadShareNote.do",
            type:"post",
            dataType:"json",
            data:{"shareId":shareId},
            success:function (result) {
                var title=result.data.cn_share_title;
                var body=result.data.cn_share_body;
                $("#noput_note_title").html(title);
                $("#noput_note_title").next().html(body);
            },
            error:function (){
                alert("分享笔记预览信息找不到了 》》》》》")
            }

        })


    });




})