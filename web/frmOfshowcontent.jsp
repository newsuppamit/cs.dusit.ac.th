<%-- 
    Document   : showcontent
    Created on : May 20, 2013, 11:24:17 PM
    Author     : Note
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" type="image/x-icon" href="">
        <meta http-equiv="X-UA-Compatible" content="IE=8" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>หลักสูตรวิทยาการคอมพิวเตอร์ มหาวิทยาลัยราชภัฏสวนดุสิต ศูนย์การเรียนรางน้ำ</title>
        <script type="text/javascript" src="js/showcontent.js"></script>
    </head>
    <body>
        <div data-role="page">
            <jsp:include page="jsp/header.jsp"/>
            <div data-role="content">          
                <div class="colshcontent">
                        <div class="frmcontent">
                            <div class="headcontent"></div>
                            <div class="showcontent"></div>
                        </div>
                </div>
            </div>
            <jsp:include page="jsp/footer.jsp" />
        </div>
        <br/>
    </body>
</html>