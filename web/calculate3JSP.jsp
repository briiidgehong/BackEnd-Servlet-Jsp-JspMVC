<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>myCalculator</title>
    <style>
        input{
            width:50px;
            height:50px;
        }
        .output{
            height: 50px;
            font-size: 24px;
            font-weight: bold;
            text-align: right;
            background-color: darkgray;
            padding: 0px 5px;

        }
    </style>
</head>
<body>
    <div>
        <form action="calculateServlet3" method="post">
            <table>
                <tr>
                    <td class="output" colspan="4"></td>
                </tr>
                <tr>
                    <td><input type="submit" name="operator" value="CE"/></td>
                    <td><input type="submit" name="operator" value="C"/></td>
                    <td><input type="submit" name="operator" value="BS"/></td>
                    <td><input type="submit" name="operator" value="/"/></td>
                </tr>
                <tr>
                    <td><input type="submit" name="value" value="7"/></td>
                    <td><input type="submit" name="value" value="8"/></td>
                    <td><input type="submit" name="value" value="9"/></td>
                    <td><input type="submit" name="operator" value="*"/></td>
                </tr>
                <tr>
                    <td><input type="submit" name="value" value="4"/></td>
                    <td><input type="submit" name="value" value="5"/></td>
                    <td><input type="submit" name="value" value="6"/></td>
                    <td><input type="submit" name="operator" value="-"/></td>
                </tr>
                <tr>
                    <td><input type="submit" name="value" value="1"/></td>
                    <td><input type="submit" name="value" value="2"/></td>
                    <td><input type="submit" name="value" value="3"/></td>
                    <td><input type="submit" name="operator" value="+"/></td>
                </tr>
                <tr>
                    <td><input type="submit" name="value" value="0"/></td>
                    <td><input type="submit" name="operator" value="."/></td>
                    <td colspan="2"><input type="submit" name="operator" value="="/></td>
                </tr>
            </table>
        </form>
    </div>

</body>
</html>