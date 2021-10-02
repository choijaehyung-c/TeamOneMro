<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	 <script src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>
	 <script src="${pageContext.request.contextPath}/resources/js/js.js"></script>
	 <script src="${pageContext.request.contextPath}/resources/js/delivery.js"></script>
    <link href="resources/css/access.css" rel="stylesheet" type="text/css">
</head>
<body>

	<body class="bg-gradient-primary">

    <div class="container">

        <!-- Outer Row -->
        <div class="row justify-content-center">

            <div class="col-xl-10 col-lg-12 col-md-9">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                          
                            <div class="col-lg-6">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-4"><img class="main_img" src="resources/img/logo.png"/></h1>
                                    </div>
                                    <form class="user">
                                    	
                                    	
                                        <div class="form-group">
                                            <input type="text" name="ah_code" class="form-control form-control-user"
                                                id="exampleInputEmail" aria-describedby="emailHelp"
                                                placeholder="Access Code">
                                        </div>
                                        <div class="form-group">
                                            <input type="password" name ="ah_pwd" class="form-control form-control-user"
                                                id="exampleInputPassword" placeholder="Password">
                                        </div>
                                        <div class="form-group">
                                            
                                        </div>
                                        <div onclick="sendDeliveryAccessInfo()" class="btn btn-primary btn-user btn-block">
                                            Login
                                        </div>
                                        <hr>

                                    </form>
                                    <div class="text-center">
                                        <a class="small" href="forgot-password.html">Forgot Password?</a>
                                    </div>
                                    <div class="text-center">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>

    </div>
    
    <footer>
    	<p>
    		인천 미추홀구 매소홀로 488번길 6-32 태승빌딩 5층 | 사업자등록번호: 202-1081-900 | H.P : 032-876-3332
    	</p>
    	
    	<p class="copyright">
    	<span class="font_bold">(주)MRONE</span>
    	Copyright ⓒ 
    	<span class="font_blk">BMIT.</span> All rights reserved.
    	</p>
    </footer>
</body>
</html>