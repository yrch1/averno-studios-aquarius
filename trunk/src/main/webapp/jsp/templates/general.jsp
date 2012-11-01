<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="org.apache.struts.Globals"%>
<%@page import="java.util.Locale"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>



<!DOCTYPE html
    PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang='<%=(Locale) session.getAttribute(Globals.LOCALE_KEY)%>' lang="<%=(Locale) session.getAttribute(Globals.LOCALE_KEY)%>">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
        <tiles:insert attribute="PHXJS"/>
        <tiles:insert attribute="PHXCSS"/>
    </head>
    <body>
        <tiles:insert attribute="PHXHEADER"/>
        <tiles:insert attribute="PHXBODY"/>
        <tiles:insert attribute="PHXFOOTER"/>
    </body>
</html>