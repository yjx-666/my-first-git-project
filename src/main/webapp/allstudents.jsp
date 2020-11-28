<%--
  Created by IntelliJ IDEA.
  User: y
  Date: 2020/11/21
  Time: 8:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>学生信息</title>
    <link rel="stylesheet" href="layui/css/layui.css" media="all">
<%--    前面不用加斜杠，因为该jsp与layui属于同级目录之下--%>
    <script src="layui/layui.js"></script>
    <%--引入所有模块--%>
</head>
<body>

<table id="demo" lay-filter="test">
</table>

<script>
<%--    table就是其中一个内置模块--%>
    layui.use('table', function(){
        //获得一个table对象
        var table = layui.table;//第一个实例
        const $ = layui.$;

        //render渲染table容器,var和const区别不大
        var tableIns = table.render({
            //elem元素标签
            elem: '#demo',//指定原始表格元素选择器（推荐id选择器）
            height: 400//容器高度
            ,url: '/all/students'//数据接口
            ,response:{
                statusCode:200
            }
            ,request: {
                limitName: 'nums' //每页数据量的参数名，默认：limit
            }
            ,toolbar: '#toolbarDemo'
            ,cols: [[ //表头
                {type:'checkbox'}
                //{type:'radio'}单选框
                ,{field: 'stuNo', title: '学号', width:120, sort: true}
                ,{field: 'stuName', title: '姓名', width:80, sort: true}
                ,{field: 'stuSex', title: '性别', width:80, sort: true}
                ,{field: 'stuClass', title: '班级', width:100, sort: true, edit: 'text'}
                ,{field: 'stuAddress', title: '地址', width: 150, edit: 'text'}
                ,{field: 'stuPhoneNumber', title: '电话', width: 177, sort: true, edit: 'text'}
                ,{fixed: 'right',title:'操作', width:250, align:'center', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
            ]]
            ,page: true //开启分页
        });
        //监听工具条
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

            if(layEvent === 'detail'){ //查看
                layer.open({
                    type: 2,
                    content: 'detail.jsp',
                    area: ['700px', '500px'],
                    success: function(layero, index){
                        console.log(layero, index);
                        // 数据绑定,从json获取相应属性的数据，即对应实体的属性名
                        const body = layer.getChildFrame('body', index)
                        body.find('#stu_no').val(data.stuNo);//注意#号绑定input的id值(唯一的)
                        body.find('#stu_name').val(data.stuName);
                        body.find('#stu_sex').val(data.stuSex);
                        body.find('#stu_class').val(data.stuClass);
                        body.find('#stu_address').val(data.stuAddress);
                        body.find('#stu_phone').val(data.stuPhoneNumber);
                    }
                });
            } else if(layEvent === 'del'){ //删除
                layer.confirm('真的删除行么', function(index){
                 $.ajax({
                     type:'post',
                     url:'/stu/delete',
                     data:{no:data.stuNo},
                     success:function (res) {
                         if(res.code === 200){
                             layer.msg("操作成功");
                             tableIns.reload();//局部刷新表格内容
                         }else{
                             layer.msg("操作失败");
                         }
                     },
                     error:function (error) {
                         layer.msg(error);
                     }
                 });
                    layer.close(index);

                });
            } else if(layEvent === 'edit'){ //编辑
                layer.open({
                    type: 2,
                    content: 'editStudent.jsp',
                    area: ['700px', '500px'],
                    success: function(layero, index){
                        console.log(layero, index);
                        // 数据绑定,从json获取相应属性的数据，即对应实体的属性名
                        const body = layer.getChildFrame('body', index)
                        body.find('#stu_no').val(data.stuNo);//注意#号绑定input的id值(唯一的)
                        body.find('#stu_name').val(data.stuName);
                        body.find('#stu_sex').val(data.stuSex);
                        body.find('#stu_class').val(data.stuClass);
                        body.find('#stu_address').val(data.stuAddress);
                        body.find('#stu_phone').val(data.stuPhoneNumber);
                    },cancel: function(index, layero){
                        layer.close(index);
                        tableIns.reload();//局部刷新表格内容
                        return false;
                    }
                });

                //同步更新缓存对应的值
/*                obj.update({
                    username: '123'
                    ,title: 'xxx'
                });
                */


            } else if(layEvent === 'LAYTABLE_TIPS'){
                layer.alert('Hi，头部工具栏扩展的右侧图标。');
            }
        });

        //工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'getCheckData':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    break;
                case 'getCheckLength':
                    var data = checkStatus.data;
                    layer.msg('选中了：'+ data.length + ' 个');
                    break;
                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选': '未全选')
                    break;
                case "addStudent":
                    layer.open({
                        type: 2,
                        content: 'addStudent.jsp',
                        area: ['700px', '500px'],
                        cancel: function(index, layero){
                    layer.close(index);
                    tableIns.reload();//局部刷新表格内容
                    // return false;
                }

            })

                    break;
            };
        });

        //监听复选框
        table.on('checkbox(test)',function (obj) {
            console.log(obj.checked); //当前是否选中状态
            console.log(obj.data); //选中行的相关数据
            console.log(obj.type); //如果触发的是全选，则为：all，如果触发的是单选，则为：one
        });

        //监听单元格编辑
        table.on('edit(test)', function(obj){
            var value = obj.value //得到修改后的值
                ,data = obj.data //得到所在行所有键值
                ,field = obj.field; //得到被修改的字段名
            layer.msg('[stuName: '+ data.stuNo +'] ' + field + ' 字段更改为：'+ value);
        });

/*        //监听行单击事件（双击事件为：rowDouble）
        table.on('row(test)', function(obj){
            var data = obj.data;

            layer.alert(JSON.stringify(data), {
                title: '当前行数据：'
            });
        });*/
    });






</script>


<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
        <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
        <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
        <button class="layui-btn layui-btn-sm" lay-event="addStudent">添加学生信息</button>
    </div>
</script>

<script type="text/html" id="barDemo" >
    <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    <%--属性 lay-event="" 是模板的关键所在，值可随意定义。--%>
    <!-- 这里同样支持 laytpl 语法，如： -->
    {{#  if(d.auth > 2){ }}
    <a class="layui-btn layui-btn-xs" lay-event="check">审核</a>
    {{#  } }}
</script>

<script>

    //一2般直接写在一个js文件中
    layui.use(['layer', 'form'], function(){
        var layer = layui.layer
            ,form = layui.form;

        layer.msg('学生信息');
    });
</script>
</body>
</html>
