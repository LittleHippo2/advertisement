//添加参数
function create_label(name){
    if(name ==='添加参数'){
        var param ="<tr>\n" +
            "<td align=\"center\">\n" +
            "<input type=\"text\"   class=\"param-data\" >\n" +
            "</td>\n" +
            "<td align=\"center\">\n" +
            "<input type=\"text\"  class=\"param-data\" >\n" +
            "<input type=\"button\"  class=\"delete-header\" onclick='delete_label(this, \"删除参数\")' value=\"删除参数\">\n" +
            "</td>\n" +
            "</tr>"
        $("#param-body").append(param);

    }else if(name ==='添加请求头'){
        var header = " <tr>\n" +
            "<td align=\"center\">\n" +
            "<input type=\"text\"  class=\"param-data\">\n" +
            "</td>\n" +
            "<td align=\"center\">\n" +
            "<input type=\"text\"  class=\"param-data\">\n" +
            "<input type=\"button\"  class=\"delete-header\" onclick='delete_label(this, \"删除请求头\")' value=\"删除请求头\">\n" +
            "</td>\n" +
            "</tr>";
        $("#header-body").append(header);
    }
}

//添加请求体
var a = 0;
function create_request_body(name){
    console.log(a);
    console.log(name);
    if (a%2 == 0){
        $('.http-body').show();
        $('#create-request-body').val("取消请求体");
        a = a+1;
    }else{
        var is = confirm("隐藏请求体将会清空请求体中的所有数据，是否继续？");
        if(is){
            $('.http-body').hide();
            $('#create-request-body').val("显示请求体");
            $('#request-body').val('');
            a= a+1;
        }
    }
}

//删除参数
function delete_label(obj, name) {
    if (name === '删除参数'){
        var is = confirm("是否删除该参数？");
        if(is){
            console.log(name);
            $(obj).parent().parent().remove();
        }
    }else if(name === '删除请求头'){
        var is = confirm("是否删除该请求头？");
        if(is){
            console.log(name);
            $(obj).parent().parent().remove();
        }
    }

}

