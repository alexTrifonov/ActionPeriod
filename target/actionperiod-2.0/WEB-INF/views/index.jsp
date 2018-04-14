
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>work_period</title>
    <script type="text/javascript" src="resources/js/jquery-3.3.1.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#finish').attr('disabled', true);
            $('#stop').attr('disabled', true);
            $('#start').on('click', function () {
                var now = new Date();
                $.ajax({
                    type : 'GET',
                    dataType : 'json',
                    url : "./startTime",
                    data : ({timeStart : now.getTime()}),
                    contentType : 'application/json'
                });
                $('#start').attr('disabled', true);
                $('#stop').removeAttr('disabled');
                $('#finish').removeAttr('disabled');
            });
            $('#stop').on('click', function () {
                var now = new Date();
                $.ajax({
                    type : 'GET',
                    dataType : 'json',
                    url : "./stopTime",
                    data : ({timeStop : now.getTime()}),
                    contentType : 'application/json'
                });
                $('#start').removeAttr('disabled');
                $('#stop').attr('disabled', true);
            });
            $('#finish').on('click', function () {
                var now = new Date();
                $.ajax({
                    type : 'GET',
                    dataType : 'json',
                    url : "./finishTime",
                    data : ({timeFinish : now.getTime()}),
                    contentType : 'application/json',
                    complete : function (data) {
                        var result = JSON.parse(data.responseText);
                        var workTime = result.fullTime;
                        $('#timeResult')[0].innerHTML = workTime;
                    }
                });
                $('#start').removeAttr('disabled');
                $('#finish').attr('disabled', true);
                $('#stop').attr('disabled', true);
            });
        })
    </script>
</head>
<body>
    <div align="center">
        <input id="start" type="button" value="Пришел">
        <input id="stop" type="button" value="Ушел">
        <input id="finish" type="button" value="Отработал">
        <label id="timeResult"></label>
        <br/>
        <br/>
        <form action="./logout" method="post">
            <input type="submit" value="Выйти"/>
        </form>
    </div>
</body>
</html>
