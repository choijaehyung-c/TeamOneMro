<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	 <script src="${pageContext.request.contextPath}/resources/js/js.js"></script>
    <link href="resources/css/access.css" rel="stylesheet" type="text/css">
    
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">
</head>
<body>

	<header>
		<div>
			<p>본 사이트는 승인된 사용자만 이용 가능합니다. 접속 권한이 없으신 사용자는 고객센터로 먼저 연락부탁드립니다.</p>
		</div>
	</header>
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
                                            <input type="text" name="ahm_code" class="form-control form-control-user"
                                                id="exampleInputEmail" aria-describedby="emailHelp"
                                                placeholder="Your Code">
                                        </div>
                                        <div class="form-group">
                                            <input type="password" name ="md_pwd" class="form-control form-control-user"
                                                id="exampleInputPassword" placeholder="Password">
                                        </div>
                                        <div class="form-group">
                                            <div class="custom-control custom-checkbox small">
                                                <input type="checkbox" class="custom-control-input" id="customCheck">
                                                <label class="custom-control-label" for="customCheck">Remember
                                                    Me</label>
                                            </div>
                                        </div>
                                        <div onclick="readyAccessMro('1','/AccessMro')" class="btn btn-primary btn-user btn-block">
                                            Login
                                        </div>
                                        <hr>

                                    </form>
                                    <hr>
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
    		<span class="font_bold">(주)MRONE</span>
    		 | 인천 미추홀구 매소홀로 488번길 6-32 태승빌딩 5층 | 사업자등록번호: 202-1081-900	
    	</p>
    	<p>
			<span class="font_red"> 전화 : +032-876-3332 </span> 
			| 이메일 : i_innew0731@naver.com
    	</p>
    	<p class="copyright">Copyright ⓒ 
    	<span class="font_blk">BMIT.</span> All rights reserved.
    	</p>
    </footer>

    <!-- Bootstrap core JavaScript-->
   <!--  <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
  <!--    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>-->

    <!-- Custom scripts for all pages-->
   <!--   <script src="js/sb-admin-2.min.js"></script> --> 
	
</body>
</html>