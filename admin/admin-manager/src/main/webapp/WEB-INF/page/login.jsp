<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width" />
    <title>企业信息化平台管理系统</title>
    <%@include file="common/common.jsp"%>
    <link href="resources/css/admin/login.css?r=${jsVersion }" rel="stylesheet" type="text/css" />
    <script src="resources/js/admin/login.js?r=${jsVersion }" type="text/javascript"></script>
</head>
<body>
 <table width="100%" height="100%" align="center" valign="middle" border="0" style="height: 100%">
    <tbody>
        <tr>
            <td align="center" valign="middle">
            
            	<!-- 登录主页面 -->
			    <table height="413" cellspacing="0" cellpadding="0"  width="612" align="center" border="0">
			        <tbody>
			            <tr>
			                <td valign="top" width="600" background="resources/css/themes/Default/login_content2.png" height="412">
			                    <div align="center">
			                        <table width="100%" height="392" border="0" cellpadding="0" cellspacing="0">
			                            <tbody>
			                                <tr>
			                                    <td colspan="2" height="181">
			                                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
			                                            <tr>
			                                                <td>&nbsp;
			                                                    
			                                                </td>
			                                                <td>&nbsp;
			                                                    
			                                                </td>
			                                            </tr>
			                                            <tr>
			                                                <td>&nbsp;
			                                                    
			                                                </td>
			                                                <td>
			                                                    <div align="right" style="margin-right: 20px;">
			                                                        <p>&nbsp;</p>
			                                                        <p>&nbsp;</p>
			                                                        <p>
			                                                            <strong><font color="#990000">
			                                                                <br>版本号: 2016版</font>&nbsp;&nbsp;
			                                                                <font color='yellow'>（深圳市芯芯之火有限公司）</font></strong>&nbsp;&nbsp;
			                                                        </p>
			                                                    </div>
			                                                </td>
			                                            </tr>
			                                        </table>
			                                    </td>
			                                </tr>
			                                <tr>
			                                    <td colspan="2" align="center" height="30">&nbsp;
			                                    </td>
			                                </tr>
			                                <tr>
			                                    <td width="38%" height="180">
			                                        <br>
			                                        <br>
			                                        <br>
			                                        <div align="center"></div>
			                                    </td>
			                                    <td width="62%">
			                                    	<form id="loginForm" method="post" novalidate="novalidate">
				                                        <table cellspacing="0" cellpadding="0" width="100%" border="0">
				                                            <tbody>
				                                                <tr>
				                                                    <td>&nbsp;</td>
				                                                    <td width="4" class="ssa">&nbsp;</td>
				                                                    <td width="264" height="35" align="left">
				                                                        <input class="easyui-validatebox" type="text" name="userName" data-options="required:true,validType:'length[3,50]'" style="width: 165px; height: 21px"/>
				                                                    </td>
				                                                </tr>
				                                                <tr>
				                                                    <td class="style1"></td>
				                                                    <td width="4" class="style1"></td>
				                                                    <td width="264" class="style1"></td>
				                                                </tr>
				                                                <tr>
				                                                    <td class="style4"></td>
				                                                    <td width="4" class="style4">&nbsp;
				                                                        
				                                                    </td>
				                                                    <td width="264" align="left" class="style4">
				                                                        <input class="easyui-validatebox" type="password" name="password" data-options="required:true,validType:'length[6,50]'" style="width: 165px; height: 21px;margin-bottom: 10px;"/>
				                                                    </td>
				                                                </tr>
				                                                <tr>
				                                                    <td class="style3"></td>
				                                                    <td class="style3"></td>
				                                                    <td valign="bottom" class="style3"></td>
				                                                </tr>
				                                                <tr>
				                                                    <td colspan="3" align="center" class="style5">
				                                                        <img src="resources/css/themes/Default/btn_login2.png" id="btnLogin" style="height:30px; width:65px; margin-right:40px; margin-left:15px;cursor:pointer"/>
				                                                        <img src="resources/css/themes/Default/btn_reset2.png" id="btnRest" width="65" height="30" border="0" style="cursor: pointer">
				                                                    </td>
				                                                </tr>
				                                                    <td colspan="3" height="40px">&nbsp;</td>
				                                                </tr>
				                                            </tbody>
				                                        </table>
			                                        </form>
			                                    </td>
			                                </tr>
			                            </tbody>
			                        </table>
			                    </div>
			                </td>
			            </tr>
			        </tbody>
			    </table>
			    <!-- 登录主页面 -->
			    
      		</td>
        </tr>
    </tbody>
</table>
</body>
</html>