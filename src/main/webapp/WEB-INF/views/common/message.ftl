<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="keywords" content="雨果翻译">
    <meta name="description" content="雨果翻译">

    <title>雨果翻译</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css" media="all">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/font-awesome.min.css" media="all">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/default.css" media="all">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/components.css" media="all">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/base.css" media="all">
    <!-- js -->
    <script src="${ctx}/js/jquery.min.js"></script>
    <script src="${ctx}/lib/bootstrap/bootstrap.min.js"></script>
    <script src="${ctx}/js/components.js"></script>
    <script src="${ctx}/js/common.js"></script>
    <script src="${ctx}/lib/layer/layer.js"></script>
</head>

<body class="c-layout-header-fixed c-layout-header-mobile-fixed c-layout-header-topbar">
<script>
    <#if success>
        alert('${message!""}');
        location.href='${ctx}/login';
    <#else>
        layer.alert('${message!""}');
    </#if>

</script>

</body>
</html>