<%@ page language="java" pageEncoding="UTF-8"%>
<!--- Inicio CSS -->
<style>
    body{ margin: 0; padding: 0; background: #eee; font-family: Arial; font-size: 12px}

    .head{
        background: #333300;
    }

    .logo{
        width: 990px;
        margin: 0 auto;
        background: url(/Aquarius/images/logo-aquarius.png) no-repeat 0 20px;
        height: 110px;
        text-indent: -9999;
    }

    .nav {background:#f2a057 url(/Aquarius/images/grad.gif) repeat-x bottom left;}

    .menu li a:hover, .menu li a.current {background:#e93; color:#fff;}
    .menu li a:hover b, .menu li a.current b {
        display:block;
        float:left;
        background:transparent url(/Aquarius/images/up_arrow.gif) no-repeat center bottom;
        cursor:pointer
    }
    .menu li {
        display:table-cell;
        margin:0; padding:0;
    }
    .menu {
        list-style:none outside none;
        margin:0 auto;
        padding:0;
        text-align:left;
        white-space:nowrap;
        width:990px;
    }
    .menu li a {
        float:left; 
        font-size:0.8em;
        height:3.1em;
        line-height:3.1em;
        letter-spacing:1px;
        padding:0 1em;
        text-decoration:none;
        color:#fff;
        background:#f2a057 url(/Aquarius/images/grad.gif) repeat-x bottom left;
        border-right:1px solid #d60;
        border-left:1px solid #fb6;
    }
    .menu li a:hover {color: #000;}
    .menu ul li ul {
        display: none;
        position: absolute;
        margin: 30px -15px;
    }
    .menu ul li:hover ul {display: block;}
    .menu ul li ul li {
        display: block;
        float: none;
        position: relative;
        color: #fff;
        line-height: 30px;
        margin: 0;
    }
    .menu ul li ul li a{color: #000; background: #ccc; border: 1px solid #ccc; width: 80px;}
    .menu ul li ul li a:hover {color: #000; background: #fff; border-bottom: 1px solid #eee;}


    .contenido{background: #fff; padding:30px 0; border-bottom: 2px solid #f2a057;}

    .content{background: #fff; width: 990px; margin: 0 auto; }

    .table { width: 990px; margin: 20px auto 40px;}
    .table a{
        background: url(/Aquarius/images/link.gif) no-repeat;
        padding-left: 20px;
        display:block;
    }
    .table .row { margin: 5px 0; border-bottom:1px solid #eee;}
    .table .row.header {border-bottom:1px solid #eee; font-weight: 700; color:#333;}
    .table .row .cell { display: inline-block; padding:5px; width: 105px; vertical-align: middle; text-align: left; }
    .table .row .cell2 { display: inline-block; padding:5px; width: 85px; vertical-align: middle; text-align: left; }
    .table .row .cell3 { display: inline-block; padding:5px; width: 150px; vertical-align: middle; text-align: left; }

    .table .row .check { display: inline-block; padding:5px; width: 30px; vertical-align: middle; text-align: left; }

    #account_info, #billing_address, #shipping_address{ 
        width: 300px;
        padding: 5px;
        margin: 5px;
        float: left;
        line-height: 1.4em;
    }
    ul, li{margin: 0; padding: 0; list-style:none}
    #account_info h2, #billing_address h2, #shipping_address h2{padding-left: 38px;color:#FF770D;font-size: 15px;}
    #account_info {background:url(/Aquarius/images/ico_account.png) no-repeat top left;}
    #billing_address {background:url(/Aquarius/images/ico_factura.png) no-repeat top left; }
    #shipping_address {background:url(/Aquarius/images/ico_envio.png) no-repeat top left; }

    h1{ font-size: 15px; font-weight: 700; color:#333300; padding-bottom: 5px; border-bottom: 1px solid #333300; }
    h2{ font-size: 13px}

    a{
        color:#6C8C37;
        text-decoration:none;
        font-weight:normal;
        font-size: 11px;
    }

    a:hover{color:#f2a057}

    .footer .span{width: 990px; margin: 0 auto;}
    .footer .span p{ color:gray; font-size: 11px;}

    .clear{clear: both}

    div.pagination {
        font-family: "Lucida Sans Unicode", "Lucida Grande", LucidaGrande, "Lucida Sans", Geneva, Verdana, sans-serif;
        padding:2px;
        margin:20px 0;
        line-height: 2.5em;
    }

    div.pagination a {
        margin: 2px;
        padding:0 4px;
        text-decoration: underline;
        color: #333300;
    }
    div.pagination a:hover{
        margin: 2px;
        background-color: #E48126;
        color: #fff;
    }
    div.pagination .current {
        padding:1px 6px;
        margin: 2px;
        background-color: #f6efcc;
        color: #6d643c;
    }
    .button{ background: #9EC048; color:#fff; border: none; cursor: pointer; font-weight: 700; display:inline; padding: 2px; margin:0 10px;}
    .button2{ cursor: pointer;padding: 0px; float: left; margin:0 20px 0 0;}

    .button:hover{ background: #E6852D; color:#fff; border: none; cursor: pointer}
    .top{ background: #ffffea; padding: 10px; margin: 0; width: 970px; height: 20px;}
        .top2{ background: #ffffea; padding: 10px; margin: 0; width: 970px; height: 150px;}

    .storeSelect{float:left}

    #pageSize{float:right}
    .link span{cursor:pointer; margin:3px 5px; color:#6C8C37;}

    .link span:hover{color:#f2a057}
    
    input{border:1px solid #ccc; margin: 0; height:20px;}
    label{margin-left: 10px; font-weight: 700;}
    .float{float:left}

</style>
<link rel="stylesheet" type="text/css" href="/Aquarius/css/style.css" media="all" />

<!-- Fin CSS -->