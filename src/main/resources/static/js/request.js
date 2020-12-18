
function http_client(name) {
    console.log("方法名称："+name);

    //获取请求类型
    var http_type = $('#body-select').val();

    //获取请求地址
    var http_url = $('#body-url').val();

    if(!http_url){
        alert("请求地址不能为空");
        return;
    }
    //获取请求参数
    //先遍历每一个tr
    var arr_tr = [];
     $('#param-body').children().each(function () {
         var arr_td =[];
         //再遍历每一个td
         $(this).children().children(".param-data").each(function(){
             arr_td.push($(this).val())
         })
         arr_tr.push(arr_td);
     });
     //请求参数对象
    var param_str ='?';
    for(var i = 0; i < arr_tr.length; i++){
        for(var l = 0; l < 2; l++){
            if(l==0){
                param_str += arr_tr[i][l]
            }else if (l == 1 && arr_tr[i][l]){
                param_str += "="+arr_tr[i][l]+"&"
            }
        }
    }

    //获取请求体
    var request_body= $("#request-body").val();

    //获取请求头
    //清空tr
    var arr_tr = [];
    $('#header-body').children().each(function () {
        //清空td
        var arr_td =[];
        //再遍历每一个td
        $(this).children().children(".param-data").each(function(){
            arr_td.push($(this).val())
        })
        arr_tr.push(arr_td);
    });
    var http_header = arr_tr;

    //日志输出
    console.log("请求类型为："+http_type)
    console.log("请求地址为："+http_url)
    console.log("请求参数为："+param_str)
    console.log("请求体为："+ request_body)
    console.log("请求头为："+http_header)

    //组装数据
    $.ajax({
        url:"/http/http_client",
        type:"POST",
        data:{
            httpType: http_type,
            httpUrl: http_url,
            httpParam: param_str,
            httpBody: request_body,
            httpHeader: JSON.stringify(http_header),
        },
        success:function (res) {
            $("#response").val("");
            $("#response").val(JSON.stringify(JSON.parse(res.data), null, 4));
        }
    })
    
}