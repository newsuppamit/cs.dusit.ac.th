<%-- 
    Document   : knowledge
    Created on : May 24, 2013, 12:09:56 AM
    Author     : NewSuppamit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="images/icon.png">
        <meta http-equiv="X-UA-Compatible" content="IE=9" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ระบบจัดการเนื้อหาเว็บ</title>
        <script type="text/javascript" src="js/admin_student.js"></script>
    </head>
    <body>
        <div data-role="page">
            <jsp:include page="../jsp/header.jsp"/>
            <div data-role="content">          
                <div class="ui-grid-a column">
                    <div class="ui-block-a column">
                        <div class="headcontent"></div>
                        <div data-role="navbar" data-iconpos="left" >
                            <ul>
                                <li><a href="#" id="tab_add" data-icon="plus"  class="ui-btn-active">เพิ่มข้อมูลนักศึกษา</a></li>
                                <li><a href="#" id="tab_edit" data-icon="gear">แก้ไข/ลบข้อมูลนักศึกษา</a></li>
                            </ul>
                        </div>
                        <div id="page_add">
                            <fieldset class="ui-grid-a default">
                                <div class="ui-block-a">
                                    <div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
                                        <label for="foo" class="ui-input-text">รหัสนักศึกษา : </label> <input
                                            type="text" id="id_stu" data-mini="true" maxlength="1000" placeholder="รหัสนักศึกษา">
                                    </div>
                                </div>
                                <div class="ui-block-b">
                                    <div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
                                        <label for="foo" class="ui-input-text">ชื่อ : </label> <input
                                            type="text" id="name" data-mini="true" maxlength="1000" placeholder="ชื่อ">
                                    </div>
                                </div>
                                <div class="ui-block-a">
                                    <div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
                                        <label for="foo" class="ui-input-text">อีเมล : </label> <input
                                            type="text" id="detail" data-mini="true" maxlength="1000" placeholder="อีเมล">
                                    </div>
                                </div>
                                <div class="ui-block-b">
                                    <div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
                                        <label for="foo" class="ui-input-text">สถานะ : </label> 
                                        <fieldset data-role="controlgroup" data-type="horizontal" data-mini="true">
                                            <input type="radio" name="status" id="1"  value="1" checked/>
                                            <label for="1">แสดง</label>
                                            <input type="radio" name="status" id="0" value="0" />
                                            <label for="0">ซ่อน</label>
                                        </fieldset>
                                    </div>
                                </div>
                            </fieldset>

                            <fieldset class="ui-grid-d">
                                <div class="ui-block-a"></div>
                                <div class="ui-block-b">
                                    <input type="button" id="submit_add" value="เพิ่ม" data-mini="true" data-icon="plus">
                                </div>
                                <div class="ui-block-c"></div>
                                <div class="ui-block-d">
                                    <input type="button" class="reset" value="ยกเลิก" data-mini="true" data-icon="refresh">
                                </div>
                                <div class="ui-block-e"></div>
                            </fieldset>
                        </div>
                        <div id="page_edit">
                            <table id="showAll">
                                <thead>
                                    <tr>
                                        <th width="120">รหัสนักศึกษา</th>
                                        <th width="220">ชื่อ</th>
                                        <th width="220">อีเมล</th>
                                        <th width="80">สถานะ</th>
                                    </tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                            <div class="comment">*คลิกที่ข้อมูลในตารางเพื่อแก้ไข<br />**ดับเบิลคลิกที่ข้อมูลในตารางเพื่อลบ</div>

                            <fieldset class="ui-grid-a default">
                                <div class="ui-block-a">
                                    <div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
                                        <label for="foo" class="ui-input-text">รหัสนักศึกษา : </label> <input
                                            type="text" id="_id_stu" readonly data-mini="true" maxlength="1000" placeholder="รหัสนักศึกษา">
                                    </div>
                                </div>
                                <div class="ui-block-b">
                                    <div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
                                        <label for="foo" class="ui-input-text">ชื่อ : </label> <input
                                            type="text" id="_name" data-mini="true" maxlength="1000" placeholder="ชื่อ">
                                    </div>
                                </div>
                                <div class="ui-block-a">
                                    <div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
                                        <label for="foo" class="ui-input-text">อีเมล : </label> <input
                                            type="text" id="_detail" data-mini="true" maxlength="1000" placeholder="อีเมล">
                                    </div>
                                </div>
                                <div class="ui-block-b">
                                    <div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
                                        <label for="foo" class="ui-input-text">สถานะ : </label> 
                                        <fieldset data-role="controlgroup" data-type="horizontal" data-mini="true">
                                            <input type="radio" name="_status" id="_status-1"  value="1"/>
                                            <label for="_status-1">แสดง</label>
                                            <input type="radio" name="_status" id="_status-0" value="0" />
                                            <label for="_status-0">ซ่อน</label>
                                        </fieldset>
                                    </div>
                                </div>
                            </fieldset>
                            <fieldset class="ui-grid-d">
                                <div class="ui-block-a"></div>
                                <div class="ui-block-b">
                                    <input type="button" id="submit_edit" value="แก้ไข" data-mini="true" data-icon="check">
                                </div>
                                <div class="ui-block-c"></div>
                                <div class="ui-block-d">
                                    <input type="button" class="reset" value="ยกเลิก" data-mini="true" data-icon="refresh">
                                </div>
                                <div class="ui-block-e"></div>
                            </fieldset>
                        </div>
                    </div>
                    <div class="ui-block-b column">
                        <jsp:include page="../jsp/columnRight.jsp" />
                    </div>
                </div>
            </div>
            <div data-role="footer" data-theme="a"><jsp:include page="../jsp/footer.jsp" /></div>
        </div><br/>
    </body>
</html>
