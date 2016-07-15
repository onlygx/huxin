<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%--
User: GaoXiang
Version: 1.0
--%>
<!-- BEGIN FORM-->
<div class="portlet light bg-inverse">
    <div class="portlet-title">
        <div class="caption">
            <span class="caption-subject font-red-sunglo bold uppercase">提现银行卡</span>
            <span class="caption-helper">编辑</span>
        </div>
    </div>
    <div class="portlet-body form">
        <!-- BEGIN FORM-->
        <form action="#" class="form-horizontal" id="editForm">
            <div class="form-body">

                <div class="form-group hide">
                    <label class="col-md-3 control-label">ID</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <i class="fa fa-male"></i>
                            <input type="hidden" name="id" value="${data.id}">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">字段：bank</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <i class="fa  fa-qq"></i>
                            <input type="text" class="form-control" required name="bank" value="${data.bank}" placeholder="">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">字段：number</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <i class="fa  fa-qq"></i>
                            <input type="text" class="form-control" required name="number" value="${data.number}" placeholder="">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">字段：name</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <i class="fa  fa-qq"></i>
                            <input type="text" class="form-control" required name="name" value="${data.name}" placeholder="">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">字段：home</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <i class="fa  fa-qq"></i>
                            <input type="text" class="form-control" required name="home" value="${data.home}" placeholder="">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">字段：userId</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <i class="fa  fa-qq"></i>
                            <input type="text" class="form-control" required name="userId" value="${data.userId}" placeholder="">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">字段：setTime</label>
                    <div class="col-md-4">
                        <div class="input-icon">
                            <i class="fa  fa-qq"></i>
                            <input type="text" class="form-control" required name="setTime" value="${data.setTime}" placeholder="">
                        </div>
                    </div>
                </div>

            </div>
            <div class="form-actions">
                <div class="row">
                    <div class="col-md-offset-3 col-md-5">
                        <button type="button" class="btn green" onclick="tools.edit('bankCard');">
                            <i class="fa  fa-cog fa-spin "></i>提交</button>
                        <button type="button" class="btn default" onclick="history.go(-1);">
                            <i class="fa  fa-refresh fa-spin "></i>返回</button>
                    </div>
                </div>
            </div>
        </form>
        <!-- END FORM-->
    </div>
</div>
<script>


</script>