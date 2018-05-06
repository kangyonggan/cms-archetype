/**
 * 批量删除
 *
 * @param self
 */
function deleteBatch(self) {
    var rows = $("#table").bootstrapTable("getSelections");
    if (rows.length == 0) {
        Message.warning("至少选择一行");
        return;
    }

    $.messager.confirm("提示", "确定删除字典吗?", function () {
        var ids = [];
        for (var i in rows) {
            ids.push(rows[i].id);
        }
        $.get(ctx + "/dashboard/system/dict/content/deleted", {
            ids: ids.join(",")
        }, function (data, status) {
            if (status == "success") {
                data = eval('(' + data + ')');
                if (data.respCo == "0000") {
                    Message.success(data.respMsg);
                    var params = $("#form").serializeForm();
                    $('#table').bootstrapTable("refresh", {query: params});
                } else {
                    Message.error(data.respMsg);
                }
            } else {
                Message.error("服务器内部错误，请稍后再试。");
            }
        })
    });
}