<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Document</title>
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Open+Sans&family=Roboto:wght@100;400;500&display=swap"
            rel="stylesheet"
        />
        <link rel="stylesheet" href="<%= request.getContextPath()%>/template/public/css/contact.css">
    </head>
    <body>
        <div class="container">
            <h1>Thank you!</h1>
            <div class="sub-container">
                <img
                    src="https://www.iconfreepik.com/wp-content/uploads/2021/04/Mail-Icon-Png-Color.png"
                    alt=""
                />
                <div class="text">
                    <p class="text-noti">Bạn đã gửi liên hệ thành công!</p>
                    <p class="text-noti">VinaEnter sẽ sớm trả lời bạn</p>
                    <p class="dash-line">- - - - -</p>
                    <a class="click-noti" href="<%=request.getContextPath()%>/Trang-chu">Click vào đây để trở về trang chủ</a>
                </div>
            </div>
        </div>
    </body>
</html>
